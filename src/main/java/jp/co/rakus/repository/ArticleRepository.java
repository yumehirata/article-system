package jp.co.rakus.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.domain.Article;
import jp.co.rakus.domain.Comment;

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
	 * ResultSetExtractorの定義
	 */
	private final static ResultSetExtractor<List<Article>> ARTICLE_EXTRACTOR = (rs) -> {

		List<Article> articleList = new ArrayList<>();
		Article article = null;
		int preId = 0;

		while (rs.next()) {
			Integer articleId = rs.getInt("aId");
			if (preId != articleId) {
				article = new Article();
				article.setId(articleId);
				article.setName(rs.getString("aName"));
				article.setContent(rs.getString("aContent"));

				article.setCommentList(new ArrayList<>());
				articleList.add(article);
			}
			if (rs.getInt("cId") > 0) {
				Comment comment = new Comment();
				comment.setId(rs.getInt("cId"));
				comment.setName(rs.getString("cName"));
				comment.setContent(rs.getString("cContent"));
				article.getCommentList().add(comment);
			}

			preId = articleId;
		}
		return articleList;
	};

	/**
	 * RowMapperの定義
	 */
	private final static RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, i) -> {
		Article article = new Article();
		article.setId(rs.getInt("id"));
		article.setName(rs.getString("name"));
		article.setContent(rs.getString("content"));

		return article;
	};

	/**
	 * articlesテーブルとcommentsテーブルの全件検索.
	 * 
	 * @return 検索してきた記事のリストを返す
	 */
	public List<Article> findAllOnce() {
		String sql = "SELECT a.id AS aId, a.name AS aName, a.content AS aContent, c.id AS cId,"
				+ "c.name AS cName, c.Content AS cContent, c.article_id AS cArticleId FROM articles a LEFT OUTER "
				+ "JOIN comments c ON a.id=c.article_id ORDER BY a.id DESC;";

		List<Article> articleList = template.query(sql, ARTICLE_EXTRACTOR);
		return articleList;
	}

	/**
	 * articlesテーブルの全件検索.
	 * 
	 * @return 検索してきた記事のリストを返す
	 */
	public List<Article> findAll() {
		String sql = "SELECT id,name,content FROM articles ORDER BY id DESC";

		List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);
		return articleList;
	}

	/**
	 * 新規記事を追加する.
	 * 
	 * @param article
	 *            追加する記事
	 */
	public void insertArticle(Article article) {
		String sql = "INSERT INTO articles(name,content) VALUES(:name,:content)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(article);

		template.update(sql, param);
	}

	/**
	 * IDで指定された記事を削除する.
	 * 
	 * @param id
	 *            該当記事を指定する記事ID
	 */
	public void deleteById(Integer id) {
		String sql = "DELETE FROM articles WHERE id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

		template.update(sql, param);
	}

	/**
	 * IDで指定された記事をコメント毎削除する.
	 * 
	 * @param id
	 *            該当記事とコメントを指定する記事ID
	 */
	public void deleteOnceById(Integer id) {
		String sql = "WITH deleted AS (DELETE FROM articles WHERE id = :id RETURNING id) DELETE FROM comments WHERE article_id IN (SELECT article_id FROM deleted)";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

		template.update(sql, param);

	}

}
