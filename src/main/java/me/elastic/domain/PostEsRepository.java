package me.elastic.domain;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface PostEsRepository extends ElasticsearchRepository<PostEsImpl, Long> {
    @Query("{\"query_string\": {\"fields\": [\"title\"], \"query\": \"*?0*\"}}")
//    @Query("{\"match\": {\"title\": \"*?0\"}}")
    List<PostEsImpl> findByTitle(String title);

    // batch
    // RDB와 elasticsearch랑 동기화되어있는지 확인 후 sync
    // elasticsearch document ID 우선은 식별 가능한 해시값
}

/**
 {
 "title": "내가 누구게",
 "content": "내용"
 }

 {
 "title": "내다",
 "content": "내용"
 }

 {
 "title": "나의 내복",
 "content": "내용"
 }

 {
 "title": "안녕하세요 저는 조앤입니다.",
 "content": "내용"
 }
 */