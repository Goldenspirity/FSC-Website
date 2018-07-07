<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header>
	<div id="logos">
		<div id="teamLogo">
			<a href="${pageContext.request.contextPath}"><img src="${pageContext.request.contextPath}/inc/images/fsc_logo.png" title="French Skyline Corporation"/></a>
		</div>
		
		<div id="socialLogos">
			<a href="https://www.facebook.com/FrenchSkylineCorp/" target="_blank"><img src="${pageContext.request.contextPath}/inc/images/facebook.png" title="Facebook"/></a>
			<a href="https://twitter.com/frskylinecorp" target="_blank"><img src="${pageContext.request.contextPath}/inc/images/twitter.png" title="Twitter" id="twitterLogo"/></a>
			<a href="https://www.twitch.tv/goldye" target="_blank"><img src="${pageContext.request.contextPath}/inc/images/twitch.png" title="Twitch" id="twitchLogo"/></a>
			<a href="https://discordapp.com/" target="_blank"><img src="${pageContext.request.contextPath}/inc/images/discord.png" title="Discord" id="discordLogo"/></a>
		</div>
	</div>
	<nav>
		<ul class="list">
	    	<li><a href="${pageContext.request.contextPath}/home">ACCUEIL</a></li>
	    	<li><a href="${pageContext.request.contextPath}/news">ACTUALITES</a></li>
	    	<li><a href="${pageContext.request.contextPath}/home">NOS EQUIPES</a></li>
	    	<li><a href="${pageContext.request.contextPath}/home">MEDIAS</a></li>
	    	
	    	<c:if test="${sessionScope.user == null}">
	    		<li><a href="${pageContext.request.contextPath}/shop">BOUTIQUE</a></li>
	    		<li><a href="inscription">S'ENREGISTRER</a></li>
	    	</c:if>
	    	
	    	<c:if test="${sessionScope.user != null}">
	    		<li><a href="${pageContext.request.contextPath}/events/tournoisInternes">EVENEMENTS</a></li>
	    		<li><a href="${pageContext.request.contextPath}/shop">BOUTIQUE</a></li>
	    	</c:if>
	    
	    </ul>
	</nav>
	<div id="loggedIn">
		<c:choose>
			<c:when test="${sessionScope.user != null}">
				<p>Bonjour <c:out value="${sessionScope.user.name}"/>. <br/>
				<a href="<c:url value='/account'/>" class="disconnect">Mon Compte</a> - 
				<a href="<c:url value='/deconnexion'/>" class="disconnect">Deconnexion</a></p>
			</c:when>
			<c:otherwise><p>Déjà enregistré ? <a href="<c:url value='/connexion'/>" class="connect">Connexion</a></p></c:otherwise>
		</c:choose>
	</div>
</header>