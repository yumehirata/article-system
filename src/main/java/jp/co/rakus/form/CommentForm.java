package jp.co.rakus.form;

/**
 * コメントに関するリクエストパラメータを受け取るフォーム.
 * 
 * @author yume.hirata
 *
 */
public class CommentForm {
	/** 記事ID */
	private String articleId;
	/** 名前 */
	private String name;
	/** コンテント */
	private String content;

	public Integer getIntegerArticleId() {
		return Integer.parseInt(articleId);
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "CommentForm [articleId=" + articleId + ", name=" + name + ", content=" + content + "]";
	}

}
