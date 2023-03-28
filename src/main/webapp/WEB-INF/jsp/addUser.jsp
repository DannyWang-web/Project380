<!DOCTYPE html>
<html>
<head>
    <title>Customer Support</title>
</head>
<body>
<h2>Create a User</h2>
<form:form method="POST" enctype="multipart/form-data" modelAttribute="userForm">
    <form:label path="userName">User Name: </form:label><br/>
    <form:input type="text" path="userName"/><br/><br/>

    <form:label path="phoneNumber">Phone Number: </form:label><br/>
    <form:input type="text" path="phoneNumber"/><br/><br/>

    <form:label path="userEmail">Email Address:</form:label><br/>
    <form:input type="text" path="userEmail"/><br/><br/>

    <form:label path="userPassword">Password: </form:label><br/>
    <form:input type="text" path="userPassword"/><br/><br/>

    <form:label path="phoneNumber">Description:</form:label><br/>
    <form:input type="text" path="userDescription"/><br/><br/>

    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>
