<!DOCTYPE html>
<html lang="en">
<head>
<title>Real Estate Prediction Engine</title>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="description">
<meta content="" name="author">

<g:javascript library="jquery" />

<!-- Le styles -->
<style type="text/css">
style type ="text /css">.gm-style .gm-style-mtc label,.gm-style .gm-style-mtc div {
	font-weight: 400
}
</style>
<style type="text/css">
.gm-style .gm-style-cc span,.gm-style .gm-style-cc a,.gm-style .gm-style-mtc div {
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


<!-- body { padding-top: 60px; padding-bottom: 40px; } -->
<!-- Bootstrap -->
<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'bootstrap.min.css')}">
<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'bootstrap-responsive.min.css')}">
<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'bootstrap-responsive.css')}">
<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'font-awesome.css')}">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<link rel="stylesheet" href="${resource(dir: 'fancybox', file: 'jquery.fancybox-v=2.1.5.css')}" type="text/css" media="screen">


<!-- CSS (necessary for Bootstrap's CSS ) -->

<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'font-awesome.min.css')}">
<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'style.css')}">
<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Titillium+Web:400,600,300,200&subset=latin,latin-ext">


<script type="text/javascript" src="js/html5shiv.js"></script>
<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="js/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="fancybox/jquery.fancybox.pack-v=2.1.5.js"></script>
<script type="text/javascript" src="js/script.js"></script>
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
<!-- fancybox init -->
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
						</ul>
						<div class="navbar-search pull-left">
							<g:form name="Search" class="navbar-form navbar-left" controller="restClient" action="getProperties">
								<div class="form-group fieldcontain text-center"> 
								<g:remoteField id="searchbar"  controller ="restClient" action="getPropertiesInfoByAjax" class="center-block form-control input-lg" value="${properties}" 
								required = "required" update="updateMe" onComplete="populate()" type="text" title="Search" placeholder="e.g. San Jose" name="query" /> 
								 <div id="updateMe" style="display: none" > ${properties}   </div>
								
									<span class="input-group-btn">
										<button class="btn btn-lg btn-primary" type="submit">Go!</button>
									</span>
								</div>
							</g:form>
						</div>
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
	
<!-- === MAIN Background === -->
<div id="wrap">
	<div class="container">
		<hr>
		
		<div class="row-fluid">
			<div class="panel panel-default" align="center" >
							<div class="media-body">
								<br>
								<br>
								<br>
								<h1>${ flash.errorMessage} </h1>
							</div>
			</div>
		</div> 
		
	</div>
	
</div>
	
</body>
</html>
