<%-- 
    Document   : account-list
    Created on : Sep 13, 2023, 11:30:02 PM
    Author     : tlminh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" type="image/png" href="assets/images/logo-title-bar.png"/>
        <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap" rel="stylesheet">

        <title>V.E.T - Danh sách nhân giống</title>

        <!-- Additional CSS Files -->
        <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/font-awesome.css">
        <link rel="stylesheet" href="assets/css/templatemo-hexashop.css">
        <link rel="stylesheet" href="assets/css/owl-carousel.css">
        <link rel="stylesheet" href="assets/css/lightbox.css">
        <style>
            .col-md-4 button{
                font-size: 15px;
                border-radius: 4px;
                background-color: green;
                padding: 5px;
                margin-left: 5px;
            }
            .col-md-4 a{
                color: white;
            }
            tr th{
                text-align: center;
            }
            tr td {
                text-align: center;
                vertical-align: middle!important;
            }
            td img{
                height: 150px;
                width: 150px;
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
                            <h2>Danh sách nhân giống</h2>
                            <span>Thông tin các đơn nhân giống</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- ***** Main Banner Area End ***** -->
        <main class="py-5 mb-5" style="width: 80%; margin: 0 auto;">
            <div class="mt-3">
                <div class="row">
                    <div class="col-md-6 my-2 w-100 mx-auto py-5">
                        <div class="input-group">
                            <form class="d-flex w-100" action="RenderBirdPairShopController" method="POST">
                                <div class="input-group-prepend">
                                    <button class="input-group-text"><img src="assets/images/search-icon.png"></button>
                                </div>                  
                                <input type="text" class="form-control" id="searchInput" name="search" placeholder="Tìm kiếm..." value="${param.search}">
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>STT</th>
                        <th>Tài khoản</th>
                        <th>Ngày tạo</th>
                        <th>Trạng thái</th>
                        <th>Chim Trống</th>
                        <th>Chim mái</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty requestScope.BIRDPAIRLIST}">
                            <c:forEach var="birdPair" items="${requestScope.BIRDPAIRLIST}" varStatus="counter">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${birdPair.username}</td>
                                    <td><fmt:formatDate value="${birdPair.create_date}" pattern="dd-MM-yyyy HH:mm"/></td>
                                    <td>${birdPair.status}</td>
                                    <c:choose>
                                        <c:when test="${not empty birdPair.birdCustomer}">
                                            <td> <img src="${birdPair.birdCustomer.img_url}" alt="${birdPair.birdCustomer.name}"/></td>
                                                <c:choose>
                                                    <c:when test="${not empty birdPair.male_bird}">
                                                    <td><img src="${birdPair.male_bird.image_url}" alt="${birdPair.male_bird.bird_name}"/></td>
                                                    </c:when>
                                                    <c:otherwise>
                                                    <td><img src="${birdPair.female_bird.image_url}" alt="${birdPair.female_bird.bird_name}"/></td>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                            </div>   
                                        </c:when>
                                        <c:otherwise>
                                            <td><img src="${birdPair.male_bird.image_url}" alt="${birdPair.male_bird.bird_name}"/></td>
                                            <td><img src="${birdPair.female_bird.image_url}" alt="${birdPair.female_bird.bird_name}"/></td>
                                            </c:otherwise>
                                        </c:choose>

                                            <td><a href="MainController?action=NavToBirdPairDetailShop&pair_id=${birdPair.pair_id}"><button class="btn btn-primary">Chi tiết</button></a></td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                 Không có sản phẩm nào
                            </tr>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
            <c:if test="${noOfPages != 1}">
                <div class="d-flex float-right align-items-center page-pagination">
                    <c:set var="numberOfPage" value="${requestScope.currentPage}"/>
                    <input type="hidden" name="numberOfPage" value="${requestScope.noOfPages}"/>
                    <input type="hidden" name="page" value="${numberOfPage}"/>
                    <c:if test="${noOfPages >= 1 && noOfPages <= 5}">  
                        <div onclick="PrevPage()" class="page-prev ${numberOfPage <= 1 ? "page-disable":""}"><i class="fa fa-angle-left" aria-hidden="true"></i></i></div>
                        <c:forEach begin="1" end="${noOfPages}" var="i">
                            <div onclick="NavToNewPage(this)" class="page-navTo ${i == requestScope.currentPage ? "page-active":""}" data-value="${i}">${i}</div>       
                        </c:forEach>
                        <div onclick="NextPage()" class="page-next ml-3 ${numberOfPage >= noOfPages ? "page-disable":""}"><i class="fa fa-angle-right" aria-hidden="true"></i></i></div>
                    </c:if>    
                    <c:if test="${noOfPages > 5}">
                        <c:if test="${numberOfPage > 2}">
                            <div onclick="PrevPage()" class="page-prev ${numberOfPage <= 1 ? "page-disable":""}"><i class="fa fa-angle-left" aria-hidden="true"></i></i></div>
                            <c:if test="${numberOfPage + 2 < noOfPages}">
                                <c:set var="beginItem" value="${numberOfPage - 2 }"/>
                                <c:set var="endItem" value="${numberOfPage + 2 }"/>
                            </c:if>
                            <c:if test="${numberOfPage + 2 >= noOfPages}">
                                <c:set var="beginItem" value="${noOfPages - 4}"/>
                                <c:set var="endItem" value="${noOfPages}"/>
                            </c:if>
                            <c:forEach begin="${beginItem}" end="${endItem}" var="i">
                                <div onclick="NavToNewPage(this)" class="page-navTo ${i == requestScope.currentPage ? "page-active":""}" data-value="${i}">${i}</div>      
                            </c:forEach>
                            <div onclick="NextPage()" class="page-next ml-3 ${numberOfPage >= noOfPages ? "page-disable":""}"><i class="fa fa-angle-right" aria-hidden="true"></i></i></div>
                        </c:if>
                        <c:if test="${numberOfPage <= 2}">
                            <div onclick="PrevPage()" class="page-prev ${numberOfPage <= 1 ? "page-disable":""}"><i class="fa fa-angle-left" aria-hidden="true"></i></i></div>
                            <c:forEach var="i" begin="1" end="5">
                                <div onclick="NavToNewPage(this)" class="page-navTo ${i == requestScope.currentPage ? "page-active":""}" data-value="${i}">${i}</div>           
                            </c:forEach>                   
                            <div onclick="NextPage()" class="page-next ml-3 ${numberOfPage >= noOfPages ? "page-disable":""}"><i class="fa fa-angle-right" aria-hidden="true"></i></i></div>
                        </c:if>        
                    </c:if>
                </div>
            </c:if>
        </main>
        <!-- jQuery -->
        <%@include file="../layout/message.jsp" %>
        <script src="assets/js/jquery-3.7.1.min.js"></script>
        <script src="assets/js/jquery.validate.min.js"></script>
        <script src="assets/js/birdshop.js"></script>
        <!-- Bootstrap -->
        <script src="assets/js/popper.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>

        <!-- Plugins -->
        <script src="assets/js/owl-carousel.js"></script>
        <script src="assets/js/accordions.js"></script>
        <script src="assets/js/datepicker.js"></script>
        <script src="assets/js/scrollreveal.min.js"></script>
        <script src="assets/js/imgfix.min.js"></script> 
        <script src="assets/js/slick.js"></script> 
        <script src="assets/js/lightbox.js"></script> 
        <script src="assets/js/isotope.js"></script> 

        <!-- Global Init -->
        <script src="assets/js/custom.js"></script>

        <script>

                                // Show the modal when the "Cấp mới tài khoản" button is clicked
                                $("#createAccountBtn").click(function () {
                                    $("#createAccountModal").modal("show");
                                });
                                $('.reset').click(function () {
                                    $('input[name=fullname]').val("");
                                    $('input[name=username]').val("");
                                });
                                $('#btn-confirrm').click(function () {
                                    let idForm = $(this).attr('data-value');
                                    $('#' + idForm).submit();
                                    return true;
                                });
                                function NavToNewPage(event) {
                                    let page = $(event).attr('data-value');
                                    if (page) {
                                        window.location.href = "MainController?action=NavToAccounts&page=" + page;
                                    }
                                }

                                function PrevPage() {
                                    let page = $('input[name=page]').val();
                                    let prevPage = Number(page) - 1;
                                    if (prevPage > 0) {
                                        window.location.href = "MainController?action=NavToAccounts&page=1";
                                    }
                                }

                                function NextPage() {
                                    let page = $('input[name=page]').val();
                                    let limitPage = $('input[name=numberOfPage]').val();
                                    let amountPage = Number(limitPage);
                                    let nextPage = Number(page) + 1;
                                    if (nextPage <= amountPage) {
                                        window.location.href = "MainController?action=NavToAccounts&page=" + amountPage;
                                    }
                                }
                                function checkUser(event) {
                                    $('#confirm-remove').css('display', 'block');
                                    let idForm = event.form.id;
                                    $('#btn-confirrm').attr('data-value', idForm);
                                    return false;
                                }
                                function cancelRemove() {
                                    $('#confirm-remove').css('display', 'none');
                                }
        </script>
    </body>
</html>
