<%-- 
Document   : home
Created on : Sep 13, 2023, 7:25:56 PM
Author     : tlminh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
                    <form id="form-order" action="MainController" method="POST">
                        <input type="hidden" name="action" value="${sessionScope.CART.birdPairList.size() == 0 ? "NavToPayment":"NavToVNPAY"}">
                        <div class="row">
                            <div class="col-lg-7">
                                <h5 class="font-weight-bold mb-3">Giỏ hàng của bạn</h5>
                                <div class="box-cart">
                                    <c:forEach items="${sessionScope.CART.birdList}" var="bird">
                                        <div class="item-cart pr-3 my-3">
                                            <div class="row align-items-center py-3 pl-3">
                                                <div class="image-item col-lg-2 col-md-2 col-sm-2 col-2">
                                                    <img src="${bird.value.bird.image_url}" alt="${bird.value.bird.bird_name}" />
                                                </div>
                                                <div class="infor-item px-5 col-lg-7 col-md-6 col-sm-7 col-7">
                                                    <h5>${bird.value.bird.bird_name}</h5>
                                                    <div class="mt-2">
                                                        <p> 1
                                                            <span style="font-size: 13px; margin-right: 5px">x</span>
                                                            <c:choose>
                                                                <c:when test="${bird.value.bird.discount > 0}">
                                                                <p style="font-size: 14px;">  <fmt:formatNumber value="${bird.value.bird.price - bird.value.bird.price * bird.value.bird.discount / 100}" pattern="#,###"/> ₫</p>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <p class="mb-0" style="font-size: 14px; color: black; display: inline-block"><fmt:formatNumber value="${bird.value.bird.price}" pattern="#,###"/> ₫</p>
                                                            </c:otherwise>
                                                        </c:choose> 
                                                        </p>
                                                    </div>
                                                </div>
                                                <div class="price-item ml-4 px-3 col-lg-2 col-md-3 col-sm-2 col-2">
                                                    <c:choose>
                                                        <c:when test="${bird.value.bird.discount > 0}">
                                                            <p class="float-right" style="font-size: 19px;font-weight: bold; position: relative "><fmt:formatNumber value="${bird.value.bird.price - bird.value.bird.price * bird.value.bird.discount / 100}" pattern="#,###"/> ₫</p>
                                                            <p style="position: absolute;top: -25px;right: -13px; display: inline-block; border-radius: 10px; background-color: #cccccc; padding: 0 5px 0 5px; color: black;"> -${bird.value.bird.discount}%</p>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <p class="mb-0 float-right" style="font-size: 20px; font-weight: bold; display: inline-block"><fmt:formatNumber value="${bird.value.bird.price}" pattern="#,###"/> ₫</p>
                                                        </c:otherwise>
                                                    </c:choose> 
                                                </div>
                                            </div>
                                        </div>                                 
                                    </c:forEach>
                                    <c:forEach items="${sessionScope.CART.accessoryList}" var="accessory">
                                        <div class="item-cart pr-3 my-3">
                                            <div class="row align-items-center py-3 pl-3">
                                                <div class="image-item col-lg-2 col-md-2 col-sm-2 col-2">
                                                    <img src="${accessory.value.accessory.image_url}" alt="${accessory.value.accessory.accessory_name}" />
                                                </div>
                                                <div class="infor-item px-5 col-lg-7 col-md-6 col-sm-7 col-7">
                                                    <h5>${accessory.value.accessory.accessory_name}</h5>
                                                    <div class="mt-2">
                                                        <p>
                                                            <c:if test="${accessory.value.free_order > 0}">
                                                            <p>Tặng kèm: ${accessory.value.free_order}</p></br>
                                                        </c:if>
                                                        <c:choose>
                                                            <c:when test="${accessory.value.accessory.discount > 0 && accessory.value.order_quantity > 0}">                               
                                                                <p style="color: red;"><p style="color: black;">Mua:</p> ${accessory.value.order_quantity} x <fmt:formatNumber value="${accessory.value.accessory.unit_price - accessory.value.accessory.unit_price * accessory.value.accessory.discount / 100}" pattern="#,###"/> ₫</p>
                                                            </c:when>

                                                            <c:when test="${accessory.value.accessory.discount == 0 && accessory.value.order_quantity > 0}">
                                                                <p class="mb-0">Mua: ${accessory.value.order_quantity} x <fmt:formatNumber value="${accessory.value.accessory.unit_price}" pattern="#,###"/> ₫</p>
                                                            </c:when>
                                                        </c:choose> 
                                                        </p>
                                                    </div>
                                                </div>
                                                <div class="price-item ml-2 px-3 col-lg-2 col-md-3 col-sm-2 col-2">
                                                    <c:choose>
                                                        <c:when test="${accessory.value.accessory.discount > 0 &&  accessory.value.free_order == 0}">                               
                                                            <p class="float-right" style="font-size: 19px;font-weight: bold; position: relative "><fmt:formatNumber value="${(accessory.value.accessory.unit_price - accessory.value.accessory.unit_price * accessory.value.accessory.discount / 100) * accessory.value.order_quantity}" pattern="#,###"/> ₫</p>
                                                            <p style="position: absolute;top: -25px;right: -13px; display: inline-block; border-radius: 10px; background-color: #cccccc; padding: 0 5px 0 5px; color: black;"> -${accessory.value.accessory.discount}%</p>
                                                        </c:when>
                                                        <c:when test="${accessory.value.order_quantity == 0}">                               
                                                            <p class="mb-0 float-right" style="font-size: 20px; font-weight: bold; display: inline-block"> Tặng kèm</p>
                                                        </c:when>
                                                        <c:when test="${accessory.value.accessory.discount > 0 &&  accessory.value.free_order > 0}">                               
                                                            <p class="mb-0 float-right" style="font-size: 20px; font-weight: bold; display: inline-block"><fmt:formatNumber value="${(accessory.value.accessory.unit_price - (accessory.value.accessory.unit_price * accessory.value.accessory.discount / 100)) * accessory.value.order_quantity}" pattern="#,###"/> ₫</p> 
                                                        </c:when>
                                                        <c:otherwise>
                                                            <p class="mb-0 float-right" style="font-size: 20px; font-weight: bold; display: inline-block"><fmt:formatNumber value="${accessory.value.accessory.unit_price *accessory.value.order_quantity}" pattern="#,###"/> ₫</p>                         
                                                        </c:otherwise>
                                                    </c:choose> 
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                    <c:forEach items="${sessionScope.CART.birdPairList}" var="birdPair">
                                        <div class="item-cart pr-3 my-3">
                                            <div class="row align-items-center py-3 pl-3">
                                                <div class="image-item col-lg-2 col-md-2 col-sm-2 col-2">
                                                    <c:choose>
                                                        <c:when test="${not empty birdPair.value.birdCustomer}">
                                                            <img class="mb-2" src="${birdPair.value.birdCustomer.img_url}" alt="${birdPair.value.birdCustomer.name}">
                                                            <img class="mt-2" src="${birdPair.value.birdShop.image_url}" alt="${birdPair.value.birdShop.bird_name}" >
                                                        </c:when>
                                                        <c:otherwise>
                                                            <img class="mb-2" src="${birdPair.value.birdMale.image_url}" alt="${birdPair.value.birdMale.bird_name}">
                                                            <img class="mt-2" src="${birdPair.value.birdFemale.image_url}" alt="${birdPair.value.birdFemale.bird_name}">
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                                <div class="infor-item px-5 col-lg-7 col-md-6 col-sm-7 col-7 mt-4">                                    
                                                    <c:choose>
                                                        <c:when test="${not empty birdPair.value.birdCustomer}">
                                                            <p class="lead fw-bold mb-2" style="font-size: 20px;">Phí nhân giống ban đầu</p>
                                                            <span style="font-size: 14px;display: block">(${birdPair.value.birdCustomer.name} với ${not empty birdPair.value.birdMale ?  birdPair.value.birdMale.bird_name : birdPair.value.birdFemale.bird_name})</span>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <p class="lead fw-bold mb-2" style="font-size: 20px;">Phí nhân giống ban đầu</p>         
                                                            <span style="font-size: 14px;display: block">(${birdPair.value.birdMale.bird_name} với ${birdPair.value.birdFemale.bird_name})</span>
                                                        </c:otherwise>
                                                    </c:choose>  
                                                </div>
                                                <div class="price-item ml-2 px-3 col-lg-2 col-md-3 col-sm-2 col-2">
                                                    <p class="mb-0 float-right" style="font-size: 20px; font-weight: bold; display: inline-block"><fmt:formatNumber value="${birdPair.value.servicePrice}" pattern="#,###"/> ₫</p>
                                                </div>
                                            </div>
                                        </div>                                 
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="col-lg-5 info-checkout mt-3">
                                <div class="form-group mt-3">
                                    <label for="user-receiver">Tên người nhận hàng:</label>
                                    <input id="user-receiver" class="input form-control" type="text" name="name" value="${sessionScope.LOGIN_USER.fullName}" required=""/>
                                </div>
                                <div class="form-group">
                                    <label for="mobile">Số điện thoại nhận hàng:</label>
                                    <input id="mobile" class="input form-control" type="text" name="mobile" value="${sessionScope.LOGIN_USER.phone}" required=""/>
                                </div>
                                <div class="form-group">
                                    <label for="address">Địa chỉ nhận hàng:</label>
                                    <input id="address" class="input form-control" type="text" name="address" value="${sessionScope.LOGIN_USER.address}" required=""/>
                                </div>
                                <div class="overall-menoy">
                                    <h5 class="mt-3">Tổng tiền thanh toán</h5>
                                    <div class="d-flex mt-3 total-items">
                                        <div class="d-flex align-items-center">
                                            <p>Số lượng sản phẩm: </p>
                                            <span style="font-weight: bold; font-size: 20px" class="ml-3">${sessionScope.CART.getTotalItem()}</span>
                                        </div>
                                    </div>
                                    <div class="d-flex mt-3 total-money">
                                        <div class="d-flex align-items-center">
                                            <p>Tổng tiền: </p>
                                            <span class="ml-3"><fmt:formatNumber value="${sessionScope.CART.cartTotalPrice}" pattern="#,###"/> ₫</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="payment-method mt-5">
                                    <h5 class="mt-3">Chọn phương thức thanh toán</h5>
                                    <table class="mt-3">
                                        <c:if test="${sessionScope.CART.birdPairList.size() == 0}">
                                            <tr class="money-payment">
                                                <td><input id="money" class="" type="radio" name="method" value="NavToPayment" checked=""/></td>
                                                <td><i style="font-size: 28px;" class="fa fa-money ml-3 icon-money" aria-hidden="true"></i></td>
                                                <td><label for="money" class="ml-3">Thanh toán khi nhận hàng</label></td>
                                            </tr>
                                        </c:if>
                                        <tr class="credit-payment">
                                            <td><input id="credit" class="" type="radio" ${sessionScope.CART.birdPairList.size() == 0 ? "":"checked"} name="method" value="NavToVNPAY"/></td>
                                            <td><img class="ml-2" style="height: 24px; width: 50px;"  src="assets/images/vnpay.png" alt="VNPAY"/></td>
                                            <td><label for="credit" class="ml-3">Thanh toán qua VNPAY</label></td>
                                        </tr>
                                    </table>
                                </div>
                                <button type="submit" class="mt-4 btn btn-primary w-100">Đặt hàng</button>
                            </div>
                        </div>
                    </form>
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
                });
                $('.money-payment').click(function (e) {
                    $('input[name=action]').val('NavToPayment');
                });
            });
        </script>

    </body>
</html>
