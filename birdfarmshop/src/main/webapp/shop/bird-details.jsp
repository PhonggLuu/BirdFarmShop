<%-- 
    Document   : bird-details
    Created on : Sep 13, 2023, 11:21:39 PM
    Author     : tlminh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" type="image/png" href="assets/images/logo-title-bar.png"/>
        <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap" rel="stylesheet">

        <title>V.E.T - Chi tiết vẹt cảnh</title>


        <!-- Additional CSS Files -->
        <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/font-awesome.css">
        <link rel="stylesheet" href="assets/css/templatemo-hexashop.css">
        <link rel="stylesheet" href="assets/css/owl-carousel.css">
        <link rel="stylesheet" href="assets/css/lightbox.css">
        <style>
            .image-container {
                display: flex;
                align-items: center;
            }
            .overlay-text {
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                background-color: rgba(0, 0, 0, 0.5);
                border-radius: 50%;
                color: #fff;
                padding: 30px;
                font-size: 25px;
                text-align: center;
            }

            #mainImage {
                border: 1px solid #000;
                transition: transform 0.3s ease-in-out;
            }

            #mainImage:hover {
                transform: scale(1.1);
                cursor: pointer;
                border: 0px;
            }
            .image-top {
                position: relative;
            }
            .overlay-container {
                position: relative;
            }
            .button-form{
                margin-bottom: 5px;
                display:block;
                color: white;
                padding: 10px;
                border: 1px solid;
                font-size: 15px;
                border-radius: 4px;
                width: 160px;
            }
            .tag-discount{
                position: absolute;
                top: 7%;
                left: 3%;
                transform: translate(-50%, -50%);
                color: #fff;
                text-align: center;
                border-top-right-radius: 10px; /* Điều chỉnh bán kính cho góc trên cùng bên trái */
                border-bottom-right-radius: 10px;
                background-color: red;
                padding: 5px 5px 5px 10px;
                font-weight: bold;
            }
            pre {
                width: 100%;
                white-space: pre-wrap;
                overflow-y: auto;
                max-height: 250px;
                font-family: inherit;
            }
            .image-bottom img {
                width: 154px;
                height: 185px;
                display: block;
                margin-bottom: 15px;
                border: 1px solid #000;
            }

        </style>
        <script>
            function swapImages(clickedImage) {
                const leftImage = document.querySelector('.image-top img');
                const rightImage1 = document.querySelectorAll('.image-bottom img')[0];
                const tempSrc = leftImage.src;
                leftImage.src = clickedImage.src;
                clickedImage.src = tempSrc;
            }
        </script>
    </head>
    <body>
        <!-- Header Start -->
        <%@include file="../layout/header.jsp" %>
        <!-- Header End -->
        <!-- ***** Main Banner Area Start ***** -->
        <div class="page-heading" id="top">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="inner-content">
                            <h2>Thông tin chi tiết</h2>
                            <span>Tất tần tật thông tin và mô tả về chú vẹt sẽ được tìm thấy tại đây!</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- ***** Main Banner Area End ***** -->
        <!-- ***** Product Area Starts ***** -->
        <c:set var="birdDetails" value="${requestScope.birdDetails}"/>
        <section class="section" id="product" style="margin-top: 60px; margin-bottom: 75px;">
            <div class="container">
                <c:if test="${LOGIN_USER.role == 'admin' || LOGIN_USER.role == 'manager'}">
                    <c:if test="${requestScope.birdDetails.status eq 'Còn hàng'}">
                        <div class="row" style="margin-bottom: 15px;">
                            <div class="col-lg-12">
                                <button class="btn-primary button-form" style="float: right;"><a href="MainController?action=UpdateBird&bird_id=${birdDetails.bird_id}"><span style="color: white;">Cập nhật chim</span></a></button>
                            </div>
                        </div>
                    </c:if>
                </c:if>
                <div class="row">
                    <div class="col-lg-7">
                        <div class="row">
                            <div class="col-lg-7">
                                <c:if test="${birdDetails != null}">
                                    <div class="image-container">
                                        <div class="image-top">
                                            <div class="overlay-container">
                                                <img id="mainImage" style="width: 370px; height: 444px;" src="${im}" alt="" onclick="swapImages(this)">
                                                <c:if test="${birdDetails.discount > 0}">
                                                    <span class="tag-discount"> -${birdDetails.discount}%</span>
                                                </c:if>
                                                <c:if test="${birdDetails.status == 'Hết hàng'}">
                                                    <div class="overlay-text">Hết hàng</div>
                                                </c:if>
                                                <c:if test="${birdDetails.status == 'Đang sinh sản'}">
                                                    <div class="overlay-text">Đang sinh sản</div>
                                                </c:if>
                                                <c:if test="${birdDetails.status == 'Đang ghép cặp'}">
                                                    <div class="overlay-text">Đang ghép cặp</div>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                            </div>
                            <div class="col-lg-5">
                                <div class="image-bottom">
                                    <c:forEach items="${birdDetails.image_urls}" var="bird">
                                        <c:if test="${bird ne im}">
                                            <img src="${bird}" alt="" onclick="swapImages(this)"/>
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-5">
                        <div class="right-content">
                            <h2>${birdDetails.bird_name}</h2>
                            <c:choose>
                                <c:when test="${birdDetails.discount > 0}">
                                    <span style="display: inline-block; color: #cccccc; font-size: 15px;"><del><fmt:formatNumber value="${birdDetails.price}" pattern="#,###"/> ₫</del></span>
                                    <span style="display: inline-block; border-radius: 10px; background-color: red; padding: 0 5px 0 5px; color: white;"> -${birdDetails.discount}%</span>
                                    <span style="font-size: 20px; color: red;"><fmt:formatNumber value="${birdDetails.price - birdDetails.price * birdDetails.discount / 100}" pattern="#,###"/> ₫</span>
                                </c:when>
                                <c:otherwise>
                                    <span style="font-size: 20px; color: red;"><fmt:formatNumber value="${birdDetails.price}" pattern="#,###"/> ₫</span>
                                </c:otherwise>
                            </c:choose>
                            <div class="mt-2">
                                <div style="white-space: nowrap; margin-top: 10px;">
                                    <h4 style="display: inline;">Giống loài: </h4>
                                    <span style="display: inline; color: black;">${birdDetails.breed_name}</span>
                                </div>
                                <c:if test="${birdDetails.mom_name != null}">
                                    <div class="bird-info" style="white-space: nowrap; margin-top: 10px;">                                   
                                        <h4 style="display: inline;">Chim mẹ: </h4>
                                        <span style="display: inline; color: black;"><a href="MainController?action=NavToBirdDetails&bird_id=${birdDetails.mom_id}">${birdDetails.mom_name}</a></span>                                  
                                    </div>
                                </c:if>

                                <c:if test="${birdDetails.dad_name != null}">
                                    <div class="bird-info" style="white-space: nowrap; margin-top: 10px;">
                                        <h4 style="display: inline;">Chim bố: </h4>
                                        <span style="display: inline; color: black;"><a href="MainController?action=NavToBirdDetails&bird_id=${birdDetails.dad_id}">${birdDetails.dad_name}</a></span>
                                    </div>
                                </c:if>
                                <div style="white-space: nowrap; margin-top: 10px;">
                                    <h4 style="display: inline;">Giới tính: </h4>
                                    <span style="display: inline; color: black;">${birdDetails.gender}</span>
                                </div>

                                <div style="white-space: nowrap; margin-top: 10px;">
                                    <h4 style="display: inline;">Màu sắc: </h4>
                                    <span style="display: inline; color: black;">${birdDetails.color}</span>
                                </div>

                                <div style="white-space: nowrap; margin-top: 10px;">
                                    <h4 style="display: inline;">Tuổi: </h4>
                                    <span style="display: inline; color: black;">${birdDetails.age} tháng</span>
                                </div>

                                <div style="white-space: nowrap; margin-top: 10px;">
                                    <h4 style="display: inline;">Tuổi trưởng thành: </h4>
                                    <span style="display: inline; color: black;">${birdDetails.grown_age} tháng</span>
                                </div>

                                <div style="white-space: nowrap; margin-top: 10px;">
                                    <h4 style="display: inline;">Lứa sinh sản: </h4>
                                    <span style="display: inline; color: black;">${birdDetails.reproduction_history}</span>
                                </div>
                                <c:if test="${not empty birdDetails.achievement}">
                                    <div style="margin-top: 10px;">
                                        <h4 style="">Thành tựu: </h4>
                                        <pre style="color: black;margin-top: 10px;">${birdDetails.achievement}</pre>
                                    </div>
                                </c:if>

                                <c:if test="${not empty birdDetails.description}">
                                    <div style="margin-top: 10px;">
                                        <h4>Mô tả chi tiết: </h4>
                                        <!--<span style="margin-top: 10px; color: black; font-size: 20px;">${birdDetails.description}</span>-->
                                        <pre style="margin-top: 10px;">${birdDetails.description}</pre>
                                    </div>
                                </c:if>
                                <div>
                                    <c:if test="${sessionScope.LOGIN_USER == null || sessionScope.LOGIN_USER.role == 'customer'}">
                                        <c:if test="${birdDetails.status eq 'Còn hàng'}">
                                            <div class="total" style="margin-top: 30px;">
                                                <div class="main-border-button">
                                                    <a class="bird-cart btn-primary" style="cursor: pointer; color: white; border: 0px; border-radius: 20px;" data-value="${birdDetails.bird_id}">Thêm vào giỏ hàng</a>
                                                </div>
                                            </div>
                                        </c:if>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- ***** Product Area Ends ***** -->
        <!-- Feedback start-->
        <%@include file="../layout/feedback.jsp" %>
        <!-- Feedback end-->
        <!-- Start Footer -->
        <%@include file="../layout/footer.jsp" %>
        <!-- End Footer -->
        <script>
            $(function () {
                var selectedClass = "";
                $("p").click(function () {
                    selectedClass = $(this).attr("data-rel");
                    $("#portfolio").fadeTo(50, 0.1);
                    $("#portfolio div").not("." + selectedClass).fadeOut();
                    setTimeout(function () {
                        $("." + selectedClass).fadeIn();
                        $("#portfolio").fadeTo(50, 1);
                    }, 500);
                });
                $(".bird-cart").click(function () {
                    let birdId = $(this).attr('data-value');
                    $.ajax({
                        url: "AddBirdToCartController",
                        type: 'POST',
                        data: {bird_id: birdId},
                        success: function (data) {
                            const json = JSON.parse(data);
                            toast({
                                title: json.status,
                                message: json.content,
                                type: json.type,
                                duration: 3000
                            });
                            $('.cart-amount').html(json.quantity);
                        }
                    });
                });
            });
        </script>
    </body>
</html>
