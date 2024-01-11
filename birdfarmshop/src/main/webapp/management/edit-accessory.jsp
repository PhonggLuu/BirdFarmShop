<%-- 
    Document   : edit-accessory
    Created on : Sep 13, 2023, 11:53:57 PM
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

        <title>V.E.T - Cập nhật thông tin phụ kiện</title>

        <!-- Additional CSS Files -->
        <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/font-awesome.css">
        <link rel="stylesheet" href="assets/css/templatemo-hexashop.css">
        <link rel="stylesheet" href="assets/css/owl-carousel.css">
        <link rel="stylesheet" href="assets/css/lightbox.css">

        <style>
            .col-lg-12 .product-part {
                margin: 0 0 10px 10px;
            }
            .col-lg-12 input {
                margin-bottom: 10px;
                width: 50%;
            }
            .col-lg-12 span {
                margin-top: 100px;
            }
            .form-left{
                padding-top: 8px;
            }
            .button-submit{
                border-radius: 10px;
                border: none;
                color: white;
                padding: 10px 20px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                float: right;
            }
            #id{
                height: 100px;
            }
            .accessory-image{
                width: 115px;
                height: 75px;
                margin-top: 7px;
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
                            <h2>Chỉnh sửa sản phẩm</h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- ***** Main Banner Area End ***** -->


        <section class="section" id="products">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <c:if test="${requestScope.MESSAGE != null}">
                            <h4 style="text-align: center; color: red;"><c:out value="${requestScope.MESSAGE}"/></h4>
                        </c:if>
                        <c:if test="${requestScope.Error != null}">
                            <h4 style="text-align: center; color: red;"><c:out value="${requestScope.Error}"/></h4>
                        </c:if>
                    </div>
                </div>
                <form action="RenderUpdateAccessoryController" method="POST" enctype="multipart/form-data">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="form-outline mt-2">
                                <label>Mã phụ kiện</label>
                                <input style="color: #0c5460;" type="text" name="txtAccessoryID" class="input form-control" pattern="[A-Z]{2}\d{3}" value="${a.accessory_id}" readonly=""/>
                            </div>

                            <div class="form-outline mt-2">
                                <label>Tên</label>
                                <input style="color: #0c5460;" type="text" name="txtAccessoryName" class="input form-control" value="${a.accessory_name}" required/>
                            </div>

                            <div class="form-outline mt-2">
                                <label>Loại phụ kiện</label>
                                <select name="txtCategoryID" class="input form-control" style="color: #0c5460;">
                                    <option value="${category_id}">${categoryname}</option>
                                    <c:forEach items="${ac}" var="ac">
                                        <c:if test="${category_id ne ac.category_id && categoryname ne ac.category_name}">
                                            <option value="${ac.category_id}">${ac.category_name}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>

                            <input type="hidden" name="category_id" value="${category_id}">

                            <div class="row">
                                <div class="col-lg-6">
                                    <div class="form-outline mt-2">
                                        <label>Giá bán</label>
                                        <div style="position: relative">
                                            <input style="color: #0c5460; width: 80%;" type="number" name="txtPrice" min="0" class="input form-control" value="${a.unit_price}" required/>
                                            <span style="position: absolute; right: 35px; top: 50%; transform: translateY(-50%);">₫</span>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-lg-6">
                                    <div class="form-outline mt-2">
                                        <label>Giảm giá (%)</label>
                                        <div style="position: relative">
                                            <input style="color: #0c5460; width: 80%;" type="number" name="txtDiscount" min="0" max="100" class="input form-control" value="${a.discount}" required=""/>                            
                                            <span style="position: absolute; right: 28px; top: 50%; transform: translateY(-50%);">%</span>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="form-outline mt-2">
                                <label>Số lượng</label>
                                <input style="color: #0c5460;" type="number" name="txtStockQuantity" min="0" max="999" class="input form-control" value="${a.stock_quantity}" required/>
                            </div>


                        </div>
                        <div class="col-lg-6">
                            <div class="form-outline mt-2">
                                <label for="txtDescribe">Mô tả</label><br>
                                <textarea class="form-control" id="txtDescribe" name="txtDescribe" rows="5" cols="50" style="width: 100%px; height: 140px; color: #0c5460;" required="">${a.description}</textarea>
                            </div>

                            <div class="form-outline mt-2">
                                <div class="row">
                                    <div class="col-lg-9">
                                        <label>Hình ảnh sản phẩm (Bắt buộc)</label>
                                        <input onchange="previewImage(event, 'productImagePreview')" style="color: #0c5460;" type="file" accept="image/*" name="txtImage"  class="input form-control" value="${url_thumnail}"/>                                           
                                    </div>
                                    <div class="col-lg-3">
                                        <img id="productImagePreview" class="accessory-image" src="${url_thumnail}"/>
                                    </div>
                                </div>
                            </div>

                            <c:if test="${requestScope.list != null}">
                                <c:forEach var="image" items="${list}" varStatus="loop">
                                    <div class="form-outline mt-2">
                                        <div class="row">
                                            <div class="col-lg-9">
                                                <label>Hình ảnh sản phẩm</label>
                                                <input onchange="previewImage(event, 'productImagePreview${loop.index + 1}')" style="color: #0c5460;" type="file" accept="image/*" name="txtImage_${loop.index + 1}" class="input form-control" value="${image.image_url}"/>
                                                <input type="hidden" name="Image_id_${loop.index + 1}" value="${image.image_id}">                                                
                                            </div>
                                            <div class="col-lg-3">
                                                <img id="productImagePreview${loop.index + 1}" class="accessory-image" src="${image.image_url}"/>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:if>                                
                        </div>
                        <div class="col-lg-12" style="margin-top: 10px;">
                            <button style="float: right;" onclick="return checkUser(this)" type="submit" class="btn btn-danger button-submit">Hủy bỏ</button>
                            <button style="margin-right: 10px; float: right;" class="button-submit btn-primary" type="submit" name="btAction" value="Update">Hoàn tất</button>
                        </div>
                    </div>

                </form>
            </div>
        </section>
        <section id="confirm-remove" class="container-fluid">
            <div class="vh-100 row">
                <div class="h-100 m-auto d-flex align-items-center">
                    <div class="box-remove bg-white p-4">
                        <h4>Xác nhận</h4>
                        <p class="mb-4 mt-4">
                            Bạn có muốn thực hiện thao tác này không ?
                        </p>
                        <div class="float-right">
                            <a type="button" id="btn-confirrm" href="RenderAccessoryDetailsController?id=${a.accessory_id}" class="btn btn-group-sm btn-primary">Xác nhận</a>
                            <button onclick="cancelRemove()" class="btn btn-group-sm btn-secondary">Hủy</button>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <%@include file="../layout/footer.jsp" %>
        <%@include file="../layout/message.jsp" %>

        <!-- jQuery -->
        <script src="assets/js/jquery-2.1.0.min.js"></script>

        <!-- Bootstrap -->
        <script src="assets/js/popper.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>

        <!-- Plugins -->
        <script src="assets/js/owl-carousel.js"></script>
        <script src="assets/js/accordions.js"></script>
        <script src="assets/js/datepicker.js"></script>
        <script src="assets/js/scrollreveal.min.js"></script>
        <script src="assets/js/waypoints.min.js"></script>
        <script src="assets/js/jquery.counterup.min.js"></script>
        <script src="assets/js/imgfix.min.js"></script> 
        <script src="assets/js/slick.js"></script> 
        <script src="assets/js/lightbox.js"></script> 
        <script src="assets/js/isotope.js"></script> 

        <!-- Global Init -->
        <script src="assets/js/custom.js"></script>
    </body>
    <script>
                                function checkUser(event) {
                                    $('#confirm-remove').css('display', 'block');
                                    let idForm = event.form.id;
                                    $('#btn-confirrm').attr('data-value', idForm);
                                    return false;
                                }
                                function cancelRemove() {
                                    $('#confirm-remove').css('display', 'none');
                                }

                                function previewImage(event, imageId) {
                                    var file = event.target.files[0];
                                    var reader = new FileReader();
                                    reader.onload = function () {
                                        var image = document.getElementById(imageId);
                                        image.src = reader.result;
                                    };
                                    reader.readAsDataURL(file);
                                }
    </script>
</html>

