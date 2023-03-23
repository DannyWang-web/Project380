<!DOCTYPE html>
<html>
<head>
    <title>Customer Support</title>
</head>
<body>
<h2>Create a User</h2>
<form:form method="POST" enctype="multipart/form-data" modelAttribute="userForm">
    <form:label path="userName">User Name</form:label><br/>
    <form:input type="text" path="userName"/><br/><br/>
    <form:label path="comment">Comment</form:label><br/>
    <form:input type="text" path="comment"/><br/><br/>
    <form:label path="body">Body</form:label><br/>
    <form:textarea path="body" rows="5" cols="30"/><br/><br/>
    <b>Attachments</b><br/>
    <input type="file" name="attachments" multiple="multiple"/><br/><br/>
    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>
