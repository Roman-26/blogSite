package com.roman.site.Service;

import com.roman.site.model.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
    Optional<Article>findOneId(Long articleId);
    Optional<Article>findOneName(String articleName);
    List<Article>findAll();
}
