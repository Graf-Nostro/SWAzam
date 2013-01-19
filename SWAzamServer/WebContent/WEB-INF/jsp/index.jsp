<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<!DOCTYPE html>
<html>
	<head>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
	</head>
    <body>
    	<h1>SWAZAM ADMIN INTERFACE</h1>
    	<div id="account" style="float:left;">Hello, <c:out value="${name}"/></div>
    	<div id="coins" style="overflow:hidden; padding-left:50px;">
    		You have <c:out value="${coins}"/> coins <br />
    	</div>
    	<div id="accountmanagement">
    		<form id="deleteuser" action="delete/<c:out value="${name}"/>" method="post">
			    <fieldset id="actions">
			    	<input type="hidden" id="hiddenusername" name="name" value="<c:out value="${name}"/>"> 
			        <input type="submit" id="submit" value="Delete Account"> 
			    </fieldset>
			</form> <br />
    		<form id="edituser" action="update/<c:out value="${name}"/>" method="post">
			    <h2>EDIT Name/Password</h2>
			    <fieldset id="inputs">
			        Username: <input id="username" type="text" name="name" value="<c:out value="${name}"/>" placeholder="Username" autofocus required>   
			        OldPassword: <input id="password" type="password" name="oldpasswd" placeholder="OldPassword" required>
			        NewPassword: <input id="password" type="password" name="newpasswd" placeholder="NewPassword" required>
			    </fieldset>
			    <fieldset id="actions">
			        <input type="submit" id="submit" value="Edit Account"> 
			    </fieldset>
			</form>
    	</div>
    	
    	<div id="requests">
    		History of submitted song requests, plus how much you spent / earned:
    		<div id="songrequests">
    			<c:forEach items="${songrequests}" var="song">
			        <div class="singlerequest">
			        	Name: <c:out value="${song.name}"/>,  
			         	<c:choose>
						   <c:when test="${song.recognizedSong}">
						   		Earned: <c:out value="${song.coins}"/>
						   </c:when>
						   <c:otherwise>
			        			Cost: <c:out value="${song.coins}"/>
			        	   </c:otherwise> 
						</c:choose> 
			        </div>
			    </c:forEach>
    		</div>
    	</div>
    	
    </body>
</html>