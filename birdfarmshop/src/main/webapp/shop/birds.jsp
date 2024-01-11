<%-- 
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

        <title>V.E.T - Vẹt Cảnh</title>

        <!-- Additional CSS Files -->
        <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/font-awesome.css">
        <link rel="stylesheet" href="assets/css/templatemo-hexashop.css">
        <link rel="stylesheet" href="assets/css/owl-carousel.css">
        <link rel="stylesheet" href="assets/css/lightbox.css">
        <style>
            #counter-box {
                position: fixed;
                bottom: 20px; /* Adjust the distance from the bottom as needed */
                right: 20px; /* Adjust the distance from the right as needed */
                background-color: #6ac8b6; /* Background color of the counter box */
                color: #fff; /* Text color */
                border-radius: 5px; /* Makes the box circular */
                width: 200px; /* Set the width of the box */
                line-height: 50px; /* Set the height of the box */
                text-align: center;
                z-index: 3;
                display: none;
                cursor: pointer;
            }

            #compare-counter {
                font-size: 1.2rem;
            }
            .btn-success{
                padding: 15px;
                border-radius: 5px;
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
                            <h2>Vẹt cảnh</h2>
                            <span>Những chú vẹt thông minh và đáng yêu nhất đã có mặt tại V.E.T</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- ***** Main Banner Area End ***** -->


        <!-- ***** Products Area Starts ***** -->
        <div class="py-5 px-2 layout-item" style="border-bottom: none" id="top">
            <form id="selectBird" action="MainController" method="POST">
                <input type="hidden" name="action" value="NavToBird"> 
                <div class="d-flex">
                    <div class="col-md-4"></div>
                    <div class="col-md-4">
                        <div class="d-flex justify-content-center mb-4">
                            <div class="search-bar">
                                <img style="width: 15px; height: 15px;" src="assets/images/search.png"/>
                                <input type="text" name="txtBirdName" id="search" placeholder="Tìm kiếm" value="${requestScope.SEARCH}">
                                <input type="hidden" name="page" value="1" />
                                <input type="submit" value="Tìm kiếm">
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <c:if test="${sessionScope.LOGIN_USER.role == 'manager' || sessionScope.LOGIN_USER.role == 'admin'}">
                            <div>
                                <a href="MainController?action=AddNewBird"><span class="btn-success">Thêm mới chim</span></a>
                            </div>
                        </c:if>
                    </div>

                </div>
                <main class="my-5">
                    <div class="container-fluid">
                        <div class="row m-0">
                            <div class="col-md-3 col-xs-12">
                                <div class="d-flex justify-content-between">
                                    <h4>Phân loại theo</h4>
                                    <button name="clear" value="clear" class=" btn-sm btn-danger text-nowrap border-0">Bỏ lọc</button>
                                </div>
                                <div class="type">Loài</div>
                                <ol class="d-block" id="typeList-1">   
                                    <li class="ml-2"><input type="radio" id="type-0" name="txtBreedId" ${requestScope.BREED_ID == null ? "checked":""} value="All"><label style="margin-left: 5px;" for="type-0">Tất cả</label></li>
                                        <c:forEach var="breed" items="${requestScope.BREEDLIST}" varStatus="counter">
                                        <li class="ml-2"><input style="padding:15px;" type="radio" id="type-${counter.count}" name="txtBreedId" value="${breed.breed_id}" ${requestScope.BREED_ID == breed.breed_id ? "checked":""}><label style="margin-left: 5px;" for="type-${counter.count}">${breed.breed_name}</label></li>
                                        </c:forEach>
                                </ol>
                                <div class="type">Giá cả</div>
                                <ol class="d-block" id="typeList-2">   
                                    <li class="ml-2"><input type="radio" ${requestScope.PRICE == null  ? "checked":""} id="type-65" name="txtPrice" value="All"><label style="margin-left: 5px;" for="type-65">Tất cả</label></li>
                                    <li class="ml-2"><input type="radio" ${requestScope.PRICE == "price < 5000000" ? "checked":""} id="type-6" name="txtPrice" value="price < 5000000"><label style="margin-left: 5px;" for="type-6">Dưới 5,000,000₫</label></li>
                                    <li class="ml-2"><input type="radio" ${requestScope.PRICE == "price >= 5000000 AND price <= 20000000" ? "checked":""} id="type-7" name="txtPrice" value="price >= 5000000 AND price <= 20000000"><label style="margin-left: 5px;" for="type-7">5,000,000₫ - 20,000,000₫</label></li>
                                    <li class="ml-2"><input type="radio" ${requestScope.PRICE == "price > 20000000" ? "checked":""} id="type-8" name="txtPrice" value="price > 20000000"><label style="margin-left: 5px;" for="type-8">Trên 20,000,000₫</label></li>
                                </ol>
                                <div class="type">Giống</div>
                                <ol class="d-block" id="typeList-3">
                                    <li class="ml-2"><input type="radio" ${requestScope.GENDER == null ? "checked":""} id="type-95" name="txtGender" value="All"><label style="margin-left: 5px;" for="type-95">Tất cả</label></li>
                                    <li class="ml-2"><input type="radio" ${requestScope.GENDER == "1" ? "checked":""} id="type-9" name="txtGender" value="1"><label style="margin-left: 5px;" for="type-9">Trống</label></li>
                                    <li class="ml-2"><input type="radio" ${requestScope.GENDER == "0" ? "checked":""} id="type-10" name="txtGender" value="0"><label style="margin-left: 5px;" for="type-10">Mái</label></li>
                                </ol>
                                <div class="type">Tuổi</div>
                                <ol class="d-block" id="typeList-4">
                                    <li class="ml-2"><input type="radio" ${requestScope.AGE == null ? "checked":""}  id="type-115" name="txtAge" value="All"><label style="margin-left: 5px;" for="type-115">Tất cả</label></li>
                                    <li class="ml-2"><input type="radio" ${requestScope.AGE == "DATEDIFF(MONTH, birthday, GETDATE()) < 5" ? "checked":""} id="type-11" name="txtAge" value="DATEDIFF(MONTH, birthday, GETDATE()) < 5"><label style="margin-left: 5px;" for="type-11">Dưới 5 tháng</label></li>
                                    <li class="ml-2"><input type="radio" ${requestScope.AGE == "DATEDIFF(MONTH, birthday, GETDATE()) >= 5 AND DATEDIFF(MONTH, birthday, GETDATE()) <= 18" ? "checked":""} id="type-12" name="txtAge" value="DATEDIFF(MONTH, birthday, GETDATE()) >= 5 AND DATEDIFF(MONTH, birthday, GETDATE()) <= 18"><label style="margin-left: 5px;" for="type-12">Từ 5 - 18 tháng</label></li>
                                    <li class="ml-2"><input type="radio" ${requestScope.AGE == "DATEDIFF(MONTH, birthday, GETDATE()) > 18" ? "checked":""} id="type-13" name="txtAge" value="DATEDIFF(MONTH, birthday, GETDATE()) > 18"><label style="margin-left: 5px;" for="type-13">Trên 18 tháng</label></li>
                                </ol>
                            </div>
                            <div class="col-md-9 col-xs-12" style="padding: 0 0 1px 0  ">
                                <div class="row mx-1">
                                    <c:choose>
                                        <c:when test="${not empty requestScope.BIRDLIST}">
                                            <div id="counter-box">
                                                <div id="compare-counter">So sánh (0)</div>
                                            </div>
                                            <c:forEach var="bird" items="${requestScope.BIRDLIST}">
                                                <div class="col-xl-4 col-md-6 col-sm-6">
                                                    <div class="card mt-1 mb-3 card-custom">
                                                        <div class="thumb-img">
                                                            <img src="${bird.image_url}" class="card-img-top" alt="${bird.bird_name}">
                                                            <c:if test="${bird.discount > 0}">
                                                                <span class="tag-discount"> -${bird.discount}%</span>
                                                            </c:if>
                                                            <div class="hover-card">
                                                                <ul class="d-flex justify-content-center">
                                                                    <li>
                                                                        <a href="MainController?action=NavToBirdDetails&bird_id=${bird.bird_id}">
                                                                            <i class="fa fa-eye"></i>
                                                                        </a>
                                                                    </li>
                                                                    <c:if test="${(sessionScope.LOGIN_USER.role == 'customer') && bird.status == 'Còn hàng'}">
                                                                    <li bird-compare-id="${bird.bird_id}">
                                                                        <a class="bird-compare" data-bird-id="${bird.bird_id}">
                                                                            <img src="assets/images/compare.png" style="width: 16px; height: 16px;"/>
                                                                        </a>
                                                                    </li>
                                                                    </c:if>
                                                                    <c:if test="${(sessionScope.LOGIN_USER == null || sessionScope.LOGIN_USER.role == 'customer') && bird.status == 'Còn hàng'}">
                                                                        <li>
                                                                            <a class="bird-cart" data-value="${bird.bird_id}">
                                                                                <i class="fa fa-shopping-cart"></i>
                                                                            </a>
                                                                        </li>
                                                                    </c:if>
                                                                </ul>
                                                            </div>
                                                            <c:if test="${bird.status == 'Hết hàng'}">
                                                                <div class="overlay-text">Hết hàng</div>
                                                            </c:if>
                                                            <c:if test="${bird.status == 'Đang sinh sản'}">
                                                                <div class="overlay-text">Đang sinh sản</div>
                                                            </c:if>
                                                            <c:if test="${bird.status == 'Đang ghép cặp'}">
                                                                <div class="overlay-text">Đang ghép cặp</div>
                                                            </c:if>
                                                        </div>
                                                        <div class="card-body">
                                                            <h5 class="card-title font-weight-bold text-center">${bird.bird_name}</h5>
                                                            <div class="card-text text-center">
                                                                <c:choose>
                                                                    <c:when test="${bird.discount > 0}">
                                                                        <span class="d-block"><del><fmt:formatNumber value="${bird.price}" pattern="#,###"/> ₫</del></span>
                                                                        <span class="d-block" style="font-size: 1.2rem; color: red;">Giá: <fmt:formatNumber value="${bird.price - bird.price * bird.discount / 100}" pattern="#,###"/> ₫</span>
                                                                    </c:when>                                                    
                                                                    <c:otherwise>
                                                                        <span style="font-size: 1.2rem;">Giá: <fmt:formatNumber value="${bird.price}" pattern="#,###"/> ₫</span>
                                                                    </c:otherwise>
                                                                </c:choose> 
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <h4 class="m-auto">Không có sản phẩm nào</h4>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div id="products" class="row justify-content-center">
                                    <c:if test="${noOfPages != 1}">
                                        <div class="d-flex float-right align-items-center pagination">
                                            <c:set var="numberOfPage" value="${requestScope.currentPage}"/>
                                            <input type="hidden" name="numberOfPage" value="${requestScope.noOfPages}"/>
                                            <input type="hidden" name="page" value="${numberOfPage}"/>
                                            <c:if test="${noOfPages >= 1 && noOfPages <= 5}">  
                                                <ul class="d-flex">
                                                    <c:if test="${requestScope.currentPage > 1}">
                                                        <li>
                                                            <a class="start-page"><<</a>
                                                        </li>
                                                    </c:if>
                                                    <c:forEach begin="1" end="${noOfPages}" var="i">
                                                        <li class="${i == requestScope.currentPage ? "active":""}" >
                                                            <a data-value="${i}" onclick="takePage(this)">${i}</a>
                                                        </li>
                                                    </c:forEach>
                                                    <c:if test="${requestScope.currentPage < noOfPages}">
                                                        <li>
                                                            <a class="end-page" >>></a>
                                                        </li>
                                                    </c:if>
                                                </ul>
                                            </c:if>    
                                            <c:if test="${noOfPages > 5}">
                                                <ul class="d-flex">
                                                    <c:if test="${numberOfPage > 2}">
                                                        <c:if test="${requestScope.currentPage > 1}">
                                                            <li id="page">
                                                                <a class="start-page"><<</a>
                                                            </li>
                                                        </c:if>
                                                        <c:if test="${numberOfPage + 2 < noOfPages}">
                                                            <c:set var="beginItem" value="${numberOfPage - 2 }"/>
                                                            <c:set var="endItem" value="${numberOfPage + 2 }"/>
                                                        </c:if>
                                                        <c:if test="${numberOfPage + 2 >= noOfPages}">
                                                            <c:set var="beginItem" value="${noOfPages - 4}"/>
                                                            <c:set var="endItem" value="${noOfPages}"/>
                                                        </c:if>
                                                        <c:forEach begin="${beginItem}" end="${endItem}" var="i">
                                                            <li class="${i == requestScope.currentPage ? "active":""}">
                                                                <a data-value="${i}" onclick="takePage(this)">${i}</a>
                                                            </li>
                                                        </c:forEach>
                                                        <c:if test="${requestScope.currentPage < noOfPages}">
                                                            <li>
                                                                <a class="end-page" >>></a>
                                                            </li>
                                                        </c:if>
                                                    </c:if>
                                                    <c:if test="${numberOfPage <= 2}">
                                                        <c:if test="${requestScope.currentPage > 1}">
                                                            <li>
                                                                <a class="start-page"><<</a>
                                                            </li>
                                                        </c:if>
                                                        <c:forEach var="i" begin="1" end="5">
                                                            <li class="${i == requestScope.currentPage ? "active":""}">
                                                                <a data-value="${i}" onclick="takePage(this)">${i}</a>
                                                            </li>       
                                                        </c:forEach>                   
                                                        <c:if test="${requestScope.currentPage < noOfPages}">
                                                            <li>
                                                                <a class="end-page" >>></a>
                                                            </li>
                                                        </c:if>
                                                    </c:if>   
                                                </ul>
                                            </c:if>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </form>
        </div>
        <!-- ***** Products Area Ends ***** -->
        <!-- Start Footer -->
        <%@include file="../layout/footer.jsp" %>
        <!-- End Footer -->
        <script>
            var selectedBirds = [];
            <c:forEach items="${sessionScope.BIRDCOMAPRE}" var="item">
            selectedBirds.push("${item}");
            $('a[data-bird-id="${item}"]').addClass('active');
            $('a[data-bird-id="${item}"]').css("background-color", "#63b885");
            </c:forEach>
            if (selectedBirds.length > 0) {
                $("#compare-counter").html("So sánh (" + (selectedBirds.length) + ")");
                $('#counter-box').css('display', 'block');
            }
            // Add an event handler for the counter-box
            $("#counter-box").on("click", function () {
                var counter = $("#compare-counter");
                var countText = counter.text();
                var count = parseInt(countText.match(/\d+/));

                if (count > 1) {
                    // Create a form and submit it to the CompareBirdController
                    var form = $("<form>")
                            .attr("method", "post")
                            .attr("action", "MainController?action=NavToCompare");

                    for (var i = 0; i < selectedBirds.length; i++) {
                        form.append($("<input>")
                                .attr("type", "hidden")
                                .attr("name", "birdIds")
                                .attr("value", selectedBirds[i]));
                    }

                    form.appendTo("body").submit();
                } else {
                    toast({
                        title: 'Lỗi',
                        message: 'Vui lòng chọn thêm một vẹt để so sánh',
                        type: 'error',
                        duration: 3000
                    });
                }
            });

            // Add a click event handler to the bird-compare icon
            $(".bird-compare").on("click", function () {
                var birdId = $(this).data("bird-id");
                var counter = $("#compare-counter");
                var countText = counter.text();
                var count = parseInt(countText.match(/\d+/));
                $('#counter-box').css('display', 'block');
                // Check if the icon is active (toggled on)
                var isActive = $(this).hasClass("active");

                if (isActive) {
                    // Decrease the counter and remove the active class
                    counter.text("So sánh (" + (count - 1) + ")");
                    if (count - 1 === 0) {
                        $('#counter-box').css('display', 'none');
                    }
                    $(this).css("background-color", ""); // Remove background color
                    $(this).removeClass("active");
                    // Remove the bird ID from the selectedBirds array
                    var index = selectedBirds.indexOf(birdId);
                    if (index > -1) {
                        selectedBirds.splice(index, 1);
                        $.ajax({
                            url: "AddBirdCompareController",
                            type: 'POST',
                            data: {bird_id: birdId}
                        });
                    }
                } else {
                    if (count < 4) {
                        // Increase the counter and add the active class
                        counter.text("So sánh (" + (count + 1) + ")");
                        $(this).css("background-color", "#63b885"); // Set background color
                        $(this).addClass("active");
                        // Add the bird ID to the selectedBirds array
                        selectedBirds.push(birdId);
                        $.ajax({
                            url: "AddBirdCompareController",
                            type: 'POST',
                            data: {bird_id: birdId}
                        });
                    } else {
                        toast({
                            title: 'Lỗi',
                            message: 'Bạn chỉ có thể só sánh 4 vẹt',
                            type: 'error',
                            duration: 3000
                        });
                    }
                }

            });
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
                $("input[name=txtBreedId]").change(function () {
                    $("#selectBird").submit();
                });
                $("input[name=txtPrice]").change(function () {
                    $("#selectBird").submit();
                });
                $("input[name=txtGender]").change(function () {
                    $("#selectBird").submit();
                });
                $("input[name=txtAge]").change(function () {
                    $("#selectBird").submit();
                });
                $(".start-page").click(function () {
                    $('input[name=page]').val(1);
                    $("#selectBird").submit();
                });
                $(".end-page").click(function () {
                    let endPage = $('input[name=numberOfPage]').val();
                    $('input[name=page]').val(endPage);
                    $("#selectBird").submit();
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
            function takePage(event) {
                let value = event.getAttribute('data-value');
                $('input[name=page]').val(value);
                $("#selectBird").submit();
            }
        </script>
    </body>
</html>
