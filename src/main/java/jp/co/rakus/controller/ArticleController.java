package jp.co.rakus.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.domain.Article;
import jp.co.rakus.domain.Comment;
import jp.co.rakus.form.ArticleForm;
import jp.co.rakus.form.CommentForm;
import jp.co.rakus.repository.ArticleRepository;
import jp.co.rakus.repository.CommentRepository;

/**
 * 掲示板の表示を行うコントローラー.
 * 
 * @author yume.hirata
 *
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private CommentRepository commentRepository;

	@ModelAttribute
	public ArticleForm setUpArticleForm() {
		return new ArticleForm();
	}

	@ModelAttribute
	public CommentForm setUpCommentForm() {
		return new CommentForm();
	}

	/**
	 * 掲示板の初期表示.
	 * 
	 * @return 初期表示画面
	 */
	@RequestMapping("/")
	public String index(Model model) {
		List<Article> articleList = articleRepository.findAll();
		
		for (Article article : articleList) {
			Integer articleId = article.getId();
			List<Comment> commentList = commentRepository.findByArticleId(articleId);
			article.setCommentList(commentList);
		}
		model.addAttribute("articleList", articleList);

		return "article";
	}

	/**
	 * 新規記事の投稿.
	 * 
	 * @param form
	 *            新規記事情報
	 * @return 表示画面
	 */
	@RequestMapping("/insertArticle")
	public String insertArticle(@Validated ArticleForm form,BindingResult result,Model model) {
		if(result.hasErrors()) {
			return index(model);
		}
		
		Article article = new Article();
		BeanUtils.copyProperties(form, article);
		articleRepository.insertArticle(article);

		return "redirect:/article/";
	}

	/**
	 * 新規コメントの投稿.
	 * 
	 * @param form
	 *            新規コメント情報
	 * @return 表示画面
	 */
	@RequestMapping("/insertComment")
	public String insertComment(@Validated CommentForm form,BindingResult result,Model model) {
		if(result.hasErrors()) {
			return index(model);
		}
		
		Comment comment = new Comment();
		BeanUtils.copyProperties(form, comment);
		comment.setArticleId(form.getIntegerArticleId());
		commentRepository.insertComment(comment);

		return "redirect:/article/";
	}

	/**
	 * 記事削除.
	 * 
	 * @param id
	 *            削除キーとなる記事ID
	 * @return 表示画面
	 */
	@RequestMapping("/deleteArticle")
	public String deleteArticle(Integer id) {
		commentRepository.deleteByArticleId(id);
		articleRepository.deleteById(id);

		return "redirect:/article/";
	}

	/**
	 * コメント削除
	 * 
	 * @param id
	 *            削除キーとなるコメントID
	 * @return 表示画面
	 */
	@RequestMapping("/deleteComment")
	public String deleteComment(Integer id) {
		commentRepository.deleteById(id);

		return "redirect:/article/";
	}

}
