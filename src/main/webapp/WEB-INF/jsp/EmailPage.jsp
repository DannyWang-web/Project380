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

<h2>Edit Email</h2>
<form:form method="POST" enctype="multipart/form-data" modelAttribute="EmailFormModel">
    <b>Email</b><br/>

    <form:label path="Email">Edit Email</form:label><br/>
    <form:textarea path="Email" rows="5" cols="30"/><br/><br/>

    <input type="submit" value="Submit"/>
</form:form>
<br>


</body>
</html>