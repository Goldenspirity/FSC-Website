<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>French Sky Corporation</title>
	<meta charset="utf-8"/>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/fonts/cssFonts.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/general/cssGeneral.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/header/cssHeader.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/footer/cssFooter.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/news/cssAllNews.css"/>
	
	<link rel="icon" type='image/png' href="${pageContext.request.contextPath}/inc/images/favicon.png" />
</head>
<body>
	<div id="blocPage">
		<div id="header"> <c:import url="/WEB-INF/jsp/header/header.jsp"></c:import> </div>
		
		<div id="newsList">

		
			<c:forEach items="${newsList}" var="news">
				<div class="news" id="news${news.id}">
					<h2>${news.title}</h2>
					<h3>Le ${news.date}</h3>
					
					<div class="presentation">
						<img class="newsBackground" src="${news.image}" />	
						<div class="content"> 
							<div class="subtitle">
								<p>${news.summary}</p>
							</div>
							<div class="readMore"><a href="${pageContext.request.contextPath}/news?id=${news.id}" class="button"><span>Lire la suite</span></a></div>
						</div>
					</div>
					
					<c:if test="${news.lastEdit != null}"><p class="lastEdit">Modifié le ${news.lastEdit}.</p></c:if>
				</div>
				
				<div class="break"></div>
			</c:forEach>
		
		</div>
		
		<div id="footer"> <c:import url="/WEB-INF/jsp/footer/footer.jsp"></c:import> </div>
	</div>
</body>
</html>