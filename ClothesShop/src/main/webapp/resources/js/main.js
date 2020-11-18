(function ($) {
    "use strict";
    /*[ Load page ]
    ===========================================================*/
    $(".animsition").animsition({
        inClass: 'fade-in',
        outClass: 'fade-out',
        inDuration: 1500,
        outDuration: 800,
        linkElement: '.animsition-link',
        loading: true,
        loadingParentElement: 'html',
        loadingClass: 'animsition-loading-1',
        loadingInner: '<div class="loader05"></div>',
        timeout: false,
        timeoutCountdown: 5000,
        onLoadEvent: true,
        browser: [ 'animation-duration', '-webkit-animation-duration'],
        overlay : false,
        overlayClass : 'animsition-overlay-slide',
        overlayParentElement : 'html',
        transition: function(url){ window.location.href = url; }
    });
    
    /*[ Back to top ]
    ===========================================================*/
    var windowH = $(window).height()/2;

    $(window).on('scroll',function(){
        if ($(this).scrollTop() > windowH) {
            $("#myBtn").css('display','flex');
        } else {
            $("#myBtn").css('display','none');
        }
    });

    $('#myBtn').on("click", function(){
        $('html, body').animate({scrollTop: 0}, 300);
    });

    /*==================================================================
    [ Fixed Header ]*/
    var headerDesktop = $('.container-menu-desktop');
    var wrapMenu = $('.wrap-menu-desktop');

    if($('.top-bar').length > 0) {
        var posWrapHeader = $('.top-bar').height();
    }
    else {
        var posWrapHeader = 0;
    }
    
    if($(window).scrollTop() > posWrapHeader) {
        $(headerDesktop).addClass('fix-menu-desktop');
        $(wrapMenu).css('top',0); 
    }  
    else {
        $(headerDesktop).removeClass('fix-menu-desktop');
        $(wrapMenu).css('top',posWrapHeader - $(this).scrollTop()); 
    }

    $(window).on('scroll',function(){
        if($(this).scrollTop() > posWrapHeader) {
            $(headerDesktop).addClass('fix-menu-desktop');
            $(wrapMenu).css('top',0); 
        }  
        else {
            $(headerDesktop).removeClass('fix-menu-desktop');
            $(wrapMenu).css('top',posWrapHeader - $(this).scrollTop()); 
        } 
    });


    /*==================================================================
    [ Menu mobile ]*/
    $('.btn-show-menu-mobile').on('click', function(){
        $(this).toggleClass('is-active');
        $('.menu-mobile').slideToggle();
    });

    var arrowMainMenu = $('.arrow-main-menu-m');

    for(var i=0; i<arrowMainMenu.length; i++){
        $(arrowMainMenu[i]).on('click', function(){
            $(this).parent().find('.sub-menu-m').slideToggle();
            $(this).toggleClass('turn-arrow-main-menu-m');
        })
    }

    $(window).resize(function(){
        if($(window).width() >= 992){
            if($('.menu-mobile').css('display') == 'block') {
                $('.menu-mobile').css('display','none');
                $('.btn-show-menu-mobile').toggleClass('is-active');
            }

            $('.sub-menu-m').each(function(){
                if($(this).css('display') == 'block') { console.log('hello');
                    $(this).css('display','none');
                    $(arrowMainMenu).removeClass('turn-arrow-main-menu-m');
                }
            });
                
        }
    });

    /*==================================================================
    [ Show / hide modal search ]*/
    $('.js-show-modal-search').on('click', function(){
        $('.modal-search-header').addClass('show-modal-search');
        $(this).css('opacity','0');
    });

    $('.js-hide-modal-search').on('click', function(){
        $('.modal-search-header').removeClass('show-modal-search');
        $('.js-show-modal-search').css('opacity','1');
    });

    $('.container-search-header').on('click', function(e){
        e.stopPropagation();
    });


    /*==================================================================
    [ Isotope ]*/
    var $topeContainer = $('.isotope-grid');
    var $filter = $('.filter-tope-group');

    // filter items on button click
    $filter.each(function () {
        $filter.on('click', 'button', function () {
            var filterValue = $(this).attr('data-filter');
            $topeContainer.isotope({filter: filterValue});
        });
        
    });

    // init Isotope
    $(window).on('load', function () {
        var $grid = $topeContainer.each(function () {
            $(this).isotope({
                itemSelector: '.isotope-item',
                layoutMode: 'fitRows',
                percentPosition: true,
                animationEngine : 'best-available',
                masonry: {
                    columnWidth: '.isotope-item'
                }
            });
        });
    });

    var isotopeButton = $('.filter-tope-group button');

    $(isotopeButton).each(function(){
        $(this).on('click', function(){
            for(var i=0; i<isotopeButton.length; i++) {
                $(isotopeButton[i]).removeClass('how-active1');
            }

            $(this).addClass('how-active1');
        });
    });

    /*==================================================================
    [ Filter / Search product ]*/
    $('.js-show-filter').on('click',function(){
        $(this).toggleClass('show-filter');
        $('.panel-filter').slideToggle(400);

        if($('.js-show-search').hasClass('show-search')) {
            $('.js-show-search').removeClass('show-search');
            $('.panel-search').slideUp(400);
        }    
    });

    $('.js-show-search').on('click',function(){
        $(this).toggleClass('show-search');
        $('.panel-search').slideToggle(400);

        if($('.js-show-filter').hasClass('show-filter')) {
            $('.js-show-filter').removeClass('show-filter');
            $('.panel-filter').slideUp(400);
        }    
    });

    /*==================================================================
    [ Cart ]*/
    $('.js-show-cart').on('click',function(){
        $('.js-panel-cart').addClass('show-header-cart');
    });

    $('.js-hide-cart').on('click',function(){
        $('.js-panel-cart').removeClass('show-header-cart');
    });

    /*==================================================================
    [ Cart ]*/
    $('.js-show-sidebar').on('click',function(){
        $('.js-sidebar').addClass('show-sidebar');
    });

    $('.js-hide-sidebar').on('click',function(){
        $('.js-sidebar').removeClass('show-sidebar');
    });

    /*==================================================================
    [ +/- num product ]*/
    $('.btn-num-product-down').on('click', function(){
        var numProduct = Number($(this).next().val());
        if(numProduct > 0) $(this).next().val(numProduct - 1);
    });

    $('.btn-num-product-up').on('click', function(){
        var numProduct = Number($(this).prev().val());
        $(this).prev().val(numProduct + 1);
    });

    /*==================================================================
    [ Rating ]*/
    $('.wrap-rating').each(function(){
        var item = $(this).find('.item-rating');
        var rated = -1;
        var input = $(this).find('input');
        $(input).val(0);

        $(item).on('mouseenter', function(){
            var index = item.index(this);
            var i = 0;
            for(i=0; i<=index; i++) {
                $(item[i]).removeClass('zmdi-star-outline');
                $(item[i]).addClass('zmdi-star');
            }

            for(var j=i; j<item.length; j++) {
                $(item[j]).addClass('zmdi-star-outline');
                $(item[j]).removeClass('zmdi-star');
            }
        });

        $(item).on('click', function(){
            var index = item.index(this);
            rated = index;
            $(input).val(index+1);
        });

        $(this).on('mouseleave', function(){
            var i = 0;
            for(i=0; i<=rated; i++) {
                $(item[i]).removeClass('zmdi-star-outline');
                $(item[i]).addClass('zmdi-star');
            }

            for(var j=i; j<item.length; j++) {
                $(item[j]).addClass('zmdi-star-outline');
                $(item[j]).removeClass('zmdi-star');
            }
        });
    });
    
    /*==================================================================*/
	$('.js-pscroll').each(function(){
		$(this).css('position','relative');
		$(this).css('overflow','hidden');
			var ps = new PerfectScrollbar(this, {
			wheelSpeed: 1,
			scrollingThreshold: 1000,
			wheelPropagation: false,
		});
		$(window).on('resize', function(){
			ps.update();
		})
	});
	
	/*==================================================================*/
    $('.js-hide-modal1').on('click',function(){
        $('.js-modal1').removeClass('show-modal1');
    });

	$('#modalLRForm').on('hidden.bs.modal', function () {
		$('body').removeClass('modal-open');
		$('.modal-backdrop').remove();
		$('#form-login').trigger('reset');
		$('#form-signup').trigger('reset');
		$("#hidden-request").val("");
	});	
	
	$('#modalLRInput12').on('change invalid',function(){
		var textfield = this;
		textfield.setCustomValidity('');
		if(!textfield.validity.valid) {
			if(textfield.value == "") textfield.setCustomValidity('Vui lòng nhập tên tài khoản');
			else textfield.setCustomValidity('Tên tài khoản chưa hợp lệ');
		}
		else {
			$.ajax({
				type : "GET",
				url : "checkUsername",
				contentType : "application/json; charset=utf-8",
				data : {
					username : textfield.value
				},
				dataType: "text",
				success : function(data){
					if(data == "Exist") {
						textfield.setCustomValidity('Tên tài khoản đã được dùng');
					}
				},
				error : function(e) {
					console.log("error: ", e);
				}
			});
		}
	});
	
	$('#modalLRInput13').on('change invalid',function(){
		var textfield = this;
		textfield.setCustomValidity('');
		if(!textfield.validity.valid) {
			if(textfield.value == "") textfield.setCustomValidity('Vui lòng nhập email');
			else textfield.setCustomValidity('Email chưa hợp lệ');
		}
	});	
	
	$('#modalLRInput14').on('change invalid',function(){
		var textfield = this;
		textfield.setCustomValidity('');
		if(!textfield.validity.valid) {
			if(textfield.value == "") textfield.setCustomValidity('Vui lòng số điện thoại');
			else textfield.setCustomValidity('Số điện thoại chưa hợp lệ');
		}
	});
	
	$('#modalLRInput15').on('change invalid',function(){
		var textfield = this;
		textfield.setCustomValidity('');
		if(!textfield.validity.valid) {
			if(textfield.value == "") textfield.setCustomValidity('Vui lòng nhập mật khẩu');
			else textfield.setCustomValidity('Mật khẩu chưa hợp lệ');
		}
	});
	
	$('#modalLRInput16').on('change invalid',function(){
		var textfield = this;
		textfield.setCustomValidity('');
		if(textfield.value == "") textfield.setCustomValidity('Vui lòng nhập lại mật khẩu');
		else {
			var pd = $('#modalLRInput15').val();
			if(textfield.value != pd ) textfield.setCustomValidity('Nhập lại mật khẩu chưa chính xác');
		}
	});	
	
	$("#form-login").submit(function(e) {
    	e.preventDefault(); // avoid to execute the actual submit of the form.
    	var form = $(this);
		var loginForm = form.serializeArray();
		var loginFormObject = {};
		var url = form.attr('action');
		$.each(loginForm, function(i, v) {
        	loginFormObject[v.name] = v.value;
    	});
    	$.ajax({
        	type: form.attr('method'),
			contentType : "application/json; charset=utf-8",
        	url: url,
        	data: JSON.stringify(loginFormObject), // serializes the form's elements.
			dataType : 'json',
        	success: function(data){
				if(data.status==2){
					$("#login-action-div").html("<a href='javascript:logOut();' class='flex-c-m trans-04 p-lr-25'>Đăng xuất</a>");
					$("#login-header-user").html("<div>TÀI KHOẢN: <b><i>" + data.username + "</i></b></div>");
					var minicart = `<div class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti js-show-cart" data-notify="2">
						<i class="zmdi zmdi-shopping-cart"></i></div>`
					$("#login-header-user").append(minicart);
					$('#modalLRForm').modal('hide');
					swal({
    					title: data.response,
    					text: "",
    					icon: "success"
					}).then(function() { 
						if(data.request=="requestAccount") requestAccount();
						else {
							if(data.request=="requestCart") requestCart();
							else checkStuff();
						}		
					});	
				}
				else {
					if(data.status==1) swal(data.response, "", "error");
					else swal(data.response, "", "warning");
				}
       		},
			error : function(e) {
				console.log("ERROR: ", e);
			}
    	});
	});
	
	$("#form-signup").submit(function(e) {
    	e.preventDefault(); // avoid to execute the actual submit of the form.
    	var form = $(this);
		var loginForm = form.serializeArray();
		var loginFormObject = {};
		var url = form.attr('action');
		$.each(loginForm, function(i, v) {
        	loginFormObject[v.name] = v.value;
    	});
    	$.ajax({
        	type: form.attr('method'),
			contentType : "application/json; charset=utf-8",
        	url: url,
        	data: JSON.stringify(loginFormObject), // serializes the form's elements.
			dataType : 'text',
        	success: function(data){
				if(data == "Success"){
					swal({
    					title: data,
    					text: "",
    					icon: "success"
					}).then(function() { 
						$("#nav-link-2").removeClass("active");
						$("#panel8").removeClass("active show");
						$("#nav-link-1").addClass("active");
						$("#panel7").addClass("active show");
						$('#form-signup').trigger('reset');
					});	
				}
				else {
					swal({
    					title: "Lỗi đăng ký",
    					text: "",
    					icon: "error"
					});
				}
       		},
			error : function(e) {
				console.log("ERROR: ", e);
			}
    	});
	});
	$("#addStuffdetailBtn").click(function(e) {
		//var color_id= $('#color_detail_drop').find(":selected").attr('id');
		//var size_id = $('#size_detail_drop').find(":selected").attr('id');
		var size = $('#size_detail_drop').find(":selected").val();
		var color = $('#color_detail_drop').find(":selected").val();
		var description = size + "-" + color;
		var id = $('#detail_id').text();
		var price = $('.mtext-106.cl2').text();
		var quantity = $('#detail_quantity').val();
		if(color == "Choose an option" || size == "Choose an option") swal("Hãy chọn kích cỡ và màu sắc", "", "warning");
		else {
			var obj = {product_id : id, price: price, quantity : quantity, description : description};
			$.ajax({
				type: "POST",
				url: "addStuffIntoCart",
				contentType : "application/json; charset=utf-8",
				data : JSON.stringify(obj),
				dataType: "text",
				success : function(data) {
				if(data=="Error") $('#modalLRForm').modal('show');
					else {
						if(data=="FailtoAdd") swal(data,"!", "error");
						else {
							var nameProduct = $('.mtext-105.cl2.js-name-detail.p-b-14').text();
							$('#addcartpre'+id).addClass('js-addedwish-b2');
							swal(nameProduct, "Đã thêm vào giỏ !", "success");
						}
					}
				},
				error : function(e) {
					console.log("ERROR: ", e);
				}
			});
		}
	});
	/*	$(".stext-106.cl6.hov1.bor3.trans-04.m-r-32.m-tb-5.btn-cate").click(function(e) {
			
		});*/
	$("#btn-bill-submit").click(function(e) {
    	e.preventDefault();
    	var city = $('#cities_drop').find(":selected").attr('id');
		var district = $('#districts_drop').find(":selected").attr('id');
		var address = $("#bill_address").val();
		var note =  $("#note_text").val();
		var subtotal = $('#sub_total').text();
		var check = parseInt(subtotal);
		if(check < 50) swal("Giá trị tạm tính tối thiểu phải là 50", "Hãy xem thêm nhiều sản phẩm nhé!", "warning");
		else {
			if(city == "city_require" || district == "district_require" || address == "") {
				swal("Vui lòng nhập đầy đủ thông tin", "", "warning");
			}
			else {
				swal({
  					title: "Xác nhận đặt hàng",
  					text: "Đơn hàng sẽ được xủ lý sau khi xác nhận",
  					icon: "info",
  					buttons: true,
  					dangerMode: true,
				}).then((willDelete) => {
  					if(willDelete) {
						city = city.substring(5);
						district = district.substring(9);
						var obj = {city : city, district : district, address : address, note : note, subtotal : subtotal};
						$.ajax({
							type: 'POST',
							contentType : "application/json; charset=utf-8",
        					url: 'solvebill',
        					data: JSON.stringify(obj),
							dataType : 'json',
							success : function(data){
								if(data!=-1) {
									swal({
    									title: "Đơn hàng đã được tiếp nhận",
    									text: "Cám ơn bạn, hãy chờ shipper liên hệ nhé!",
    									icon: "success"
									}).then(function() { 
										$("#myBtn").trigger("click");
										$(".table-shopping-cart").find("tr:gt(0)").remove();
										$('#sub_total').html('0');
										$('#total_scope').html('0');
										$('#coupon_scope').html('-'+data+'%');
									});	
								}
								else swal("Lỗi hệ thống", "Báo cáo cho cửa hàng nếu có sự thay đổi đáng ngờ", "error");
							},
							error : function(e) {
								console.log("error: ", e);
							}
						});
  					}
				});
			}
		}
	});
	
	$('table').on("click",'.btn-num-product-up.cl8.hov-btn3.trans-04.flex-c-m.plus-after', function(){
		var index = $('.btn-num-product-up.cl8.hov-btn3.trans-04.flex-c-m.plus-after').index(this);
		var press = parseInt($('.mtext-104.cl3.txt-center.num-product').eq(index).val()) + 1;
		var total = press * parseInt($(".table_row > .column-4").eq(index).text());
		$('.mtext-104.cl3.txt-center.num-product').eq(index).val(press);
		$(".table_row > .column-6").eq(index).text(total);
	});
	
	$('table').on("click",'.btn-num-product-down.cl8.hov-btn3.trans-04.flex-c-m.minus-after', function(){
		var index = $('.btn-num-product-down.cl8.hov-btn3.trans-04.flex-c-m.minus-after').index(this);
		var press = parseInt($('.mtext-104.cl3.txt-center.num-product').eq(index).val()) - 1;
		if(press<0) press = 0;
		var total = press * parseInt($(".table_row > .column-4").eq(index).text());
		$('.mtext-104.cl3.txt-center.num-product').eq(index).val(press);
		$(".table_row > .column-6").eq(index).text(total);
	});
	
	$('table').on("input",'.mtext-104.cl3.txt-center.num-product', function(){
		var index = $('.mtext-104.cl3.txt-center.num-product').index(this);
		var press = parseInt($('.mtext-104.cl3.txt-center.num-product').eq(index).val());
		if(!$(this).val()) press = 0;
		var total = press * parseInt($(".table_row > .column-4").eq(index).text());
		$(".table_row > .column-6").eq(index).text(total);
	});
	
	function checkStuff() {
		var ID = [];
		$(".isotope-item").each(function() { 
        	if(this.id) ID.push(this.id);
		});
		$.ajax({
			type : "POST",
			url : "checkStuffexistOrNot",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify(ID),
			dataType: "json",
			success : function(data){
				$.each(data, function(key,value) {
					$("#"+value).addClass('js-addedwish-b2');
				});
			},
			error : function(e) {
				console.log("error: ", e);
			}
		});	
	}
	
	$('#cities_drop').on("change", function(){
		var id = $('#cities_drop').find(":selected").attr('id');
		if(id != "city_require") {
			var sub_id = id.substring(5);
			$.ajax({
				type : "GET",
				url : "getdistrictsbyid",
				contentType : "application/json; charset=utf-8",
				data : {
					id : sub_id
				},	
				dataType: "json",
				success : function(data){
					$('#districts_drop').html('');
					var cities = '<option id="district_require">Chọn quận...</option>';
					$('#districts_drop').append(cities);
					$.each(data, function(i) {
						cities ='<option id="district_'+data[i].id+'">'+data[i].district_name+"</option>";
						$('#districts_drop').append(cities);
					});
				},
				error : function(e) {
					console.log("error: ", e);
				}
			});	
		}
	});
	
	$(".btn-cate").click(function(e) {
		var id = e.target.id
		id = id.substring(5);
		$.ajax({
			type : "GET",
			url : "getshopcate",
			contentType : "application/json; charset=utf-8",
			data : {
				id : id
			},	
			success : function(data){
				window.location.href = data;
			},
			error : function(e) {
					console.log("error: ", e);
			}
		});
	});
	
	$(document).ready(function() {
		$( ".btn-num-product-up.cl8.hov-btn3.trans-04.flex-c-m" ).click(function() {
			var index = $('.btn-num-product-up.cl8.hov-btn3.trans-04.flex-c-m').index(this);
			var press = parseInt($('.mtext-104.cl3.txt-center.num-product').eq(index).val());
			var total = press * parseInt($(".table_row > .column-4").eq(index).text());
			$(".table_row > .column-6").eq(index).text(total);
		});
		
		$( ".btn-num-product-down.cl8.hov-btn3.trans-04.flex-c-m" ).click(function() {
			var index = $('.btn-num-product-down.cl8.hov-btn3.trans-04.flex-c-m').index(this);
			var press = parseInt($('.mtext-104.cl3.txt-center.num-product').eq(index).val());
			var total = press * parseInt($(".table_row > .column-4").eq(index).text());
			$(".table_row > .column-6").eq(index).text(total);
		});
		
		$(".wrap-table-shopping-cart").siblings().css({
    		'width': ($(".wrap-table-shopping-cart").width() + 2 + 'px')
  		});

  		if(window.location.pathname == '/ClothesShop/shop') checkStuff();
	});
})(jQuery);