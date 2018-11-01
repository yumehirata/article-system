package jp.co.rakus.domain;

/**
 * 掲示板を設定するドメイン.
 * 
 * @author yume.hirata
 *
 */
public class Article {
	/** id */
	private Integer id;
	/** ユーザーの名前 */
	private String name;
	/** コメント */
	private String comment;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCommentList() {
		return commentList;
	}

	public void setCommentList(String commentList) {
		this.commentList = commentList;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", name=" + name + ", comment=" + comment + ", commentList=" + commentList + "]";
	}

	/** コメントのリスト */
	private String commentList;

}
