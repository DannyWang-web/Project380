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

<h2>Edit PhoneNumber</h2>
<form:form method="POST" enctype="multipart/form-data" modelAttribute="PhoneNumberFormModel">
    <b>PhoneNumberForm</b><br/>

    <form:label path="PhoneNumber">Edit PhoneNumber</form:label><br/>
    <form:textarea path="PhoneNumber" rows="5" cols="30"/><br/><br/>

    <input type="submit" value="Submit"/>
</form:form>
<br>

</br>
</body>
</html>