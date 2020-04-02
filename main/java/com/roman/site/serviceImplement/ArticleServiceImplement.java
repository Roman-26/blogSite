package com.roman.site.serviceImplement;

import com.roman.site.Service.ArticleService;
import com.roman.site.model.Article;
import com.roman.site.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImplement implements ArticleService{

    @Autowired
    public ArticleRepository articleRepository;

    @Override
    public Optional<Article> findOneId(Long articleId) {
        return articleRepository.findById(articleId);
    }

    @Override
    public Optional<Article> findOneName(String articleName) {
        return Optional.empty();
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }


}
