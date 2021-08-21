## 엘라스틱 서치를 스프링과 연동하는 방법

### Spring-data-elasticsearch 모듈을 이용해 elasticsearchRepository 인터페이스를 이용하기
* 장점
  JPA와 사용법이 유사하다.
* 단점
  엔티티에 @Document(인덱스, 타입)을 명시해주어야하기 때문에 인덱스 이름을 변경하며 엔티티를 사용할 수 없다.


### Spring-data-elasticsearch 모듈의 elasticsearchTemplate 객체를 이용하기
* 장점
  application.yml을 이용해 elasticsearchTemplate을 빈으로 생성할 필요 없이 자동으로 생성 가능하다.
* 단점
  엔티티에 @Document(인덱스, 타입)을 명시해주어야하기 때문에 인덱스 이름을 변경하며 엔티티를 사용할 수 없다.

### ES에서 지원하는 Low level Rest Client 모듈을 다운받아 RestClient 클래스 사용하기
* ES를 아예 REST API로 통신하는 방법 (curl 통신과 유사)
* 통신을 위해 헤더 바디를 작성할 때 사용자가 Json으로 직접 작성하는 방식이기에 ES 모듈과의 의존성이 거의 없다.


### ES에서 지원하는 High level Rest Client 모듈을 다운받아 HighLevelRestClient 클래스를 사용하기
* ES를 아예 REST API로 통신하는 방법 (curl 통신과 유사)
* 통신을 위해 헤더, 바디를 작성할 때 Request Builder를 이용해 데이터를 전달한다.
* ES 모듈에 의존성이 높다.


[Docker 기반의 Elasticsearch 설치 및 실행 - Aloha](https://jinhokwon.github.io/devops/elasticsearch/elasticsearch-docker/)