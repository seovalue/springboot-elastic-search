package me.elastic.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Objects;

@Document(indexName = "blog")
@Getter
@NoArgsConstructor
public class PostEsImpl implements Post {
    @Id
    private Long id;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String content;

    @Builder
    public PostEsImpl(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public PostEsImpl(PostImpl post) {
        if (Objects.nonNull(post.getId())) {
            this.id = post.getId();
        }
        this.title = post.getTitle();
        this.content = post.getContent();
        ;
    }

    @Override
    public PostImpl toPostImpl() {
        return new PostImpl(this);
    }

    @Override
    public PostEsImpl toPostEsImpl() {
        return this;
    }
}
