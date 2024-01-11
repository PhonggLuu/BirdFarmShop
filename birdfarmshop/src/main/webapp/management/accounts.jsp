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

        <title>V.E.T - Danh sách tài khoản</title>

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
                padding: 5px;
                margin-left: 5px;
                border:none;
            }
            .col-md-4 a{
                color: white;
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
                            <c:if test="${sessionScope.LOGIN_USER.role == 'manager'}">
                                <h2>Danh sách tài khoản nhân viên</h2>
                                <span>Thông tin tài khoản của tất cả nhân viên trên hệ thống V.E.T</span>
                            </c:if>
                            <c:if test="${sessionScope.LOGIN_USER.role == 'admin'}">
                                <h2>Danh sách tài khoản</h2>
                                <span>Thông tin tài khoản của tất cả người dùng trên hệ thống V.E.T</span>
                            </c:if>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- ***** Main Banner Area End ***** -->
        <main class="py-5 mb-5" style="width: 80%; margin: 0 auto;">
            <div class="mt-3">
                <div class="row">
                    <div class="col-md-4" style="margin-top: 10px;">
                        <c:if test="${sessionScope.LOGIN_USER.role == 'admin'}">
                            <a href="MainController?action=NavToAccounts&roles=all"><button class="btn-success ${param.roles == 'all' ? "active":"" || param.roles == null ? "active":""}">Tất cả</button></a>
                            <a href="MainController?action=NavToAccounts&roles=manager"><button class="btn-success ${param.roles == 'manager' ? "active":""}">Quản lý</button></a>
                            <a href="MainController?action=NavToAccounts&roles=staff"><button class="btn-success ${param.roles == 'staff' ? "active":""}">Nhân viên</button></a>
                            <a href="MainController?action=NavToAccounts&roles=customer"><button class="btn-success ${param.roles == 'customer' ? "active":""}">Khách hàng</button></a>
                        </c:if>
                    </div>

                    <div class="col-md-6 my-2">
                        <div class="input-group">
                            <form class="d-flex w-100" action="RenderAccountsController" method="POST">
                                <div class="input-group-prepend">
                                    <button class="input-group-text"><img src="assets/images/search-icon.png"></button>

                                </div>                  
                                <input type="text" class="form-control" id="searchInput" name="search" placeholder="Tìm kiếm..." value="${param.search}">
                            </form>
                        </div>
                    </div>
                    <div class="col-md-2 text-center">
                        <!-- Button "Cấp mới tài khoản" -->
                        <button class="btn btn-success mb-3 p-3" id="createAccountBtn">Cấp mới tài khoản</button>
                    </div>
                </div>
            </div>

            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>STT</th>
                        <th>Tên người dùng</th>
                        <th>Tên tài khoản</th>
                        <th>Nhóm người dùng</th>
                        <th>Trạng thái</th>
                        <th style="text-align: center;">Khóa tài khoản</th>
                        <th style="text-align: center;">Cấp lại mật khẩu</th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${sessionScope.LOGIN_USER.role == 'admin'}">
                        <c:forEach var="user" items="${requestScope.ACCOUNT_LIST}" varStatus="loop">
                            <tr>
                                <td>${loop.index + 1}</td>
                                <td><a href="MainController?action=NavToProfile&username=${user.username}">${user.fullName}</a></td>
                                <td>${user.username}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${user.role == 'admin'}">
                                            Quản trị viên
                                        </c:when>
                                        <c:when test="${user.role == 'manager'}">
                                            Quản lý
                                        </c:when>
                                        <c:when test="${user.role == 'staff'}">
                                            Nhân viên 
                                        </c:when>
                                        <c:otherwise>
                                            Khách hàng
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${user.status == 'active'}">
                                            Đang hoạt động 
                                        </c:when>
                                        <c:when test="${user.status == 'inactive'}">
                                            Chưa kích hoạt
                                        </c:when>
                                        <c:otherwise>
                                            Đã bị khóa  
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td style="text-align: center;">
                                    <c:if test="${user.role != 'admin'}">
                                        <c:choose>
                                            <c:when test="${user.status == 'active'}">
                                                <form id="lock-${loop.index + 1}" action="MainController" method="POST">
                                                    <input type="hidden" name="action" value="UpdateActivity"/>
                                                    <input type="hidden" name="username" value="${user.username}"/>
                                                    <input type="hidden" name="type" value="lock"/>
                                                    <button onclick="return checkUser(this)" type="submit" class="btn btn-danger">Khóa</button>
                                                </form>
                                            </c:when>
                                            <c:when test="${user.status == 'lock'}">
                                                <form id="op-${loop.index + 1}" action="MainController" method="POST">
                                                    <input type="hidden" name="action" value="UpdateActivity"/>
                                                    <input type="hidden" name="username" value="${user.username}"/>
                                                    <input type="hidden" name="type" value="open"/>
                                                    <button onclick="return checkUser(this)" type="submit" class="btn btn-danger">Mở Khóa</button>
                                                </form>
                                            </c:when>
                                        </c:choose>
                                    </c:if>
                                </td>
                                <td style="text-align: center;">
                                    <c:if test="${user.role != 'admin'}">
                                        <form id="rs-${loop.index + 1}" action="MainController" method="POST">
                                            <input type="hidden" name="action" value="UpdateActivity"/>
                                            <input type="hidden" name="username" value="${user.username}"/>
                                            <input type="hidden" name="type" value="reset"/>
                                            <button onclick="return checkUser(this)" class="btn btn-primary">Cấp lại MK</button>
                                        </form>     
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${sessionScope.LOGIN_USER.role == 'manager'}">
                        <c:set var="count" value="0"/>
                        <c:forEach var="userMG" items="${requestScope.ACCOUNT_LIST}">
                            <c:if test="${userMG.role == 'staff'}">
                                <tr>
                                    <c:set var="count" value="${count + 1}"/>
                                    <td>${count}</td>
                                    <td>${userMG.username}</td>
                                    <td>${userMG.fullName}</td>
                                    <td>
                                        <c:if test="${userMG.role == 'staff'}">
                                            Nhân viên
                                        </c:if>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${userMG.status == 'active'}">
                                                Đang hoạt động 
                                            </c:when>
                                            <c:when test="${userMG.status == 'inactive'}">
                                                Chưa kích hoạt
                                            </c:when>
                                            <c:otherwise>
                                                Đã bị khóa  
                                            </c:otherwise>
                                        </c:choose>   
                                    </td>
                                    <c:choose>
                                        <c:when test="${userMG.status == 'active'}">
                                            <td style="text-align: center;">
                                                <form id="lock-${count}" action="MainController" method="POST">
                                                    <input type="hidden" name="action" value="UpdateActivity"/>
                                                    <input type="hidden" name="username" value="${userMG.username}"/>
                                                    <input type="hidden" name="type" value="lock"/>
                                                    <button onclick="return checkUser(this)" type="submit" class="btn btn-danger">Khóa</button>
                                                </form>
                                            </td>
                                        </c:when>
                                        <c:when test="${userMG.status == 'lock'}">
                                            <td style="text-align: center;">
                                                <form id="op-${count}"  action="MainController" method="POST">
                                                    <input type="hidden" name="action" value="UpdateActivity"/>
                                                    <input type="hidden" name="username" value="${userMG.username}"/>
                                                    <input type="hidden" name="type" value="open"/>
                                                    <button onclick="return checkUser(this)" type="submit" class="btn btn-danger">Mở Khóa</button>
                                                </form>
                                            </td>
                                        </c:when>
                                        <c:otherwise>
                                            <td style="text-align: center;"></td>
                                        </c:otherwise>
                                    </c:choose>
                                    <td style="text-align: center;">
                                        <form id="rs-${count}" action="MainController" method="POST">
                                            <input type="hidden" name="action" value="UpdateActivity"/>
                                            <input type="hidden" name="username" value="${userMG.username}"/>
                                            <input type="hidden" name="type" value="reset"/>
                                            <button onclick="return checkUser(this)" class="btn btn-primary">Cấp lại MK</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </c:if>
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

        <!-- Bootstrap Modal for Creating New Account -->
        <div class="modal fade vertical-alignment" id="createAccountModal" tabindex="-1" role="dialog" aria-labelledby="createAccountModalLabel" aria-hidden="true" data-backdrop="false">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="createAccountModalLabel">Tạo tài khoản nhân viên</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form id="createAccountForm" action="MainController" method="POST">
                        <input type="hidden" name="action" value="CreateAccount"/>
                        <div class="modal-body">
                            <!-- Form for Creating New Account -->

                            <div class="form-group">
                                <label for="fullName">Họ và tên:</label>
                                <input type="text" class="form-control" id="fullName" name="fullname" value="${param.fullname}" required="">
                            </div>
                            <div class="form-group">
                                <label for="username">Tên tài khoản:</label>
                                <input type="text" class="form-control" id="username" name="newusername" value="${param.newusername}" required="">
                            </div>
                            <!-- Radio Button Options -->
                            <div class="form-group">
                                <c:if test="${sessionScope.LOGIN_USER.role == 'admin'}">
                                    <label>Nhóm người dùng:</label><br>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="role" id="role2" checked="" value="staff">
                                        <label class="form-check-label" for="role2">Nhân viên</label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="role" id="role3" value="manager">
                                        <label class="form-check-label" for="role3">Quản lý</label>
                                    </div>
                                </c:if>
                                <c:if test="${sessionScope.LOGIN_USER.role == 'manager'}">
                                    <label>Nhóm người dùng:</label><br>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="role" id="role2" checked="" value="staff">
                                        <label class="form-check-label" for="role2">Nhân viên</label>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                            <button type="button" class="btn btn-secondary reset">Đặt lại</button>
                            <button type="submit" class="btn btn-primary">Tạo tài khoản</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <section id="confirm-remove" class="container-fluid">
            <div class="vh-100 row">
                <div class="h-100 m-auto d-flex align-items-center">
                    <div class="box-remove bg-white p-4">
                        <div class="d-flex align-items-center justify-content-between">
                            <h4>Xác nhận</h4>
                            <div onclick="cancelRemove()" class="btn-close">
                                <i class="fa fa-times" aria-hidden="true"></i>
                            </div>
                        </div> 
                        <p class="mb-4 mt-4">
                            Bạn có muốn thực hiện thao tác này không ?
                        </p>
                        <div class="float-right">
                            <button id="btn-confirrm" data-value="" class="btn btn-group-sm btn-primary">Xác nhận</button>
                            <button  onclick="cancelRemove()" class="btn btn-group-sm btn-secondary">Hủy</button>
                        </div>
                    </div>
                </div>
            </div>
        </section> 
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
