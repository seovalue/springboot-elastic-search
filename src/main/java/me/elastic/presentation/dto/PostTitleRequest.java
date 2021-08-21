package me.elastic.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostTitleRequest {
    private String title;

    public String title() {
        return title;
    }
}
