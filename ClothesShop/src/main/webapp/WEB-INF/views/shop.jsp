<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="shared/head.jsp"%>
		
<title>Sản phẩm</title>
<body class="animsition important">

	<%@include file="shared/header.jsp"%>
	<%@include file="shared/minicart.jsp"%>

	<div class="bg0 m-t-23 p-b-140">
		<div class="container">
			<div class="flex-w flex-sb-m p-b-52">
				<div class="flex-w flex-l-m filter-tope-group m-tb-10">
					<a href="shop"><button class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 how-active1" data-filter="*">Tất cả</button></a>
					<c:forEach items="${cates}" var="cate">
						<button type="button" class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 btn-cate"
							data-filter=".women" 
							id="cate_${cate.id}">${cate.cate_name}</button>
					</c:forEach>
				</div>
			<%@include file="shared/filter.jsp"%>
			</div>
			
			<script type="text/javascript">
				function showModal(a) {
					$.ajax({
						type : "GET",
						contentType : "application/json",
						url : "getProductById",
						data : {
							id : a
						},
						dataType : 'json',
						timeout : 100000,
						success : function(data) {
							$('.js-modal1').addClass('show-modal1');
							$('.slick3-dots li img')[0].src = '<c:url value="/resources/images/' + data.image + '"/>';
							$('.wrap-pic-w.pos-relative img')[0].src = '<c:url value="/resources/images/' + data.image + '"/>';
							$('.item-slick3.slick-current').attr('data-thumb','<c:url value="/resources/images/' + data.image + '"/>');
							$('.wrap-pic-w.pos-relative a')[0].href = '<c:url value="/resources/images/' + data.image + '"/>';
							$('.mtext-105.cl2.js-name-detail.p-b-14').text(data.product_name);
							$('.mtext-106.cl2').text(data.price);
							$('.stext-102.cl3.p-t-23').text(data.description);
							$('#detail_id').text(a);
						},
						error : function(e) {
							console.log("ERROR: ", e);
						}
					});
				} 
				
				function addStuffPre(product_id, price, elm) {
					var obj = {product_id : product_id, price: price, quantity : 1, description : "M-Trắng"};
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
									var nameProduct = $(elm).parent().parent().find('.js-name-b2').html();
									$(elm).addClass('js-addedwish-b2');
									swal(nameProduct, "Đã thêm vào giỏ ! \n (Kích cỡ - Màu sắc mặc định)", "success");
								}
							}
						},
						error : function(e) {
							console.log("ERROR: ", e);
						}
					});
					return false;
				}
			</script>
			
			<div class="row isotope-grid">
				<c:forEach items="${list}" var="stuff">
					<div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item women" id="${stuff.id}">
						
						<div class="block2">
							<div class="block2-pic hov-img0">
								<img src="<c:url value="/resources/images/${stuff.image}"/>"
									alt="IMG-PRODUCT"> <a href="javascript:showModal(${stuff.id});"
									class="block2-btn flex-c-m stext-103 cl2 size-102 bg0 bor2 hov-btn1 p-lr-15 trans-04 
									js-show-modal1">Quick View</a>
							</div>

							<div class="block2-txt flex-w flex-t p-t-14">
								<div class="block2-txt-child1 flex-col-l ">
									<a href="product-detail.html"
										class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">${stuff.product_name}</a>
										<span class="stext-105 cl3">${stuff.price}</span>
								</div>

								<div class="block2-txt-child2 flex-r p-t-3">
									<a href="" onclick="return addStuffPre(${stuff.id}, ${stuff.price}, this)" 
									class="btn-addwish-b2 dis-block pos-relative js-addwish-b2" id="addcartpre${stuff.id}">
										<!-- <img class="icon-heart1" src="<c:url value="/resources/images/icons/icon-heart-01.png"/>"
										alt="ICON"> -->
										<svg width="1em" height="1em" viewBox="0 0 16 16" class="icon-heart1 bi bi-cart-check 
										dis-block trans-04" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  											<path fill-rule="evenodd" d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 
  											0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 
  											0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 
  											2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm7 0a1 1 0 1 0 0 2 1 1 0 0 0 
  											0-2z"/>
  											<path fill-rule="evenodd" d="M11.354 5.646a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 
  											0l-1.5-1.5a.5.5 0 1 1 .708-.708L8 8.293l2.646-2.647a.5.5 0 0 1 .708 0z"/>
										</svg> <!-- <img
										class="icon-heart2 "
										src="<c:url value="/resources/images/icons/icon-heart-02.png"/>" alt="ICON">  -->
										<svg width="1em" height="1em" viewBox="0 0 16 16" class="icon-heart2 bi bi-cart-check-fill 
										dis-block trans-04 ab-t-l" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  											<path fill-rule="evenodd" d="M.5 1a.5.5 0 0 0 0 1h1.11l.401 1.607 1.498 7.985A.5.5 0 0 0 4
  											 12h1a2 2 0 1 0 0 4 2 2 0 0 0 0-4h7a2 2 0 1 0 0 4 2 2 0 0 0 0-4h1a.5.5 0 0 0 .491-.408l1.5
  											 -8A.5.5 0 0 0 14.5 3H2.89l-.405-1.621A.5.5 0 0 0 2 1H.5zM4 14a1 1 0 1 1 2 0 1 1 0 0 1-2 
  											 0zm7 0a1 1 0 1 1 2 0 1 1 0 0 1-2 0zm.354-7.646a.5.5 0 0 0-.708-.708L8 8.293 6.854 
  											 7.146a.5.5 0 1 0-.708.708l1.5 1.5a.5.5 0 0 0 .708 0l3-3z"/>
										</svg>
									</a>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>

			
			<div class="flex-c-m flex-w w-full p-t-45">
				<a href="#" class="flex-c-m stext-101 cl5 size-103 bg2 bor1 hov-btn1 p-lr-15 trans-04"> Load More </a>
			</div>
		</div>
	</div>
	
	<%@include file="core/productmodal.jsp" %>
	<%@include file="shared/footer.jsp"%>
	<%@include file="shared/backtotop.jsp"%>

	<script src="<c:url value="/resources/vendor/jquery/jquery-3.2.1.min.js"/>"></script>
	<script src="<c:url value="/resources/vendor/animsition/js/animsition.min.js"/>"></script>
	<script src="<c:url value="/resources/vendor/bootstrap/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/resources/vendor/select2/select2.min.js"/>"></script>
	<script>
		$(".js-select2").each(function() {
			$(this).select2({
				minimumResultsForSearch : 20,
				dropdownParent : $(this).next('.dropDownSelect2')
			});
		})
	</script>
	<script src="<c:url value="/resources/vendor/daterangepicker/moment.min.js"/>"></script>
	<script src="<c:url value="/resources/vendor/daterangepicker/daterangepicker.js"/>"></script>
	<script src="<c:url value="/resources/vendor/slick/slick.min.js"/>"></script>
	<script src="<c:url value="/resources/js/slick-custom.js"/>"></script>
	<script src="<c:url value="/resources/vendor/parallax100/parallax100.js"/>"></script>
	<script>$('.parallax100').parallax100();</script>
	<script src="<c:url value="/resources/vendor/MagnificPopup/jquery.magnific-popup.min.js"/>"></script>
	<script>
		$('.gallery-lb').each(function() { // the containers for all your galleries
			$(this).magnificPopup({
				delegate : 'a', // the selector for gallery item
				type : 'image',
				gallery : {
					enabled : true
				},
				mainClass : 'mfp-fade'
			});
		});
	</script>
	<script src="<c:url value="/resources/vendor/isotope/isotope.pkgd.min.js"/>"></script>
	<script src="<c:url value="/resources/vendor/sweetalert/sweetalert.min.js"/>"></script>
	<script src="<c:url value="/resources/vendor/perfect-scrollbar/perfect-scrollbar.min.js"/>"></script>
	<%-- <script src="<c:url value="/resources/js/product.js"/>"></script> --%>
	<script src="<c:url value="/resources/js/main.js"/>"></script>
</body>
</html>