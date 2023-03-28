<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jiangyufeng
  Date: 2023/3/23
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Photo view</title>
</head>
<body>
<h2>
  Photo #${attachment.attachmentName}
</h2>
Photo description: <c:out value="${attachment.attachmentDescription}"/><br/><br/>
Photo create time: <c:out value="${attachment.createTime}"/><br/><br/>
<img src="<c:url value="/photo/attachment/${attachment.attachmentId}" />" />
<br/><br/>

<h2>Comments</h2>
<c:forEach items="${commentDatabase}" var="comment">
<c:choose>
    <c:when test="${fn:length(commentDatabase) != 0 && comment.attachmentId == attachmentId}">

        Comment Id# ${comment.commentId}:
        <%--            attachmentId${entry.attachmentId}--%>
        (User: <c:out value="${comment.userName}"/>)<br />
        <c:out value="${comment.commentContent}"/><br />

    </c:when>
</c:choose>
</c:forEach>
[<a href="<c:url value="/photo/addComment/${attachment.attachmentId}" />">Add comment</a>]<br />
<br /><br />
<a href="<c:url value="/photo/index" />">Return to photo index</a>
</body>
</html>
