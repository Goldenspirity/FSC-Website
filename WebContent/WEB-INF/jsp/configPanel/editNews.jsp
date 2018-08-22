<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>French Sky Corporation</title>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/fonts/cssFonts.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/general/cssGeneral.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/header/cssHeader.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/footer/cssFooter.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/configPanel/cssConfigPanel.css"/>
	
	<link rel="icon" type='image/png' href="${pageContext.request.contextPath}/inc/images/favicon.png" />
</head>
<body>
	<div id="blocPage">

		<div id="header"> <c:import url="/WEB-INF/jsp/header/header.jsp"></c:import> </div>
		<div id="content">
			<div id="newsManagement" class="contentDiv">
				<h2>Editer l'actualité n°${news.id}</h2>
				<form method="post" action="${pageContext.request.contextPath}/config/editNews?id=${news.id}">
					<label for="title">Titre (~ 50 caractères)</label><input type="text" name="title" id="title" value="${news.title}"/>
					<br/>
					<label for="imageUrl">Url de l'image</label><input type="text" name="imageUrl" id="imageUrl" value="${news.image}"/>
					<br/>
					<label for="summary">Résumé (~ 300 caractères)</label><textarea name="summary" id="summary">${news.summary}</textarea>
					<br />
					<label for="content">Contenu</label><textarea name="content" id="newsContent">${news.content}</textarea>
					<br />
					<input type="submit" value="Envoyer" />
				</form>
			</div>
		</div>
	
		<div id="footer"> <c:import url="/WEB-INF/jsp/footer/footer.jsp"></c:import> </div>
		
	</div>
</body>
</html>