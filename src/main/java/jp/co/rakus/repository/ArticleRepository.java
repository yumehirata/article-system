package jp.co.rakus.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.rakus.domain.Article;

/**
 * articlesテーブルを操作するリポジトリ.
 * 
 * @author yume.hirata
 *
 */
@Repository
public class ArticleRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	//あとでDBに合わせて修正するかも
	private final static RowMapper<Article> ARTICLE_ROW_MAPPER = (rs,i) ->{
		Article article = new Article();
		article.setId(rs.getInt("id"));
		article.setName(rs.getString("name"));
		article.setContent(rs.getString("content"));
		
		return article;
	};

}
