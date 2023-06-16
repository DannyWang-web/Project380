<%@ page import="hkmu.comps380f.model.User" %>
<%@ page import="org.springframework.data.jpa.repository.support.SimpleJpaRepository" %>
<%@ page import="hkmu.comps380f.dao.UserRepository" %>
<!DOCTYPE html>
<html>
<head>
  <title>Customer Support</title>
</head>
<body>
<p>Hello <security:authentication property="principal.username" />!</p>

<h2>Add Your Description</h2>
<form:form method="POST" enctype="multipart/form-data" modelAttribute="photoFormModel">
  <b>Dscription</b><br/>

  <form:label path="description">Description</form:label><br/>
  <form:textarea path="description" rows="5" cols="30"/><br/><br/>

  <input type="submit" value="Submit"/>
</form:form>
<br>
<a href="<c:url value="/photo/index"/>"> Return to photo index </a>
</br>
</body>
</html>