package me.elastic.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Objects;

@Document(indexName = "blog") // index를 먼저 생성해서 매핑해놓은 다음에 Document와 매핑이 되어야한다.
@Getter
@NoArgsConstructor
// indexing된 포스트가 들어가야함.
// id, title, content
// RDB에서는 여러가지 인자들을 필요로하므로 ES용 도메인(검색에만 필요한 엔티티)이랑 분리해야하는 것이 맞음.
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
