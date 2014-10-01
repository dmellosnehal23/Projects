
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Sign Up</title>
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
<script src="js.js">

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
<%String parameter =request.getParameter("flag");
out.println("<form name=form1>");
out.println("<input type='hidden' name='param' value="+parameter+"></form>");

System.out.println("Paramter is " +parameter);        

%>
<script type="text/javascript">
function invalidlogin()
{
       var p1= form1.param.value;
        if (p1=="yes")
                {
       alert("Already exists.");
                }
}

</script>
<script>
function showDiv()
{
       
    	   if(document.getElementById("userType").value == "0")
           {
               document.getElementById("div1").style.display = "block";
               document.getElementById("div11").style.display = "block";

               document.getElementById("div2").style.display = "block";
               document.getElementById("div22").style.display = "block";

           }
       else if(document.getElementById("userType").value == "1"){
           document.getElementById("div1").style.display = "none";
           document.getElementById("div11").style.display = "none";

           document.getElementById("div2").style.display = "none";
           document.getElementById("div22").style.display = "none";

       }
   }
   function showRate()
   {
       if(document.getElementById("plan").value == "1")
           document.getElementById("rate").value = "50 $";
       else if(document.getElementById("plan").value == "2")
           document.getElementById("rate").value = "75 $";
       else if(document.getElementById("plan").value == "3")
           document.getElementById("rate").value = "100 $";
       else if(document.getElementById("plan").value == "4")
           document.getElementById("rate").value = "125 $";
      
          
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
                <li><a href="#">Action</a></li>
                <li><a href="#">Commedy</a></li>
                <li><a href="#">Drama</a></li>
                <li><a href="#">Animation</a></li>
                <li><a href="#">Documentry</a></li>
                <li><a href="#">Family</a></li>
                <li><a href="#">Sci-fi</a></li>
                <li><a href="#">Horror</a></li>
                <li><a href="#">Romance</a></li>
               
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
        
              <%!String states[]={
			"Select State",
		    "Alabama",
		    "Alaska",
		    "American Samoa",
		    "Arizona",
		    "Arkansas",
		    "California",
		    "Colorado",
		    "Connecticut",
		    "Delaware",
		    "District of Columbia",
		    "Florida",
		    "Georgia",
		    "Guam",
		    "Hawaii",
		    "Idaho",
		    "Illinois",
		    "Indiana",
		    "Iowa",
		    "Kansas",
		    "Kentucky",
		    "Louisiana",
		    "Maine",
		    "Maryland",
		    "Massachusetts",
		    "Michigan",
		    "Minnesota",
		    "Mississippi",
		    "Missouri",
		    "Montana",
		    "Nebraska",
		    "Nevada",
		    "New Hampshire",
		    "New Jersey",
		    "New Mexico",
		    "New York",
		    "North Carolina",
		    "North Dakota",
		    "Northern Marianas Islands",
		    "Ohio",
		    "Oklahoma",
		    "Oregon",
		    "Pennsylvania",
		    "Puerto Rico",
		    "Rhode Island",
		    "South Carolina",
		    "South Dakota",
		    "Tennessee",
		    "Texas",
		    "Utah",
		    "Vermont",
		    "Virginia",
		    "Virgin Islands",
		    "Washington",
		    "West Virginia",
		    "Wisconsin",
		    "Wyoming"
}; %>

		<div style="height:500px;border:1px solid #ccc;width:800px;font:16px/26px Georgia, Garamond, Serif;overflow:auto;">
        <H2 ALIGN="CENTER">Sign Up</H2> 


                 <form name="signup" method="post" action="signup" onsubmit="return validateSignUp()">
              	 <table align="center" style="font-family: cursive;margin-left: 150px" border="1">
                    
                   <tr><td> <label class="contact"><strong>Membership Id:</strong></label></td>
                   <td> <input type="text" class="contact_input" name="membershipId" required/>(xxx-xx-xxxx)</td>
					</tr>  
					                         
                    <tr><td><label class="contact"><strong>First Name:</strong></label></td>
                   <td> <input type="text" class="contact_input" name="fname" required/></td>
                    </tr>

					<tr><td><label class="contact"><strong>Last Name:</strong></label></td>
                    <td><input type="text" class="contact_input" name="lname" required/></td>
					</tr> 
					 
					 <!-- Change type="email"... -->        
					<tr><td><label class="contact"><strong>Email:</strong></label></td>
                    <td><input type="email" class="contact_input" name="email" required/></td>
                    </tr>

					<tr><td><label class="contact"><strong>Password:</strong></label></td>
                    <td><input type="password" class="contact_input" name="password" required/></td>
                    </tr>
                    
					<tr><td><label class="contact"><strong>Confirm Password:</strong></label></td>
                   <td> <input type="password" class="contact_input" name="cpassword" required/></td>
                    </tr>
                    
					<tr><td><label class="contact"><strong>Address:</strong></label></td>
                   <td> <textarea class="contact_textarea" name="address"></textarea></td>
                    </tr>
                    
					<tr><td><label class="contact"><strong>City:</strong></label></td>
                   <td> <input type="text" class="contact_input" name="city" required/></td>
                    </tr>
                    
					<tr><td><label class="contact"><strong>State:</strong></label></td>
	                   <td><select name="state" >
	                   	<%for(int i=0;i<states.length;i++ ){ %>
	                   		<option value="<%=states[i]%>"><%=states[i]%></option>
	                   	<%} %>
	                   </select></td>
                    </tr>
                    
					<tr><td><label class="contact"><strong>Zipcode:</strong></label></td>
                   <td> <input type="text" class="contact_input" name="zipcode" required/>-<input type="text" class="contact_input" name="zipcode1"/></td>
                    </tr>
                    
					<tr><td> <label class="contact"><strong>Memebership Type:</strong></label></td>
                  <td>  <select name="userType" id="userType" onchange="showDiv();">
                    <option value="select">Select Type</option>
                    <option value="1">Regular</option>
                    <option value="0">Premium</option>
                    </select></td>
                    </tr>
                   
                   
					<tr><td>
					<div class="form_row" id="div1" style="display:none">
					
                    <label class="contact"><strong>Subscription Plan :</strong></label>
                    </div></td>
                    
                   <td><div class="form_row" id="div11" style="display:none">
                    <select name="plan" id="plan" onchange="showRate()">
                    <option value="select">Select Plan</option>
                    <option value="1">Monthly</option>
                   <!--  <option value="2">Quarterly</option>
                    <option value="3">Half-yearly</option>
                    <option value="4">Yearly</option>
                    </select> -->
                    </div></td>
					</tr>


					<tr><td>
					<div class="form_row" id="div2" style="display:none">
					
					<label class="contact" ><strong>Subscription Rate :</strong></label></div></td>
					<td><div class="form_row" id="div22" style="display:none">
					<input type="text" id="rate" class="contact_input" name="rate" value="" disabled /></div></td>
					
					</tr>
					
                    <tr></tr>
					<tr>
                  <!--  <td> <input type="submit" name="signup" value="Cancel" class="contact" /></td>
                   <td> <input type="submit" name="signup" value="Reset" class="contact" /></td>
                   -->  <td><input type="submit" name="signup" value="Submit" class="contact" /></td>
                    </tr>
 
              </table>
                </form>
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

