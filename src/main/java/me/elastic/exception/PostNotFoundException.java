package me.elastic.exception;

import org.aspectj.weaver.ast.Not;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PostNotFoundException extends NotFoundException {
    public PostNotFoundException() {
        super("게시글이 존재하지 않습니다.");
    }

    public PostNotFoundException(String message) {
        super(message);
    }
}
