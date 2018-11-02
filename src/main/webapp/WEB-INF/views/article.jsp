<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>掲示板アプリケーション</h2>

<form:form modelAttribute="articleForm" action="${pageContext.request.contextPath}/article/insertArticle">
	<form:errors path="name" cssStyle="color:red" element="div"/>
	投稿者名<form:input path="name"/><br>
	<form:errors path="content" cssStyle="color:red" element="div"/>
	投稿内容<form:textarea path="content"/><br>
	<input type="submit" value="記事投稿">
</form:form>

<c:forEach var="article" items="${articleList}">
	<hr>
			投稿ID：<c:out value="${article.id}"/><br>
			投稿者名：<c:out value="${article.name}"/><br>
			投稿内容：<c:out value="${article.content}"/><br>
			<form action="${pageContext.request.contextPath}/article/deleteArticle" method="post">
				<input type="hidden" name="id" value="${article.id}">
				<input type="submit" value="記事削除">
			</form>
	<br>
		<c:forEach var="comment" items="${article.commentList}">
			コメントID：<c:out value="${comment.id}"/><br>
			コメント者名：<c:out value="${comment.name}"/><br>
			コメント内容：<c:out value="${comment.content}"/><br>
			<form action="${pageContext.request.contextPath}/article/deleteComment" method="post">
				<input type="hidden" name="id" value="${comment.id}">
				<input type="submit" value="コメント削除">
			</form>
		<br>
		</c:forEach>
		
	<form:form modelAttribute="commentForm" action="${pageContext.request.contextPath}/article/insertComment">
		<c:if test="commentForm.articleId == article.id">
		<form:errors path="name" cssStyle="color:red" element="div"/>
		</c:if>
		名前：<form:input path="name"/><br>
				<c:if test="commentForm.articleId == article.id">
		<form:errors path="content" cssStyle="color:red" element="div"/>
				</c:if>
		コメント：<form:textarea path="content"/><br>
		<form:hidden path="articleId" value="${article.id}"/>
		<input type="submit" value="コメント投稿">
	</form:form>
</c:forEach>
</body>
</html>