package me.elastic.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.elastic.domain.Post;
import me.elastic.domain.PostImpl;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponse {
    private Long id;
    private String title;
    private String content;

    public static PostResponse of(Post postI) {
        PostImpl post = postI.toPostImpl();
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }
}
