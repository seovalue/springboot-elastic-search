package me.elastic.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "POST")
public class PostImpl implements Post{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @Builder
    public PostImpl(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public PostImpl(PostEsImpl postEs) {
        if (Objects.nonNull(postEs.getId())) {
            this.id = postEs.getId();
        }
        this.title = postEs.getTitle();
        this.content = postEs.getContent();;
    }

    @Override
    public PostImpl toPostImpl() {
        return this;
    }

    @Override
    public PostEsImpl toPostEsImpl() {
        return new PostEsImpl(this);
    }
}
