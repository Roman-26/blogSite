package com.roman.site.repository;

import com.roman.site.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article,Long> {

}
