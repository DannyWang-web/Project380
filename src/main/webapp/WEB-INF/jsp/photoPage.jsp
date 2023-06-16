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

    <c:url var="logoutUrl" value="/logout"/>
    <form action="${logoutUrl}" method="post">
        <input type="submit" value="Log out" />
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>

    <h2>Photo #${attachment.attachmentName}</h2>

    <c:choose>
        <c:when test="${userName  ne 'anonymousUser'}">
            Photo uploader:<a href="<c:url value="/photo/profile/${attachment.user.getUserName()}" />">
            <c:out value="${attachment.user.getUserName()}"/><br/><br/>
            </a>
        </c:when>
        <c:otherwise>
            Photo uploader:
            <c:out value="${attachment.user.getUserName()}"/><br/><br/>
            </a>
        </c:otherwise>

    </c:choose>
    Photo description: <c:out value="${attachment.attachmentDescription}"/><br/><br/>
    Photo create time: <c:out value="${attachment.createTime}"/><br/><br/>

    <img style="width:800px;height:400px" src="<c:url value="/photo/attachment/download/${attachment.attachmentId}" />" />
    <br/>

    <c:if test="${userName  ne 'anonymousUser'}">
        <c:choose>
            <c:when test="${userName eq attachment.user.getUserName()}">
                [<a href="<c:url value="/photo/editAttachmentDescription/userId/${attachment.userId}/attachmentId/${attachment.attachmentId}" />">
                    Edit the Photo Description
                </a>]
            </c:when>
            <c:otherwise>
                <security:authorize access="hasRole('ADMIN')">
                    [<a href="<c:url value="/photo/editAttachmentDescription/userId/${attachment.userId}/attachmentId/${attachment.attachmentId}" />">
                    Edit the Photo Description
                    </a>]
                </security:authorize>
                <br>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${userName eq attachment.user.getUserName()}">
                [<a href="<c:url value="/photo/deleteAttachment/userId/${attachment.userId}/attachmentId/${attachment.attachmentId}" />">
                    Delete
                </a>]<br>
            </c:when>
            <c:otherwise>
                <security:authorize access="hasRole('ADMIN')">
                    [<a href="<c:url value="/photo/deleteAttachment/userId/${attachment.userId}/attachmentId/${attachment.attachmentId}" />">
                        Delete
                    </a>]<br>
                </security:authorize>
            </c:otherwise>
        </c:choose>
    </c:if>

    <h2>Comments</h2>
    <c:forEach items="${commentDatabase}" var="comment">
    <c:choose>
        <c:when test="${fn:length(commentDatabase) != 0 && comment.attachmentId == attachmentId}">
            (User: <c:out value="${comment.userName}"/>):<c:out value="${comment.commentContent}"/><br>

            <c:if test="${userName  ne 'anonymousUser'}">
                <c:choose>
                    <c:when test="${userName eq comment.userName}">
                        [<a href="<c:url value="/photo/editComment/attachmentId/${attachment.attachmentId}/commentId/${comment.commentId}" />"> Edit the comment</a>]
                    </c:when>
                    <c:otherwise>
                        <security:authorize access="hasRole('ADMIN')">
                            [<a href="<c:url value="/photo/editComment/attachmentId/${attachment.attachmentId}/commentId/${comment.commentId}" />"> Edit the comment</a>]
                        </security:authorize>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${userName eq comment.userName}">
                        [<a href="<c:url value="/photo/deleteComment/attachmentId/${attachment.attachmentId}/commentId/${comment.commentId}" />">Delete</a>]<br>
                    </c:when>
                    <c:otherwise>
                        <security:authorize access="hasRole('ADMIN')">
                            [<a href="<c:url value="/photo/deleteComment/attachmentId/${attachment.attachmentId}/commentId/${comment.commentId}" />">Delete</a>]<br>
                        </security:authorize>
                    </c:otherwise>
                </c:choose>

            </c:if>
<br>
        </c:when>
    </c:choose>
    </c:forEach>
    <br>

    <security:authorize access="hasRole('USER') or hasRole('ADMIN')">
        [<a href="<c:url value="/photo/addComment/${attachment.attachmentId}" />">Add comment</a>]<br />
    </security:authorize>

    <br /><br />
    <a href="<c:url value="/" />">Return to photo index</a>
</body>
</html>
