<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="shared/head.jsp"%>
		
<title>Giỏ hàng</title>
<body class="animsition">

	<%@include file="shared/header.jsp"%>
	<div class="container">
		<div class="bread-crumb flex-w p-l-25 p-r-15 p-t-30 p-lr-0-lg">
			<a href="home" class="stext-109 cl8 hov-cl1 trans-04">Trang chủ
				<i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
			</a>
			<span class="stext-109 cl4">Giỏ hàng</span>
		</div>
	</div>
	
	<script>
		function updateCart() {
			var array = [];
			var id,quantity,total,description;
			var size = $(".table_row > .column-7").length;
			for(var i = 0 ;i<size ; i++) {
				var obj = {};
				id = $(".table_row > .column-7").eq(i).attr('id');
				description = $(".table_row > .column-3").eq(i).text();
				quantity = $(".table_row > .column-5 .num-product").eq(i).val();
				total = $(".table_row > .column-6").eq(i).text();
				obj['id'] = id;
				obj['description'] = description;
				obj['quantity'] = quantity;
				obj['total'] = total;
				array.push(obj);
			}
			$.ajax({
				type : "POST",
				url : "updateCart",
				contentType : "application/json; charset=utf-8",
				data : JSON.stringify(array),
				dataType: "json",
				success : function(data){
					swal("Đã cập nhật", "", "success");
					var subtotal = 0;
					$(".table-shopping-cart").find("tr:gt(0)").remove();
					var parsedJSON = JSON.parse(JSON.stringify(data));
					var length = parsedJSON.length;
					for(var i=0;i<length;i++) {
						subtotal = subtotal + parseInt(parsedJSON[i].total);
						var myResponse = `<tr class="table_row">
						<td class="column-1">
						<div class="how-itemcart1">
							<img src="<c:url value="/resources/images/`+ parsedJSON[i].image+`"/>" alt="IMG">
						</div>
						</td>
						<td class="column-2">`+parsedJSON[i].name+`</td>
						<td class="column-3">`+parsedJSON[i].description+`</td>
						<td class="column-4">`+parsedJSON[i].price+`</td>
						<td class="column-5">
							<div class="wrap-num-product flex-w m-l-auto m-r-0">
								<div class="btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m minus-after">
									<i class="fs-16 zmdi zmdi-minus"></i>
								</div>
								<input class="mtext-104 cl3 txt-center num-product" type="number" name="num-product1" value="`+parsedJSON[i].quantity+`">
								<div class="btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m plus-after">
									<i class="fs-16 zmdi zmdi-plus"></i>
								</div>
							</div>
						</td>
						<td class="column-6">`+parsedJSON[i].total+`</td>
						<td class="column-7" style="display:none;" id="`+parsedJSON[i].product_id+`"></td>
						</tr>`;
						$('.table-shopping-cart').append(myResponse);
					}
					$('#sub_total').html(subtotal);
					var coupon = $('#coupon_scope').text().slice(1,-1);
					var responseTotal = subtotal-(parseInt(coupon)*subtotal)/100-0.1;
					$('#total_scope').html(Math.round(responseTotal));
				},
				error : function(e) {
					console.log("error: ", e);
				}
			});			
		}
	</script>
	
	<form class="bg0 p-t-75 p-b-85">
		<div class="container">
			<div class="row">
				<div class="col-lg-10 col-xl-7 m-lr-auto m-b-50">
					<div class="m-l-25 m-r--38 m-lr-0-xl">
						<div class="wrap-table-shopping-cart">
							<table class="table-shopping-cart">
								<tr class="table_head">
									<th class="column-1">Sản phẩm</th>
									<th class="column-2"></th>
									<th class="column-3">Chi tiết</th>
									<th class="column-4">Giá</th>
									<th class="column-5">Số lượng</th>
									<th class="column-6">Tổng</th>
								</tr>
								<c:forEach items="${list}" var="stuff">
								<tr class="table_row">
									<td class="column-1">
										<div class="how-itemcart1">
											<img src="<c:url value="/resources/images/${stuff.image}"/>" alt="IMG">
										</div>
									</td>
									<td class="column-2">${stuff.name}</td>
									<td class="column-3">${stuff.description}</td>
									<td class="column-4">${stuff.price}</td>
									<td class="column-5">
										<div class="wrap-num-product flex-w m-l-auto m-r-0">
											<div class="btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m">
												<i class="fs-16 zmdi zmdi-minus"></i>
											</div>
											<input class="mtext-104 cl3 txt-center num-product" type="number" 
											name="num-product1" value="${stuff.quantity}">
											<div class="btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m">
												<i class="fs-16 zmdi zmdi-plus"></i>
											</div>
										</div>
									</td>
									<td class="column-6">${stuff.total}</td>
									<td class="column-7" style="display:none;" id="${stuff.product_id}"></td>
								</tr>
								</c:forEach>
							</table>
						</div>

						<div class="flex-w flex-sb-m bor15 p-t-18 p-b-15 p-lr-40 p-lr-15-sm">
							 <div class="flex-w flex-m m-r-20 m-tb-5">
								<input class="stext-104 cl2 plh4 size-117 bor13 p-lr-20 m-r-10 m-tb-5" type="text" 
								name="coupon" placeholder="Mã Coupon">
								<div class="flex-c-m stext-101 cl2 size-118 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-5">
									Áp dụng
								</div>
							</div>  

							<div class="flex-c-m stext-101 cl2 size-119 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-10">
								<button type = "button" onclick="updateCart()">Cập nhật giỏ</button>
							</div>
						</div>
					</div>
				</div>

				<div class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-50">
					<div class="bor10 p-lr-40 p-t-30 p-b-40 m-l-63 m-r-40 m-lr-0-xl p-lr-15-sm">
						<h4 class="mtext-109 cl2 p-b-30">
							Giao Hàng
						</h4>

						<div class="flex-w flex-t bor12 p-b-13">
							<div class="size-208">
								<span class="stext-110 cl2">Tạm tính:</span>
							</div>
							<div class="size-209" >
								<span class="mtext-110 cl2" id = "sub_total" style="padding-left: 10%;">${subtotal}</span>
							</div>
						</div>

						<div class="flex-w flex-t bor12 p-t-15 p-b-30" style="padding-bottom: 0px;">
							<div class="size-208 w-full-ssm">
								<span class="stext-110 cl2">Thanh toán:</span>
							</div>
							<div class="size-209 p-r-18 p-r-0-sm w-full-ssm" style="padding-right: 0;">
								<p class="stext-111 cl6 p-t-2"  style="padding-left: 10%;">Thanh toán trực tiếp khi nhận hàng
									<br>( Ship COD )
								</p>
								
								<div class="p-t-15">
									<span class="stext-112 cl8">Thông tin giao hàng</span>
									<div class="rs1-select2 rs2-select2 bor8 bg0 m-b-12 m-t-9">
										<select class="js-select2" name="cities" id="cities_drop">
											<option id="city_require">Chọn thành phố...</option>
											<c:forEach items="${cities}" var="city">
												<option id="city_${city.id}">${city.city_name}</option>
											</c:forEach>
										</select>
										<div class="dropDownSelect2"></div>
									</div>
									
									<div class="rs1-select2 rs2-select2 bor8 bg0 m-b-12 m-t-9">
										<select class="js-select2" name="districts" id="districts_drop">
											<option id="district_require">Chọn quận...</option>
										</select>
										<div class="dropDownSelect2"></div>
									</div>
									
									<div class="bor8 bg0 m-b-12">
										<input class="stext-111 cl8 plh3 size-111 p-lr-15" type="text" id="bill_address"
										name="address" placeholder="Địa chỉ (số nhà...)">
									</div>
									<div class="bor8 bg0 m-b-22">
										<textarea class="stext-111 cl8 plh3 size-111 p-lr-15" id="note_text"
										name="note" placeholder="Ghi chú"></textarea>
									</div>		
								</div>
							</div>
						</div>
						
						<div class="flex-w flex-t p-t-27 p-b-33">
							<div class="size-208">
								<span class="stext-110 cl2">Tích luỹ:</span>
							</div>
							<div class="size-209" >
								<span class="mtext-110 cl2" id="coupon_scope" 
								style="padding-left: 10%;">-${coupon}%</span>
							</div>
							<div class="size-208">
								<span class="mtext-101 cl2">Tổng tiền:</span>
							</div>
							<div class="size-209 p-t-1">
								<span class="mtext-110 cl2" id="total_scope" style="padding-left: 10%;">
								<c:set var = "total_response" value = "${(subtotal-(coupon*subtotal/100))}"/>
								<c:if test="${(total_response % 1) eq 0.5}">
            						<c:set var="total_response" value="${total_response-0.1}" />
        						</c:if>
  								<fmt:formatNumber type = "number" 
         						maxFractionDigits = "0" value = "${total_response}" />
								</span>
							</div>
						</div>
						
						<button type="button" id="btn-bill-submit"
							class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer">Đặt hàng
						</button>
					</div>
				</div>
			</div>
		</div>
	</form>
		
	<%@include file="shared/footer.jsp"%>
	<%@include file="shared/backtotop.jsp"%>
		
	<script src="<c:url value="/resources/vendor/jquery/jquery-3.2.1.min.js"/>"></script>
	<script src="<c:url value="/resources/vendor/animsition/js/animsition.min.js"/>"></script>
	<script src="<c:url value="/resources/vendor/bootstrap/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/resources/vendor/select2/select2.min.js"/>"></script>
	<script>
		$(".js-select2").each(function(){
			$(this).select2({
				minimumResultsForSearch: 20,
				dropdownParent: $(this).next('.dropDownSelect2')
			});
		})
	</script>
	<script src="<c:url value="/resources/vendor/MagnificPopup/jquery.magnific-popup.min.js"/>"></script>
	<script src="<c:url value="/resources/vendor/perfect-scrollbar/perfect-scrollbar.min.js"/>"></script>
	<script src="<c:url value="/resources/vendor/sweetalert/sweetalert.min.js"/>"></script>
	<script src="<c:url value="/resources/js/main.js"/>"></script>
</body>
</html>