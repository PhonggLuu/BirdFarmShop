/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */
$('.type-shop').click(function (event) {
    $('.type-pair').css('display', 'none');
    $('.bird-shop').css('display', 'block');
    $('.bird-customer').css('display', 'none');
});
$('.type-customer').click(function (event) {
    let login = $('#checkLogin').attr('data-value');
    if (login) {
        toast({
            title: 'Lỗi',
            message: 'Bạn chưa đăng nhập',
            type: 'error',
            duration: 3000
        });
        return;
    } else {
        $('.type-pair').css('display', 'none');
        $('.bird-shop').css('display', 'none');
        $('.bird-customer').css('display', 'block');
    }
});
$('.back-choose h5').click(function (event) {
    $('.type-pair').css('display', 'block');
    $('.bird-shop').css('display', 'none');
    $('.bird-customer').css('display', 'none');
});
//box1
var breedIntital = null;
var checkBird1 = false;
var checkBird2 = false;
$('main #breedSelect1').change(function (event) {
    let breedSelect1 = $(this).val();
    $('main #breedSelect2').val(breedSelect1);
    if (!breedSelect1) {
        $('#birdInformation1').html(" <div class=\"bird-info-row\">\n"

                + "    </div>");
        $('#birdInformation2').html(" <div class=\"bird-info-row\">\n"

                + "    </div>");
        $('#birdSelect1').html("");
        $('#birdSelect2').html("");
        return;
    }
    $.ajax({
        url: 'MainController?action=NavToPairBirds',
        type: 'POST',
        data: {breedIdMale: breedSelect1},
        success: function (data) {
            $('#birdSelect1').html(data);
            checkBird1 = false;
            checkBird2 = false;
        }
    });
    if (breedIntital !== null && breedSelect1 !== breedIntital) {
        $('#birdInformation1').html(" <div class=\"bird-info-row\">\n"

                + "    </div>");
        $('#birdInformation2').html(" <div class=\"bird-info-row\">\n"

                + "    </div>");
        $('#birdSelect1').html("");
        $('#birdSelect2').html("");
    }
    breedIntital = breedSelect1;
});
//box2
$('main #breedSelect2').change(function (event) {
    let breedSelect2 = $(this).val();
    $('main #breedSelect1').val(breedSelect2);
    if (!breedSelect2) {
        $('#birdInformation1').html(" <div class=\"bird-info-row\">\n"

                + "    </div>");
        $('#birdInformation2').html(" <div class=\"bird-info-row\">\n"

                + "    </div>");
        $('#birdSelect1').html("");
        $('#birdSelect2').html("");
        return;
    }
    $.ajax({
        url: 'MainController?action=NavToPairBirds',
        type: 'POST',
        data: {breedIdMale: breedSelect2},
        success: function (data) {
            $('#birdSelect1').html(data);
            checkBird1 = false;
            checkBird2 = false;
        }
    });
    $.ajax({
        url: 'MainController?action=NavToPairBirds',
        type: 'POST',
        data: {breedIdFemale: breedSelect2},
        success: function (data) {
            $('#birdSelect2').html(data);
        }
    });
    if (breedIntital !== null && breedSelect2 !== breedIntital) {
        $('#birdInformation1').html(" <div class=\"bird-info-row\">\n"

                + "    </div>");
        $('#birdInformation2').html(" <div class=\"bird-info-row\">\n"

                + "    </div>");
        $('#birdSelect1').html("");
        $('#birdSelect2').html("");
    }
    breedIntital = breedSelect2;
});
//select bird1
$('#birdSelect1').change(function (event) {
    let birdId = $(this).val();
    let breedID = $('#breedSelect1').val();
    if (!birdId) {
        $('#birdInformation1').html(" <div class=\"bird-info-row\">\n"

                + "    </div>");
        return;
    }
    $.ajax({
        url: 'MainController?action=NavToPairBirds',
        type: 'POST',
        data: {birdId: birdId},
        success: function (data) {
            $('#birdInformation1').html(data);
            checkBird1 = true;
        }
    });
    if (checkBird2 === false) {
        $.ajax({
        url: 'MainController?action=NavToPairBirds',
        type: 'POST',
        data: {breedIdFemale: breedID, birdId: birdId},
        success: function (data) {
            $('#birdSelect2').html(data);
        }
    });
    }
    
});

//select bird2
$('#birdSelect2').change(function (event) {
    let birdId = $(this).val();
    let breedID = $('#breedSelect2').val();
    if (!birdId) {
        $('#birdInformation2').html(" <div class=\"bird-info-row\">\n"

                + "    </div>");
        return;
    }
    $.ajax({
        url: 'MainController?action=NavToPairBirds',
        type: 'POST',
        data: {birdId: birdId},
        success: function (data) {
            $('#birdInformation2').html(data);
            checkBird2 = true;
        }
    });
    if (checkBird1 === false) {
        $.ajax({
            url: 'MainController?action=NavToPairBirds',
            type: 'POST',
            data: {breedIdMale: breedID, birdId: birdId},
            success: function (data) {
                $('#birdSelect1').html(data);
            }
        });
    }
});
$('main #breedSelect3').change(function (event) {
    let breedSelect3 = $(this).val();
    if (breedSelect3 === '') {
        $('input[name=gender1]').val("");
        $('input[name=gender2]').val("");
        $('#birdInformation3').html("");
        $('#birdInformation4').html("");
        $('#breedSelect4').html("");
        return;
    }
    $('main #breedSelect4').val(breedSelect3);
    const arr = breedSelect3.split('-');
    let gender = 0;
    if (arr[2] === 'true') {
        $('input[name=gender1]').val("Chim trống");
    } else {
        $('input[name=gender1]').val("Chim mái");
        gender = 1;
    }
    $.ajax({
        url: 'MainController?action=NavToPairBirds',
        type: 'POST',
        data: {birdCustomerId: arr[1]},
        success: function (data) {
            $('#birdInformation3').html(data);
        }
    });
    $.ajax({
        url: 'MainController?action=NavToPairBirds',
        type: 'POST',
        data: {breedShopId: arr[0], gender: gender},
        success: function (data) {
            console.log(data);
            $('#breedSelect4').html(data);
            $('input[name=gender2]').val("");
            $('#birdInformation4').html("");
        }
    });
});
$('main #breedSelect4').change(function (event) {
    let breedSelect4 = $(this).val();
    if (breedSelect4 === '') {
        $('input[name=gender2]').val("");
        $('#birdInformation4').html("");
        return;
    }
    $('main #breedSelect4').val(breedSelect4);
    const arr = breedSelect4.split('-');
    if (arr[2] === 'true') {
        $('input[name=gender2]').val("Chim trống");
    } else {
        $('input[name=gender2]').val("Chim mái");
    }
    $.ajax({
        url: 'MainController?action=NavToPairBirds',
        type: 'POST',
        data: {birdShopId: arr[1]},
        success: function (data) {
            $('#birdInformation4').html(data);
        }
    });
});
//bird-customer end
$('.show-password').click(function () {
    const type = $('[name="password"]').attr('type');
    if (type === 'password') {
        $('[name="password"]').attr('type', 'text');
        $(this).html('<i class="fa fa-eye-slash" aria-hidden="true"></i>');
    } else {
        $('[name="password"]').attr('type', 'password');
        $(this).html('<i class="fa fa-eye" aria-hidden="true"></i>');
    }
});
$('.show-re-password').click(function () {
    const type = $('[name="re-password"]').attr('type');
    if (type === 'password') {
        $('[name="re-password"]').attr('type', 'text');
        $(this).html('<i class="fa fa-eye-slash" aria-hidden="true"></i>');
    } else {
        $('[name="re-password').attr('type', 'password');
        $(this).html('<i class="fa fa-eye" aria-hidden="true"></i>');
    }
});
$('.new-password').click(function () {
    const type = $('[name="newpassword"]').attr('type');
    if (type === 'password') {
        $('[name="newpassword"]').attr('type', 'text');
        $(this).html('<i class="fa fa-eye-slash" aria-hidden="true"></i>');
    } else {
        $('[name="newpassword').attr('type', 'password');
        $(this).html('<i class="fa fa-eye" aria-hidden="true"></i>');
    }
});
$('.old-password').click(function () {
    const type = $('[name="oldpassword"]').attr('type');
    if (type === 'password') {
        $('[name="oldpassword"]').attr('type', 'text');
        $(this).html('<i class="fa fa-eye-slash" aria-hidden="true"></i>');
    } else {
        $('[name="oldpassword').attr('type', 'password');
        $(this).html('<i class="fa fa-eye" aria-hidden="true"></i>');
    }
});
$('.input').focus(function () {
    $('.error-notification').css('visibility', 'hidden');
});
$(".form-login").validate({
    rules: {
        account: {
            required: true,
            regex: /^[^\s]+$/
        },
        password: {
            required: true
        }
    },
    messages: {
        account: {
            required: 'Vui lòng nhập tài khoản của bạn',
            regex: "Tài khoản không chứa khoảng trắng"
        },
        password: {
            required: 'Vui lòng nhập mật khẩu của bạn'
        }
    }
});
$("#update-tracking").validate({
    rules: {
        quantity_egg: {
            required: true,
            number: true,
            min: 0
        },
        quantity_young_bird: {
            required: true,
            number: true,
            min: 0
        },
        content: {
            required: true,
            regex: /^(?!\s)[\s\S]*$/
        },
        file: {
            required: true
        }
    },
    messages: {
        quantity_egg: {
            required: 'Vui lòng nhập số trứng',
            number: 'Vui lòng nhập số',
            min: 'Vui lòng nhập số trứng lớn hơn hoặc bằng 0'
        },
        quantity_young_bird: {
            required: 'Vui lòng nhập số chim con',
            number: 'Vui lòng nhập số',
            min: 'Vui lòng nhập số chim non lớn hơn hoặc bằng 0',
            max: 'Số chim non không lớn hơn số trứng'
        },
        content: {
            required: 'Vui lòng nhập nội dung theo dõi',
            regex: 'Không nhập kí tự trắng đầu tiên'
        },
        file: {
            required: 'Vui lòng chọn ảnh'
        }
    }
});
$(".form-register").validate({
    rules: {
        name: {
            required: true,
            maxlength: 30,
            regex: /^(?!\s)[\s\S]*$/
        },
        mobile: {
            required: true,
            regex: /^0([0-9]{9,10})$/
        },
        email: {
            required: true,
            email: true
        },
        account: {
            required: true,
            minlength: 5,
            regex: /^[^\s]+$/
        },
        password: {
            required: true,
            regex: /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/
        },
        're-password': {
            required: true,
            equalTo: "[name=password]"
        }
    },
    messages: {
        name: {
            required: 'Vui lòng nhập tên của bạn',
            maxlength: 'Không nhập quá 30 kí tự',
            regex: "Không nhập kí tự trắng đầu tiên"
        },
        mobile: {
            required: 'Vui lòng nhập điện thoại của bạn',
            regex: 'Vui lòng nhập đúng định dạng số điện thoại bắt đầu số 0 và có 10 số'
        },
        email: {
            required: 'Vui lòng nhập email',
            email: 'Vui lòng nhập đúng định dạng email (company@example.com)'
        },
        account: {
            required: 'Vui lòng điền tên đăng nhập',
            minlength: 'Tên tài khoản phải có ít nhất 5 ký tự',
            regex: "Tài khoản không chứa khoảng trắng"
        },
        password: {
            required: 'Vui lòng nhập mật khẩu của bạn',
            regex: 'Mật khẩu phải có ít nhất 8 ký tự, bao gồm chữ hoa, chữ thường, số và ký tự đặc biệt'
        },
        're-password': {
            required: 'Vui lòng nhập mật khẩu của bạn',
            equalTo: 'Xác nhận mật khẩu không đúng'
        }
    }
});
$('#form-createBird').validate({
    rules: {
        txtBreedId: {
            required: true
        },
        nameBird: {
            required: true,
            regex: /^(?!\s)[\s\S]*$/
        },
        filePicture: {
            required: true
        }
    },
    messages: {
        txtBreedId: {
            required: 'Vui lòng chọn giống vẹt'
        },
        nameBird: {
            required: 'Vui lòng nhập tên vẹt của bạn',
            regex: "Không nhập kí tự trắng đầu tiên"
        },
        filePicture: {
            required: 'Vui lòng chọn ảnh'
        }
    }
});
$('#form-order').validate({
    rules: {
        name: {
            required: true,
            maxlength: 30,
            regex: /^(?!\s)[\s\S]*$/
        },
        mobile: {
            required: true,
            regex: /^0([0-9]{9,10})$/
        },
        address: {
            required: true
        }
    },
    messages: {
        name: {
            required: 'Vui lòng nhập tên của bạn',
            maxlength: 'Không nhập quá 30 kí tự',
            regex: "Không nhập kí tự trắng đầu tiên"
        },
        mobile: {
            required: 'Vui lòng nhập điện thoại của bạn',
            regex: 'Vui lòng nhập đúng định dạng số điện thoại bắt đầu số 0 và có 10 số'
        },
        address: {
            required: 'Vui lòng nhập địa chỉ nhận hàng'
        }
    }
});
$('#createAccountForm').validate({
    rules: {
        fullname: {
            required: true,
            maxlength: 30,
            regex: /^(?!\s)[\s\S]*$/
        },
        username: {
            required: true,
            minlength: 5,
            regex: /^[^\s]+$/
        }
    },
    messages: {
        fullname: {
            required: 'Vui lòng nhập tên của nhân viên',
            maxlength: 'Không nhập quá 30 kí tự',
            regex: "Không nhập kí tự trắng đầu tiên"
        },
        username: {
            required: 'Vui lòng nhập tài khoản của nhân viên',
            minlength: 'Tên tài khoản phải có ít nhất 5 ký tự',
            regex: "Tài khoản không chứa khoảng trắng"
        }
    }
});
$("#form-updatePassword").validate({
    rules: {
        oldpassword: {
            required: true
        },
        newpassword: {
            required: true,
            regex: /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/
        },
        're-password': {
            required: true,
            equalTo: "[name=newpassword]"
        }
    },
    messages: {
        oldpassword: {
            required: 'Vui lòng điền mật khẩu cũ'
        },
        newpassword: {
            required: 'Vui lòng nhập mật khẩu mới của bạn',
            regex: 'Mật khẩu phải có ít nhất 8 ký tự, bao gồm chữ hoa, chữ thường, số và ký tự đặc biệt'
        },
        're-password': {
            required: 'Vui lòng nhập mật khẩu mới của bạn',
            equalTo: 'Xác nhận mật khẩu không đúng'
        }
    }
});
$.validator.addMethod(
        "regex",
        function (value, element, regexp) {
            var re = new RegExp(regexp);
            return this.optional(element) || re.test(value);
        },
        "Vui lòng kiểm tra đầu vào của bạn"
        );

