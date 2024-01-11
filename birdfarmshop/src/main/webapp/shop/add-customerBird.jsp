<%-- 
    Document   : add-birdCustomer
    Created on : Oct 4, 2023, 7:33:16 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>V.E.T - Chim khách khàng</title>
        <link rel="icon" type="image/png" href="assets/images/logo-title-bar.png"/>
        <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/font-awesome.css">
        <link rel="stylesheet" href="assets/css/templatemo-hexashop.css">
        <link rel="stylesheet" href="assets/css/owl-carousel.css">
        <link rel="stylesheet" href="assets/css/lightbox.css">
        <link rel="stylesheet" href="assets/css/bird-pair.css">
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
                            <h2>Nhân giống sinh sản</h2>
                            <span>Ghép cặp và tạo ra những cá thể vẹt cảnh đôc đáo, mang lại giá trị cao</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- ***** Main Banner Area End ***** -->
        <main class="bird-customer new-bird">
            <div class="comparison-column customer-create--bird">
                <a href="MainController?action=NavToPairBirds"><button id="button-back" class="button-create px-4">Quay lại</button></a>
                <div class="column-content">
                    <h2>Thêm một chú vẹt mới</h2>
                    <form id="form-createBird" action="CreateBirdCustomerController" method="POST" enctype="multipart/form-data"> 
                        <!-- EL to populate the category combo box -->
                        <select class="combo-box" name="txtBreedId" id="breedSelect5">
                            <option value = "">Chọn giống vẹt</option>
                            <c:forEach items="${requestScope.BIRD_BREEDS}" var="breed">
                                    <option value="${breed.breed_id}" ${param.txtBreedId == breed.breed_id ? "selected":""}>${breed.breed_name}</option>
                            </c:forEach>
                        </select>
                        <!-- EL to populate the bird name combo box -->
                        <div class="mt-3">
                            <label class="mb-1" style="color: black; width: 150px" for="name">Tên con vẹt: </label>
                            <input type="text" id="account" class="form-control" name="nameBird" value="${param.nameBird}" required="">          
                        </div> 
                        <div class="mt-3 d-flex align-items-center">
                            <label for="gender-1" class="mb-1" class="mr-4">Giới tính: </label>
                            <input id="gender-1" class="ml-4 mr-1" type="radio" name="gender" value="1" checked=""/><label for="gender-1">Trống</label>
                            <p style="display: inline-block; width: 30px"></p>
                            <input id="gender-2" class="mr-1"type="radio" name="gender" value="0" ${param.gender == 0 ? "checked":""}/><label for="gender-2">Mái</label>
                        </div>
                        <div class="mt-3">
                            <label style="color: black; width: 150px"  for="imageBird">Ảnh con vẹt: </label>
                            <input style="background: none; border: none" type="file" id="imageBird" class="form-control mt-2" name="filePicture" accept="image/jpeg, image/png, image/gif" required="">          
                        </div>  

                        <!-- Additional rows to display bird information -->
                        <div id="birdInformation3">         
                            <div class="bird-info-row">
                              
                            </div>
                        </div>
                </div>
            </div>
        </div>
        <div class="box-button-create">
            <button type="submit" class="button-createBird">Thêm vẹt mới</button>  
        </div>
    </from>
</main>
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
        $('#breedSelect3').change(function () {
            let breedId = $(this).val();
            $('input[type=file]').val('');
            $('#birdInformation3').html(`<div class="bird-info-row"></div>`);
        });
        $('input[type=file]').change(function (e) {
            let file = e.target.files[0];
            if (file) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    let nodeImage = $('#birdImage1').val();
                    if(nodeImage){
                       $('#birdImage1').attr('src', e.target.result);
                    }else{
                        const imgNode = document.createElement('img');
                        imgNode.alt = 'Chim khách hàng';
                        imgNode.src = e.target.result;
                        imgNode.id = 'birdImage1';
                        $('.bird-info-row').html(imgNode); 
                    }
                };
                reader.readAsDataURL(file);
            }
        });
    });
</script>
</body>
</html>