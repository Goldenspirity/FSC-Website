<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.sdzee.beans.Tournament" %>
<!DOCTYPE html>
<html>
<head>
	<title>French Skyline Corporation</title>
	
	<meta charset="utf-8"/>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/fonts/cssFonts.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/general/cssGeneral.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/events/internal/cssBracket.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/header/cssHeader.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/footer/cssFooter.css" />
	
	<link rel="icon" type='image/png' href="${pageContext.request.contextPath}/inc/images/favicon.png" />
	
</head>
<body>
	<div id="blocPage">
		<div id="header">
			<c:import url="/WEB-INF/jsp/header/header.jsp"></c:import>
		</div> 
		
		<span id="backLink"><a href="${pageContext.request.contextPath}/events/tournoisInternes">Retour</a></span>
		
		<div id="globalDiv" class="globalDiv">
			<div id="titleAndDate">
				<h1 id="title">Title</h1>
				<p id="date">Date</p>
			</div>
			<div class="mainDiv" id="m0">
				<div class="secundaryDiv" id="se0">
					<div class="team" id="t0"><span></span></div>
					<div class="team" id="t1"><span></span></div>
				</div>
				<div class="secundaryDiv" id="se1">
					<div class="matchDiv" id="ma0">
						<div class="tsDiv" id="ts0">
							<div class="team" id="t2"><span></span></div>
							<div class="score" id="sc0"><span></span></div>
						</div>
						<c:if test="${sessionScope.user != null && (sessionScope.user.role == 'organizer' || sessionScope.user.role == 'admin' || sessionScope.user.role == 'superadmin')}">
							<span class="result" id="r0">Résultat</span>
						</c:if>
						<div class="tsDiv" id="ts1">
							<div class="team" id="t3"><span></span></div>
							<div class="score" id="sc1"><span></span></div>
						</div>
					</div>
				</div>
				<div class="secundaryDiv" id="se2">
					<div class="matchDiv" id="ma1">
						<div class="tsDiv" id="ts2">
							<div class="team" id="t4"><span></span></div>
							<div class="score" id="sc2"><span></span></div>
						</div>
						<c:if test="${sessionScope.user != null && (sessionScope.user.role == 'organizer' || sessionScope.user.role == 'admin' || sessionScope.user.role == 'superadmin')}">
							<span class="result" id="r1">Résultat</span>
						</c:if>
						<div class="tsDiv" id="ts3">
							<div class="team" id="t5"><span></span></div>
							<div class="score" id="sc3"><span></span></div>
						</div>
					</div>
					
					<div class="matchDiv" id="ma2">
						<div class="tsDiv" id="ts4">
							<div class="team" id="t6"><span></span></div>
							<div class="score" id="sc4"><span></span></div>
						</div>
						<c:if test="${sessionScope.user != null && (sessionScope.user.role == 'organizer' || sessionScope.user.role == 'admin' || sessionScope.user.role == 'superadmin')}">
							<span class="result" id="r2">Résultat</span>
						</c:if>
						<div class="tsDiv" id="ts5">
							<div class="team" id="t7"><span></span></div>
							<div class="score" id="sc5"><span></span></div>
						</div>
					</div>
				</div>
				<div class="secundaryDiv" id="se3">
					<div class="matchDiv" id="ma3">
						<div class="tsDiv" id="ts6">
							<div class="team" id="t8"><span></span></div>
							<div class="score" id="sc6"><span></span></div>
						</div>
						<c:if test="${sessionScope.user != null && (sessionScope.user.role == 'organizer' || sessionScope.user.role == 'admin' || sessionScope.user.role == 'superadmin')}">
							<span class="result" id="r3">Résultat</span>
						</c:if>
						<div class="tsDiv" id="ts7">
							<div class="team" id="t9"><span></span></div>
							<div class="score" id="sc7"><span></span></div>
						</div>
					</div>
					
					<div class="matchDiv" id="ma4">
						<div class="tsDiv" id="ts8">
							<div class="team" id="t10"><span></span></div>
							<div class="score" id="sc8"><span></span></div>
						</div>
						<c:if test="${sessionScope.user != null && (sessionScope.user.role == 'organizer' || sessionScope.user.role == 'admin' || sessionScope.user.role == 'superadmin')}">
							<span class="result" id="r4">Résultat</span>
						</c:if>
						<div class="tsDiv" id="ts9">
							<div class="team" id="t11"><span></span></div>
							<div class="score" id="sc9"><span></span></div>
						</div>
					</div>
					
					<div class="matchDiv" id="ma5">
						<div class="tsDiv" id="ts10">
							<div class="team" id="t12"><span></span></div>
							<div class="score" id="sc10"><span></span></div>
						</div>
						<c:if test="${sessionScope.user != null && (sessionScope.user.role == 'organizer' || sessionScope.user.role == 'admin' || sessionScope.user.role == 'superadmin')}">
							<span class="result" id="r5">Résultat</span>
						</c:if>
						<div class="tsDiv" id="ts11">
							<div class="team" id="t13"><span></span></div>
							<div class="score" id="sc11"><span></span></div>
						</div>
					</div>
					
					<div class="matchDiv" id="ma6">
						<div class="tsDiv" id="ts12">
							<div class="team" id="t14"><span></span></div>
							<div class="score" id="sc12"><span></span></div>
						</div>
						<c:if test="${sessionScope.user != null && (sessionScope.user.role == 'organizer' || sessionScope.user.role == 'admin' || sessionScope.user.role == 'superadmin')}">
							<span class="result" id="r6">Résultat</span>
						</c:if>
						<div class="tsDiv" id="ts13">
							<div class="team" id="t15"><span></span></div>
							<div class="score" id="sc13"><span></span></div>
						</div>
					</div>
					
				</div>

				<div class="secundaryDiv" id="se4">
					<div class="matchDiv" id="ma7">
						<div class="tsDiv" id="ts14">
							<div class="team" id="t16"><span></span></div>
							<div class="score" id="sc14"><span></span></div>
						</div>
						<c:if test="${sessionScope.user != null && (sessionScope.user.role == 'organizer' || sessionScope.user.role == 'admin' || sessionScope.user.role == 'superadmin')}">
							<span class="result" id="r7">Résultat</span>
						</c:if>
						<div class="tsDiv" id="ts15">
							<div class="team" id="t17"><span></span></div>
							<div class="score" id="sc15"><span></span></div>
						</div>
					</div>
					
					<div class="matchDiv" id="ma8">
						<div class="tsDiv" id="ts16">
							<div class="team" id="t18"><span></span></div>
							<div class="score" id="sc16"><span></span></div>
						</div>
						<c:if test="${sessionScope.user != null && (sessionScope.user.role == 'organizer' || sessionScope.user.role == 'admin' || sessionScope.user.role == 'superadmin')}">
							<span class="result" id="r8">Résultat</span>
						</c:if>
						<div class="tsDiv" id="ts17">
							<div class="team" id="t19"><span></span></div>
							<div class="score" id="sc17"><span></span></div>
						</div>
					</div>
				</div>
				<div class="secundaryDiv" id="se5">
					<div class="matchDiv" id="ma9">
						<div class="tsDiv" id="ts18">
							<div class="team" id="t20"><span></span></div>
							<div class="score" id="sc18"><span></span></div>
						</div>
						<c:if test="${sessionScope.user != null && (sessionScope.user.role == 'organizer' || sessionScope.user.role == 'admin' || sessionScope.user.role == 'superadmin')}">
							<span class="result" id="r9">Résultat</span>
						</c:if>
						<div class="tsDiv" id="ts19">
							<div class="team" id="t21"><span></span></div>
							<div class="score" id="sc19"><span></span></div>
						</div>
					</div>
				</div>
				<div class="secundaryDiv" id="se6">
					<div class="team" id="t22"><span></span></div>
					<div class="team" id="t23"><span></span></div>
				</div>
			</div>
			
			<div class="wrappingDiv" id="wrappingDiv">
				<div class="mainDiv" id="m1">
					<div class="secundaryDiv" id="se7">
						<div class="matchDiv" id="ma10">
							<div class="tsDiv" id="ts20">
								<div class="team" id="t24"><span></span></div>
								<div class="score" id="sc20"><span></span></div>
							</div>
							<c:if test="${sessionScope.user != null && (sessionScope.user.role == 'organizer' || sessionScope.user.role == 'admin' || sessionScope.user.role == 'superadmin')}">
								<span class="result" id="r10">Résultat</span>
							</c:if>
							<div class="tsDiv" id="ts21">
								<div class="team" id="t25"><span></span></div>
								<div class="score" id="sc21"><span></span></div>
							</div>
						</div>
					</div>
					<div class="secundaryDiv" id="se8">
						<div class="team" id="t26"><span></span></div>
						<div class="team" id="t27"><span></span></div>
					</div>
				</div>
				<div class="mainDiv" id="m2">
					<div class="secundaryDiv" id="se9">
						<div class="matchDiv" id="ma11">
							<div class="tsDiv" id="ts22">
								<div class="team" id="t28"><span></span></div>
								<div class="score" id="sc22"><span></span></div>
							</div>
							<c:if test="${sessionScope.user != null && (sessionScope.user.role == 'organizer' || sessionScope.user.role == 'admin' || sessionScope.user.role == 'superadmin')}">
								<span class="result" id="r11">Résultat</span>
							</c:if>
							<div class="tsDiv" id="ts23">
								<div class="team" id="t29"><span></span></div>
								<div class="score" id="sc23"><span></span></div>
							</div>
						</div>					
					</div>
					<div class="secundaryDiv" id="se10">
						<div class="team" id="t30"><span></span></div>
						<div class="team" id="t31"><span></span></div>					
					</div>
				</div>
			</div>
		</div>
		
		<div id="resultWindowOverlay">
			<div id="resultWindow">
				<form method="post" action="<c:url value='bracket?id=${tournament.id}'/>">
					<h5>Entrez vos résultats !</h5>
					<p><span id="team0"></span><input type='text' name="score0" id="score0"/></p>
					<p><span id="team1"></span><input type='text' name="score1" id="score1"/></p>
					<input type='submit' value='Enregistrer le résultat' id="submitResult"/>
				</form>
			</div>
		</div>
		
		<div id="footer"> <c:import url="/WEB-INF/jsp/footer/footer.jsp"></c:import> </div> 
	</div>
	
	<script src="${pageContext.request.contextPath}/inc/js/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/inc/js/events/bracketHuitEvents.js"></script>
	<script>
		jQuery(document).ready(function(){
			$("#title").html("${tournament.title}");
			$("#date").html("${tournament.dateTournament}");
			
			<c:forEach var="spot" items="${tournament.spots}">
					$("#t${spot.key}").html("<span>${spot.value}</span>");
			</c:forEach>
			
			<c:forEach var="score" items="${tournament.scores}">
				<c:if test="${score.value[0] != -1 && score.value[1] != -1}">
					$("#sc${score.key*2}").html("<span>${score.value[0]}</span>");
					$("#sc${score.key*2+1}").html("<span>${score.value[1]}</span>");
					if (${score.value[0]} > ${score.value[1]}) {
						$("#sc${score.key*2}").css("background-color","#3a8138");
						$("#sc${score.key*2+1}").css("background-color","#960000");
					} else {
						$("#sc${score.key*2}").css("background-color","#960000");
						$("#sc${score.key*2+1}").css("background-color","#3a8138");						
					}
				</c:if>
			</c:forEach>
		});
	</script>
</body>
</html>