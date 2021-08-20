package me.elastic.application;

import me.elastic.domain.Post;
import me.elastic.domain.PostEsRepository;
import me.elastic.exception.PostNotFoundException;
import me.elastic.presentation.dto.PostRequest;
import me.elastic.presentation.dto.PostResponse;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private final PostEsRepository postEsRepository;

    public PostService(PostEsRepository postEsRepository) {
        this.postEsRepository = postEsRepository;
    }

    public PostResponse save(PostRequest postRequest) {
        Post post = postEsRepository.save(postRequest.post());
        return PostResponse.of(post);
    }

    public PostResponse findById(Long id) {
        Post post = postEsRepository.findById(id).orElseThrow(PostNotFoundException::new);
        return PostResponse.of(post);
    }
}
