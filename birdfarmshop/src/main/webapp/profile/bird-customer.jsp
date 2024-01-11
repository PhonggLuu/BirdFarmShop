

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" type="image/png" href="assets/images/logo-title-bar.png"/>
        <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap" rel="stylesheet">

        <title>V.E.T - Chim khách hàng</title>

        <!-- Additional CSS Files -->
        <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/font-awesome.css">
        <link rel="stylesheet" href="assets/css/templatemo-hexashop.css">
        <link rel="stylesheet" href="assets/css/owl-carousel.css">
        <link rel="stylesheet" href="assets/css/lightbox.css">
        <style type="text/css">
            body {
                margin: 0;
                padding-top: 150px;
                color: #2e323c;
                background: #f5f6fa;
                position: relative;
                height: 100%;
            }
            .account-settings .user-profile {
                margin: 0 0 1rem 0;
                padding-bottom: 1rem;
                text-align: center;
            }
            .account-settings .user-profile .user-avatar {
                margin: 0 0 1rem 0;
            }
            .account-settings .user-profile .user-avatar img {
                width: 90px;
                height: 90px;
                -webkit-border-radius: 100px;
                -moz-border-radius: 100px;
                border-radius: 100px;
            }
            .account-settings .user-profile h5.user-name {
                margin: 0 0 0.5rem 0;
            }
            .account-settings .user-profile h6.user-email {
                margin: 0;
                font-size: 0.8rem;
                font-weight: 400;
                color: #9fa8b9;
            }
            .account-settings .about {
                margin: 2rem 0 0 0;
                text-align: center;
            }
            .account-settings .about h5 {
                margin: 0 0 15px 0;
                color: #007ae1;
            }
            .account-settings .about p {
                font-size: 0.825rem;
            }
            .form-control {
                border: 1px solid #cfd1d8;
                -webkit-border-radius: 2px;
                -moz-border-radius: 2px;
                border-radius: 2px;
                font-size: .825rem;
                background: #ffffff;
                color: #2e323c;
            }

            .form-group .form-control {
                white-space: pre-wrap;
                font-size: 16px;
            }
            .form-group a {
                text-decoration: none;
                color: black;
            }

            .card {
                background: #ffffff;
                -webkit-border-radius: 5px;
                -moz-border-radius: 5px;
                border-radius: 5px;
                border: 0;
                margin-bottom: 1rem;
            }

            .about li {
                margin-bottom: 10px;
                padding-top: 10px;
                padding-bottom: 10px;
                padding-left: 5px;
                text-align: left;
            }

            .about li:hover {
                background: #cccccc;
            }
            .about .active-profile a
            {
                color:  orange;
            }
            .about a .icon{
                width: 24px;
                height: 24px;
                margin: 0px 10px 0px 0px;
                font-size: 24px;
            }

            .about a {
                color: black;
            }
            .text-primary{
                text-transform: uppercase;
                font-size: 20px;
            }
            .gutters{
                margin-bottom: 20px;
            }
            .register-date{
                font-style: italic;
                font-weight: bold;
            }
            .nav-item{
                width: 20%;
                text-align: center;
            }
            .image-item img{
                height: 100px;
                width: 100px;
            }
            .card .bird {
                border: 1px solid #e9e9ed;
                border-radius: 15px;
                padding: 0 12px;
            }
            .card .order{
                width: 40px;
            }
            .body-bird{
                max-height: 585px;
                overflow-y: scroll;
            }
            .body-bird::-webkit-scrollbar {
                border-radius: 0;
                width: 8px;
            }

            .body-bird::-webkit-scrollbar-thumb {
                border-radius: 4px;
                background-color: rgba(22, 24, 35, 0.06);
            }

            .body-bird::-webkit-scrollbar-track {
                border-radius: 0;
                background-color: rgba(0, 0, 0, 0);
            }
        </style>

    </head>

    <body>
        <!-- Header Start -->
        <%@include file="../layout/header.jsp" %>
        <!-- Header End -->
        <main>
            <div class="container container-custome">
                <div class="row gutters">
                    <%@include file="../layout/sidebar-profile.jsp" %>
                    <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">
                        <div class="card h-100">
                            <div class="card-body profile-section">
                                <div class="row gutters">
                                    <div class="card-body order-section">
                                        <!-- Tab buttons -->
                                        <ul class="nav nav-tabs" id="orderTabs">
                                            <li class="nav-item">
                                                <a class="nav-link active" data-toggle="tab" href="#allBird">Tất cả</a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link" data-toggle="tab" href="#pairBird">Đang ghép cặp</a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link" data-toggle="tab" href="#yetPairBird">Chưa ghép cặp</a>
                                            </li>
                                        </ul>

                                        <!-- Tab content -->
                                        <div class="tab-content">
                                            <!-- Pending Orders Tab -->
                                            <div class="tab-pane fade show active" id="allBird">
                                                <div class="row">
                                                    <!-- Card components for pending orders -->
                                                    <div class="col-12 body-bird">
                                                        <c:choose>
                                                            <c:when test="${not empty requestScope.BIRDCUMTOMER}">
                                                                <c:forEach var="birdAll" items="${requestScope.BIRDCUMTOMER}" varStatus="counter">
                                                                    <div class="card w-100 mt-2"> <!-- Add the w-100 class to make the card expand to full width -->
                                                                        <div class="card-body bird">
                                                                            <!-- Order ID and Create Date -->
                                                                            <div class="row align-items-center">
                                                                                <div class="order col-1">
                                                                                    <h6 class="text-center">${counter.count}</h6>
                                                                                </div>
                                                                                <div class="col-3">
                                                                                    <h5>${birdAll.name}</h5>
                                                                                </div>
                                                                                <div class="col-2">
                                                                                    <c:if test="${birdAll.gender == true}">
                                                                                        <h5>Chim trống</h5>
                                                                                    </c:if>
                                                                                    <c:if test="${birdAll.gender == false}">
                                                                                        <h5>Chim mái</h5>
                                                                                    </c:if>
                                                                                </div>
                                                                                <div class="col-3">
                                                                                    <h5>${birdAll.status}</h5>
                                                                                </div>
                                                                                <div class="image-item col-auto">
                                                                                    <img src="${birdAll.img_url}" alt="${birdAll.name}" />
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </c:forEach>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <div class="mt-5">
                                                                    <h4 class="text-center">Bạn chưa có con chim nào</h4>
                                                                </div>
                                                            </c:otherwise>
                                                        </c:choose>

                                                    </div>
                                                </div>
                                            </div>

                                            <div class="tab-pane fade" id="pairBird">
                                                <div class="row">
                                                    <!-- Card components for pending orders -->
                                                    <div class="col-12 body-bird">
                                                        <c:choose>
                                                            <c:when test="${not empty requestScope.BIRDCUMTOMER}">
                                                                <c:set var="counter2" value="0"/>
                                                                <c:forEach var="birdPair" items="${requestScope.BIRDCUMTOMER}">
                                                                    <c:if test="${birdPair.status == 'Đang ghép cặp'}">
                                                                        <c:set var="counter2" value="${counter2 + 1}"/>
                                                                        <div class="card w-100 mt-2"> <!-- Add the w-100 class to make the card expand to full width -->
                                                                            <div class="card-body bird">
                                                                                <!-- Order ID and Create Date -->
                                                                                <div class="d-flex justify-content-between align-items-center">
                                                                                    <div class="order">
                                                                                        <h6 class="text-center">${counter2}</h6>
                                                                                    </div>
                                                                                    <div>
                                                                                        <h5>${birdPair.name}</h5>
                                                                                    </div>
                                                                                    <div>
                                                                                        <c:if test="${birdAll.gender == true}">
                                                                                            <h5>Chim trống</h5>
                                                                                        </c:if>
                                                                                        <c:if test="${birdAll.gender == false}">
                                                                                            <h5>Chim mái</h5>
                                                                                        </c:if>
                                                                                    </div>
                                                                                    <div>
                                                                                        <h5>${birdPair.status}</h5>
                                                                                    </div>
                                                                                    <div class="image-item">
                                                                                        <img src="${birdPair.img_url}" alt="${birdPair.name}" />
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <div class="mt-5">
                                                                    <h4 class="text-center">Bạn chưa có con chim nào</h4>
                                                                </div>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="tab-pane fade" id="yetPairBird">
                                                <div class="row">
                                                    <!-- Card components for pending orders -->
                                                    <div class="col-12 body-bird">
                                                        <c:choose>
                                                            <c:when test="${not empty requestScope.BIRDCUMTOMER}">
                                                                <c:set var="counter3" value="0"/>
                                                                <c:forEach var="yetPairBird" items="${requestScope.BIRDCUMTOMER}">
                                                                    <c:if test="${yetPairBird.status == 'Chưa ghép cặp'}">
                                                                        <c:set var="counter3" value="${counter3 + 1}"/>
                                                                        <div class="card w-100 mt-2"> <!-- Add the w-100 class to make the card expand to full width -->
                                                                            <div class="card-body bird">
                                                                                <!-- Order ID and Create Date -->
                                                                                <div class="d-flex justify-content-between align-items-center">
                                                                                    <div class="order">
                                                                                        <h6 class="text-center">${counter3}</h6>
                                                                                    </div>
                                                                                    <div>
                                                                                        <h5>${yetPairBird.name}</h5>
                                                                                    </div>
                                                                                    <div>
                                                                                        <c:if test="${birdAll.gender == true}">
                                                                                            <h5>Chim trống</h5>
                                                                                        </c:if>
                                                                                        <c:if test="${birdAll.gender == false}">
                                                                                            <h5>Chim mái</h5>
                                                                                        </c:if>
                                                                                    </div>
                                                                                    <div>
                                                                                        <h5>${yetPairBird.status}</h5>
                                                                                    </div>
                                                                                    <div class="image-item">
                                                                                        <img src="${yetPairBird.img_url}" alt="${yetPairBird.name}" />
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <div class="mt-5">
                                                                    <h4 class="text-center">Bạn chưa có con chim nào</h4>
                                                                </div>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
        </main>
        <!-- Start Footer -->
        <%@include file="../layout/footer.jsp" %>
        <!-- End Footer -->
    </body>
</html>
