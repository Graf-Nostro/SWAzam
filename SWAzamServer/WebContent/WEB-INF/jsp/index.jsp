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
    	<div id="account">Hello, <c:out value="${name}"/></div>
    	<div id="accountmanagement"><a href="RESTUserManagement/delete">Delete Account</a></div>
    	<div id="coins">
    		You have <c:out value="${coins}"/> coins <br />
    	</div>
    	<div id="requests">
    		History of submitted song requests, plus how much you spent / earned:
    		<div id="songrequests">
    			<c:forEach items="${songrequests}" var="song">
			        <div class="singlerequest">
			        	Name: <c:out value="${song.name}"/>, &nbsp;
			         <!--	<c:choose>
						   <c:when test="${song.recognizedSong}">
						   		Earned: <c:out value="${song.coins}"/>
						   </c:when>
						   <c:otherwise>
			        			Cost: <c:out value="${song.coins}"/>
			        	   </c:otherwise> 
						</c:choose> --> 
			        </div>
			    </c:forEach>
    		</div>
    	</div>
    	
    </body>
</html>