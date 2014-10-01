<!DOCTYPE html>
<html lang="en">
<head>
<title>Real Estate Prediction Engine</title>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="description">
<meta content="" name="author">

<!-- Le styles -->
<style type="text/css">
.gm-style .gm-style-mtc label,.gm-style .gm-style-mtc div
{
	font-weight: 400
}
</style>
<style type="text/css">
.gm-style .gm-style-cc span,.gm-style .gm-style-cc a,.gm-style .gm-style-mtc div
	{
	font-size: 10px
}
</style>
<style type="text/css">
@media print {
	.gm-style .gmnoprint,.gmnoprint {
		display: none
	}
}

@media screen {
	.gm-style .gmnoscreen,.gmnoscreen {
		display: none
	}
}
</style>
<style type="text/css">
.gm-style {
	font-family: Roboto, Arial, sans-serif;
	font-size: 11px;
	font-weight: 400;
	text-decoration: none
}
</style>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-responsive.min.css">

<script src="//www.google-analytics.com/ga.js" style=""></script>

<!-- Bootstrap -->
<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'bootstrap.min.css')}">
<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'bootstrap-responsive.min.css')}">
<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'bootstrap-responsive.css')}">
<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'font-awesome.css')}">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<link rel="stylesheet" type="text/css"  href="${resource(dir: 'fancybox', file: 'jquery.fancybox-v=2.1.5.css')}" media="screen">

<!-- CSS (necessary for Bootstrap's CSS ) -->

<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'font-awesome.min.css')}" >
<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'style.css')}">
<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Titillium+Web:400,600,300,200&subset=latin,latin-ext">

<style>
/**marketing **/

.marketing {
    color: #5A5A5A;
    text-align: center;
    padding-left: 15px;
  	padding-right: 15px;
}

.marketing h1 {
    font-size: 60px;
    font-weight: 200;
    letter-spacing: -1px;
    line-height: 1;
    margin: 60px 0 10px;
}

.marketing .marketing-byline {
    color: #999999;
    font-size: 20px;
    font-weight: 300;
    line-height: 1.25;
    margin-bottom: 40px;
}

.marketing-img {
    display: block;
    margin: 0 auto 30px;
    max-height: 200px;
}

* MARKETING CONTENT
-------------------------------------------------- */



/* Center align the text within the three columns below the carousel */
.marketing .col-lg-4 {
  text-align: center;
  margin-bottom: 20px;
}
.marketing h2 {
  font-weight: normal;
}
.marketing .col-lg-4 p {
  margin-left: 10px;
  margin-right: 10px;
}

/* CUSTOMIZE THE CAROUSEL
-------------------------------------------------- */

/* Carousel base class */
.carousel {
  margin-bottom: 60px;
}
/* Since positioning the image, we need to help out the caption */
.carousel-caption {
  z-index: 10;
}

/* Declare heights because of positioning of img element */
.carousel .item {
  height: 400px;
  background-color:#555;
}
.carousel img {
  position: absolute;
  top: 0;
  left: 0;
  min-height: 600px;
}

/* faster sliding speed */
.carousel-inner > .item {
    -webkit-transition: 0.3s ease-in-out left;
    -moz-transition: 0.3s ease-in-out left;
    -o-transition: 0.3s ease-in-out left;
    transition: 0.3s ease-in-out left;
}
</style>

<g:javascript src="html5shiv.js"></g:javascript>
<g:javascript src="jquery-1.10.2.min.js"></g:javascript>
<g:javascript src="jquery-migrate-1.2.1.min.js"></g:javascript>
<g:javascript src="bootstrap.min.js"></g:javascript>
<g:javascript src="jquery.easing.1.3.js"></g:javascript>
<g:javascript src="jquery.raty.js"></g:javascript>
<script type="text/javascript" src="fancybox/jquery.fancybox.pack-v=2.1.5.js"></script>
<g:javascript src="operations.js"></g:javascript>
<script type="text/javascript" src="www.google-analytics.com/ga.js"></script>

<script>
$(document).ready(function(e) {
	var lis = $('.nav > li');
	menu_focus(lis[0], 1);

	$(".fancybox").fancybox({
		padding : 10,
		helpers : {
			overlay : {
				locked : false
			}
		}
	});

});
</script>
<script>
!function($) {
	$(function() {
		// carousel demo
		$('#myCarousel').carousel()
	})
}(window.jQuery)
</script>


<g:javascript library="jquery" />
<g:javascript library="jquery-ui" />

<r:layoutResources/>
<script>
function populate()
{	
	var list = $("#updateMe").html().replace(/'/g, "")
	
	var res = list.split(",");
	
	var rlist = [] ;
	res[0] = res[0].substring(1); //removing [
	var len = res.length 
	res[len-1] = res[len-1].substring(0, res[len-1].length -1); // removing ]
	
	for(var i in res)
		{	
			rlist.push(res[i]);
		}				
	
	$( "#searchbar" ).autocomplete({
	      	      source:  rlist
	      	    });
}
                    
</script>
</head>

<body>
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
							<% if(session.username == "admin"){ %>
							<li style="color: white; padding-top: 10px;">
								<i class="icon-user icon-white"></i>  
							</li>
							<li class="active">
								<g:link controller="home" action="admin">Admin</g:link>
							</li>
							<%}%>
							<li style="color: white; padding-top: 10px;">
								<i class="icon-user icon-white"></i>  
							</li>
							<li><a href="#about">About Us</a></li>
							<li style="color: white; padding-top: 10px;">
								<i class="icon-signal icon-white"></i>  
							</li>
							<li><a href="#whatwedo">What We Do</a></li>
							<li style="color: white; padding-top: 10px;">
								<span class="icon-envelope icon-white"></span>  
							</li>
							<li><a href="#contact">Contact Us</a></li>
						</ul>
						<ul class="nav pull-right">
							<% if(session.username != null){ %>
								<li style="color: white; padding-top: 10px;">
									<i class="icon-th-list icon-white"></i>
								</li>
								<li>
									<g:link controller="restClient" action="getUserWatchlist">My Watclist</g:link>
								</li>
								<li style="color: white; padding-top: 10px;">
									<i class="icon-user icon-white"></i> ${session.username}</li>
								<li class="divider-vertical"></li>
								<li style="color: white; padding-top: 10px;">
									<i class="icon-user icon-white"></i>
								</li>
								<li>
									<g:link controller="user" action="logout"> Logout</g:link>
								</li>
							<% }
							else
							{ %>
								<li style="color: white; padding-top: 10px;">
									<i class="icon-user icon-white"></i>  
								</li>
								<li>
									<g:link mapping="register"> Sign In</g:link>
								</li>
							<%}%>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</header>
	<div class="container" style="width: 1280px; height: 600px;">
	<!-- === MAIN Background === -->
		<!-- Carousel
    	================================================== -->
		<div id="myCarousel" class="carousel slide" style="height: 340px;">
			<div class="carousel-inner" style="height: 380px;">
				<div class="item active">
					<g:img dir="images" file="property-main-1.jpg" class="img-responsive"/>
				</div>
				<div class="item">
					<g:img dir="images" file="property-main-2.jpg" class="img-responsive"/>
				</div>
				<div class="item">
					<g:img dir="images" file="property-main-3.jpg" class="img-responsive"/>
				</div>
			</div>
			<a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
	     	<a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
		</div>
		<!-- /.carousel -->
		<!-- Main hero unit for a primary marketing message or call to action -->
		<div class="col-lg-12 text-center v-center">
			<h1>Welcome to Real Realty!</h1>
			<p class="lead">Find the Best Places to Live</p>
		
			<g:form name="Search" class="col-lg-12" controller ="restClient" action="getProperties" >
			 <g:remoteField id="searchbar"  controller ="restClient" action="getPropertiesInfoByAjax" class="center-block form-control input-lg" value="${properties}" 
				required = "required" update="updateMe" onComplete="populate()" type="text" title="Search" placeholder="e.g. San Jose" name="query" /> 
			 <div id="updateMe" style="display: none" > ${properties}   </div>
			 <span class="input-group-btn">
				<button class="btn btn-lg btn-primary btn-inverse" type="submit">
					<i class="icon-search icon-white"></i>
				</button>
			</span>
			</g:form>
		</div>
	</div>
	
	<hr class="soften">
	<div class="marketing">
		<section id="about" class="section appear clearfix">
		
			<h2>Introducing </h2>
			<p class="lead marketing-byline">Team Real Realty</p>
			<hr class="soften">
			<div class="row-fluid" style="margin-left: 100px; margin-right: 100px;">
				<ul class="thumbnails" style="margin-left: 100px; margin-right: 100px;">
					<li class="span3" style="margin-left: 30px; margin-right: 45px;">
						<a class="thumbnail">
							<g:img dir="images" file="Minion-1.jpg"  
								style="height: 292px; width: 388px; padding-left: 4px; padding-right: 6px; border-left-width: 6px; border-right-width: 6px;"/>
							<h4>Priyanka Agal</h4>
						</a>
					</li>
					<li class="span3" style="margin-left: 45px; margin-right: 45px;">
						<a class="thumbnail">
							<g:img dir="images" file="Minion-2.jpg"  
								style="width: 388px; height: 292px; padding-right: 6px; border-left-width: 6px; padding-left: 4px;"/>
							<h4>Snehal Dmello</h4>
						</a>
					</li>
					<li class="span3" style="margin-left: 30px; margin-right: 45px;">
						<a class="thumbnail">
							<g:img dir="images" file="Minion-3.jpg"
								style="height: 292px; width: 388px; padding-left: 4px; border-left-width: 6px; padding-right: 6px; border-right-width: 6px; margin-left: 0px; margin-right: 30px;" />
							<h4>Abhijeet Upadhye</h4>
						</a>
					</li>
				</ul>
			</div>
		</section>
	</div>
	<hr class="soften">
	<div class="marketing">
		<section id="whatwedo">
			<h2 class="align-center">What We Do </h2>
			<p class="lead marketing-byline">We give you analytics</p>
			<hr class="soften">
			<div class="row-fluid" style="margin-left: 200px; width: 1170px; height: 332px; margin-right: 100px;">
            	<ul class="thumbnails" style="height: 320px;">
              		<li class="span4">
                		<div class="thumbnail" style="width: 310px; height: 315px;">
							<g:img dir="images" style="width: 300px; height: 200px;" alt="product name" file="feature3.jpg"/>
							<h3>Search home listings</h3>
							<p>We gather data from various websites to bring real estate listings.</p>
						</div>
					</li>
              		<li class="span4">
                		<div class="thumbnail" style="width: 310px; height: 315px;">
							<g:img dir="images" style="width: 300px; height: 200px;" alt="product name" file="feature2.jpg"/>
							<h3>Predictive Analytics</h3>
							<p>We anlayze the real estate data to predict rise and drop in prices</p>
						</div>
					</li>
					<li class="span4">
                		<div class="thumbnail" style="width: 310px; height: 315px;">
							<g:img dir="images" style="width: 300px; height: 200px;" alt="product name" file="feature1.jpg" />
							<h3>External factor Widgets</h3>
							<p>Show external factors affecting the real estate</p>
						</div>
					</li>
				</ul>
			</div>
		</section>
	</div>
	<hr class="soften">
	<section id="contact">
		<div id="contacts">
			<div class="row" style="margin-left: 420px; width: 600px;">
				<div class="col-sm-offset-3 col-sm-6">
					<g:form class="well" controller="restClient" action="postMessage" style="height: 410px;padding-bottom: 34px;">
						<legend>Contact Us</legend>
						<div class="control-group">
							<div class="controls">
								<input id="name" class="form-control" style="height: 20px; width: 550px;" type="text" data-validation-required-message="Please enter your name" required="" 
									placeholder="Full Name">
								<p class="help-block"></p>
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<input id="email" class="form-control" style="height: 20px; width: 550px;" type="email" data-validation-required-message="Please enter your email" required="" 
									placeholder="Email">
								<div class="help-block"></div>
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<textarea id="message" class="form-control" style="width: 550px;" maxlength="999" data-validation-minlength-message="Min 5 characters" 
									minlength="5" data-validation-required-message="Please enter your message" required="" placeholder="Message" cols="100" rows="10">
								</textarea>
								<div class="help-block"></div>
							</div>
						</div>
						<button type="submit" class="btn btn-primary pull-right" style="width: 70px;">Send</button>
					</g:form>
				</div>
			</div>
		</div>
	</section>
	<br>
	
	<div id="footer" class="section footer" style = "height: 100px; background-color:#000000">
		<div class="container align-center" style="margin-top: 0px; border-top-width: 50px; padding-top: 50px;">
			<p class="text-muted credit align-center" style = "color: #777777; font-size: 16px; font-weight: 300; line-height: 1.6em;">&copy; Real Realty 2014</p>
		</div>
	</div>
</body>
</html>