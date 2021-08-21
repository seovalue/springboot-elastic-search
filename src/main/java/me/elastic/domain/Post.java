package me.elastic.domain;

import javax.persistence.Entity;

public interface Post {
    PostImpl toPostImpl();
    PostEsImpl toPostEsImpl();
}
