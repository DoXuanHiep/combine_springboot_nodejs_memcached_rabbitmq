package com.group.news.service.memcached;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemcachedPost {
    private Integer id;

    private String content;

    private Integer amountLike;

    private MemcachedTitle title;

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Integer getAmountLike() {
        return amountLike;
    }

    public MemcachedTitle getTitle() {
        return title;
    }
}
