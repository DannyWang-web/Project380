<!DOCTYPE html>
<html>
<head>
    <title>Customer Support</title>
    <style>
        .error {
            color: red;
            font-weight: bold;
            display: block;
        }
    </style>
</head>
<body>
<h2>Create a User</h2>
<form:form method="POST" modelAttribute="User">
    <form:label path="userName">User Name: </form:label><br/>
    <form:errors path="userName" cssClass="error" />
    <form:input type="text" path="userName"/><br/><br/>

    <form:label path="phoneNumber">Phone Number: </form:label><br/>
    <form:input type="text" path="phoneNumber"/><br/><br/>

    <form:label path="userEmail">Email Address:</form:label><br/>
    <form:input type="text" path="userEmail"/><br/><br/>

    <form:label path="userPassword">Password: </form:label><br/>
    <form:errors path="userPassword" cssClass="error" />
    <form:input type="text" path="userPassword"/><br/><br/>

    <form:label path="confirm_password">Confirm Password</form:label><br/>
    <form:errors path="confirm_password" cssClass="error" />
    <%-- confirm_password来源于 UserManagementController中form的变量   --%>
    <form:input type="text" path="confirm_password" /><br/><br/>

    <form:label path="roles">Roles</form:label><br/>
    <form:errors path="roles" cssClass="error" />
    <form:checkbox path="roles" value="ROLE_USER"/>ROLE_USER
    <form:checkbox path="roles" value="ROLE_ADMIN"/>ROLE_ADMIN

    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>
