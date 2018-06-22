<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
    	<meta charset="utf-8"/>
    
        <title>Connexion - French Sky Corporation</title>
        
        <link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/fonts/cssFonts.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/general/cssGeneral.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/header/cssHeader.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/footer/cssFooter.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/connexion/cssConnexion.css" />
        
		<link rel="icon" type='image/png' href="${pageContext.request.contextPath}/inc/images/favicon.png" />
    </head>
    <body>
    
    	<div id="blocPage">
	    	
	    	<div id="header"> <c:import url="/WEB-INF/jsp/header/header.jsp"></c:import> </div> 
	    	
	    	<div id="form" class="center">
		        <form method="post" action="<c:url value='/connexion' />">
		            <fieldset>
						<div class="input">
		                <label for="username">Pseudo</label>
		                <input type="text" id="username" name="username" value="" size="20" maxlength="60" />
		                </div>
		                <span class="error">${form.errors['username']}</span>
						
						<div class="input">
		                <label for="password">Mot de passe</label>
		                <input type="password" id="password" name="password" value="" size="20" maxlength="20" />
		                </div>
		                <span class="error">${form.errors['password']}</span>
	
						<div class="input">
		                <input type="submit" value="Connexion" id="submit" />
		                </div>
		                <br />
		                
		                <p class="${empty form.errors ? 'succes' : 'error'}">${form.result}</p>
		                
		            </fieldset>
		        </form>
	        </div>
	        
	        <div id="footer"> <c:import url="/WEB-INF/jsp/footer/footer.jsp"></c:import> </div> 
        </div>
    </body>
</html>