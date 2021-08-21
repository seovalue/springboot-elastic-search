package me.elastic.application;

import me.elastic.domain.Post;
import me.elastic.presentation.dto.PostRequest;
import me.elastic.presentation.dto.PostResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Test
    void save() {
        PostRequest postRequest = new PostRequest("제목", "내용");
        PostResponse response = postService.save(postRequest);
        assertThat(response.getId()).isNotNull();
    }
}