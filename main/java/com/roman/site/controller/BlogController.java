package com.roman.site.controller;


import com.roman.site.Service.ArticleService;
import com.roman.site.exeptions.ArticleNotFoundExeption;
import com.roman.site.model.Article;
import com.roman.site.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/blog")
    public String blog(Model model){
        List<Article>list = articleService.findAll();
        model.addAttribute("posts",list);
        return "blog-main";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model){
        return "blog-add";
    }

    @PostMapping("blog/add")
    public String postArticleAdd(@RequestParam String title,
                                 @RequestParam String anons,
                                 @RequestParam String full_text){
        Article article = new Article(title,anons,full_text);
        articleRepository.save(article);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public String blogIdDetails(@PathVariable(name = "id")Long id,Model model){
        if (!articleRepository.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Article>articleList = articleService.findOneId(id);
        ArrayList<Article>list = new ArrayList<>();
        articleList.ifPresent(list::add);
        model.addAttribute("post",list);
        return "blog-details";
    }

   @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(name = "id")Long id,Model model){
       if (!articleRepository.existsById(id)){
           return "redirect:/blog";
       }
       Optional<Article>articleList = articleService.findOneId(id);
       ArrayList<Article>list = new ArrayList<>();
       articleList.ifPresent(list::add);
       model.addAttribute("post",list);
       return "blog-edit";
   }

   @PostMapping("blog/{id}/edit")
    public String blogUpdate(@PathVariable(name = "id")Long id,
                             @RequestParam String title,
                             @RequestParam String anons,
                             @RequestParam String full_text){
       Article article = articleRepository.findById(id).orElseThrow(ArticleNotFoundExeption::new);
       article.setTitle(title);
       article.setAnons(anons);
       article.setFull_text(full_text);
       articleRepository.save(article);
       return "redirect:/blog";
   }

   @PostMapping("blog/{id}/remove")
    public String blogDelete(@PathVariable(name = "id")Long id){
       Article article = articleRepository.findById(id).orElseThrow(ArticleNotFoundExeption::new);
       articleRepository.delete(article);
       return "redirect:/blog";
   }
}
