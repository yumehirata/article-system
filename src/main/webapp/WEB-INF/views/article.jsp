<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>掲示板アプリケーション</h2>


<c:forEach var="article" items="${articleList}">
	<hr>
		投稿ID：<c:out value="${article.id}"/><br>
		投稿者名：<c:out value="${article.name}"/><br>
		投稿内容：<c:out value="${article.content}"/><br>
		<input type="submit" value="記事削除">
	<br>
<!--		<c:forEach var="comment" items="${commentList}">
				コメントID：<c:out value="${comment.id}"/>
				コメント者名<c:out value="${comment.name}"/>
				コメント内容<c:out value="${comment.content}"/>
		</c:forEach>
-->
</c:forEach>


</body>
</html>