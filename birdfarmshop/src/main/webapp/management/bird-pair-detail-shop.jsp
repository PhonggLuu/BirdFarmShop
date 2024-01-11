

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

        <title>V.E.T - Chi tiết nhân giống</title>

        <!-- Additional CSS Files -->
        <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/font-awesome.css">
        <link rel="stylesheet" href="assets/css/templatemo-hexashop.css">
        <link rel="stylesheet" href="assets/css/owl-carousel.css">
        <link rel="stylesheet" href="assets/css/lightbox.css">
        <style type="text/css">
            body {
                margin: 0;
                padding-top: 150px;
                color: #2e323c;
                background: #f5f6fa;
                position: relative;
                height: 100%;
            }
            .account-settings .user-profile {
                margin: 0 0 1rem 0;
                padding-bottom: 1rem;
                text-align: center;
            }
            .account-settings .user-profile .user-avatar {
                margin: 0 0 1rem 0;
            }
            .account-settings .user-profile .user-avatar img {
                width: 90px;
                height: 90px;
                -webkit-border-radius: 100px;
                -moz-border-radius: 100px;
                border-radius: 100px;
            }
            .account-settings .user-profile h5.user-name {
                margin: 0 0 0.5rem 0;
            }
            .account-settings .user-profile h6.user-email {
                margin: 0;
                font-size: 0.8rem;
                font-weight: 400;
                color: #9fa8b9;
            }
            .account-settings .about {
                margin: 2rem 0 0 0;
                text-align: center;
            }
            .account-settings .about h5 {
                margin: 0 0 15px 0;
                color: #007ae1;
            }
            .account-settings .about p {
                font-size: 0.825rem;
            }
            .form-control {
                border: 1px solid #cfd1d8;
                -webkit-border-radius: 2px;
                -moz-border-radius: 2px;
                border-radius: 2px;
                font-size: .825rem;
                background: #ffffff;
                color: #2e323c;
            }

            .form-group .form-control {
                white-space: pre-wrap;
                font-size: 16px;
            }
            .form-group a {
                text-decoration: none;
                color: black;
            }

            .card {
                background: #ffffff;
                -webkit-border-radius: 5px;
                -moz-border-radius: 5px;
                border-radius: 5px;
                border: 0;
                margin-bottom: 1rem;
            }

            .about li {
                margin-bottom: 10px;
                padding-top: 10px;
                padding-bottom: 10px;
                padding-left: 5px;
                text-align: left;
            }

            .about li:hover {
                background: #cccccc;
            }
            .about .active-profile a
            {
                color:  orange;
            }
            .about a .icon{
                width: 24px;
                height: 24px;
                margin: 0px 10px 0px 0px;
                font-size: 24px;
            }

            .about a {
                color: black;
            }
            .text-primary{
                text-transform: uppercase;
                font-size: 20px;
            }
            .gutters{
                margin-bottom: 20px;
            }
            .register-date{
                font-style: italic;
                font-weight: bold;
            }
            .nav-item{
                width: 20%;
                text-align: center;
            }
            .image-item img{
                height: 100px;
                width: 100px;
            }
            .card .bird {
                border: 1px solid #e9e9ed;
                border-radius: 15px;
                padding: 0 12px;
            }
            .card .order{
                width: 40px;
            }
            .body-bird{
                max-height: 585px;
                overflow-y: scroll;
            }
            .body-bird::-webkit-scrollbar {
                border-radius: 0;
                width: 8px;
            }

            .body-bird::-webkit-scrollbar-thumb {
                border-radius: 4px;
                background-color: rgba(22, 24, 35, 0.06);
            }

            .body-bird::-webkit-scrollbar-track {
                border-radius: 0;
                background-color: rgba(0, 0, 0, 0);
            }
        </style>

    </head>

    <body>
        <!-- Header Start -->
        <%@include file="../layout/header.jsp" %>
        <!-- Header End -->
        <main class="detail-bird-pair">
            <div class="container container-custome">
                <div class="row gutters justify-content-center">   
                    <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">
                        <div class="card h-100">
                            <div class="card-body order-section">
                                <!-- Tab buttons -->
                                <div class="tab-pane">
                                    <!-- Similar structure as the Pending Orders Tab -->
                                    <div class="row">
                                        <!-- Card components for pending orders -->
                                        <div class="col-12">
                                            <div class="card w-100"> 
                                                <div class="card-body card-pair p-0">
                                                    <a style="color: black;" class="ml-3"href="MainController?action=NavToBirdPairShop">
                                                        <i style="color: black; font-size: 1rem; width: 20px;" class="fa fa-arrow-left" aria-hidden="true"></i>
                                                        Quay lại
                                                    </a>
                                                    <c:choose>
                                                        <c:when test="${not empty requestScope.BIRDPAIR}">
                                                            <c:set var="birdPair" value="${requestScope.BIRDPAIR}"/>
                                                            <c:choose>
                                                                <c:when test="${not empty birdPair.birdCustomer}">
                                                                    <div class="box-pair mt-2">
                                                                        <div class="d-flex align-items-center justify-content-between">
                                                                            <div class="pair-img pl-3 py-3 text-center">
                                                                                <img src="${birdPair.birdCustomer.img_url}" alt="${birdPair.birdCustomer.name}" />
                                                                                <p>${birdPair.birdCustomer.name}</p>
                                                                                <c:if test="${birdPair.birdCustomer.gender}">
                                                                                    (Trống)
                                                                                </c:if>
                                                                                <c:if test="${!birdPair.birdCustomer.gender}">
                                                                                    (Mái)
                                                                                </c:if>
                                                                            </div>
                                                                            <div class="text-center">
                                                                                <div class="pair-status mb-4">
                                                                                    ${birdPair.status}
                                                                                </div>                                                                         
                                                                            </div>
                                                                            <c:choose>
                                                                                <c:when test="${not empty birdPair.male_bird}">
                                                                                    <a href="MainController?action=NavToBirdDetails&bird_id=${birdPair.male_bird.bird_id}"> 
                                                                                        <div class="pair-img pl-3 py-3 text-center">
                                                                                            <img src="${birdPair.male_bird.image_url}" alt="${birdPair.male_bird.bird_name}"/>
                                                                                            <p>${birdPair.male_bird.bird_name}</p>
                                                                                            <p>Trống</p>
                                                                                        </div>
                                                                                    </a>
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <a href="MainController?action=NavToBirdDetails&bird_id=${birdPair.female_bird.bird_id}">
                                                                                        <div class="pair-img pr-3 py-3 text-center">
                                                                                            <img src="${birdPair.female_bird.image_url}" alt="${birdPair.female_bird.bird_name}"/>
                                                                                            <p>${birdPair.female_bird.bird_name}</p>
                                                                                            <p>(Mái)</p>
                                                                                        </div>
                                                                                    </a>
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </div>
                                                                    </div>
                                                                    <div class="line-status d-flex align-items-center py-5">
                                                                        <div class="img-status col-6 h-100">
                                                                            <c:choose>
                                                                                <c:when test="${not empty birdPair.pList}">
                                                                                    <div class="d-flex justify-content-around align-items-center">
                                                                                        <div class="img-button">
                                                                                            <img onclick="nextImage()" src="assets/images/decrease-button.png"  alt="nút quay lại" />
                                                                                        </div>
                                                                                        <img id="main-ImgPair" src="${birdPair.pList.get(0)}"  alt="ảnh chính giống" />
                                                                                        <div class="img-button">
                                                                                            <img onclick="prevImage()" src="assets/images/increase-button.png"  alt="nút tiếp" />
                                                                                        </div>
                                                                                    </div>

                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <h5 class="text-center">Chưa có ảnh</h5>
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                            <div class="img-more d-flex mt-4 justify-content-center">

                                                                            </div>
                                                                        </div>
                                                                        <div class="line-time col-6 h-100">
                                                                            <ul class="p-2 mt-3">
                                                                                <c:forEach var="tracking" items="${requestScope.TRACKINGLIST}">
                                                                                    <li class="mb-2"><fmt:formatDate value="${tracking.date}" pattern="dd-MM-yyyy HH:mm"/> <span class="ml-3">${tracking.content}
                                                                                            <c:if test="${not empty tracking.username}">
                                                                                                <p class="text-danger d-inline">( ${tracking.username} )</p>
                                                                                            </c:if>
                                                                                        </span></li>
                                                                                    </c:forEach>
                                                                            </ul>
                                                                        </div>
                                                                    </div>
                                                                    <div class="content-birdpair mx-3">
                                                                        <c:if test="${birdPair.status == 'Đã sinh sản' || birdPair.status == 'Đã ấp nở' ||  birdPair.status == 'Đã thanh toán'}">
                                                                            <div class="d-flex">
                                                                                <p>Số lượng trứng</p>
                                                                                <p class="ml-3">${birdPair.number_egg}</p>
                                                                            </div>
                                                                        </c:if>
                                                                        <c:if test="${birdPair.status == 'Đã ấp nở' ||  birdPair.status == 'Đã thanh toán'}">
                                                                            <div class="d-flex">
                                                                                <p>Số lượng chim non</p>
                                                                                <p class="ml-3">${birdPair.number_young_bird}</p>
                                                                            </div>
                                                                        </c:if>
                                                                        <div class="d-flex">
                                                                            <div class="price-pair">
                                                                                <p>Giá tiền một con chim non</p>
                                                                                <span class="text-muted">(Giá tiền chim con được tính trên 20% giá gốc tiền chim ghép cặp)</span>
                                                                            </div>
                                                                            <p class="ml-3"><fmt:formatNumber value="${birdPair.young_bird_price}" pattern="#,###"/> ₫</p>
                                                                        </div>

                                                                        <div class="d-flex">
                                                                            <p>Tổng tiền cần thanh toán</p>
                                                                            <p class="ml-3 text-danger"><fmt:formatNumber value="${birdPair.number_young_bird * birdPair.young_bird_price}" pattern="#,###"/> ₫</p>
                                                                        </div>

                                                                        <c:if test="${birdPair.status == 'Chờ lấy chim' || birdPair.status == 'Đang ghép cặp'||birdPair.status == 'Đã sinh sản'}">
                                                                            <div class="text-center border-bottom-0">
                                                                                <button style="background-color: #007bff; color: white; border-radius: 20px;" id="payment-youngBird" class="mt-3 py-2 px-3">Cập nhật theo dõi nhân giống</button>
                                                                            </div>
                                                                        </c:if>
                                                                        <c:if test="${birdPair.status == 'Đã ấp nở'}">
                                                                            <form id="update-tracking" action="UpdateStatusTrackingController" method="POST" enctype="multipart/form-data">
                                                                                <input type="hidden" name="pair_id" value="${requestScope.BIRDPAIR.pair_id}"/>
                                                                                <input type="hidden" name="order_id" value="${requestScope.BIRDPAIR.order_id}" />
                                                                                <div class="text-center border-bottom-0">
                                                                                    <button style="background-color: #007bff; color: white; border-radius: 20px;" onclick="return checkUser(this)" id="payment-youngBird" class="mt-3 py-2 px-3">Hủy đơn</button>
                                                                                </div>
                                                                            </form>
                                                                        </c:if>
                                                                    </div>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <div class="box-pair mt-2">
                                                                        <div class="d-flex align-items-center justify-content-between">
                                                                            <a href="MainController?action=NavToBirdDetails&bird_id=${birdPair.male_bird.bird_id}"> 
                                                                                <div class="pair-img pl-3 py-3 text-center">
                                                                                    <img src="${birdPair.male_bird.image_url}" alt="${birdPair.male_bird.bird_name}"/>
                                                                                    <p>${birdPair.male_bird.bird_name}</p>
                                                                                    <c:if test="${birdPair.male_bird.gender}">
                                                                                        <p>(Trống)</p>
                                                                                    </c:if>
                                                                                </div>
                                                                            </a>
                                                                            <div class="text-center">
                                                                                <div class="pair-status mb-4">
                                                                                    ${birdPair.status}
                                                                                </div>                                                                         
                                                                            </div>
                                                                            <a href="MainController?action=NavToBirdDetails&bird_id=${birdPair.female_bird.bird_id}">
                                                                                <div class="pair-img pr-3 py-3 text-center">
                                                                                    <img src="${birdPair.female_bird.image_url}" alt="${birdPair.female_bird.bird_name}"/>
                                                                                    <p>${birdPair.female_bird.bird_name}</p>
                                                                                    <c:if test="${birdPair.male_bird.gender}">
                                                                                        <p>(Mái)</p>
                                                                                    </c:if>
                                                                                </div>
                                                                            </a>
                                                                        </div>
                                                                    </div>
                                                                    <div class="line-status d-flex align-items-center py-5">
                                                                        <div class="img-status col-6 h-100">
                                                                            <c:choose>
                                                                                <c:when test="${not empty birdPair.pList}">
                                                                                    <div class="d-flex justify-content-around align-items-center">
                                                                                        <div class="img-button">
                                                                                            <img onclick="nextImage()" src="assets/images/decrease-button.png"  alt="nút quay lại" />
                                                                                        </div>
                                                                                        <img id="main-ImgPair" src="${birdPair.pList.get(0)}"  alt="ảnh chính giống" />
                                                                                        <div class="img-button">
                                                                                            <img onclick="prevImage()" src="assets/images/increase-button.png"  alt="nút tiếp" />
                                                                                        </div>
                                                                                    </div>

                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <h5 class="text-center">Chưa có ảnh</h5>
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                            <div class="img-more d-flex mt-4 justify-content-center">

                                                                            </div>
                                                                        </div>
                                                                        <div class="line-time col-6 h-100">
                                                                            <ul class="p-2 mt-3">
                                                                                <c:forEach var="tracking" items="${requestScope.TRACKINGLIST}">
                                                                                    <li class="mb-2"><fmt:formatDate value="${tracking.date}" pattern="dd-MM-yyyy HH:mm"/> <span class="ml-3">${tracking.content}
                                                                                            <c:if test="${not empty tracking.username}">
                                                                                                <p class="text-danger d-inline">( ${tracking.username} )</p>
                                                                                            </c:if>
                                                                                        </span></li>
                                                                                    </c:forEach>
                                                                            </ul>
                                                                        </div>
                                                                    </div>
                                                                    <div class="content-birdpair mx-3">
                                                                        <c:if test="${birdPair.status == 'Đã sinh sản' || birdPair.status == 'Đã ấp nở' ||  birdPair.status == 'Đã thanh toán'}">
                                                                            <div class="d-flex">
                                                                                <p>Số lượng trứng</p>
                                                                                <p class="ml-3">${birdPair.number_egg}</p>
                                                                            </div>
                                                                        </c:if>
                                                                        <c:if test="${birdPair.status == 'Đã ấp nở' ||  birdPair.status == 'Đã thanh toán'}">
                                                                            <div class="d-flex">
                                                                                <p>Số lượng chim non</p>
                                                                                <p class="ml-3">${birdPair.number_young_bird}</p>
                                                                            </div>
                                                                        </c:if>
                                                                        <div class="d-flex">
                                                                            <div class="price-pair pr-1">
                                                                                <p>Giá tiền một con chim non</p>
                                                                                <span class="text-muted">(Giá tiền chim con được tính trên 20% giá gốc tiền chim của cửa hàng ghép cặp)</span>
                                                                            </div>
                                                                            <p class="ml-3"><fmt:formatNumber value="${birdPair.young_bird_price}" pattern="#,###"/> ₫</p>
                                                                        </div>
                                                                        <div class="d-flex">
                                                                            <p>Tổng tiền cần thanh toán</p>
                                                                            <p class="ml-3 text-danger"><fmt:formatNumber value="${birdPair.number_young_bird * birdPair.young_bird_price}" pattern="#,###"/> ₫</p>
                                                                        </div>
                                                                        <c:if test="${birdPair.status == 'Chờ lấy chim' || birdPair.status == 'Đang ghép cặp'||birdPair.status == 'Đã sinh sản'}">
                                                                            <div class="text-center border-bottom-0">
                                                                                <button style="background-color: #007bff; color: white;" id="payment-youngBird" class="mt-3 py-2 px-3">Cập nhật theo dõi nhân giống</button>
                                                                            </div>
                                                                        </c:if>
                                                                        <c:if test="${birdPair.status == 'Đã ấp nở'}">
                                                                            <form id="update-tracking" action="UpdateStatusTrackingController" method="POST" enctype="multipart/form-data">
                                                                                <input type="hidden" name="pair_id" value="${requestScope.BIRDPAIR.pair_id}"/>
                                                                                <input type="hidden" name="order_id" value="${requestScope.BIRDPAIR.order_id}" />
                                                                                <div class="text-center border-bottom-0">
                                                                                    <button style="background-color: #007bff; color: white; border-radius: 20px;" onclick="return checkUser(this)" id="payment-youngBird" class="mt-3 py-2 px-3">Hủy đơn</button>
                                                                                </div>
                                                                            </form>
                                                                        </c:if>
                                                                    </div>        
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <div class="my-5">
                                                                <h4 class="text-center">Bạn chưa có ghép cặp chim</h4>
                                                            </div>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <section id="confirm-payment" class="container-fluid">
            <div class="vh-100 row">
                <div class="h-100 m-auto d-flex align-items-center">
                    <div class="box-remove bg-white p-4">
                        <div class="d-flex align-items-center justify-content-between">
                            <h4>Cập nhật trạng thái nhân giống</h4>
                            <div onclick="cancelRemove()" class="btn-close">
                                <i class="fa fa-times" aria-hidden="true"></i>
                            </div>
                        </div>
                        <form id="update-tracking" action="UpdateStatusTrackingController" method="POST" enctype="multipart/form-data">
                            <input type="hidden" name="pair_id" value="${requestScope.BIRDPAIR.pair_id}"/>
                            <input type="hidden" name="order_id" value="${requestScope.BIRDPAIR.order_id}" />
                            <div class="row mt-5">
                                <div style="width: 350px" class="col-12 col-md-6">
                                    <c:if test="${requestScope.BIRDPAIR.status == 'Đang ghép cặp'}">
                                        <div class="number-egg w-100 mr-2 mb-3">
                                            <label for="egg">Số lượng trứng: </label>
                                            <input type="number" class="input w-100 pl-2" id="egg" min="0" name="quantity_egg" value="${requestScope.BIRDPAIR.number_egg }"/>
                                        </div>
                                    </c:if>
                                    <c:if test="${requestScope.BIRDPAIR.status == 'Đã sinh sản'}">
                                        <div class="bird-young w-100 mb-3">
                                            <label for="birdYoung">Số lượng chim con: </label>
                                            <input type="number" class="input w-100 pl-2" id="birdYoung" min="0" max="${requestScope.BIRDPAIR.number_egg}" name="quantity_young_bird" value="${requestScope.BIRDPAIR.number_young_bird}"/>
                                        </div>
                                    </c:if>
                                    <div>
                                        <label for="content">Nội dung theo dõi: </label>
                                        <textarea id="content" name="content" rows="5" class="w-100 pl-2" placeholder="Nhập nội dung theo dõi">${param.content}</textarea>
                                    </div>
                                </div>
                                <div class="col-12 col-md-6">
                                    <label for="image">Chọn ảnh: </label>
                                    <input id="image" type="file" name="file"  accept="image/jpeg, image/png, image/gif" required="">
                                    <div class="img-upload my-3">

                                    </div>
                                </div>
                            </div>
                        </form>
                        <div class="d-flex float-right">
                            <button onclick="submitForm()" class="btn btn-group-sm btn-primary mx-1">Cập nhật</button>
                            <button  onclick="cancelRemove()" class="btn btn-group-sm btn-secondary mx-1">Hủy</button>
                        </div>
                    </div>
                </div>
            </div>
        </section>             
        <!-- Start Footer -->
        <%@include file="../layout/footer.jsp" %>
        <!-- End Footer -->
        <script>
            <c:if test="${not empty requestScope.TRACKINGLIST && requestScope.BIRDPAIR.status == 'Đã ấp nở'}">
            var date = String(${requestScope.TRACKINGLIST.get(0).date});
            console.log(date);
            </c:if>
            $('#payment-youngBird').click(function () {
                $('#confirm-payment').css('display', 'block');
            });
            function cancelRemove() {
                $('#confirm-payment').css('display', 'none');
                $('.img-upload').html('');
            }
            const mainImage = document.querySelector("#main-ImgPair");
            const secondaryImages = document.querySelector(".img-more");
            const imageList = [];
            <c:forEach var="img" items="${requestScope.BIRDPAIR.pList}">
            imageList.push({
                img_url: "${img}"
            });
            </c:forEach>
            if (imageList.length > 0) {
                secondaryImages.innerHTML === '';
                for (let i = 1; i < imageList.length; i++) {
                    if (i <= 4) {
                        const nodeImg = document.createElement("img");
                        nodeImg.src = imageList[i].img_url;
                        nodeImg.alt = "ảnh phụ nhân giống";
                        nodeImg.setAttribute("onclick", "choseImage(" + i + ")");
                        secondaryImages.appendChild(nodeImg);
                    }
                }
            }
            let currentImageIndex = 0;

            function updateImages() {
                if (imageList.length - currentImageIndex > 4) {
                    secondaryImages.innerHTML = '';
                    var count = 0;
                    for (let i = currentImageIndex; i < imageList.length; i++) {
                        if (i !== currentImageIndex && count < 4) {
                            count += 1;
                            const nodeImg = document.createElement("img");
                            nodeImg.src = imageList[i].img_url;
                            nodeImg.alt = "ảnh chính giống";
                            nodeImg.setAttribute("onclick", "choseImage(" + i + ")");
                            secondaryImages.appendChild(nodeImg);
                        }
                    }
                }
            }
            function changeImages(direction) {
                if (direction === "next") {
                    if (currentImageIndex + 1 <= imageList.length - 1) {
                        currentImageIndex += 1;
                        mainImage.src = imageList[currentImageIndex].img_url;
                    }

                } else if (direction === "prev") {
                    if (currentImageIndex - 1 >= 0) {
                        currentImageIndex -= 1;
                        mainImage.src = imageList[currentImageIndex].img_url;
                    }
                } else {
                    mainImage.src = imageList[currentImageIndex].img_url;
                }
                updateImages();
            }
            const nextButton = document.querySelector(".img-button img[alt='nút tiếp']");
            const prevButton = document.querySelector(".img-button img[alt='nút quay lại']");

            function nextImage() {
                changeImages("next");
            }

            function prevImage() {
                changeImages("prev");
            }
            function submitForm() {
                $('#update-tracking').submit();
            }
            function choseImage(event) {
                currentImageIndex = event;
                changeImages();
            }
            $('input[type=file]').change(function (e) {
                let file = e.target.files[0];
                if (file) {
                    var reader = new FileReader();
                    reader.onload = function (e) {
                        const imgNode = document.createElement('img');
                        imgNode.src = e.target.result;
                        imgNode.style = "height: 230px; width: 280px";
                        imgNode.alt = "ảnh theo dõi";
                        $('.img-upload').html(imgNode);
                    };
                    reader.readAsDataURL(file);
                }
            });
            $('input[name=quantity_egg]').change(function () {
                const numberBirdNode = $('input[name=quantity_young_bird]');
                numberBirdNode.val(0);
                numberBirdNode.attr('max', $(this).val());
            });

        </script>
    </body>
</html>
