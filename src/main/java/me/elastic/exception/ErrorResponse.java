package me.elastic.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String message;

    public static ErrorResponse of(String message) {
        return new ErrorResponse(message);
    }
}
