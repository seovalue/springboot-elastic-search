package me.elastic.presentation;

import me.elastic.application.PostService;
import me.elastic.presentation.dto.PostRequest;
import me.elastic.presentation.dto.PostResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

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
}
