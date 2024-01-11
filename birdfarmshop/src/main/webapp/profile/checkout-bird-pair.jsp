<%-- 
    Document   : home
    Created on : Sep 13, 2023, 7:25:56 PM
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

        <title>V.E.T - Thế giới Vẹt Cảnh</title>

        <!-- Additional CSS Files -->
        <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/font-awesome.css">
        <link rel="stylesheet" href="assets/css/templatemo-hexashop.css">
        <link rel="stylesheet" href="assets/css/owl-carousel.css">
        <link rel="stylesheet" href="assets/css/lightbox.css">
    </head>

    <body>
        <!-- Header Start -->
        <%@include file="../layout/header.jsp" %>
        <!-- Header End -->
        <!-- ***** Subscribe Area Starts ***** -->
        <div class="main-banner" style="border-bottom: none" id="top">
            <main id="maincontent" class="page-checkout">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-7">
                            <c:set var="count" value="0"/>
                            <h5 class="font-weight-bold mb-3">Giỏ hàng của bạn</h5>
                            <div class="box-cart pr-3">
                                <c:if test="${not empty requestScope.MALEBIRD}">
                                    <c:set var="count" value="${count + 1}" />
                                    <c:set var="birdMale" value="${requestScope.MALEBIRD}"/>
                                    <div class="item-cart my-3">
                                        <div class="row align-items-center py-3 pl-3">
                                            <div class="image-item col-lg-2 col-md-2 col-sm-2 col-2">
                                                <img src="${birdMale.image_url}" alt="${birdMale.bird_name}" />
                                            </div>
                                            <div class="infor-item px-5 col-md-6 col-sm-7 col-7">
                                                <h5>${birdMale.bird_name}</h5>
                                                <div class="mt-2">
                                                    <p> 1
                                                        <span style="font-size: 13px; margin-right: 5px">x</span>
                                                        <c:choose>
                                                            <c:when test="${birdMale.discount > 0}">
                                                            <p style="font-size: 14px;">  <fmt:formatNumber value="${birdMale.price - birdMale.price * birdMale.discount / 100}" pattern="#,###"/> ₫</p>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <p class="mb-0" style="font-size: 14px; color: black; display: inline-block"><fmt:formatNumber value="${birdMale.price}" pattern="#,###"/> ₫</p>
                                                        </c:otherwise>
                                                    </c:choose> 
                                                    </p>
                                                </div>
                                            </div>
                                            <div class="price-item ml-4 px-3 col-md-3 col-sm-2 col-2">
                                                <c:choose>
                                                    <c:when test="${birdMale.discount > 0}">
                                                        <p class="float-right" style="font-size: 19px;font-weight: bold; position: relative "><fmt:formatNumber value="${birdMale.price - birdMale.price * birdMale.discount / 100}" pattern="#,###"/> ₫</p>
                                                        <p style="position: absolute;top: -25px;right: -13px; display: inline-block; border-radius: 10px; background-color: #cccccc; padding: 0 5px 0 5px; color: black;"> -${birdMale.discount}%</p>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <p class="mb-0 float-right" style="font-size: 20px; font-weight: bold; display: inline-block"><fmt:formatNumber value="${birdMale.price}" pattern="#,###"/> ₫</p>
                                                    </c:otherwise>
                                                </c:choose> 
                                            </div>
                                        </div>
                                    </div>                                 
                                </c:if>
                                <c:if test="${not empty requestScope.FEMALEBIRD}">
                                    <c:set var="count" value="${count + 1}" />
                                    <c:set var="birdFemale" value="${requestScope.FEMALEBIRD}"/>
                                    <div class="item-cart my-3">
                                        <div class="row align-items-center py-3 pl-3">
                                            <div class="image-item col-lg-2 col-md-2 col-sm-2 col-2">
                                                <img src="${birdFemale.image_url}" alt="${birdFemale.bird_name}" />
                                            </div>
                                            <div class="infor-item px-5 col-md-6 col-sm-7 col-7">
                                                <h5>${birdFemale.bird_name}</h5>
                                                <div class="mt-2">
                                                    <p> 1
                                                        <span style="font-size: 13px; margin-right: 5px">x</span>
                                                        <c:choose>
                                                            <c:when test="${birdFemale.discount > 0}">
                                                            <p style="font-size: 14px;">  <fmt:formatNumber value="${birdFemale.price - birdFemale.price * birdFemale.discount / 100}" pattern="#,###"/> ₫</p>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <p class="mb-0" style="font-size: 14px; color: black; display: inline-block"><fmt:formatNumber value="${birdFemale.price}" pattern="#,###"/> ₫</p>
                                                        </c:otherwise>
                                                    </c:choose> 
                                                    </p>
                                                </div>
                                            </div>
                                            <div class="price-item ml-4 px-3 col-md-3 col-sm-2 col-2">
                                                <c:choose>
                                                    <c:when test="${birdFemale.discount > 0}">
                                                        <p class="float-right" style="font-size: 19px;font-weight: bold; position: relative "><fmt:formatNumber value="${birdFemale.price - birdFemale.price * birdFemale.discount / 100}" pattern="#,###"/> ₫</p>
                                                        <p style="position: absolute;top: -25px;right: -13px; display: inline-block; border-radius: 10px; background-color: #cccccc; padding: 0 5px 0 5px; color: black;"> -${birdFemale.discount}%</p>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <p class="mb-0 float-right" style="font-size: 20px; font-weight: bold; display: inline-block"><fmt:formatNumber value="${birdFemale.price}" pattern="#,###"/> ₫</p>
                                                    </c:otherwise>
                                                </c:choose> 
                                            </div>
                                        </div>
                                    </div> 
                                </c:if>
                                <c:if test="${not empty requestScope.BIRDPAIR}">
                                    <c:set var="count" value="${count + 1}" />
                                    <c:set var="birdPair" value="${requestScope.BIRDPAIR}" />
                                    <div class="item-cart my-3">
                                        <div class="row align-items-center py-3 pl-3">
                                            <div class="image-item col-lg-2 col-md-2 col-sm-2 col-2">

                                                <img src="${not empty birdPair.pList ? birdPair.pList.get(0):"assets/images/bird-compare-1.jpg"}" alt="chim non" />
                                            </div>
                                            <div class="infor-item px-5 col-md-6 col-sm-7 col-7">
                                                <h5>Chim non</h5>
                                                <div class="mt-2">
                                                    <p>${birdPair.number_young_bird}
                                                        <span style="font-size: 13px; margin-right: 5px">x</span>
                                                    <p class="mb-0" style="font-size: 14px; color: black; display: inline-block"><fmt:formatNumber value="${birdPair.young_bird_price}" pattern="#,###"/> ₫</p> 
                                                    </p>
                                                </div>
                                            </div>
                                            <div class="price-item ml-4 px-3 col-md-3 col-sm-2 col-2">
                                                <p class="mb-0 float-right" style="font-size: 20px; font-weight: bold; display: inline-block"><fmt:formatNumber value="${birdPair.number_young_bird * birdPair.young_bird_price}" pattern="#,###"/> ₫</p>
                                            </div>
                                        </div>
                                    </div>   
                                </c:if>
                                <c:if test="${not empty requestScope.CHEAPESTCAGE}">
                                    <c:set var="count" value="${count + 1}" />
                                    <c:set var="cage" value="${requestScope.CHEAPESTCAGE}" />
                                    <div class="item-cart my-3">
                                        <div class="row align-items-center py-3 pl-3">
                                            <div class="image-item col-lg-2 col-md-2 col-sm-2 col-2">
                                                <img src="${cage.image_url}" alt="${cage.accessory_name}" />
                                            </div>
                                            <div class="infor-item px-5 col-md-6 col-sm-7 col-7">
                                                <h5>${cage.accessory_name}</h5>
                                                <div class="mt-2">
                                                    <p>${not empty requestScope.MALEBIRD && not empty requestScope.FEMALEBIRD ? "2" : "1"}
                                                        <span style="font-size: 13px; margin-right: 5px">x</span>
                                                        <c:choose>
                                                            <c:when test="${cage.discount > 0}">
                                                            <p style="font-size: 14px;">  <fmt:formatNumber value="${cage.unit_price - cage.unit_price * cage.discount / 100}" pattern="#,###"/> ₫</p>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <p class="mb-0" style="font-size: 14px; color: black; display: inline-block"><fmt:formatNumber value="${cage.unit_price}" pattern="#,###"/> ₫</p>
                                                        </c:otherwise>
                                                    </c:choose> 
                                                    </p>
                                                </div>
                                            </div>
                                            <div class="price-item ml-4 px-3 col-md-3 col-sm-2 col-2">
                                                <c:choose>
                                                    <c:when test="${cage.discount > 0}">
                                                        <p class="float-right" style="font-size: 19px;font-weight: bold; position: relative "><fmt:formatNumber value="${cage.unit_price - cage.unit_price * cage.discount / 100}" pattern="#,###"/> ₫</p>
                                                        <p style="position: absolute;top: -25px;right: -13px; display: inline-block; border-radius: 10px; background-color: #cccccc; padding: 0 5px 0 5px; color: black;"> -${cage.discount}%</p>
                                                    </c:when>
                                                    <c:when test="${cage.unit_price == 0}">
                                                        <p class="mb-0 float-right" style="font-size: 20px; font-weight: bold; display: inline-block"> Tặng kèm</p>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <p class="mb-0 float-right" style="font-size: 20px; font-weight: bold; display: inline-block"><fmt:formatNumber value="${cage.unit_price}" pattern="#,###"/> ₫</p>
                                                    </c:otherwise>
                                                </c:choose> 
                                            </div>
                                        </div>
                                    </div>   
                                </c:if>
                            </div>
                        </div>  
                        <div class="col-lg-5 info-checkout mt-4">
                            <div class="overall-menoy border-top-0">
                                <h5 class="mt-3">Tổng tiền thanh toán</h5>
                                <div class="d-flex mt-3 total-items">
                                    <div class="d-flex align-items-center">
                                        <p>Số lượng sản phẩm: </p>
                                        <span style="font-weight: bold; font-size: 20px" class="ml-3">${count}</span>
                                    </div>
                                </div>
                                <div class="d-flex mt-3 total-money">
                                    <div class="d-flex align-items-center">
                                        <p>Tổng tiền: </p>
                                        <span class="ml-3"><fmt:formatNumber value="${sessionScope.LISTBIRDPAIR.get(3)}" pattern="#,###"/> ₫</span>
                                    </div>
                                </div>
                            </div>
                            <div class="payment-method mt-5">
                                <h5 class="mt-3">Chọn phương thức thanh toán</h5>
                                <table class="mt-3">
                                    <tr class="money-payment">
                                        <td><input id="money" class="" type="radio" name="method" value="NavToPayment" checked=""/></td>
                                        <td><i style="font-size: 28px;" class="fa fa-money ml-3 icon-money" aria-hidden="true"></i></td>
                                        <td><label for="money" class="ml-3">Thanh toán khi nhận hàng</label></td>
                                    </tr>
                                    <tr class="credit-payment">
                                        <td><input id="credit" class="" type="radio" name="method" value="NavToVNPAY"/></td>
                                        <td><img class="ml-2" style="height: 24px; width: 50px;"  src="assets/images/vnpay.png" alt="VNPAY"/></td>
                                        <td><label for="credit" class="ml-3">Thanh toán qua VNPAY</label></td>
                                    </tr>
                                </table>
                            </div>
                            <form id="form-order" action="MainController" method="POST">
                                <input type="hidden" name="action" value="NavToPaymentBirdPair">
                                <button type="submit" class="mt-4 btn btn-primary w-100">Thanh toán</button>
                            </form>
                        </div>
                    </div>

            </main>
        </div>   
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
                $('.credit-payment').click(function (e) {
                    $('input[name=action]').val('NavToVNPAY');
                    console.log($('input[name=action]').val());
                });
                $('.money-payment').click(function (e) {
                    $('input[name=action]').val('NavToPaymentBirdPair');
                });
            });

        </script>

    </body>
</html>
