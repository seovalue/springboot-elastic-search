package me.elastic.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.elastic.domain.PostImpl;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    private String title;
    private String content;

    public PostImpl post() {
        return PostImpl.builder()
                .title(title)
                .content(content)
                .build();
    }
}
