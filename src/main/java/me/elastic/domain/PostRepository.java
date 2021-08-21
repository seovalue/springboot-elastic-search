package me.elastic.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostImpl, Long> {
}
