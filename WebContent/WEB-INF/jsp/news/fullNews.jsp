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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/news/cssFullNews.css"/>
	
	<link rel="icon" type='image/png' href="${pageContext.request.contextPath}/inc/images/favicon.png" />
</head>
<body>
	<div id="blocPage">
		<div id="header"> <c:import url="/WEB-INF/jsp/header/header.jsp"></c:import> </div>
		
		<div id="news">
		
			<h1>${news.title}</h1>
		
			<img src="${news.image}" />
			
			<span class="date" id="date">Publié le ${news.date}</span>
			<p id="newsContent"><ts:nl2br>${news.content}</ts:nl2br></p>
			
			<c:if test="${news.lastEdit != null}">
				<p id="lastEdit">Modifé le ${news.lastEdit}.</p>
			</c:if>

			<div id="signature">
				<p>La French Sky Corporation</p>
				<img src="${pageContext.request.contextPath}/inc/images/fsc_logo.png" title="French Skyline Corporation"/>
			</div>
			
			<c:if test="${sessionScope.user != null && (sessionScope.user.role == 'admin' || sessionScope.user.role == 'superadmin')}">
				<span id="edit"><a href="config/editNews?id=${news.id}">Edit</a></span>
			</c:if>
			
		</div>
		
		<div id="footer"> <c:import url="/WEB-INF/jsp/footer/footer.jsp"></c:import> </div>
	</div>
</body>
</html>