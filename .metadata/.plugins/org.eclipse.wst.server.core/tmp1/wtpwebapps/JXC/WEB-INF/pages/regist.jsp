<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><!-->
<html>
<!--<![endif]-->

<!-- BEGIN HEAD -->

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>龙在天下</title>

<meta content="width=device-width, initial-scale=1.0" name="viewport" />

<meta content="" name="description" />

<meta content="" name="author" />

<!-- BEGIN GLOBAL MANDATORY STYLES -->

<link href="media/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />

<link href="media/css/bootstrap-responsive.min.css" rel="stylesheet"
	type="text/css" />

<link href="media/css/font-awesome.min.css" rel="stylesheet"
	type="text/css" />

<link href="media/css/style-metro.css" rel="stylesheet" type="text/css" />

<link href="media/css/style.css" rel="stylesheet" type="text/css" />

<link href="media/css/style-responsive.css" rel="stylesheet"
	type="text/css" />

<link href="media/css/default.css" rel="stylesheet" type="text/css"
	id="style_color" />

<link href="media/css/uniform.default.css" rel="stylesheet"
	type="text/css" />

<!-- END GLOBAL MANDATORY STYLES -->

<!-- BEGIN PAGE LEVEL STYLES -->

<link href="media/css/login.css" rel="stylesheet" type="text/css" />

<!-- END PAGE LEVEL STYLES -->

<link rel="shortcut icon" href="media/image/favicon.ico" />

</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="login">

	<!-- BEGIN LOGO -->

	<div class="logo">

		<img src="media/image/logo-big.png" alt="" />

	</div>

	<!-- END LOGO -->

	<!-- BEGIN LOGIN -->

	<div class="content">

		<!-- BEGIN LOGIN FORM -->

		<form class="form-vertical login-form" action="regist" method="post">

			<h3 class="form-title">龙在天下国际数据漫游</h3>

			<div class="alert alert-error hide">

				<button class="close" data-dismiss="alert"></button>

				<span>Enter any username and password.</span>

			</div>

			<div class="control-group">

				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->

				<label class="control-label visible-ie8 visible-ie9">Username</label>

				<div class="controls">

					<div class="input-icon left">

						 <input class="m-wrap placeholder-no-fix"
							type="text" placeholder="用户名" name="username" />

					</div>

				</div>

			</div>

			<div class="control-group">

				<label class="control-label visible-ie8 visible-ie9">Password</label>

				<div class="controls">

					<div class="input-icon left">

						<input class="m-wrap placeholder-no-fix"
							type="password" placeholder="密码" name="password" />

					</div>

				</div>

			</div>
			
			<div class="control-group">

				<label class="control-label visible-ie8 visible-ie9">Re-type Your Password</label>

				<div class="controls">

					<div class="input-icon left">

						<input class="m-wrap placeholder-no-fix" type="password" placeholder="确认密码" name="rpassword"/>

					</div>

				</div>

			</div>

			<div class="control-group">
				<label class="control-label visible-ie8 visible-ie9">姓名</label>
				<!-- 姓名 -->
				<div class="controls">

					<div class="input-icon left">

						<input class="m-wrap placeholder-no-fix" type="text" placeholder="真实姓名" name="name"/>

					</div>

				</div>
			</div>
			
			<div class="control-group">

				<label class="control-label">性别：</label>

				<div class="controls">

					<select tabindex="1" class="small m-wrap" name="sex">

						<option name="sex" selected="selected" value="0">女</option>

						<option name="sex" value="1">男</option>

					</select>
				</div>

			</div>
			
			<div class="control-group">
				<label class="control-label visible-ie8 visible-ie9">身份证号</label>
				<!-- 身份证号 -->
				<div class="controls">

					<div class="input-icon left">

						<input class="m-wrap placeholder-no-fix" type="text" placeholder="身份证号" name="idnumber"/>

					</div>

				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label visible-ie8 visible-ie9">出生日期</label>
				<!-- 出生日期 -->
				<div class="controls">

					<div class="input-icon left">

						<input class="m-wrap placeholder-no-fix" type="text" placeholder="出生日期" name="birth"/>

					</div>

				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label visible-ie8 visible-ie9">电话号码</label>
				<!-- 电话 -->
				<div class="controls">

					<div class="input-icon left">

						<input class="m-wrap placeholder-no-fix" type="text" placeholder="电话号码" name="mobile"/>

					</div>

				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label visible-ie8 visible-ie9">柜台ID</label>
				<!-- 柜台ID -->
				<div class="controls">

					<div class="input-icon left">

						<input class="m-wrap placeholder-no-fix" type="text" placeholder="柜台ID" name="counterid"/>

					</div>

				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label visible-ie8 visible-ie9">主管ID</label>
				<!-- 主管ID -->
				<div class="controls">

					<div class="input-icon left">

						<input class="m-wrap placeholder-no-fix" type="text" placeholder="主管ID" name="headid"/>

					</div>

				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label visible-ie8 visible-ie9">联系人姓名</label>
				<!-- 联系人姓名 -->
				<div class="controls">

					<div class="input-icon left">

						<input class="m-wrap placeholder-no-fix" type="text" placeholder="联系人姓名" name="contactsname"/>

					</div>

				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label visible-ie8 visible-ie9">联系人电话</label>
				<!-- 联系人电话 -->
				<div class="controls">

					<div class="input-icon left">

						<input class="m-wrap placeholder-no-fix" type="text" placeholder="联系人电话" name="contactstel"/>

					</div>

				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label visible-ie8 visible-ie9">入职日期</label>
				<!-- 入职日期 -->
				<div class="controls">

					<div class="input-icon left">

						<input class="m-wrap placeholder-no-fix" type="text" placeholder="入职日期" name="entrydate"/>

					</div>

				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label visible-ie8 visible-ie9">联系地址</label>
				<!-- 联系地址 -->
				<div class="controls">

					<div class="input-icon left">

						<input class="m-wrap placeholder-no-fix" type="text" placeholder="联系地址" name="address"/>

					</div>

				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label">级别标识：</label>
				<!-- 级别标识 -->
				<div class="controls">

					<label class="radio">

					<div class="radio"><span><input type="radio" value="0000" name="gradetype"></span></div>

					普通用户

					</label>

					<label class="radio">

					<div class="radio"><span class="checked"><input type="radio" checked="" value="0001" name=""gradetype""></span></div>

					管理员

					</label>  


				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label">是否有效：</label>
				<!-- 是否有效 -->
				<div class="controls">

					<label class="radio">

					<div class="radio"><span><input type="radio" value="0001" name="isvalid"></span></div>

					有效

					</label>

					<label class="radio">

					<div class="radio"><span class="checked"><input type="radio" checked="" value="0002" name="isvalid"></span></div>

					无效

					</label>  


				</div>
			</div>
			
			<div class="control-group">

				<div class="controls">

					<label class="checkbox">

					<input type="checkbox" name="tnc"/> I agree to the <a href="#">Terms of Service</a> and <a href="#">Privacy Policy</a>

					</label>  

					<div id="register_tnc_error"></div>

				</div>

			</div>

			<div class="form-actions">

				<button onclick="f1();" class="btn blue pull-left">

					返回<i class="m-icon-swapright m-icon-white"></i>

				</button>

				<button type="submit" class="btn green pull-right">

					注册 <i class="m-icon-swapright m-icon-white"></i>

				</button>

			</div>

		</form>

		<!-- END LOGIN FORM -->

	</div>

	<!-- END LOGIN -->

	<!-- BEGIN COPYRIGHT -->

	<div class="copyright">2013 &copy; Metronic. Admin Dashboard
		Template.</div>

	<!-- END COPYRIGHT -->

	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->

	<!-- BEGIN CORE PLUGINS -->

	<script src="media/js/jquery-1.10.1.min.js" type="text/javascript"></script>

	<script src="media/js/jquery-migrate-1.2.1.min.js"
		type="text/javascript"></script>

	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->

	<script src="media/js/jquery-ui-1.10.1.custom.min.js"
		type="text/javascript"></script>

	<script src="media/js/bootstrap.min.js" type="text/javascript"></script>

	<!--[if lt IE 9]>

	<script src="media/js/excanvas.min.js"></script>

	<script src="media/js/respond.min.js"></script>  

	<![endif]-->

	<script src="media/js/jquery.slimscroll.min.js" type="text/javascript"></script>

	<script src="media/js/jquery.blockui.min.js" type="text/javascript"></script>

	<script src="media/js/jquery.cookie.min.js" type="text/javascript"></script>

	<script src="media/js/jquery.uniform.min.js" type="text/javascript"></script>

	<!-- END CORE PLUGINS -->

	<!-- BEGIN PAGE LEVEL PLUGINS -->

	<script src="media/js/jquery.validate.min.js" type="text/javascript"></script>

	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->

	<script src="media/js/app.js" type="text/javascript"></script>

	<script src="media/js/regist.js" type="text/javascript"></script>

	<!-- END PAGE LEVEL SCRIPTS -->

	<script>

		jQuery(document).ready(function() {     

		  App.init();

		  Login.init();

		});
		
		function f1(){
			window.location="/regist";
		}
	</script>

	<!-- END JAVASCRIPTS -->


</body>
<!-- END BODY -->
</html>