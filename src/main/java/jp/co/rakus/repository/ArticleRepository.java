package jp.co.rakus.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
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
	
	
	/**
	 * RowMapperの定義
	 */
	private final static RowMapper<Article> ARTICLE_ROW_MAPPER = (rs,i) ->{
		Article article = new Article();
		article.setId(rs.getInt("id"));
		article.setName(rs.getString("name"));
		article.setContent(rs.getString("content"));
		
		return article;
	};
	
	/**
	 * articlesテーブルの全件検索.
	 * 
	 * @return	検索してきた記事のリストを返す
	 */
	public List<Article> findAll(){
		String sql = "SELECT id,name,content FROM articles ORDER BY id DESC";
		
		List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);
		return articleList;
	}
	
	/**
	 * 新規記事を追加する.
	 * 
	 * @param article	追加する記事
	 */
	public void insertArticle(Article article) {
		String sql = "INSERT INTO articles(name,content) VALUES(:name,:content)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(article);
		
		template.update(sql, param);
	}
	
	/**
	 * IDで指定された記事を削除
	 * 
	 * @param id	該当記事を指定する記事ID
	 */
	public void deleteArticle(Integer id) {
		String sql = "DELETE FROM articles WHERE id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		
		template.update(sql, param);
	}

}
