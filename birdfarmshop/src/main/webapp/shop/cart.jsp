<%-- 
    Document   : cart-view
    Created on : Sep 13, 2023, 11:24:00 PM
    Author     : tlminh
--%>

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

        <title>V.E.T - Giỏ hàng</title>

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
        <!-- ***** Main Banner Area Start ***** -->
        <div class="page-heading" id="top">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="inner-content">
                            <h2>Giỏ hàng</h2>
                            <span>Kiểm tra lại giỏ hàng và tiến hành thanh toán nhanh chóng với V.E.T</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- ***** Main Banner Area End ***** -->
        <c:if test="${not empty sessionScope.CART && sessionScope.CART.totalItem == 0}">
            <c:redirect url="MainController?action=NavToHome"/>
        </c:if>
        <c:set var="count" value="0" />
        <section class="h-100">
            <div class="container-fluid h-100 py-5">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-10">
                        <c:if test="${sessionScope.CART.totalItem > 0}" ><a onclick="return clearCart(this)" href="MainController?action=DeleteCart"><button type="submit" class="float-right btn btn-danger">Xóa giỏ hàng</button></a></c:if>
                        <c:if test="${not empty sessionScope.CART.birdList}">
                            <div class="d-flex justify-content-between align-items-center mb-4">
                                <h3 class="fw-normal mb-0 text-black">Vẹt cảnh</h3>
                            </div><hr>
                        </c:if>
                        <c:forEach items="${sessionScope.CART.birdList}" var="bird" varStatus="counter">
                            <c:set var="count" value="${count + 1}" />
                            <div class="card rounded-3 mb-5">
                                <div class="card rounded-0">
                                    <div class="card-body bg-light">
                                        <div class="row d-flex justify-content-between align-items-center">
                                            <div class="pl-3 bg-light text-danger">
                                                <h4><c:out value="${count}"/></h4>
                                            </div>
                                            <div class="col-md-2 col-lg-2 col-xl-2" style="text-align: center;">
                                                <img src="${bird.value.bird.image_url}" class="img-fluid rounded-3" alt="Vẹt cảnh" style="height: 150px; width: 120px;">
                                            </div>
                                            <div class="col-md-3 col-lg-3 col-xl-3">
                                                <p class="lead fw-bold mb-2" style="font-size: 23px;">${bird.value.bird.bird_name}</p>
                                                <c:forEach var="breed" items="${requestScope.BREED_LIST}">
                                                    <c:if test="${breed.breed_id == bird.value.bird.breed_id}">
                                                        <p><span class="text-muted">Giống: </span>${breed.breed_name}
                                                        </c:if>
                                                    </c:forEach>
                                            </div>
                                            <div class="col-md-2 col-lg-2 col-xl-2 d-flex">
                                                <button class="btn btn-link px-2" style="visibility: hidden"
                                                        onclick="this.parentNode.querySelector('input[type=number]').stepDown()">
                                                    <img src="assets/images/decrease-button.png"/>
                                                </button>
                                                <input id="form1" min="0" name="quantity" value="1" type="number" disabled=""
                                                       class="form-control form-control-sm" style="text-align: center; height: 40px; border: 1px solid; font-size: 16px;"/>
                                                <button class="btn btn-link px-2" style="visibility: hidden"
                                                        onclick="this.parentNode.querySelector('input[type=number]').stepUp()">
                                                    <img src="assets/images/increase-button.png"/>
                                                </button>
                                            </div>
                                            <div class="col-md-3 col-lg-2 col-xl-2 offset-lg-1">
                                                <c:choose>
                                                    <c:when test="${bird.value.bird.discount > 0}">
                                                        <h5 class="mb-0" style="font-weight: bold; display: inline-block"><del><fmt:formatNumber value="${bird.value.bird.price}" pattern="#,###"/> ₫</del></h5>
                                                        <h5 style="display: inline-block; border-radius: 10px; background-color: #cccccc; padding: 0 5px 0 5px; color: black;"> -${bird.value.bird.discount}%</h5>
                                                        <h5 style="font-size: 20px; color: red;"><fmt:formatNumber value="${bird.value.bird.price - bird.value.bird.price * bird.value.bird.discount / 100}" pattern="#,###"/> ₫</h5>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <h5 class="mb-0" style="font-weight: bold; display: inline-block"><fmt:formatNumber value="${bird.value.bird.price}" pattern="#,###"/> ₫</h5>
                                                    </c:otherwise>
                                                </c:choose> 
                                            </div>
                                            <div class="col-md-1 col-lg-1 col-xl-1 text-end">
                                                <a onclick="return checkRemove(this)" href="MainController?action=RemoveBirdFromCart&bird_id=${bird.key}" class="text-danger"><img src="assets/images/remove-button.png"/></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>      
                        </c:forEach>
                        <c:if test="${not empty null}">
                            <div class="d-flex justify-content-between align-items-center mb-4">
                                <h3 class="fw-normal mb-0 text-black">Tổ chim non</h3>
                            </div><hr>
                        </c:if>
                        <c:if test="${not empty sessionScope.CART.birdPairList}">
                            <div class="d-flex justify-content-between align-items-center mb-4">
                                <h3 class="fw-normal mb-0 text-black">Nhân giống</h3>
                            </div><hr>
                        </c:if>
                        <c:forEach items="${sessionScope.CART.birdPairList}" var="birdPair">
                            <c:set var="count" value="${count + 1}"/>
                            <div class="card rounded-3 mb-4">
                                <div class="card-body p-4">
                                    <div class="row d-flex justify-content-between align-items-center">
                                        <div class="pl-3 text-danger">
                                            <h4><c:out value="${count}"/></h4>
                                        </div>
                                        <div class="col-md-2 col-lg-2 col-xl-2" style="text-align: center;">
                                            <c:choose>
                                                <c:when test="${not empty birdPair.value.birdCustomer}">
                                                    <img src="${birdPair.value.birdCustomer.img_url}" class="img-fluid rounded-3 mb-3" alt="${birdPair.value.birdCustomer.name}"" style="height: 150px; width: 120px;">
                                                    <img src="${birdPair.value.birdShop.image_url}" class="img-fluid rounded-3" alt="${birdPair.value.birdShop.bird_name}" style="height: 150px; width: 120px;">
                                                </c:when>
                                                <c:otherwise>
                                                    <img src="${birdPair.value.birdMale.image_url}" class="img-fluid rounded-3 mb-3" alt="${birdPair.value.birdMale.bird_name}" style="height: 150px; width: 120px;">
                                                    <img src="${birdPair.value.birdFemale.image_url}" class="img-fluid rounded-3" alt="${birdPair.value.birdFemale.bird_name}" style="height: 150px; width: 120px;">
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        <div class="col-md-3 col-lg-3 col-xl-3">
                                            <c:choose>
                                                <c:when test="${not empty birdPair.value.birdCustomer}">
                                                    <p class="lead fw-bold mb-2" style="font-size: 23px;">Phí nhân giống ban đầu</p>
                                                    <span>(${birdPair.value.birdCustomer.name} với ${not empty birdPair.value.birdMale ?  birdPair.value.birdMale.bird_name : birdPair.value.birdFemale.bird_name})</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <p class="lead fw-bold mb-2" style="font-size: 23px;">Phí nhân giống ban đầu</p>         
                                                    <span>(${birdPair.value.birdMale.bird_name} với ${birdPair.value.birdFemale.bird_name})</span>
                                                </c:otherwise>
                                            </c:choose>               
                                        </div>
                                        <div class="col-md-2 col-lg-2 col-xl-2 d-flex">
                                            <input id="form1" min="1" disabled="" name="quantity" value="1" type="number"
                                                   class="form-control form-control-sm" style="text-align: center; height: 40px; border: 1px solid; font-size: 16px;"/>
                                        </div>
                                        <div class="col-md-3 col-lg-2 col-xl-2 offset-lg-1">                                         
                                            <h5 class="mb-0" style="font-weight: bold; display: inline-block"><fmt:formatNumber value="${birdPair.value.servicePrice}" pattern="#,###"/> ₫</h5>
                                        </div>
                                        <div class="col-md-1 col-lg-1 col-xl-1 text-end">
                                            <a onclick="return checkRemove(this)" href="MainController?action=RemoveBirdPairFromCart&bird_pair_id=${birdPair.key}" class="text-danger"><img src="assets/images/remove-button.png"/></i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                        <c:if test="${not empty sessionScope.CART.accessoryList}">
                            <div class="d-flex justify-content-between align-items-center mb-4">
                                <h3 class="fw-normal mb-0 text-black">Phụ kiện</h3>
                            </div><hr>
                        </c:if>
                        <c:forEach items="${sessionScope.CART.accessoryList}" var="accessory">
                            <c:set var="count" value="${count + 1}"/>
                            <div class="card rounded-3 mb-4">
                                <div class="card-body p-4">
                                    <div class="row d-flex justify-content-between align-items-center">
                                        <div class="pl-3 text-danger">
                                            <h4><c:out value="${count}"/></h4>
                                        </div>
                                        <div class="col-md-2 col-lg-2 col-xl-2" style="text-align: center;">
                                            <img src="${accessory.value.accessory.image_url}" class="img-fluid rounded-3" alt="Phụ kiện" style="height: 150px; width: 120px;">
                                        </div>
                                        <div class="col-md-3 col-lg-3 col-xl-3">
                                            <p class="lead fw-bold mb-2" style="font-size: 23px;">${accessory.value.accessory.accessory_name}</p>
                                            <c:forEach var="category" items="${requestScope.CATEGORY_LIST}">
                                                <c:if test="${accessory.value.accessory.category_id == category.category_id}">
                                                    <p><span class="text-muted">Loại phụ kiện: </span>${category.category_name}</p>
                                                    <c:if test="${accessory.value.free_order > 0}">
                                                        <p>Tặng kèm ${accessory.value.free_order}</p>
                                                    </c:if>
                                                    <c:choose>
                                                        <c:when test="${accessory.value.accessory.discount > 0 && accessory.value.order_quantity > 0}">          
                                                            <p style="color: red;"><p id="accessory-${accessory.key}" class="d-inline">${accessory.value.order_quantity}</p> x <p class="d-inline"><fmt:formatNumber value="${accessory.value.accessory.unit_price - accessory.value.accessory.unit_price * accessory.value.accessory.discount / 100}" pattern="#,###"/> ₫</p></p>
                                                        </c:when>

                                                        <c:when test="${accessory.value.accessory.discount == 0 && accessory.value.order_quantity > 0}">
                                                            <p class="mb-0"><p id="accessory-${accessory.key}" class="d-inline">${accessory.value.order_quantity}</p> x <p class="d-inline"><fmt:formatNumber value="${accessory.value.accessory.unit_price}" pattern="#,###"/> ₫</p></p>
                                                        </c:when>
                                                    </c:choose> 
                                                </c:if>
                                            </c:forEach>

                                        </div>
                                        <div class="col-md-2 col-lg-2 col-xl-2 d-flex">
                                            <button class="btn btn-link px-2" 
                                                    onclick="DownQuantityAccessory(this, '${accessory.key}', '${accessory.value.free_order}')">
                                                <img src="assets/images/decrease-button.png"/>
                                            </button>

                                            <input id="form1" min="1" disabled="" name="quantity" value="${accessory.value.order_quantity + accessory.value.free_order}" type="number"
                                                   class="form-control form-control-sm" style="text-align: center; height: 40px; border: 1px solid; font-size: 16px;"/>

                                            <button class="btn btn-link px-2"
                                                    onclick="UpQuantityAccessory(this, '${accessory.key}')">
                                                <img src="assets/images/increase-button.png"/>
                                            </button>
                                        </div>
                                        <div class="col-md-3 col-lg-2 col-xl-2 offset-lg-1">
                                            <c:choose>
                                                <c:when test="${accessory.value.accessory.discount > 0 &&  accessory.value.free_order == 0}">                               
                                                    <h5 style="display: inline-block; border-radius: 10px; background-color: #cccccc; padding: 0 5px 0 5px; color: black;"> -${accessory.value.accessory.discount}%</h5>
                                                    <h5 id="price-${accessory.key}" style="font-size: 20px; color: red;"><fmt:formatNumber value="${(accessory.value.accessory.unit_price - (accessory.value.accessory.unit_price * accessory.value.accessory.discount / 100)) * accessory.value.order_quantity}" pattern="#,###"/> ₫</h5>
                                                </c:when>
                                                 <c:when test="${accessory.value.order_quantity == 0}">                               
                                                    <h5 class="mb-0" style="font-weight: bold; display: inline-block">Tặng kèm</h5>
                                                </c:when>
                                                 <c:when test="${accessory.value.accessory.discount > 0 &&  accessory.value.free_order > 0}">                               
                                                    <h5 id="price-${accessory.key}" class="mb-0" style="font-weight: bold; display: inline-block"><fmt:formatNumber value="${(accessory.value.accessory.unit_price - (accessory.value.accessory.unit_price * accessory.value.accessory.discount / 100)) * accessory.value.order_quantity}" pattern="#,###"/> ₫</h5>
                                                </c:when>
                                                <c:otherwise>
                                                    <h5 id="price-${accessory.key}" class="mb-0" style="font-weight: bold; display: inline-block"><fmt:formatNumber value="${accessory.value.accessory.unit_price *accessory.value.order_quantity}" pattern="#,###"/> ₫</h5>
                                                </c:otherwise>
                                            </c:choose> 
                                        </div>

                                        <div class="col-md-1 col-lg-1 col-xl-1 text-end">
                                            <c:if test="${accessory.value.free_order == 0}">
                                                 <a onclick="return checkRemove(this)" href="MainController?action=RemoveAccessoryFromCart&accessory_id=${accessory.key}" class="text-danger"><img src="assets/images/remove-button.png"/></i></a>
                                            </c:if>
                                        </div>          

                                    </div>
                                </div>
                            </div>
                        </c:forEach>

<!--                        <div class="card mb-4">
                            <div class="card-body p-4 d-flex flex-row">
                                <div class="form-outline flex-fill">
                                    <input type="text" id="form1" class="form-control form-control-lg" placeholder="Nhập mã khuyến mãi" />
                                </div>
                                <button type="button" class="btn btn-outline-warning btn-lg ms-3">Áp dụng</button>
                            </div>
                        </div><hr>-->
                        <h3 style="text-align: right">Tổng cộng</h3>
                        <h4 id="price-cart" style="text-align: right"><fmt:formatNumber value="${sessionScope.CART.cartTotalPrice}" pattern="#,###"/> ₫</h4>

                        <div class="card">
                            <div class="card-body">
                                <form action="MainController">
                                    <input type="hidden" name="action" value="NavToCheckout"/>
                                    <button type="submit" class="btn btn-warning btn-block btn-lg">Tiến hành thanh toán</button>
                                </form>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </section>
        <section id="confirm-remove" class="container-fluid">
            <div class="vh-100 row">
                <div class="h-100 m-auto d-flex align-items-center">
                    <div class="box-remove bg-white p-4">
                        <div class="d-flex align-items-center justify-content-between">
                            <h4>Xóa sản phẩm</h4>
                            <div onclick="cancelRemove()" class="btn-close">
                                <i class="fa fa-times" aria-hidden="true"></i>
                            </div>
                        </div> 
                        <p class="mb-4 mt-4">
                            Bạn có muốn xóa sản phẩm đang chọn không ?
                        </p>
                        <div class="float-right">
                            <a id="button-confirm" href="#"><button class="btn btn-group-sm btn-primary">Xác nhận</button></a>
                            <button onclick="cancelRemove()" class="btn btn-group-sm btn-secondary">Hủy</button>
                        </div>
                    </div>
                </div>
            </div>
        </section>
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
            });
            function checkRemove(event) {
                $('#confirm-remove').css('display', 'block');
                $('#button-confirm').attr('href', event.href);
                return false;
            }
            function cancelRemove() {
                $('#confirm-remove').css('display', 'none');
            }
            function clearCart(event) {
                $('#confirm-remove').css('display', 'block');
                $('#button-confirm').attr('href', event.href);
                return false;
            }
            function DownQuantityAccessory(event, accessory_id, min) {
                let numberAccessory = event.parentNode.querySelector('input[type=number]');
                let number = Number(numberAccessory.value);
                if (number == min || number == 1) {
                    return;
                } else {
                    $.ajax({
                        url: 'UpdateAccessoryCartController',
                        type: 'POST',
                        data: {accessory_id: accessory_id, type: 'down'},
                        success: function (data) {
                            if(data){
                                const json = JSON.parse(data);
                                numberAccessory.stepDown();
                                let price = Number(json.totalPrice).toLocaleString('vi-VN');
                                $('#price-cart').html(price + ' ₫');
                                $('.cart-amount').html(json.quantity);
                                $('#accessory-'+accessory_id).html(json.totalAccessory);
                                $('#price-'+accessory_id).html(Number(json.totalPriceAccessory).toLocaleString('vi-VN')+"₫");
                            }
                        }
                    });
                }
            }
            function UpQuantityAccessory(event, accessory_id) {
                let numberAccessory = event.parentNode.querySelector('input[type=number]');
                if (numberAccessory) {
                    $.ajax({
                        url: 'UpdateAccessoryCartController',
                        type: 'POST',
                        data: {accessory_id: accessory_id, type: 'up'},
                        success: function (data) {
                            if (data) {
                                numberAccessory.stepUp();
                                const json = JSON.parse(data);
                                let price = Number(json .totalPrice).toLocaleString('vi-VN');
                                $('#price-cart').html(price + ' ₫');
                                $('.cart-amount').html(json.quantity);
                                $('#accessory-'+accessory_id).html(json.totalAccessory);
                                $('#price-'+accessory_id).html(Number(json.totalPriceAccessory).toLocaleString('vi-VN')+"₫");
                            } else {
                                toast({title: 'Lỗi', message: 'Sản phẩm không đủ số lượng', type: 'error', duration: 3000});
                            }
                        }
                    });
                }
            }
        </script>
    </body>
</html>
