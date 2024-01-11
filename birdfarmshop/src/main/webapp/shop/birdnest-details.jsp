<%-- 
    Document   : accessory-details
    Created on : Sep 13, 2023, 11:22:17 PM
    Author     : tlminh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="a" value="${a}" />

<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" type="image/png" href="assets/images/logo-title-bar.png"/>
        <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap" rel="stylesheet">

        <title>V.E.T - Chi tiết tổ chim non</title>


        <!-- Additional CSS Files -->
        <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/font-awesome.css">
        <link rel="stylesheet" href="assets/css/templatemo-hexashop.css">
        <link rel="stylesheet" href="assets/css/owl-carousel.css">
        <link rel="stylesheet" href="assets/css/lightbox.css">
        <style>
            .col-lg-12 a {
                border-radius: 10px;
                border: 1px solid rgb(221, 221, 227);
                background-color: #f5c6cb;
                display: flex;
                justify-content: center;
                align-items: center;
                margin: 0 auto;
                width: 50%;
                margin-bottom: 30px;
                margin-top: 20px;
                width: 150px;
                float: right;
            }
            .image-container {
                display: flex;
                align-items: center;
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

            .image-bottom img {
                width: 154px;
                height: 185px;
                display: block;
                margin-bottom: 15px;
                border: 1px solid #000;
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
            pre {
                width: 100%;
                white-space: pre-wrap;
                overflow-y: auto;
                max-height: 200px;
                font-family: inherit;
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
                            <h2>Chi tiết tổ chim non</h2>
                            <span>Tìm hiểu về sản phẩm và sắm ngay cho chú vẹt cưng của bạn!</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- ***** Main Banner Area End ***** -->


        <!-- ***** Product Area Starts ***** -->
        <c:set var="birdNest" value="${NEST}" />


        <section class="section" id="product" style="margin-top: 30px; margin-bottom: 75px;">
            <div class="container">
                <c:if test="${LOGIN_USER.role == 'admin' || LOGIN_USER.role == 'manager'}">
                    <div class="row" style="margin-bottom: 15px;">
                        <div class="col-lg-12">
                            <button class="btn-primary button-form" style="float: right;"><a class="btn-update" href="MainController?action=NavToUpdateBirdNest&id=${birdNest.nest_id}"><span>Cập nhật tổ chim</span></a></button>
                        </div>
                    </div>
                </c:if>
                <div class="row">
                    <div class="col-lg-7">                    
                        <div class="row">
                            <c:if test="${birdNest != null}">
                                <div class="col-lg-7">
                                    <c:set var="image_url_arr" value="${birdNest.image_url_arr}" />
                                    <div class="image-container">
                                        <div class="left-image">
                                            <img id="mainImage" style="width: 370px; height: 444px;" src="${IMG}" alt="Image 1" onclick="swapImages(this)">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-5">
                                    <div class="image-bottom">
                                        <c:forEach items="${IMG_URL}" var="img_url">
                                            <c:if test="${img_url ne IMG}">
                                                <img src="${img_url}" alt="" onclick="swapImages(this)"/>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </div>
                    <div class="col-lg-5">
                        <div class="right-content">
                            <h2 id="nameAccessory">${birdNest.nest_name}</h2>
                            <span style="font-size: 20px; color: red;"><fmt:formatNumber value="${birdNest.price}" pattern="#,###"/> ₫</span>
                            <div style="white-space: nowrap; margin-top: 10px;">
                                <h4 style="display: inline;">Chim bố: </h4>
                                <span style="display: inline; color: black;">${birdNest.dad_bird_id}</span>
                            </div>
                            <div style="white-space: nowrap; margin-top: 10px;">
                                <h4 style="display: inline;">Chim mẹ: </h4>
                                <span style="display: inline; color: black;">${birdNest.mom_bird_id}</span>
                            </div>
                            <div style="white-space: nowrap; margin-top: 10px;">
                                <h4 style="display: inline;">Chim non/trứng trong tổ: </h4>
                                <span style="display: inline; color: black;">${birdNest.baby_quantity}</span>
                            </div>
                            <div style="margin-top: 10px;">
                                <h4>Mô tả chi tiết: </h4>
                                <pre style="margin-top: 10px; color: black;">${birdNest.description}</pre>
                            </div>
                        </div>
                        <div class="total">
                            <c:if test="${(sessionScope.LOGIN_USER == null || sessionScope.LOGIN_USER.role == 'customer') && bird.status != 'Đã bán'}">
                                <div class="total" style="margin-top: 30px;">
                                    <div class="main-border-button">
                                        <a class="bird-cart btn-primary" style="cursor: pointer; color: white; border: 0px; border-radius: 20px;" >Thêm vào giỏ hàng</a>
                                    </div>
                                </div>
                            </c:if>
                            <div style="clear: both;"></div>
                        </div>



                    </div>
                </div>
            </div>
        </div>
    </div>
</section> 
<%@include file="../layout/feedback.jsp" %>
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

    function incrementQuantity(inputId, maxQuantity, unitPrice)
    {
        var quantityInput = document.getElementById(inputId);
        var currentValue = parseInt(quantityInput.value);

        var total1 = document.getElementById("total");
        var warning = document.getElementById("warning");
        if (!isNaN(currentValue) && currentValue < maxQuantity) {
            quantityInput.value = currentValue + 1;
            total1.innerHTML = unitPrice * quantityInput.value + " ₫";

            warning.innerHTML = "";
        } else {
            //alert("Số lượng không đủ!");
            warning.innerHTML = "Số lượng không đủ!";
        }
    }


    function decrementQuantity(inputId, unitPrice) {
        var quantityInput = document.getElementById(inputId);
        var currentValue = parseInt(quantityInput.value);
        var total = document.getElementById("total");
        var warning = document.getElementById("warning");
        if (!isNaN(currentValue) && currentValue > 0) {
            quantityInput.value = currentValue - 1;
            total.innerHTML = unitPrice * quantityInput.value + " ₫";
            warning.innerHTML = "";
        }
    }

    function formatNumber(n) {
        // format number 1000000 to 1.234.567
        return n.replace(/\D/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ".");
    }

    function ConvertToNumber(priceStr) {
        var priceParts = priceStr.split(".");
        var price = "";
        for (var i = 0; i < priceParts.length; i++) {
            price += priceParts[i];
        }
        return Number.parseInt(price);
    }


    function updateTotal() {
        var unitPrice = document.getElementById("unit_price");
        var quantityInput = document.getElementById("quantityInput"); // Lấy phần tử input số lượng

        console.log(unitPrice);
        console.log(quantityInput);
    }


    function addToCart() {
        var name = document.getElementById("nameAccessory");
        var quantity = document.getElementById("quantityInput");
        var total = document.getElementById("total");
        sessionStorage.setItem("name", JSON.stringify(name));
        sessionStorage.setItem("quantity", JSON.stringify(quantity));
        sessionStorage.setItem("total", JSON.stringify(total));

    }

    function swapImages(clickedImage) {
        const leftImage = document.querySelector('.left-image img');
        const rightImage1 = document.querySelectorAll('.right-images img')[0];
        const rightImage2 = document.querySelectorAll('.right-images img')[1];

        const tempSrc = leftImage.src;
        leftImage.src = clickedImage.src;
        clickedImage.src = tempSrc;
    }

</script>

</body>


</html>

