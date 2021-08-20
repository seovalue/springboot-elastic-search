package me.elastic.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.elastic.domain.Post;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    private String title;
    private String content;

    public Post post() {
        return Post.builder()
                .title(title)
                .content(content)
                .build();
    }
}
