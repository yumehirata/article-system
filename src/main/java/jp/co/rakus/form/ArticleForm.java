package jp.co.rakus.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 記事に関するリクエストパラメータを受け取るフォーム.
 * 
 * @author yume.hirata
 *
 */
public class ArticleForm {
	/** 名前 */
	@NotBlank(message="名前を入力してください")
	@Size(max=50, message="名前は50文字以内で入力してください")
	private String name;
	/** コンテント */
	@NotBlank(message="内容を入力してください")
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
