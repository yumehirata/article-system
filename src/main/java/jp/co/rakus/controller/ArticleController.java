package jp.co.rakus.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.domain.Article;
import jp.co.rakus.repository.ArticleRepository;

@Controller
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private ServletContext application;
	
	@RequestMapping("/")
	public String index() {
		List<Article> articleList = articleRepository.findAll();
		application.setAttribute("articleList", articleList);
		
		return "article";
	}
}
