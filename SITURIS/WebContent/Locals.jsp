<%@ page contentType="text/html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>

<tags:headerLogo title='Where do you want to go?'/>

<body>
<tags:headerAndMenu/> 
<h2>Where do you want to go?</h2>
<tags:displayMessages/>
<br/>

<div class="body" align="center">
  <jsp:include page="map.jsp">
    <jsp:param name="link" value="false" />
  </jsp:include>
</div>
<br/>
 <tags:footer/>

</body>
</html>