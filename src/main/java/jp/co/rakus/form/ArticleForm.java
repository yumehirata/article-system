package jp.co.rakus.form;

/**
 * 記事に関するリクエストパラメータを受け取るフォーム.
 * 
 * @author yume.hirata
 *
 */
public class ArticleForm {
	/** 名前 */
	private String name;
	/** コンテント */
	private String content;

	@Override
	public String toString() {
		return "ArticleForm [name=" + name + ", content=" + content + "]";
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

}
