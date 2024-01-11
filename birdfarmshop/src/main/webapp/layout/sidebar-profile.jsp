<%-- 
    Document   : sidebar-profile
    Created on : Oct 9, 2023, 3:51:49 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-xl-3 col-lg-3 col-md-12 col-sm-12 col-12">
    <div class="card h-100">
        <div class="card-body">
            <div class="account-settings">
                <div class="user-profile">
                    <div class="user-avatar">

                        <img src="assets/images/avatar.jpg" alt="Maxwell Admin">
                    </div>
                    <h5>${sessionScope.LOGIN_USER.fullName}</h5>
                </div>
                <div class="about">
                    <ul>
                        <li class="${param.action == "NavToProfile" ? "active-profile":""
                                     || param.action == "NavToUpdatePassword" ? "active-profile":""
                                     || param.action == "NavToEditProfile" ? "active-profile":""   }"> 
                            <a href="MainController?action=NavToProfile" class="profile-link">

                                <img class="icon" src="assets/images/profile.png" alt="avatar"/>
                                <span>Thông tin người dùng</span>
                            </a>
                        </li>
                        <c:if test="${sessionScope.LOGIN_USER.role == 'customer'}">

                            <li class="${param.action == "NavToCustomerOrder" ? "active-profile":""}">
                                <a href="MainController?action=NavToCustomerOrder" class="order-link">

                                    <img class="icon" src="assets/images/order.png" alt="Đơn hàng"/>
                                    <span>Đơn hàng</span>
                                </a>
                            </li>
                            <li class="${param.action == "NavToBirdCustomer" ? "active-profile":""}">
                                <a href="MainController?action=NavToBirdCustomer" class="order-link">
                                    <img class="icon" src="assets/images/bird-customer.png" alt="Chim của khách hàng"/>
                                    <span>Chim khách hàng</span>
                                </a>
                            </li>
                            <li class="${param.action == "NavToBirdPairCustomer" ? "active-profile":""
                                        || param.action == "NavToBirdPairDetail" ? "active-profile":""}">
                                <a href="MainController?action=NavToBirdPairCustomer" class="order-link">
                                    <img class="icon" src="assets/images/bird-love-heart.png" alt="Ghép cặp"/>
                                    <span>Ghép cặp</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>