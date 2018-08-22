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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/shop/cssShop.css"/>
	
	<link rel="icon" type='image/png' href="${pageContext.request.contextPath}/inc/images/favicon.png" />
</head>
<body>
	<div id="blocPage">
		<div id="header"> <c:import url="/WEB-INF/jsp/header/header.jsp"></c:import> </div>
		
		<div id="content">
			<h1>La boutique de la French Sky Corporation !</h1>
			
			<div class="item">
				<div class="picture">
					<img src="${pageContext.request.contextPath}/inc/images/test_image.png" />
				</div>
				
				<div class="description">
					<p>Ceci est une description. Ceci est une description. Ceci est une description. Ceci est une description.</p>
				</div>
				
				<div class="QtyAndPrice">
					<select name="quantity">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
					</select>
					<span>xx,xx €</span>
				</div>
			</div>
		
		</div>
		
		<div id="footer"> <c:import url="/WEB-INF/jsp/footer/footer.jsp"></c:import> </div>
	</div>
</body>
</html>