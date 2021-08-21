# 8. Elasticsearch Repositories
> 원문: [Spring Data Elasticsearch](https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#elasticsearch.repositories)

해당 Document에서 예시로 들 Entity는 다음과 같다.
```java
@Document(indexName="books")
class Book {
    @Id
    private String id;

    @Field(type = FieldType.text)
    private String name;

    @Field(type = FieldType.text)
    private String summary;

    @Field(type = FieldType.Integer)
    private Integer price;

	// getter ...
}
```

## 8.1 Automatic creation of indices with the corresponding mapping
`@Document` 어노테이션에는 `indexName`을 달 수 있다. `indexName`은 Elasticsearch에 저장될 인덱스 명이며, 해당 어노테이션에 의해 이 인덱스가
Elasticsearch에 정의되어있는지 확인한다. 만약 정의되어있지 않다면 인덱스를 자동으로 생성한다. 

`@Document` 어노테이션에 대한 자세한 설명은 [여기](https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#elasticsearch.mapping)에서 확인할 수 있다.

인덱스를 좀 더 자세히 설정하기 위해서는 `@Setting` 어노테이션을 사용하면 가능하다.

## 8.2 Query methods

Elasticsearch 모듈은 문자열 쿼리, 기본 검색 쿼리, 기준 기반 쿼리 또는 메서드 명에서 파생된 모든 기본 쿼리 기능을 지원한다. 만약 메서드 명에서 쿼리를 만들기가 어려운 경우에는 elasticsearch 모듈에 포함된 `@Query` 어노테이션을 활용하여 쿼리를 커스텀할 수 있다.

쿼리는 JPA와는 달리 elasticsearch 쿼리 생성 메커니즘에 따라 동작한다. 예를 들어, `findByNameAndPrice`의 경우 다음과 같은 쿼리로 변경된다.

```
{
    "query": {
        "bool" : {
            "must" : [
                { "query_string" : { "query" : "?", "fields" : [ "name" ] } },
                { "query_string" : { "query" : "?", "fields" : [ "price" ] } }
            ]
        }
    }
}
```

elasticsearch의 또다른 검색 쿼리 API는 [Elasticsearch 검색 API 정리](https://esbook.kimjmin.net/04-data/4.4-_search) 에서 확인할 수 있다.

메서드 명을 통해 지원하는 쿼리 키워드에는 여러가지가 있는데, 일부 예시를 들자면 다음과 같다.


|Keyword|Sample|Elasticsearch Query String|
|-------|------|---------------------------|
|And |findByNameAndPrice |{ "query" : { "bool" : { "must" : [ { "query_string" : { "query" : "?", "fields" : [ "name" ] } }, { "query_string" : { "query" : "?", "fields" : [ "price" ] } } ] } }}|
|Or |findByNameOrPrice |{ "query" : { "bool" : { "should" : [ { "query_string" : { "query" : "?", "fields" : [ "name" ] } }, { "query_string" : { "query" : "?", "fields" : [ "price" ] } } ] } }}|  

자세한 내용은 [여기](https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#elasticsearch.query-methods.criterions)의 `Table 2`에서 확인할 수 있다.


### Method Return Types
여러 요소를 반환해야할 때에는 다음과 같은 반환 유형을 갖도록 정의할 수 있다.

* List<T>

* Stream<T>

* SearchHits<T>

* List<SearchHit<T>>

* Stream<SearchHit<T>>
 
* SearchPage<T>

### Using @Query
```java
interface BookRepository extends ElasticsearchRepository<Book, String> {
    @Query("{\"match\": {\"name\": {\"query\": \"?0\"}}}")
    Page<Book> findByName(String name,Pageable pageable);
}
```

여기서 `?0`, `?1`은 파라미터를 받을 때 사용한다. 확실한 레퍼런스는 아직 없지만 `?0`로 하였을 때 첫번째 파라미터를 바인딩하는 것을 확인했다.


## 8.3 Reactive Elasticsearch Repositories

Reactive Elasticsearch Repository는 Spring Data Elasticsearch의 반응형 저장소이다.
이를 사용하기 위해서는 다음과 같은 인터페이스를 이용할 수 있다.

* ReactiveRepository

* ReactiveCrudRepository

* ReactiveSortingRepository

사용 방법은 위 리포지토리을 구현하기만 하면 된다.

사용할 수 있는 메서드는 보통 JPA Repository와 비슷한다. 리턴 타입이 새롭다.

```java
interface ReactivePersonRepository extends ReactiveSortingRepository<Person, String> {

  Flux<Person> findByFirstname(String firstname);                                   

  Flux<Person> findByFirstname(Publisher<String> firstname);                        

  Flux<Person> findByFirstnameOrderByLastname(String firstname);                    

  Flux<Person> findByFirstname(String firstname, Sort sort);                        

  Flux<Person> findByFirstname(String firstname, Pageable page);                    

  Mono<Person> findByFirstnameAndLastname(String firstname, String lastname);       

  Mono<Person> findFirstByLastname(String lastname);                                

  @Query("{ \"bool\" : { \"must\" : { \"term\" : { \"lastname\" : \"?0\" } } } }")
  Flux<Person> findByLastname(String lastname);                                     

  Mono<Long> countByFirstname(String firstname)                                     

  Mono<Boolean> existsByFirstname(String firstname)                                 

  Mono<Long> deleteByFirstname(String firstname)                                    
}
```

Configuration 파일은 다음과 같다.

```java
@Configuration
@EnableReactiveElasticsearchRepositories
public class Config extends AbstractReactiveElasticsearchConfiguration {

  @Override
  public ReactiveElasticsearchClient reactiveElasticsearchClient() {
    return ReactiveRestClients.create(ClientConfiguration.localhost());
  }
}
```

Reactive Repository와 관련된 내용은 아직 어려워서 자세한 내용은 [이 문서](https://spring.io/blog/2016/11/28/going-reactive-with-spring-data)를 참고하면 좋을 것 같다.


## 8.4 Annotations for repository methods
### @Highlight

`@Highlight` 어노테이션은 반환된 엔티티의 강조 표시 필드가 포함되어야하는지를 나타낸다. 예를 들어
책의 이름이나 요약에서 일부 텍스트를 검색하고 찾은 데이터를 강조 표시할 때 사용할 수 있다.

```java
interface BookRepository extends Repository<Book, String> {

    @Highlight(fields = {
        @HighlightField(name = "name"),
        @HighlightField(name = "summary")
    })
    List<SearchHit<Book>> findByNameOrSummary(String text, String summary);
}
```

위와 같이 강조 표시할 여러 필드를 설정할 수 있으며 @Highlight 및 @HighlightField 주석 모두 @HighlightParameters 주석으로 추가로 사용자 지정할 수 있다. 

검색 결과에서 강조된 데이터는 SearchHit 클래스에서 검색할 수 있다.

## 8.5 Annotation based configuration

이래나 저래나 Elasticsearch Repository를 이용하려면 다음과 같은 configuration 파일을 작성해주어야한다.

```java
@Configuration
@EnableElasticsearchRepositories(                             
  basePackages = "org.springframework.data.elasticsearch.repositories"
  )
static class Config {

  @Bean
  public ElasticsearchOperations elasticsearchTemplate() {    
      // ...
  }
}
```

위에서 `@EnableElasticsearchRepositories` 어노테이션은 basePackages 작성 없이 어노테이션만 사용하는 것이 오히려 더 잘 동작하는 것 같다. 왜인지는 아직 모르겠다..


