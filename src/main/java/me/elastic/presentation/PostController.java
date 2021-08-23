package me.elastic.presentation;

import lombok.Getter;
import me.elastic.application.PostService;
import me.elastic.exception.ErrorResponse;
import me.elastic.exception.NotFoundException;
import me.elastic.presentation.dto.PostRequest;
import me.elastic.presentation.dto.PostResponse;
import me.elastic.presentation.dto.PostTitleRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts")
    public ResponseEntity<PostResponse> create(@RequestBody PostRequest postRequest) {
        PostResponse response = postService.save(postRequest);
        return ResponseEntity.created(URI.create("/posts" + response.getId())).body(response);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostResponse> findById(@PathVariable Long id) {
        PostResponse response = postService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostResponse>> findAllByTitle(@RequestParam String title) {
        List<PostResponse> responses = postService.findAllByTitle(title);
        return ResponseEntity.ok(responses);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.of(e.getMessage()));
    }
}
