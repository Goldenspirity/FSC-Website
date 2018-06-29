<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	
	<title>French Skyline Corporation</title>
	
	<meta charset="utf-8"/>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/fonts/cssFonts.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/general/cssGeneral.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/index/cssIndex.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/header/cssHeader.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/footer/cssFooter.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/owlcarousel/owl.carousel.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/owlcarousel/owl.theme.default.min.css">
	
	<link rel="icon" type='image/png' href="${pageContext.request.contextPath}/inc/images/favicon.png" />
</head>
<body>
	<div id="blocPage">
		<div id="header"> <c:import url="/WEB-INF/jsp/header/header.jsp"></c:import> </div> 
		
		<div id="mainDiv">
		 
			<div id="news" class="owl-carousel">
			
				<c:forEach items="${newsList}" var="news">
					<div class="item" id="news0"> 
						<img class="newsBackground" src="${news.image}" />	
						
						<div class="overlay">
							<img class="sliderButton prevBtn" src="${pageContext.request.contextPath}/inc/images/newsPrevBtn.png" />
							
							<div class="overlayContent"> 
								<div class="title"><h1>${news.title}</h1></div>
								<div class="subtitle">
									<p>${news.summary}</p>
								</div>
								<div class="readMore"><a href="${pageContext.request.contextPath}/news?id=${news.id}" class="button"><span>Lire la suite</span></a></div>
							</div>
							
							<img class="sliderButton nextBtn" src="${pageContext.request.contextPath}/inc/images/newsNextBtn.png" />
						</div>
					</div>
				</c:forEach>
			</div> 
			
			<div id="bottom">
				<div id="lastResults">
					<div class="title"><h2>Derniers tournois</h2></div>
					<div id="resultWrapper">
						<div id="2v2" class="result">
							<h3>2v2</h3>
							<h4>Fr_On3_TriP - Jonath025fr</h4>
							<p>G4G 27/06/18, 17h00</p>
							
							<h4>VinZ - Hasukah</h4>
							<p>G4G 25/06/18, 20h30</p>
							
							<h4>Neimad - IronWolfStreet</h4>
							<p>G4G 24/06/18, 17h30</p>
						</div>
						
						<div class="verticalLine"></div>
						
						<div id="3v3" class="result">
							<h3>3v3</h3>
							<h4>Antho - MkTriPTv - FiFOU78</h4>
							<p>G4G 25/06/18, 21h30</p>
							
							<h4>Golden - Veda - Goldye</h4>
							<p>G4G xx/xx/xx : 2è place</p>
							
							<h4>Golden - Veda - Goldye</h4>
							<p>G4G xx/xx/xx : 2è place</p>
						</div>
					</div>
				</div> 				
				<div id="social">
					<div id="twitch">
						<h2><a href="https://www.twitch.tv/frenchskylinecorporation" target="_blank">Twitch - French Sky Corporation</a></h2>
						<div id="twitch-embed"></div>
					</div>
					
					<div id="videoAndTwitter">
						<div id="lastVideo" class="socialNotTwitch">
							<h2>Vidéo du moment</h2>
							<iframe id="lastVideo" width="600" height="300" src="https://www.youtube.com/embed/ny3yw4YVIhU"></iframe>
						</div>
						<div id="lastTweet" class="socialNotTwitch">
							<h2>Twitter de la FSC</h2>
							<a class="twitter-timeline" href="https://twitter.com/frskylinecorp?ref_src=twsrc%5Etfw"
							  	data-width="600"
	  							data-height="300">
								Tweets by frskylinecorp</a> 
							<script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div id="footer"> <c:import url="/WEB-INF/jsp/footer/footer.jsp"></c:import> </div> 
	</div>
	
	
	<script src="${pageContext.request.contextPath}/inc/js/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/inc/js/owl.carousel.min.js"></script>
	<script src="https://embed.twitch.tv/embed/v1.js"></script>
	<script src="${pageContext.request.contextPath}/inc/js/index/jsIndex.js"></script>
	
</body>
</html>