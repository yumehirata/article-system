package jp.co.rakus.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.domain.Comment;

/**
 * commentsテーブルを操作するリポジトリ.
 * 
 * @author yume.hirata
 *
 */
@Repository
public class CommentRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	//DBに入っている値のまま。取ってきたときに変更する
	private final static RowMapper<Comment> COMMENT_ROW_MAPPER = (rs,i) ->{
		Comment comment = new Comment();
		comment.setId(rs.getInt("id"));
		comment.setName(rs.getString("name"));
		comment.setContent(rs.getString("content"));
		comment.setArticleId(rs.getInt("article_id"));
		return comment;
	};
	
	/**
	 * 投稿IDによる指定検索.
	 * 
	 * @param articleId	検索キーとなる記事ID
	 * @return	検索してきたコメントリストを返す
	 */
	public List<Comment> findByArticleId(int articleId) {
		String sql ="SELECT id, name, content, article_id FROM comments WHERE article_id=:article_id ORDER BY id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("article_id", articleId);
		
		List<Comment> commentList = template.query(sql, param,COMMENT_ROW_MAPPER);
		
		return commentList;
	}

}
