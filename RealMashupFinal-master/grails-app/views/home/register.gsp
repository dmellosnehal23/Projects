<!DOCTYPE html>
<html lang="en">
<head>

<title>Real Estate Prediction Engine</title>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="description">
<meta content="" name="author">

<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'bootstrap.min.css')}">
<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'bootstrap-responsive.min.css')}">
<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'bootstrap-responsive.css')}">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<link rel="stylesheet" type="text/css" href="${resource(dir: 'fancybox', file: 'jquery.fancybox-v=2.1.5.css')}" media="screen">

<!-- CSS (necessary for Bootstrap's CSS ) -->
<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'font-awesome.min.css')}">
<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'style.css')}">

<!-- JavaScript -->
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/operations.js"></script>
</head>

<body>
	<!-- Navigation Bar -->
	<header>
		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="navbar-inner" style="height: 60px;">
				<div class="container">
					<div class="navbar-header">
						<button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
							<span class="icon-bar"></span> 
							<span class="icon-bar"></span> 
							<span class="icon-bar"></span>
						</button>
						<a class="brand" href="#">Real Realty</a>
					</div>
					<div class="nav-collapse collapse navbar-responsive-collapse">
						<ul class="nav">
							<li style="color: white; padding-top: 10px;">
								<i class="icon-home icon-white"></i>  
							</li>
							<li class="active">
								<g:link controller="home" action="index">Home</g:link>
							</li>
						</ul>
					</div>
					<ul class="nav-collapse collapse pull-right">
				        <li style="margin-top: 10px; height: 25px;">
				        	<a href="#register" data-toggle="modal" style="color: white; height: 20px; margin-top: 5px;">
								<i class="icon-pencil icon-white"></i>Register Here</a>
							
				        </li>
				    </ul>
				</div>
			</div>
		</div>
	</header>
	
	<!-- Navigation Ends -->
	<!-- Main Container -->
	<section>
	<div class="container login">
		<div class="row ">
			<div class="center span4 well">
				<div id="loginMsg" class="alert alert-danger" hidden="true"></div>
				<div id="loginMsgSuccess" class="alert alert-success" hidden="true">
				</div>
				<legend>Please Sign In</legend>
				<g:form method="post" controller="restClient" action="authenticateUser" accept-charset="UTF-8" id="loginForm">
					<div class="input-prepend">
						<span class="add-on">
							<i class="icon-user"></i>
						</span> 
						<input type="email" id="email" class="span3" name="email" placeholder="Email" required="true" />
					</div>
					<div class="input-prepend">
						<span class="add-on">
							<i class="icon-lock"></i>
						</span> 
						<input type="password" id="password" class="span3" name="password" placeholder="Password" required="true" />
					</div>
					<label class="checkbox"> <input type="checkbox" name="remember" value="1" /> Remember Me</label>
					<input type="hidden" value="login" name="operation" />
					<button type="submit" name="submit" class="btn btn-success btn-block" id="loginBtn">Sign in</button>
					<button type="submit" name="submit" class="btn btn-block" id="loginClearBtn">Clear</button>
				</g:form>
			</div>
		</div>
	</div>
	</section>
	<!-- Main Container Ends -->
	
	<script type="text/javascript">
		window.onload = function () 
		{
		    document.getElementById("regpassword").onchange = validatePassword;
		    document.getElementById("regpasswordrep").onchange = validatePassword;
		}
		
		function validatePassword()
		{
			var pass2=document.getElementById("regpasswordrep").value;
			var pass1=document.getElementById("regpassword").value;
			if(pass1!=pass2)
			    document.getElementById("regpasswordrep").setCustomValidity("Passwords Don't Match");
			else
			    document.getElementById("regpasswordrep").setCustomValidity('');  
			//empty string means no validation error
		}
</script>
	
	<!-- Forgot Password Model Box -->
	<div id="register" class="modal hide fade in" style="display: none;">
		<div class="modal-header">
			<a class="close" data-dismiss="modal" aria-hidden="true">
				<i class="icon-remove"></i>
			</a>
			<h3 id="registerModalLbl">Registration Form</h3>
		</div>
		<div class="modal-body">
			<div id="successMsg" class="alert alert-success" hidden="true"></div>
			<div id="failureMsg" class="alert alert-danger" hidden="true"></div>
			<g:form id="registerForm" controller="restClient" action="registerUser">
				<div class="controls controls-row">
					<input id="fname" name="fname" type="text" class="span2" placeholder="First Name" required="true"/> 
					<input id="lname" name="lname" type="text" class="span2" placeholder="Last Name" required="true"/>
				</div>
				<div class="controls controls-row">
					<input id="email" name="email" type="email" class="span4" placeholder="Email address" required="true"/>
				</div>
				<div class="controls controls-row">
					<input type="password" id="regpassword" class="span4" name="password" placeholder="Password" required="true" />
				</div>
				<div class="controls controls-row">
					<input type="password" id="regpasswordrep" class="span4" name="passwordrep" placeholder="Re-enter Password" required="true" />
					<input type="hidden" value="register" name="operation" />
				</div>
				<div class="modal-footer">
					<button type="submit" id="registerBtn" class="btn btn-primary" >Submit</button>
					<a href="#" class="btn" data-dismiss="modal">Close</a>
				</div>
			</g:form>
		</div>
	</div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<div id="footer" class="section footer " style = "position: relative ;height: 100px; background-color:#000000">
		<div class="container align-center" style="margin-top: 0px; border-top-width: 50px; padding-top: 50px;">
			<p class="text-muted credit align-center" style = "color: #777777; font-size: 16px; font-weight: 300; line-height: 1.6em;">&copy; Real Realty 2014</p>
		</div>
	</div>
</body>
</html>