<%-- 
    Document   : message
    Created on : Sep 24, 2023, 12:36:22 AM
    Author     : Admin
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- board-message start-->
<div id="toast">

</div>
<!-- board-message end-->
<script type="text/javascript" src="assets/js/toastmessage.js"></script>
<c:choose>            
    <c:when test="${not empty sessionScope.ERROR}">
        <script>
            toast({
                title: 'Lỗi',
                message: '<c:out value="${sessionScope.ERROR}" />',
                type: 'error',
                duration: 3000
            });
        </script>
        <c:remove var="ERROR" scope="session" />
    </c:when>
    <c:when test="${not empty sessionScope.SUCCESS}">
        <script>
            toast({
                title: 'Thành công',
                message: '<c:out value="${sessionScope.SUCCESS}" />',
                type: 'success',
                duration: 3000
            });
        </script>
        <c:remove var="SUCCESS" scope="session" />
    </c:when>
</c:choose>
