<%-- 
   Document   : accessories
   Created on : Sep 13, 2023, 11:20:56 PM
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

        <title>V.E.T - Tổ chim non</title>

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
                height: 50px; /* Set the height of the box */
                display: flex;
                justify-content: center;
                align-items: center;
                z-index: 3;
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
                            <h2>Tổ chim non</h2>
                            <span>Những chú vẹt con đáng yêu đang trong quá trình ấp nở và nuôi dưỡng tại V.E.T </span>
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
                                <a href="MainController?action=NavToNewBirdNest"><span class="btn-success">Thêm mới chim</span></a>
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
                                <div class="type">Giống vẹt</div>
                                <ol class="d-block" id="typeList-1">   
                                    <li class="ml-2"><input type="radio" id="type-0" name="txtBreedId" ${requestScope.BREED_ID == null ? "checked":""} value="All"><label for="type-0">Tất cả</label></li>
                                        <c:forEach var="breed" items="${requestScope.BREEDLIST}" varStatus="counter">
                                        <li class="ml-2"><input type="radio" id="type-${counter.count}" name="txtBreedId" value="${breed.breed_id}" ${requestScope.BREED_ID == breed.breed_id ? "checked":""}><label for="type-${counter.count}">${breed.breed_name}</label></li>
                                        </c:forEach>
                                </ol>
                                <div class="type">Giá bán</div>
                                <ol class ="d-block" id="typeList-2">
                                    <li><input type="radio" ${requestScope.PRICE == null  ? "checked":""} id="type-65" name="txtPrice" value="All"><label for="type-65">Tất cả</label></li>
                                    <li><input type="radio" ${requestScope.PRICE == "price < 5000000" ? "checked":""} id="type-6" name="txtPrice" value="price < 5000000"><label for="type-6">Dưới 5,000,000₫</label></li>
                                    <li><input type="radio" ${requestScope.PRICE == "price >= 5000000 AND price <= 20000000" ? "checked":""} id="type-7" name="txtPrice" value="price >= 5000000 AND price <= 20000000"><label for="type-7">5,000,000₫ - 20,000,000₫</label></li>
                                    <li><input type="radio" ${requestScope.PRICE == "price > 20000000" ? "checked":""} id="type-8" name="txtPrice" value="price > 20000000"><label for="type-8">Trên 20,000,000₫</label></li>
                                </ol>
                            </div>
                            <div class="col-md-9 col-xs-12" style="padding: 0 0 1px 0  ">
                                <div class="row mx-1">
                                    <c:choose>
                                        <c:when test="${not empty requestScope.BIRD_NEST_LIST}">
                                            <c:forEach items="${requestScope.BIRD_NEST_LIST}" var="nest">
                                                <div class="col-xl-4 col-md-6 col-sm-6">
                                                    <div class="card mt-1 mb-3 card-custom">
                                                        <div class="thumb-img">
                                                            <img src="${nest.image_url}" class="card-img-top" alt="${nest.nest_name}">
                                                            <c:if test="${nest.discount > 0}">
                                                                <span class="tag-discount"> -${nest.discount}%</span>
                                                            </c:if>
                                                            <div class="hover-card">
                                                                <ul class="d-flex justify-content-center">
                                                                    <li>
                                                                        <a href="RenderBirdNestDetailsController?id=${nest.nest_id}">
                                                                            <i class="fa fa-eye"></i>
                                                                        </a>
                                                                    </li>
                                                                    <c:if test="${(sessionScope.LOGIN_USER == null || sessionScope.LOGIN_USER.role == 'customer')}">
                                                                        <c:if test="${nest.status == 'Còn hàng'}">
                                                                            <li><a style="cursor: pointer" class="accessory-cart" data-value="${nest.nest_id}" href="MainController?action=AddBirdNestToCart&nest_id=${nest.nest_id}"><i class="fa fa-shopping-cart"></i></a></li>
                                                                                </c:if>
                                                                            </c:if>
                                                                </ul>
                                                            </div>
                                                            <c:if test="${nest.status == 'Đã bán'}">
                                                                <div class="overlay-text">Đã bán</div>
                                                            </c:if>
                                                        </div>
                                                        <div class="card-body">
                                                            <h5 class="card-title font-weight-bold text-center">${nest.nest_name}</h5>
                                                            <div class="card-text text-center">
                                                                <c:choose>
                                                                    <c:when test="${nest.discount > 0}">
                                                                        <span class="d-block"><del><fmt:formatNumber value="${nest.price}" pattern="#,###"/> ₫</del></span>
                                                                        <span class="d-block" style="font-size: 1.2rem; color: red;">Giá: <fmt:formatNumber value="${nest.price - nest.price * nest.discount / 100}" pattern="#,###"/> ₫</span>
                                                                    </c:when>                                                    
                                                                    <c:otherwise>
                                                                        <span style="font-size: 1.2rem;">Giá: <fmt:formatNumber value="${nest.price}" pattern="#,###"/> ₫</span>
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
                    $("#selectNest").submit();
                });
                $("input[name=txtPrice]").change(function () {
                    $("#selectNest").submit();
                });
                $(".prev-page").click(function () {
                    let  page = $('input[name=page]').val();
                    let prevPage = Number(page) - 1;
                    $('input[name=page]').val(prevPage);
                    $("#selectBird").submit();
                });
                $(".next-page").click(function () {
                    let  page = $('input[name=page]').val();
                    let nextpage = Number(page) + 1;
                    $('input[name=page]').val(nextpage);
                    $("#selectBird").submit();
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