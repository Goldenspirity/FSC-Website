<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>French Sky Corporation</title>
	
	<meta charset="utf-8"/>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/fonts/cssFonts.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/general/cssGeneral.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/events/internal/cssInternalTournaments.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/header/cssHeader.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/footer/cssFooter.css"/>
	
	<link rel="icon" type='image/png' href="${pageContext.request.contextPath}/inc/images/favicon.png" />
	
	<script src="${pageContext.request.contextPath}/inc/js/jquery.js"></script>
	
</head>
<body>
	<div id="blocPage">
		<div id="header">
			<c:import url="/WEB-INF/jsp/header/header.jsp"></c:import>
		</div> 
		
		<div id="content">
			<c:if test="${sessionScope.user != null && (sessionScope.user.role == 'organizer' || sessionScope.user.role == 'admin' || sessionScope.user.role == 'superadmin')}">
				<a href="<c:url value='tournoisInternes/newTournament'/>" id="newTournament">Nouveau tournoi</a>
			</c:if>
			
			
			<div id="tournamentList">
				<h1>Tournois Internes</h1>
			
				<table>
				<tr><th>N°</th>
					<th>Date</th>
					<th>Titre</th>
					<th>Taille du bracket</th>
					<c:if test="${sessionScope.user != null && (sessionScope.user.role == 'organizer' || sessionScope.user.role == 'admin' || sessionScope.user.role == 'superadmin')}">
						<th class="cellRemove"></th>
					</c:if>
				</tr>
				
				<c:forEach items="${tournamentsList}" var="tournament">
					<tr class="tournamentLine${tournament.id}">
						<td class="cellLink${tournament.id} cellLink cellLeft"><a href="<c:url value='tournoisInternes/bracket?id=${tournament.id}'/>">${tournament.id}</a></td>
						<td class="cellLink${tournament.id} cellLink"><a href="<c:url value='tournoisInternes/bracket?id=${tournament.id}'/>">${tournament.dateTournament}</a></td>
					    <td class="cellLink${tournament.id} cellLink"><a href="<c:url value='tournoisInternes/bracket?id=${tournament.id}'/>">${tournament.title}</a></td>
					    <td class="cellLink${tournament.id} cellLink cellRight"><a href="<c:url value='tournoisInternes/bracket?id=${tournament.id}'/>">${tournament.numberOfTeams}</a></td>
					    <c:if test="${sessionScope.user != null && (sessionScope.user.role == 'organizer' || sessionScope.user.role == 'admin' || sessionScope.user.role == 'superadmin')}">
					    	<td class="cellRemove${tournament.id} cellRemove"><a href="<c:url value='tournoisInternes/removeTournament'><c:param name='id' value='${tournament.id}'/></c:url>">Supprimer</a></td>
					    </c:if>
					</tr>
					
					<script>
						jQuery(document).ready(function(){
							$("#content #tournamentList table tr .cellLink${tournament.id}").mouseenter(function() {
								$("#content #tournamentList table tr .cellLink${tournament.id}").css("background-color", "rgb(40, 40, 40)");
							});
							
							$("#content #tournamentList table tr .cellLink${tournament.id}").mouseleave(function() {
								$("#content #tournamentList table tr .cellLink${tournament.id}").css("background-color", "rgba(50, 50, 50, 0.25)");
							});
							
							$("#content #tournamentList table tr .cellLink${tournament.id}").click(function (){
								$(location).attr('href', 'tournoisInternes/bracket?id=${tournament.id}')
							});
							
							$("#content #tournamentList table tr .cellRemove${tournament.id}").mouseenter(function() {
								$("#content #tournamentList table tr .cellRemove${tournament.id} a").css("color", "#a5a5a5");
							});
							
							$("#content #tournamentList table tr .cellRemove${tournament.id}").mouseleave(function() {
								$("#content #tournamentList table tr .cellRemove${tournament.id} a").css("color", "white");
							});
							
							$("#content #tournamentList table tr .cellLink${tournament.id}").click(function (){
								$(location).attr('href', 'tournoisInternes/bracket?id=${tournament.id}')
							});
						});
					</script>
		
				</c:forEach>
				</table>
			</div>
		</div>
		
		<div id="footer"> <c:import url="/WEB-INF/jsp/footer/footer.jsp"></c:import> </div> 
	</div>
	
	
	<script src="${pageContext.request.contextPath}/inc/js/events/internalTournaments.js"></script>

</body>
</html>