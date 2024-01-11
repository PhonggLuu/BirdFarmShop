<%-- 
    Document   : header
    Created on : Sep 21, 2023, 9:52:26 PM
    Author     : Admin
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="toCompare" value="MainController?action=NavToCompare"/>
<c:url var="toLogin" value="MainController?action=NavToLogin"/>
<c:url var="logout" value="MainController?action=Logout"/>
<c:url var="toAccessories" value="MainController?action=NavToAccessory"/>
<c:url var="toBirds" value="MainController?action=NavToBird"/>
<c:url var="toBirdNests" value="MainController?action=NavToBirdNests"/>
<c:url var="toCart" value="MainController?action=NavToCart"/>
<c:url var="toProfile" value="MainController?action=NavToProfile"/>
<c:url var="toOrders" value="MainController?action=NavToOrders"/>
<c:url var="toShopOrders" value="MainController?action=NavToShopOrders"/>
<c:url var="toAccounts" value="MainController?action=NavToAccounts"/>
<c:url var="toReports" value="MainController?action=NavToReports"/>
<c:url var="toPair" value="MainController?action=NavToPairBirds"/>
<c:url var="toAddAccessory" value="MainController?action=NavToAddAccessory"/>
<c:url var="toEditProfile" value="MainController?action=NavToEditProfile"/>
<c:url var="toUpdatePassword" value="MainController?action=NavToUpdatePassword"/>
<c:url var="toBirdPairShop" value="MainController?action=NavToBirdPairShop"/>
<!-- ***** Preloader Start ***** -->
<div id="preloader">
    <div class="jumper">
        <div></div>
        <div></div>
        <div></div>
    </div>
</div>  
<!-- ***** Preloader End ***** -->

<!-- ***** Header Area Start ***** -->
<header class="header-area header-sticky">
    <div class="container home-custom">
        <div class="row">
            <div class="col-12">
                <nav class="main-nav">
                    <!-- ***** Logo Start ***** -->
                    <a href="MainController?action=NavToHome" class="logo scroll-to-section">
                        <img src="assets/images/logo.png">
                    </a>
                    <!-- ***** Logo End ***** -->
                    <!-- ***** Menu Start ***** -->
                    <ul class="nav">
                        <li class="scroll-to-section"><a href="MainController?action=NavToHome" class="${param.action == null ? "active":"" 
                                                                                                         || param.action == 'NavToHome' ? "active":""}">Trang chủ</a></li>
                            <c:if test="${LOGIN_USER == null || LOGIN_USER.role == 'customer' || LOGIN_USER.role == 'staff'}">
                            <li class="submenu"><a class="${param.action == 'NavToBird' ? "active":""
                                                            ||param.action == 'NavToBirdNests' ? "active":""
                                                            ||param.action == 'NavToAccessory' ? "active":""}">Sản phẩm</a>
                                <ul>
                                    <li><a href="${pageScope.toBirds}">Vẹt cảnh</a></li>
                                   <%-- <li><a href="${pageScope.toBirdNests}">Tổ chim non</a></li>--%>
                                    <li><a href="${pageScope.toAccessories}">Phụ kiện</a></li>
                                </ul>
                            </li>
                            <c:if test="${sessionScope.LOGIN_USER.role == 'staff'}">
                                <li class="scroll-to-section"><a class="${param.action == 'NavToShopOrders' ? "active":""}" href="${pageScope.toShopOrders}">Đơn hàng</a></li>
                                <li class="scroll-to-section"><a class="${param.action == 'NavToBirdPairShop' ? "active":""}" href="${pageScope.toBirdPairShop}">Nhân giống</a></li>
                            </c:if>
                            <c:if test="${LOGIN_USER == null || LOGIN_USER.role == 'customer'}">
                                <li class="scroll-to-section"><a class="${param.action == 'NavToPairBirds' ? "active":""}" href="${pageScope.toPair}">Nhân giống</a></li>
                                <li id="show-cart" class="scroll-to-section">
                                    <a href="${pageScope.toCart}"><i style="font-size: 25px" class="fa fa-shopping-cart" aria-hidden="true"></i></a>
                                    <div class="cart-amount">
                                        <c:choose>
                                            <c:when test="${sessionScope.CART == null}">0</c:when>
                                            <c:otherwise>${sessionScope.CART.totalItem}</c:otherwise>
                                        </c:choose>
                                    </div>
                                </li>

                                <c:if test="${sessionScope.LOGIN_USER == null}">
                                    <li id="checkLogin" data-value="login" class="scroll-to-section"> <a href="${pageScope.toLogin}">Đăng nhập</a></li>
                                    </c:if>
                                </c:if>
                            </c:if>
                            <c:if test="${LOGIN_USER.role == 'admin' || LOGIN_USER.role == 'manager'}">
                            <li class="submenu"><a class="${param.action == 'NavToBird' ? "active":""
                                                            ||param.action == 'NavToBirdNests' ? "active":""
                                                            ||param.action == 'NavToAccessory' ? "active":""}">Sản phẩm</a>
                                <ul>
                                    <li><a href="${pageScope.toBirds}">Vẹt cảnh</a></li>
                                   <%-- <li><a href="${pageScope.toBirdNests}">Tổ chim non</a></li>--%>
                                    <li><a href="${pageScope.toAccessories}">Phụ kiện</a></li>
                                </ul>
                            </li>
                            <li class="scroll-to-section"><a class="${param.action == 'NavToShopOrders' ? "active":""}" href="${pageScope.toShopOrders}">Đơn hàng</a></li>
                            <li class="scroll-to-section"><a class="${param.action == 'NavToBirdPairShop' ? "active":""}" href="${pageScope.toBirdPairShop}">Nhân giống</a></li>
                            <li class="scroll-to-section"><a class="${param.action == 'NavToAccounts' ? "active":""}" href="${pageScope.toAccounts}">Tài khoản</a></li>
                            <li class="scroll-to-section"><a class="${param.action == 'NavToReports' ? "active":""}" href="${pageScope.toReports}">Thống kê</a></li>
                            </c:if>
                            <c:if test="${sessionScope.LOGIN_USER != null}">
                            <li class="submenu"><a class="user-name text-right ${param.action == 'NavToProfile' ? "active":""}"">${LOGIN_USER.fullName}</a>
                                <ul>
                                    <li><a href="${pageScope.toProfile}">Cá nhân</a></li>
                                    <li><a href="${pageScope.logout}">Đăng xuất</a></li>
                                </ul>
                            </li>
                        </c:if>
                    </ul>           
                    <a class='menu-trigger'>
                        <span>Menu</span>
                    </a>
                    <!-- ***** Menu End ***** -->
                </nav>
            </div>
        </div>
    </div>
</header>
<!-- ***** Header Area End ***** -->
