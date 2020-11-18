<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script>
	function logOut() {
		$.ajax({	
			type : "GET",
			url : "requestlogout",
			success : function() {
				$(".js-addedwish-b2").removeClass("js-addedwish-b2");
				$("#login-action-div").html("<a href='' class='flex-c-m trans-04 p-lr-25' data-toggle='modal' data-target='#modalLRForm'> Đăng nhập </a>");
				$("#login-header-user").html("");
				swal({
					title: "Đã đăng xuất",
					text: "",
					icon: "success"
				}).then(function() {
						if(window.location.pathname != '/ClothesShop/shop') window.location.href = 'home';
					});	
			},
			error : function(e) {
				console.log("ERROR: ", e);
			}
		});
	}
	
	function requestAccount() {
		$.ajax({	
			type : "GET",
			url : "requestAccount",
			dataType : 'text',
			success : function(data) {
				if(data=="Error") {
					$("#hidden-request").val("requestAccount");
					$('#modalLRForm').modal('show');
				}
				else window.location.href = data;
			},
			error : function(e) {
				console.log("ERROR: ", e);
			}
		});
	}
	
	function requestCart() {
		$.ajax({	
			type : "GET",
			url : "requestCart",
			dataType : 'text',
			success : function(data) {
				if(data=="Error") {
					$("#hidden-request").val("requestCart");
					$('#modalLRForm').modal('show');
				}
				else window.location.href = data;
			},
			error : function(e) {
				console.log("ERROR: ", e);
			}
		});
	}
</script>

<header class="header-v4">
	<div class="container-menu-desktop" style="z-index: 10;">
		<div class="top-bar">
			<div class="content-topbar flex-sb-m h-full container">
				<div class="left-top-bar">
				Free ship toàn quốc - Tích luỹ 1% coupon giảm giá cá nhân với mỗi đơn hàng có giá trị từ 500 
				(không bao gồm chiết khẩu) - Tối đa 15%
				</div>
				<div class="right-top-bar flex-w h-full">
					<div id="login-action-div">
						<c:if test="${sessionScope.id eq null}">
							<a href="" class="flex-c-m trans-04 p-lr-25" data-toggle="modal" data-target="#modalLRForm"> Đăng nhập </a>
 						</c:if>
 						<c:if test="${sessionScope.id ne null}">
 							<a href="javascript:logOut();" class="flex-c-m trans-04 p-lr-25">Đăng xuất</a>
 						</c:if>
 					</div>
					<a href="javascript:requestAccount();" class="flex-c-m trans-04 p-lr-25">Tài Khoản</a>
				</div>
			</div>
		</div>
		<div class="wrap-menu-desktop how-shadow1">
			<nav class="limiter-menu-desktop container">
				<img src="<c:url value="/resources/images/icons/logo-01.png" />" alt="IMG-LOGO">
				<div class="menu-desktop">
					<ul class="main-menu">
						<li id="li-index"><a href="home">Trang chủ</a></li>
						<li class="label1" data-label1="hot" id="li-shop"><a href="shop">Sản phẩm</a></li>
						<li id="li-cart"><a href="javascript:requestCart();">Giỏ hàng</a></li>
					</ul>
				</div>
				<div class="wrap-icon-header flex-w flex-r-m">
					<div id="login-header-user">
						<c:if test="${sessionScope.id ne null}">
							<div>TÀI KHOẢN: <b><i>${sessionScope.username}</i></b></div>
						</c:if>
					</div>
					<c:if test="${sessionScope.id ne null}">
						<div class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti js-show-cart" data-notify="2">
							<i class="zmdi zmdi-shopping-cart"></i>
						</div>
					</c:if>
				</div>
			</nav>
		</div>
	</div>
	<%@include file="login.jsp"%>
</header>