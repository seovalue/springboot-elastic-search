package me.elastic.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PostEsRepository extends ElasticsearchRepository<PostEsImpl, Long> {
    @Query("{\"match\": {\"title\": {\"query\": \"?0\"}}}")
    Page<PostEsImpl> findByTitle(String title, Pageable pageable);
}
