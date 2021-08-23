package me.elastic.application;

import me.elastic.domain.Post;
import me.elastic.domain.PostImpl;
import me.elastic.domain.PostEsImpl;
import me.elastic.domain.PostEsRepository;
import me.elastic.domain.PostRepository;
import me.elastic.exception.PostNotFoundException;
import me.elastic.presentation.dto.PostRequest;
import me.elastic.presentation.dto.PostResponse;
import me.elastic.presentation.dto.PostTitleRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final PostEsRepository postEsRepository;

    public PostService(PostRepository postRepository, PostEsRepository postEsRepository) {
        this.postRepository = postRepository;
        this.postEsRepository = postEsRepository;
    }

    @Transactional
    public PostResponse save(PostRequest postRequest) {
        Post post = postRequest.post();
        PostImpl savedPost = postRepository.save(post.toPostImpl());
        postEsRepository.save(post.toPostEsImpl());
        return PostResponse.of(savedPost);
    }

    public PostResponse findById(Long id) {
        PostEsImpl postEsImpl = postEsRepository.findById(id).orElseThrow(PostNotFoundException::new);
        return PostResponse.of(postEsImpl);
    }

    public List<PostResponse> findAllByTitle(String title) {
        List<PostEsImpl> posts = postEsRepository.findByTitle(title);
        return posts.stream()
                .map(PostResponse::of)
                .collect(Collectors.toList());
    }
}
