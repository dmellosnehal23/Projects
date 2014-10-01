
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Login</title>
<meta name="description" content="" />
<meta name="keywords" content="" />
<link rel="stylesheet" type="text/css" href="templatemo_style.css" />
<link rel="stylesheet" type="text/css" href="stylesheet/styles.css" />
<!-- <link rel="stylesheet" type="text/css" href="style.css" /> -->

<script language="javascript" type="text/javascript">
function clearText(field)
{
    if (field.defaultValue == field.value) field.value = '';
    else if (field.value == '') field.value = field.defaultValue;
}
</script>
<script language="javascript" type="text/javascript" src="scripts/mootools-1.2.1-core.js"></script>
<script language="javascript" type="text/javascript" src="scripts/mootools-1.2-more.js"></script>
<script language="javascript" type="text/javascript" src="scripts/slideitmoo-1.1.js"></script>
<script type="text/javascript" src="jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="jquery.dropotron-1.0.js"></script>
<script type="text/javascript" src="jquery.slidertron-1.1.js"></script>
<script type="text/javascript" src="js.js"></script>

<script type="text/javascript">
	$(function() {
		$('#menu > ul').dropotron({
			mode: 'fade',
			globalOffsetY: 11,
			offsetY: -15
		});
		/* $('#slider').slidertron({
			viewerSelector: '.viewer',
			indicatorSelector: '.indicator span',
			reelSelector: '.reel',
			slidesSelector: '.slide',
			speed: 'fast',
			advanceDelay: 4000
		}); */
	});
</script>
<%String parameter =request.getParameter("isValid");
out.println("<form name=form1>");
out.println("<input type='hidden' name='param' value="+parameter+"></form>");

System.out.println("Paramter is " +parameter);	

%>

<script type="text/javascript">
function invalidlogin()
{
	var p1= form1.param.value;
	 if (p1=="No")
		 {
	alert("Invalid login Details. Try Again.");
 

		 }
}

</script>

<!-- <script language="javascript" type="text/javascript">
	window.addEvents({
		'domready': function(){
			/* thumbnails example , div containers */
			new SlideItMoo({
						overallContainer: 'SlideItMoo_outer',
						elementScrolled: 'SlideItMoo_inner',
						thumbsContainer: 'SlideItMoo_items',		
						itemsVisible: 5,
						elemsSlide: 3,
						duration: 200,
						itemsSelector: '.SlideItMoo_element',
						itemWidth: 140,
						showControls:1});
		},
		
	});
</script> -->
</head>

<body>
<script type="text/javascript">invalidlogin()</script>
<div id="templatemo_wrapper">
	<div id="templatemo_menu">
	<center>
		<ul>
			<li><span>Video Library Management System</span></li>
		</ul>
	</center>
        <!-- <ul>
            <li><a href="#" class="current"><span>.01</span>Home</a></li>
            <li><a href="http://www.templatemo.com/page/1" target="_parent"><span>.02</span>Templates</a></li>
            <li><a href="http://www.flashmo.com/page/1" target="_parent"><span>.03</span>Flash</a></li>
            <li><a href="http://www.koflash.com" target="_parent"><span>.04</span>Gallery</a></li>
            <li><a href="#"><span>.05</span>Company</a></li>
            <li><a href="#"><span>.06</span>Contact</a></li>
        </ul>  -->   	
    </div> 						<!-- end of templatemo_menu -->
    
    
    
      <div id="templatemo_header_bar">
      		<!--Menu Here -->
    
            <!-- <div id="header"><div class="right"></div>
            
                <h1><a href="#">
                    <img src="images/templatemo_logo.png" alt="Site Title" />
                    <span>Online Store Template</span>
                </a></h1>
            </div>
            
            <div id="search_box">
                <form action="#" method="get">
                    <input type="text" value="Enter keyword here..." name="q" size="10" id="searchfield" title="searchfield" onfocus="clearText(this)" onblur="clearText(this)" />
                    <input type="submit" name="Search" value="" alt="Search" id="searchbutton" title="Search" />
                </form>
            </div>
            
            <div id="slider">
				<div class="viewer">
			<div class="reel">
				<div class="slide">
					<img src="images/slide01.jpg" alt="" />
				</div>
				<div class="slide">
					<img src="imagess/slide02.jpg" alt="" />
				</div>
				<div class="slide">
					<img src="images/slide03.jpg" alt="" />
				</div>
				<div class="slide">
					<img src="images/slide04.jpg" alt="" />
				</div>
				<div class="slide">
					<img src="images/slide05.jpg" alt="" />
				</div>
			</div>
		</div>
		<div class="indicator">
			<span>1</span>
			<span>2</span>
			<span>3</span>
			<span>4</span>
			<span>5</span>
		</div>
	</div> -->
         
    
    
	</div>          <!-- end of templatemo_header_bar -->
     
    <div class="cleaner"></div>
    
        <div id="sidebar"><div class="sidebar_top"></div><div class="sidebar_bottom"></div>
    	
        <div class="sidebar_section">
        
            <h2>Members</h2>
            
            <form id="login" method="post" action="login" >
                <label>Username</label>
                <input type="text" value="" name="email" size="10" class="input_field" title="username" />
                <label>Password</label>
                <input type="password" value="" name="password" class="input_field" title="password" required />
                <a href="signup.jsp">Create Account</a> 
              <input type="submit" name="login" value="Login" alt="Login" id="" title="" />
            </form>
            
			<div class="cleaner"></div>
            
		</div>
        
        <div class="sidebar_section">
        
        	<h2>Categories</h2>
                    
            <ul class="categories_list">
                <li><a href="">Action</a></li>
                <li><a href="">Commedy</a></li>
                <li><a href="">Drama</a></li>
                <li><a href="">Animation</a></li>
                <li><a href="">Documentry</a></li>
                <li><a href="">Family</a></li>
                <li><a href="">Sci-fi</a></li>
                <li><a href="">Horror</a></li>
                <li><a href="">Romance</a></li>
               
            </ul>
        </div>
        
        <!-- <div class="sidebar_section">
        
            <h2>Discounts</h2>
            
            <div class="image_wrapper"><a href="http://www.templatemo.com/page/7" target="_parent"><img src="images/image_01.jpg" alt="product" /></a></div>            
          <div class="discount"><span>25% off</span> | <a href="#">Read more</a></div>
        
        </div>   -->
        
    </div> <!-- end of sidebar -->
       
    <div id="templatmeo_content" >
    
           
    
	    	<div id="latest_product_gallery">
	    			<h2>Featured Movies</h2>
	    			<div id="SlideItMoo_outer">	
                <div id="SlideItMoo_inner">			
                    <div id="SlideItMoo_items">
                        <div class="SlideItMoo_element">
                                <a href="#">
                                <img src="images/mov1.jpg" alt="product 1" /></a>
                           
                        </div>	
                        <div class="SlideItMoo_element">
                                <a href="#">
                                <img src="images/mov2.jpg" alt="product 2" /></a>
        
                        </div>
                        <div class="SlideItMoo_element">
                                <a href="#">
                                <img src="images/mov3.jpg" alt="product 3" /></a>
        
                        </div>
                        <div class="SlideItMoo_element">
                                <a href="#">
                                <img src="images/mov4.jpg" alt="product 4" /></a>
        
                        </div>
                        <div class="SlideItMoo_element">
                                <a href="#">
                                <img src="images/mov5.jpg" alt="product 4" /></a>
        
                        </div>
                        
                    </div>			
                </div>
            </div>	<!-- end of latest_content_gallery -->
            </div> 	
            
   
        <div class="content_section">
        
              
        	<h2>Welcome to online Video Library</h2>
            
<!--             <p><a href="http://www.templatemo.com" target="_parent">Free CSS Templates</a> are provided by <a href="http://www.templatemo.com" target="_parent">TemplateMo.com</a> for everyone. Feel free to use this template for your websites. Credit goes to <a href="http://www.photovaco.com" target="_blank">Free Photos</a> for photos used in this template. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed et quam vitae ipsum vulputate varius vitae semper nunc. Quisque eget elit quis augue pharetra feugiat.</p>
 -->     	 </div>
        
        <div class="content_section">
       
        	<h2>Our Movies</h2>

				<!-- <video id="sampleMovie" width="640" height="360" preload controls>

				<source src="big_buck_bunny_480p.ogv"
					type='video/ogg; codecs="theora, vorbis"'/>
				 </video> -->


				  <div class="product_box margin_r35">
                    
	                    <h3>Ice Age4</h3>
    
                      <div class="image_wrapper">
						<video id="sampleMovie"  width="200" height="200" preload controls>

						<source src="big_buck_bunny_480p.ogv"
							type='video/ogg; codecs="theora, vorbis"' /> </video>

					</div>
                            
                    </div>
                    
                    <div class="product_box margin_r35">
                        <h3>The Hulk</h3>
    
                      <div class="image_wrapper"> 
                      <video id="sampleMovie"  width="200" height="200"  preload controls>

						<source src="The_Incredible_Hulk.ogv"
							type='video/ogg; codecs="theora, vorbis"' /> </video>
                       </div>
                                                
                     </div>
                    
                    <div class="product_box">
                        <h3>New Moon</h3>
    
                      <div class="image_wrapper">
                      <video id="sampleMovie"  width="200" height="200" preload controls>

						<source src="big_buck_bunny_480p.ogv"
							type='video/ogg; codecs="theora, vorbis"' /> </video>
                        </div>
                    
                    </div>
                                            
        </div> 
    
    </div> <!-- end of templatmeo_content -->
    
    
</div> <!-- end of templatemo_wrapper -->

<div id="templatemo_footer_wrapper">

	<div id="templatemo_footer">
    
    	<ul class="footer_menu">
            <li><a href="">About US</a></li>
            <li><a href="">Contact Us</a></li>
            <li class="last_menu"><a href="">Terms</a></li>
      
        </ul>

       | Copyright © 2012 <a href="">273 Project</a> | 
       
    
    </div> <!-- end of footer -->

</div> <!-- end of footer_wrapper -->
</body>
</html>