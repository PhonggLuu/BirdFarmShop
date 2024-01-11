<%-- 
    Document   : compair-birds
    Created on : Sep 13, 2023, 11:23:00 PM
    Author     : tlminh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>V.E.T - So sánh</title>
        <link rel="icon" type="image/png" href="assets/images/logo-title-bar.png"/>
        <!-- Additional CSS Files -->
        <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/font-awesome.css">
        <link rel="stylesheet" href="assets/css/templatemo-hexashop.css">
        <link rel="stylesheet" href="assets/css/owl-carousel.css">
        <link rel="stylesheet" href="assets/css/lightbox.css">
        <link rel="stylesheet" href="assets/compare-bird/css/style.css"> <!-- Resource style -->
        <script src="assets/compare-bird/js/modernizr.js"></script> <!-- Modernizr -->
        <style>
            #counter-box:hover{
                cursor: pointer;
            }
        </style>
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
                            <h2>So sánh thông tin</h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- ***** Main Banner Area End ***** -->

        <main>
            <section class="cd-products-comparison-table py-5">
                <div class="cd-products-table">
                    <a  style="color: white;font-size: 1.6rem; line-height: 2.5" class="btn-lg btn-primary py-3" href="${toBirds}">
                        <i style="color: white; font-size: 1.6rem; width: 20px;" class="fa fa-arrow-left" aria-hidden="true"></i>
                        Quay lại
                    </a>
                    <div class="cd-products-wrapper mt-5">
                        <ul class="cd-products-columns">
                            <li class="title" style="width: 220px">
                                <ul class="cd-features-list">
                                    <li style="line-height: 320px">
                                        Hình ảnh
                                    </li>    
                                    <li clas="birdPrice">
                                        Giá cả
                                    </li>
                                    <li>Màu sắc</li>
                                    <li>Tuổi</li>
                                    <li>Giới tính</li>
                                    <li>Lịch sử sinh sản</li>
                                    <li>Thành tựu</li>
                                    <li>Trạng thái</li>
                                    <li></li>
                                </ul>
                            </li>
                            <c:forEach var="bird" items="${requestScope.BIRDS}">
                                <li class="product">
                                    <div onclick="remove(this, '${bird.bird_id}')" class="cd-remove-bird">
                                        <i class="fa fa-times" aria-hidden="true"></i>
                                    </div>
                                    <ul class="cd-features-list">
                                        <li style="padding: 25px 40px;">
                                            <img class="birdImage" src="${bird.image_url}" style="height: 250px" alt="product image">
                                            <h3> ${bird.bird_name} </h3>
                                        </li>
                                        <c:choose>
                                            <c:when test="${bird.discount > 0}">
                                                <li>
                                                    <span class="d-block"><del><fmt:formatNumber value="${bird.price}" pattern="#,###"/> ₫</del></span>
                                                    <span class="d-block birdPrice" style="color: red; margin-top: 5px;"><fmt:formatNumber value="${bird.price - bird.price * bird.discount / 100}" pattern="#,###"/> ₫</span>
                                                </li>
                                            </c:when>                                                    
                                            <c:otherwise>
                                                <li clas="birdPrice">
                                                    <span><fmt:formatNumber value="${bird.price}" pattern="#,###"/> ₫</span>
                                                </li>
                                            </c:otherwise>
                                        </c:choose> 
                                        <li>${bird.color}</li>
                                        <li>${bird.age} tháng</li>
                                        <c:if test="${!bird.gender}">
                                        <li>Mái</li>
                                        </c:if>
                                        <c:if test="${bird.gender}">
                                        <li>Trống</li>
                                        </c:if>
                                        <li>${bird.reproduction_history} </li>
                                        <li>${bird.achievement} </li>
                                        <li>${bird.status}</li>
                                        <li>
                                            <a href="MainController?action=NavToBirdDetails&bird_id=${bird.bird_id}">
                                                <button style="font-size: 1.6rem;" class="detail-info btn btn-success">Chi tiết</button>
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div> <!-- .cd-products-table -->
            </section> <!-- .cd-products-comparison-table -->
        </main>
        <!-- Start Footer -->
        <%@include file="../layout/footer.jsp" %>
        <!-- End Footer -->
        <script>
            function remove(e, data) {
                e.closest(".product").remove();
                $.ajax({
                    url: "AddBirdCompareController",
                    type: 'POST',
                    data: {bird_id: data},
                    success: function (check) {
                        const number = Number(check);
                        if (check == 0) {
                            window.location.href = 'MainController?action=NavToBird';
                        }
                    }
                });
            }
        </script>
    </body>
</html>

