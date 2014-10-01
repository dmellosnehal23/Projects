
 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

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
<title>Home</title>

<%@ page import=  "entity.Movie"%>
<%@ page import=  "entity.Person"%>
<%@ page import=  "java.util.Date"%>
<%@ page import=  "java.text.SimpleDateFormat"%>
<%@ page import=  "java.util.Calendar"%>
</head>

<%
	HttpSession sess = request.getSession(false);

	Movie[] listMovies	= (Movie[])sess.getAttribute("displayMovies");
	Person personDetails	= (Person)sess.getAttribute("personDetails");
	session.setAttribute("personDetails", personDetails);
	session.setAttribute("memberId", personDetails.getMembershipId());
	session.setAttribute("userType", personDetails.getUserType());


 %>
 
<body>
<% 
	Calendar TimeStop = personDetails.getLastLogin();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String TimeStop_Str = sdf.format(TimeStop.getTime());
%>

<div id="templatemo_wrapper">
	<div id="templatemo_menu">
	<center>
		<ul>
			<li><span>Video Library Management System</span></li>
		</ul>
	</center>
         	
    </div> 						<!-- end of templatemo_menu -->
    
    
    
      <div id="templatemo_header_bar">
    
    	<div id="menu">
		<ul>
			<li class="first"><a href="memberhome.jsp">Home</a></li>
			<li><a href =" memberhome?formname=allMovies">View Movies</a></li>
			<li><a href =" memberhome?formname=myAccount">My Account</a></li>
			
			<li><a href =" memberhome?formname=myCart">View Cart</a></li>
			<li ><a href="logout">Logout</a></li>
			<li class="last">Last Login <%= TimeStop_Str%></li>
			
		</ul>
		</div>
    
    
    
	</div>          <!-- end of templatemo_header_bar -->
     
    <div class="cleaner"></div>
    
        <div id="sidebar"><div class="sidebar_top"></div><div class="sidebar_bottom"></div>
    	
        <div class="sidebar_section">
        
            <h2>Search</h2>
            
          <!--   <form id="login" method="post" action="login" onsubmit="return validateForm(form1)">
                <label>Username</label>
                <input type="text" value="" name="username" size="10" class="input_field" title="username" />
                <label>Password</label>
                <input type="password" value="" name="password" class="input_field" title="password" />
                <a href="signup.jsp">Create Account</a> 
              <input type="submit" name="login" value="Login" alt="Login" id="" title="" />
            </form> -->
            
        <form name='searchMovie' action='searchForMember' method='post' onsubmit="return isSelect();" >
		<input type='hidden' name='formname' value='searchMovie'/>		
		
		<label>Search Movie By:</label>              
	  	<select name="searchBy" id="select" class="input_field">
	                    <option value="">Select</option>
	                    <option value="movieName">Name</option>
	                    <option value="movieBanner">Banner</option>
	                    <option value="movieRent">Rent</option>
	                    </select>
		<label>Keyword:</label>
		<input type='text' name='searchTextValue' class="input_field"/>		
		<input type='submit' value='Go'/>
		
		</form>
            
		<div class="cleaner"></div>
            
		</div>
        
        <div class="sidebar_section">
        
        	<h2>Categories</h2>
                    
            <ul class="categories_list">
            
                <li><a href="searchForMember?formname=searchMovie&searchBy=movieCategory&searchTextValue=Action">Action</a></li>
                <li><a href="searchForMember?formname=searchMovie&searchBy=movieCategory&searchTextValue=Comedy">Comedy</a></li>
                <li><a href="searchForMember?formname=searchMovie&searchBy=movieCategory&searchTextValue=Drama">Drama</a></li>
                <li><a href="searchForMember?formname=searchMovie&searchBy=movieCategory&searchTextValue=Animation">Animation</a></li>
                <li><a href="searchForMember?formname=searchMovie&searchBy=movieCategory&searchTextValue=Documentary">Documentary</a></li>
                <li><a href="searchForMember?formname=searchMovie&searchBy=movieCategory&searchTextValue=Family">Family</a></li>
                <li><a href="searchForMember?formname=searchMovie&searchBy=movieCategory&searchTextValue=Sci-Fi">Sci-Fi</a></li>
                <li><a href="searchForMember?formname=searchMovie&searchBy=movieCategory&searchTextValue=Horror">Horror</a></li>
                <li><a href="searchForMember?formname=searchMovie&searchBy=movieCategory&searchTextValue=Romance">Romance</a></li>
               
            </ul>
        </div>
        
        
        
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
        
              
        	<h2> Welcome <%= personDetails.getFirstName() %> <%= personDetails.getLastName() %></h2>
            
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
							type='video/ogg; codecs="theora, vorbis"'></source> </video>

					</div>
                            
                    </div>
                    
                    <div class="product_box margin_r35">
                        <h3>The Hulk</h3>
    
                      <div class="image_wrapper"> 
                      <video id="sampleMovie"  width="200" height="200"  preload controls>

						<source src="big_buck_bunny_480p.ogv"
							type='video/ogg; codecs="theora, vorbis"'></source> </video>
                       </div>
                                                
                     </div>
                    
                    <div class="product_box">
                        <h3>New Moon</h3>
    
                      <div class="image_wrapper">
                      <video id="sampleMovie"  width="200" height="200" preload controls>

						<source src="big_buck_bunny_480p.ogv"
							type='video/ogg; codecs="theora, vorbis"'></source> </video>
                        </div>
                    
                    </div>
                                            
        </div> 
    
    </div> <!-- end of templatmeo_content -->
    
    
</div> <!-- end of templatemo_wrapper -->

<div id="templatemo_footer_wrapper">

	<div id="templatemo_footer">
    
    	<ul class="footer_menu">
            <li><a href="about.jsp">About US</a></li>
            <li><a href="contactUs.jsp">Contact Us</a></li>
            <li class="last_menu"><a href="terms.jsp">Terms</a></li>
      
        </ul>

       | Copyright © 2012 <a href="">273 Project</a> | 
       
    
    </div> <!-- end of footer -->

</div> <!-- end of footer_wrapper -->

</body>
</html>

