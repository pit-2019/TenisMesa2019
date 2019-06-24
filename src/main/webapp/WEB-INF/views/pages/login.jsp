<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bootstrap Simple Login Form</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
<style type="text/css">
	.login-form {
		width: 340px;
    	margin: 50px auto;
	}
    .login-form form {
    	margin-bottom: 15px;
        background: #f7f7f7;
        box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
        padding: 30px;
    }
    .login-form h2 {
        margin: 0 0 15px;
    }
    .form-control, .btn {
        min-height: 38px;
        border-radius: 2px;
    }
    .btn {        
        font-size: 15px;
        font-weight: bold;
    }
</style>
</head>
<body>
<section id="form"><!--form-->
		<div class="container">
			<div class="row">
				<div class="col-sm-2"></div>
				<div class="col-sm-4">
					<div class="login-main">
						<h1>Login</h1>
					</div>
					<div class="login-form"><!--login form-->
						<form action="#">
							<input type="text" id="idUsername" placeholder="Username" />
							<input type="password" id="idPassword" placeholder="Password" />
							<span id="idMensajeError"></span>
<!-- 							<span> -->
<!-- 								<input type="checkbox" class="checkbox">  -->
<!-- 								Keep me signed in -->
<!-- 							</span> -->
							
							<button type="submit"  id="idBtnLogin"  class="btn btn-success">Login</button>
						</form>
					</div><!--/login form-->
				</div>			
				<div class="col-sm-4">
					<div class="login-main">
						<h1>Registrarse</h1>
					</div>
					<div class="login-form"><!--login form-->
						<form action="#">
							<input type="text" id="idUsernameRegister" placeholder="Username" />
							<input type="password" id="idPasswordRegister" placeholder="Password" />
							<span id="idMensajeError"></span>
<!-- 							<span> -->
<!-- 								<input type="checkbox" class="checkbox">  -->
<!-- 								Keep me signed in -->
<!-- 							</span> -->
							<button type="submit" id="idBtnRegister" class="btn btn-success">Registrarse</button>
						</form>
					</div><!--/login form-->
				</div>
				<div class="col-sm-2"></div>
			</div>
		</div>
		<div id="confirmarUsuario"></div>
	</section><!--/form-->


	<script src="js/login/login.js"></script>
</body>
</html>                                		                            



