<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<footer>
	<div></div>
	
	<div id="copyright">
	<c:if test="${sessionScope.user != null && (sessionScope.user.role == 'admin' || sessionScope.user.role == 'superadmin')}">
		<a href="${pageContext.request.contextPath}/config">Panneau de configuration</a> <br/>
	</c:if>
		Copyright© 2018 - French Sky Corporation - Tous droits réservés
	</div>
</footer>