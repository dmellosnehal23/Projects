
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page import=  "entity.Movie"%>
<%@ page import=  "entity.*"%>
<%@ page import=  "java.util.Date"%>
<%@ page import=  "java.text.SimpleDateFormat"%>
<%@ page import=  "java.util.Calendar"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>List Movies</title>
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
</head>



<%
	HttpSession sess = request.getSession(false);

	Movie[] listMovies	= (Movie[])sess.getAttribute("displayMovies");
 %>

<body>
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
			<li class="first"><a href="adminhome.jsp"><b>Home</b></a>
				
							
			</li>
			
			<li>
				<span class="opener"><b>Movies</b></span>
				<ul>
					<li><a href="adminHome?formname=addMovie">Add Movie</a></li>
					<li><a href="adminHome?formname=allMovies">View Movies</a></li>
				</ul>
				
			</li>
			
			<li>
				<span class="opener"><b>View Members</b></span>
				<ul>
					<li><a href="adminHome?formname=displayAllMembers">All Members</a></li>
					<li><a href="adminHome?formname=displaySimple">Simple Members </a></li>
					<li><a href="adminHome?formname=displayPremium">Premium Members </a></li>
				</ul>
				
			</li>
			
			<li>
				<span class="opener"><b>Categories</b></span>
				<ul>
					<li><a href="searchForAdmin?formname=searchMovie&searchBy=movieCategory&searchTextValue=Action">Action</a></li>
                    <li><a href="searchForAdmin?formname=searchMovie&searchBy=movieCategory&searchTextValue=Comedy">Comedy</a></li>
                    <li><a href="searchForAdmin?formname=searchMovie&searchBy=movieCategory&searchTextValue=Drama">Drama</a></li>
                    <li><a href="searchForAdmin?formname=searchMovie&searchBy=movieCategory&searchTextValue=Animation">Animation</a></li>
                    <li><a href="searchForAdmin?formname=searchMovie&searchBy=movieCategory&searchTextValue=Documentary">Documentary</a></li>
                    <li><a href="searchForAdmin?formname=searchMovie&searchBy=movieCategory&searchTextValue=Family">Family</a></li>
                    <li><a href="searchForAdmin?formname=searchMovie&searchBy=movieCategory&searchTextValue=Sci-Fi">Sci-Fi</a></li>
                    <li><a href="searchForAdmin?formname=searchMovie&searchBy=movieCategory&searchTextValue=Horror">Horror</a></li>
                    <li><a href="searchForAdmin?formname=searchMovie&searchBy=movieCategory&searchTextValue=Romance">Romance</a></li>

				</ul>
				
			</li>
			
			
			<li  class="last"><a href="logout"><b>Logout</b></a></li>
		</ul>
		</div>
    
    	<!-- <li><a href =" memberhome?formname=allMovies">View Movies</a></li> -->
    
    
    
	</div>          <!-- end of templatemo_header_bar -->
     
    <div class="cleaner"></div>
    
        <div id="sidebar"><div class="sidebar_top"></div><div class="sidebar_bottom"></div>
    	
        <div class="sidebar_section">
        
            <h2>Search Movie</h2>
            
          <!--   <form id="login" method="post" action="login" onsubmit="return validateForm(form1)">
                <label>Username</label>
                <input type="text" value="" name="username" size="10" class="input_field" title="username" />
                <label>Password</label>
                <input type="password" value="" name="password" class="input_field" title="password" />
                <a href="signup.jsp">Create Account</a> 
              <input type="submit" name="login" value="Login" alt="Login" id="" title="" />
            </form> -->

				<form name='searchMovie' action='searchForAdmin' method='post' onsubmit="return isSelect()">
					<input type='hidden' name='formname' value='searchMovie' />

					<label>Search Movie By:</label>
					<select name="searchBy" id="select" class="input_field">
						<option value="">Select</option>
						<option value="movieName">Name</option>
						<option value="movieBanner">Banner</option>
						<option value="movieRent">Rent</option>
					</select>
					<label>Keyword:</label>
					<input type='text' name='searchTextValue' class="input_field" size="10" />
					<input type='submit' value='Go' />

				</form>
				
				<div class="cleaner"></div>
            
		</div>

		<div class="sidebar_section">
		
			 <h2>Search Member</h2>
				<form name='searchPerson' action='searchForAdmin' method='post' onsubmit="return isSelect1()">
					<input type='hidden' name='formname' value='searchPerson' />

					<label>Search Person By:</label>
					<select name="searchBy" id="select1" class="input_field">
						<option value="">Select</option>
						<option value="firstName">First Name</option>
						<option value="lastName">Last Name</option>
						<option value="emailId">Email Id</option>
						<option value="city">City</option>
						<option value="state">State</option>
					</select>
					<label>Keyword:</label>
					<input type='text' name='searchTextValue' class="input_field" size="10" />
					<input type='submit' value='Go' />

				</form>

				<div class="cleaner"></div>
            
		</div>
        
        <!-- <div class="sidebar_section">
        
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
        </div> -->
        
        
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
        	
        		<div style="height:500px;border:1px solid #ccc;width:800px;font:16px/26px Georgia, Garamond, Serif;overflow:auto;">
        					
					<H2 ALIGN="CENTER">List of Movies</H2>
					
					<%
					if (listMovies == null) {
						out.println("<h3 align='center'>No Movies</h3>");
					} else {
				%>
					
					
					<TABLE align='center' style="font-family: cursive;margin-left: 20px" border="1">
						<TH>Number
						<TH>Movie Name</TH>
						<TH>Banner</TH>
						<TH>Release Date</TH>
						<TH>Category</TH>
						<TH>Rent($)</TH>
						<TH>Copies Availble</TH>
						<TH>Update</TH>
						<TH>Delete</TH>
						</TR>
						</TR>
					
						<% 
					for(int i=0;i<listMovies.length;i++){ %>
					
						<form name='updateMovie' action='update' method='get'>
							<input type='hidden' name='formname' value='updateMovie'>
							<input type='hidden' name='movieId' value=<%=listMovies[i].getMovieId()%>>
							<% 
							Calendar TimeStop = listMovies[i].getMovieReleaseDate();
						    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						    String TimeStop_Str = sdf.format(TimeStop.getTime());
							%>
							<TR>
					
								<TD><%=(i+1) %></TD>
								<TD><a href="movieInfoAdmin?movieId=<%=listMovies[i].getMovieId()%>"><%=listMovies[i].getMovieName() %></a>
								<TD><%=listMovies[i].getMovieBanner()%>
								<TD><%= TimeStop_Str %>
								<TD><%=listMovies[i].getMovieCategory()%>
								<TD><%=listMovies[i].getMovieRent()%>
								<TD><%=listMovies[i].getAvailableCopies()%>
								<TD><input type='submit' value='Update'></td>
						</form>
					
						<form name='deleteMovie' action='delete' method='post'>
							<input type='hidden' name='movieId' value="<%=listMovies[i].getMovieId()%>"/>	
							<input type='hidden' name='movieName' value="<%=listMovies[i].getMovieName() %>"/>
					
							<input type='hidden' name='formname' value='deleteMovie'/>
							<TD><input type='submit' value='Delete'></td>
							</tr>
							
						</form>
					
					
						<% 
					  }}
					
					%>
				</TABLE>
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