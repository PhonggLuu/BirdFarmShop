

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

        <title>V.E.T - Ghép cặp</title>

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
            .nav-item:first-child{
                width: 10%;
            }
            .nav-item{
                width: 15%;
            }
            #orderTabs li:hover{
                cursor: pointer;
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
                            <div class="card-body order-section">
                                <!-- Tab buttons -->
                                <form id="birdPairCustomerOrder" action="MainController" method="POST">
                                    <input type="hidden" name="action" value="NavToBirdPairCustomer" />
                                    <input type="hidden" name="status" value="All">
                                    <ul class="nav nav-tabs" id="orderTabs">
                                        <li class="nav-item">
                                            <a onclick="changeStatus(this)" data-value="All" class="nav-link ${param.status == 'All' ? "active":"" || param.status == null ? "active":""}">Tất cả</a>
                                        </li>
                                        <li class="nav-item">
                                            <a onclick="changeStatus(this)" data-value="Chờ lấy chim" class="nav-link ${param.status == 'Chờ lấy chim' ? "active":""}">Chờ lấy chim</a>
                                        </li>
                                        <li class="nav-item">
                                            <a onclick="changeStatus(this)" data-value="Đang ghép" class="nav-link ${param.status == 'Đang ghép' ? "active":""}">Đang ghép</a>
                                        </li>
                                        <li class="nav-item">
                                            <a onclick="changeStatus(this)" data-value="Đã sinh sản" class="nav-link ${param.status == 'Đã sinh sản' ? "active":""}">Đã sinh sản</a>
                                        </li>
                                        <li class="nav-item">
                                            <a onclick="changeStatus(this)" data-value="Đã ấp nở" class="nav-link ${param.status == 'Đã ấp nở' ? "active":""}">Đã ấp nở</a>
                                        </li>
                                        <li class="nav-item">
                                            <a onclick="changeStatus(this)" data-value="Đã thanh toán" class="nav-link ${param.status == 'Đã thanh toán' ? "active":""}">Đã thanh toán</a>
                                        </li>
                                        <li class="nav-item">
                                            <a onclick="changeStatus(this)" data-value="Đã hủy" class="nav-link ${param.status == 'Đã hủy' ? "active":""}">Đã hủy</a>
                                        </li>
                                    </ul>
                                    <div class="tab-pane">
                                        <!-- Similar structure as the Pending Orders Tab -->
                                        <div class="row">
                                            <!-- Card components for pending orders -->
                                            <div class="col-12">
                                                <div class="card w-100"> 
                                                    <div class="card-body card-pair p-0">
                                                        <c:choose>
                                                            <c:when test="${not empty requestScope.BIRDPAIRLIST}">
                                                                <c:forEach var="birdPair" items="${requestScope.BIRDPAIRLIST}">
                                                                    <c:choose>
                                                                        <c:when test="${not empty birdPair.birdCustomer}">
                                                                            <div class="box-pair mt-2">
                                                                                <p class="ml-3">Mã đơn hàng: ${birdPair.order_id}</p>
                                                                                <div class="d-flex align-items-center justify-content-between">
                                                                                    <div class="pair-img pl-3 py-3 text-center">
                                                                                        <img src="${birdPair.birdCustomer.img_url}" alt="${birdPair.birdCustomer.name}" />
                                                                                        <p>${birdPair.birdCustomer.name}</p>
                                                                                    </div>
                                                                                    <div class="text-center">
                                                                                        <div class="pair-status mb-4">
                                                                                            ${birdPair.status}
                                                                                        </div>
                                                                                        <a href="MainController?action=NavToBirdPairDetail&order_id=${birdPair.order_id}&pair_id=${birdPair.pair_id}">Xem chi tiết</a>
                                                                                    </div>
                                                                                    <c:choose>
                                                                                        <c:when test="${not empty birdPair.male_bird}">
                                                                                            <div class="pair-img pr-3 py-3 text-center">
                                                                                                <img src="${birdPair.male_bird.image_url}" alt="${birdPair.male_bird.bird_name}"/>
                                                                                                <p>${birdPair.male_bird.bird_name} (Trống)</p>
                                                                                            </div>
                                                                                        </c:when>
                                                                                        <c:otherwise>
                                                                                            <div class="pair-img pr-3 py-3 text-center">
                                                                                                <img src="${birdPair.female_bird.image_url}" alt="${birdPair.female_bird.bird_name}"/>
                                                                                                <p>${birdPair.female_bird.bird_name} (Mái)</p>
                                                                                            </div>
                                                                                        </c:otherwise>
                                                                                    </c:choose>
                                                                                </div>
                                                                            </div>   
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <div class="box-pair mt-2">
                                                                                <p class="ml-3">Mã đơn hàng: ${birdPair.order_id}</p>
                                                                                <div class="d-flex align-items-center justify-content-between">
                                                                                    <div class="pair-img pl-3 py-3 text-center">
                                                                                        <img src="${birdPair.male_bird.image_url}" alt="${birdPair.male_bird.bird_name}"/>
                                                                                        <p>${birdPair.male_bird.bird_name} (Trống)</p>
                                                                                    </div>
                                                                                    <div class="text-center">
                                                                                        <div class="pair-status mb-4">
                                                                                            ${birdPair.status}
                                                                                        </div>
                                                                                        <a href="MainController?action=NavToBirdPairDetail&order_id=${birdPair.order_id}&pair_id=${birdPair.pair_id}">Xem chi tiết</a>
                                                                                    </div>
                                                                                    <div class="pair-img pr-3 py-3 text-center">
                                                                                        <img src="${birdPair.female_bird.image_url}" alt="${birdPair.female_bird.bird_name}"/>
                                                                                        <p>${birdPair.female_bird.bird_name} (Mái)</p>
                                                                                    </div>
                                                                                </div>
                                                                            </div>   
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </c:forEach>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <div class="mt-5">
                                                                    <h4 class="text-center">Bạn chưa có ghép cặp chim</h4>
                                                                </div>
                                                            </c:otherwise>
                                                        </c:choose>   
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>

                        </div>
                    </div>
                </div>
        </main>
        <!-- Start Footer -->
        <%@include file="../layout/footer.jsp" %>
        <!-- End Footer -->
        <script>
            function changeStatus(event) {
                let status = event.getAttribute("data-value");
                $('input[name=status]').val(status);
                $('#birdPairCustomerOrder').submit();
            }
        </script>
    </body>
</html>

<div class="bird-info-row" customer-hidden\">
    <span class="info-title">Giới tính</span>
    <c:if test="${!bird.gender}">
        <span>Mái</span>
    </c:if>
    <c:if test="${bird.gender}">
        <span>Trống</span>  
    </c:if>
</div>