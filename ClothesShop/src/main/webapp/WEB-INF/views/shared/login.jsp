<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap">
<link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.17.0/css/mdb.min.css" rel="stylesheet">

<div class="modal fade" id="modalLRForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="z-index: 10000;">
  <div class="modal-dialog cascading-modal" role="document">
    <div class="modal-content">
      <div class="modal-c-tabs">
        <ul class="nav nav-tabs md-tabs tabs-2 light-blue darken-3" role="tablist">
          <li class="nav-item">
            <a class="nav-link active" id="nav-link-1" data-toggle="tab" href="#panel7" role="tab"><i class="fas fa-user mr-1"></i>Đăng nhập</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" id="nav-link-2" data-toggle="tab" href="#panel8" role="tab"><i class="fas fa-user-plus mr-1"></i>Đăng ký</a>
          </li>
        </ul>
        <div class="tab-content">
          <div class="tab-pane fade in show active" id="panel7" role="tabpanel">
			<form id= "form-login" action="requestlogin" method="post">
            	<div class="modal-body mb-1">
              		<div class="md-form form-sm mb-5">
                		<i class="fas fa-user prefix"></i>
               			<input type="text" id="modalLRInput10" name ="username" class="form-control form-control-sm validate" required>
                		<label data-error="wrong" data-success="right" for="modalLRInput10">Tài khoản</label>
              		</div>
              		<div class="md-form form-sm mb-4">
                		<i class="fas fa-lock prefix"></i>
                		<input type="password" id="modalLRInput11" name="pass_word" class="form-control form-control-sm validate" required>
                		<label data-error="wrong" data-success="right" for="modalLRInput11">Mật khẩu</label>
                		<input type="hidden" id="hidden-request" name="request" value="" />
              		</div>
              		<div class="text-center mt-2">
                		<button type="submit" class="btn btn-info"><div style="height: -webkit-fill-available;">Đăng nhập</div><i class="fas fa-sign-in ml-1"></i></button>
              		</div>
           		</div>
         	</form>
            
            
			<div class="modal-footer">
              <div class="options text-center text-md-right mt-1">
                <p>Không phải thành viên?<a href="#" class="blue-text"> Đăng ký</a></p>
                <p style="text-align: left;"><a href="#" class="blue-text">Quên mật khẩu</a></p>
              </div>
              <button type="button" class="btn btn-outline-info waves-effect ml-auto" id="login-close-btn" data-dismiss="modal">Đóng</button>
            </div>
		</div>
        <div class="tab-pane fade" id="panel8" role="tabpanel">
			<form id="form-signup" action="requestsignup" method="post">
            	<div class="modal-body">
            		<div class="md-form form-sm mb-5">
                		<i class="fas fa-user prefix"></i>
               			<input type="text" id="modalLRInput12" name ="username" class="form-control form-control-sm validate" 
               			pattern="^[\w\d]{1,50}$" title="Tối đa 50 ký tự chữ và số" required>
                		<label data-error="wrong" data-success="right" for="modalLRInput12">Tên tài khoản</label>
              		</div>
              		<div class="md-form form-sm mb-5">
                		<i class="fas fa-envelope prefix"></i>
                		<input type="email" id="modalLRInput13" name ="mail" class="form-control form-control-sm validate" 
                		pattern="^[\w\d]{1,40}@gmail.com$" title="*@gmail.com" required>
                		<label data-error="wrong" data-success="right" for="modalLRInput13">Email</label>
              		</div>
              		<div class="md-form form-sm mb-5">
                		<i class="fas fa-mobile prefix"></i>
               			<input type="tel" id="modalLRInput14" name ="phone" class="form-control form-control-sm validate" 
               			pattern="^0\d{9}$" title="Bắt buộc 10 ký tự số và bắt đầu là 0" required>
                		<label data-error="wrong" data-success="right" for="modalLRInput14">Điện thoại</label>
              		</div>
              		<div class="md-form form-sm mb-5">
                		<i class="fas fa-lock prefix"></i>
                		<input type="password" id="modalLRInput15" name ="pass_word" class="form-control form-control-sm validate" 
                		pattern="^[\|\^%!@#&\.\w\d]{9,50}$" 
                		title="Tối thiểu 9 ký tự bao gồm chữ, số và một vài ký tự đặc biệt ! % . | @ & ^ #" required>
                		<label data-error="wrong" data-success="right" for="modalLRInput15">Mật khẩu</label>
             		</div>
              		<div class="md-form form-sm mb-4">
                		<i class="fas fa-lock prefix"></i>
                		<input type="password" id="modalLRInput16" name ="re_pass_word" class="form-control form-control-sm validate" required>
                		<label data-error="wrong" data-success="right" for="modalLRInput16">Nhập lại mật khẩu</label>
              		</div>
              		<div class="text-center form-sm mt-2">
                		<button class="btn btn-info"><div style="height: -webkit-fill-available;">Đăng ký</div><i class="fas fa-sign-in ml-1"></i></button>
              		</div>
            	</div>
			</form>
            <div class="modal-footer">
              <div class="options text-right">
                <p class="pt-1">Đã có tài khoản?<a href="#" class="blue-text">Đăng nhập</a></p>
              </div>
              <button type="button" class="btn btn-outline-info waves-effect ml-auto" data-dismiss="modal">Close</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.4/umd/popper.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.17.0/js/mdb.min.js"></script>