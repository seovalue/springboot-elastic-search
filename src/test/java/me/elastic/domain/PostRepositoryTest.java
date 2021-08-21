package me.elastic.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    void save() {
        PostImpl postImpl = PostImpl.builder()
                .title("title")
                .content("content")
                .build();
        PostImpl save = postRepository.save(postImpl);
        assertThat(save.getId()).isNotNull();
        assertThat(save.getTitle()).isEqualTo("title");
        assertThat(save.getContent()).isEqualTo("content");
    }
}