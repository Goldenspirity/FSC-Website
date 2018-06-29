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
	
	<link rel="icon" type='image/png' href="${pageContext.request.contextPath}/inc/images/favicon.png" />
</head>
<body>
	<div id="blocPage">
		<div id="header"> <c:import url="/WEB-INF/jsp/header/header.jsp"></c:import> </div>
		
		<div id="newsList">
			<table>
			<c:forEach items="${newsList}" var="news">
				<tr><td><a href="<c:url value='/news?id=${news.id}'/>">${news.title}</a></td>
				    <td>${news.date}</td>
				    <c:if test="${user.role != null && user.role == 'admin'}">
				    	<!-- <td><a href="<c:url value='news/removeNews'><c:param name='id' value='${news.id}'/></c:url>">Supprimer</a></td></tr> -->
				    	<td><form method="post" action="<c:url value='news/removeNews'></c:url>">
				    		<input type="hidden" name="newsId" value="${news.id}" />
				    		<input type="submit" value="Supprimer" />
				    	</form></td>
				    </c:if>
			</c:forEach>
			</table>
		
		</div>
		
		<div id="footer"> <c:import url="/WEB-INF/jsp/footer/footer.jsp"></c:import> </div>
	</div>
</body>
</html>