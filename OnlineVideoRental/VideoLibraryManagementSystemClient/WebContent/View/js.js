//for Login Page
//DO: for email type="email" & pass required

//Sign UP Page
//Do: all required

function validateSignUp()
{
	//for membership id
	var id = document.forms["signup"]["membershipId"].value.trim();
	// var regexObj = /^?([0-9]{3})?[-. ]?([0-9]{2})[-. ]?([0-9]{4})$/;
	var regexObj = /^\(?([0-9]{3})\)?[-. ]?([0-9]{2})[-. ]?([0-9]{4})$/;
	if (!regexObj.test(id)) {
	
		alert("Enter valid membershipID in valid format.. ");
		return false;
	}
	
	//for firstname
	var fname = document.forms["signup"]["fname"].value.trim();
	var letters = /^[a-zA-Z]+$/; 
	if(!fname.match(letters))  
	{
		alert("Please Enter valid Name");
		document.signup.fname.focus(); 
		return false;
	}
	
	//for lastname
	var lname = document.forms["signup"]["lname"].value.trim();
	var letters = /^[a-zA-Z]+$/;
	if(!lname.match(letters))  
	{
		alert("Please Enter valid Name");
		document.signup.lname.focus(); 
		return false;
	}
	
	//for password match  
	var password = document.forms["signup"]["password"].value;
	if(password.length<6)
		{
			alert ("Password length must be of atleast 6 characters..");
			document.signup.password.focus(); 
			return false;
			
		}
	var cpassword = document.forms["signup"]["cpassword"].value;
	if(cpassword!=password)
		{
			alert("Confirm Password must have to same as Password");
			document.signup.cpassword.focus(); 
			return false;
		}
	
	//for zipcode
	var zipcode = document.forms["signup"]["zipcode"].value.trim();
	var zipcode1 = document.forms["signup"]["zipcode1"].value.trim();
	var zip = /^[0-9]+$/;
	if(!zipcode.match(zip))  
	{
		alert("Please Enter valid Zipcode");
		document.signup.zipcode.focus(); 
		return false;
	}
	
	/*if(!isNaN(zipcode))
		{
			alert("Please Enter Digits for zipcode.");
			return false;
		}*/
	if(zipcode.length!=5)
		{
			alert("Enter zipcode of 5 digits..");
			document.signup.zipcode.focus();
			return false;
		}
	if(zipcode1.length!=0)
		{
			/*if(!isNan(zipcode1))
				{
					alert("Please Enter Digits for zipcode.");
					return false;
				}*/
			if(!zipcode1.match(zip))  
			{
				alert("Please Enter valid Zipcode");
				document.signup.zipcode1.focus(); 
				return false;
			}
			if(zipcode1.length!=4)
				{
					alert("Second part of zipcode is of only 4 digits.");
					return false;
				}
		}
	
	//for Usertype
	var userType = document.forms["signup"]["userType"].value.trim();
	if(userType=="select")
		{
			alert("Please select the User Type..");
			return false;
		}
	
	//for state
	var state = document.forms["signup"]["state"].value;
	if(state=="Select State")
		{
			alert("Please Select State");
			document.signup.state.focus();
			return false;
		}

	var rate = document.forms["signup"]["plan"].value;
	if(rate=="select")
		{
			alert("Please Select State");
			document.signup.state.focus();
			return false;
		}

	return true;
	
}

//for signUp Page

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

//for Sign Up
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


//for search movie and search person--all pages,member and admin

function isSelect(){
	
	var select=document.getElementById('select').value;
	//var select=document.forms["select"]["select"].value;
	if(select==""||select=="select")
		{
			alert("Please select the value..");
			document.getElementById('select').focus();
			return false;
		}
	
	
	return true;
}

function isSelect1(){
	
	var select=document.getElementById('select1').value;
	//var select=document.forms["select"]["select"].value;
	if(select==""||select=="select")
		{
			alert("Please select the value..");
			document.getElementById('select1').focus();
			return false;
		}
	
	
	return true;
}

	
//for addMovie.jsp
function checkMovie(){
	
	var inte = /^[0-9]+$/;
	var letters = /^[a-zA-Z0-9]+$/; 
	var moviename=document.forms["addMovie"]["movieName"].value.trim();
	if(!moviename.match(letters))  
	{
		alert("Please Enter valid Movie Name..");
		document.addMovie.moviename.focus(); 
		return false;
	}
	
	var banner=document.forms["addMovie"]["banner"].value.trim();
	if(!banner.match(letters))  
	{
		alert("Please Enter valid Banner for Movie..");
		document.addMovie.banner.focus(); 
		return false;
	}
	
	var copies=document.forms["addMovie"]["copies"].value.trim();
	if(!copies.match(inte))  
	{
		alert("Please Enter valid Copies..");
		document.addMovie.copies.focus(); 
		return false;
	}
	
	var rent=document.forms["addMovie"]["rent"].value.trim();
	if(!rent.match(inte))  
	{
		alert("Please Enter valid Rent");
		document.addMovie.rent.focus(); 
		return false;
	}
	
	var select=document.forms["addMovie"]["category"].value.trim();
	if(select=="select"||select=="")
		{
			alert("Please select the value..");
			document.addMovie.category.focus(); 
			return false;
		}

	return true;
}
	
//for updateMovie.jsp
function saveMovie(){
	
	var int = /^[0-9]+$/;
	var letters = /^[a-zA-Z0-9]+$/;
	var moviename=document.forms["saveMovie"]["movieName"].value.trim();
	if(!moviename.match(letters))  
	{
		alert("Please Enter valid Movie Name..");
		document.saveMovie.moviename.focus(); 
		return false;
	}
	
	var banner=document.forms["saveMovie"]["banner"].value.trim();
	if(!banner.match(letters))  
	{
		alert("Please Enter valid Banner for Movie..");
		document.saveMovie.banner.focus(); 
		return false;
	}
	
	var copies=document.forms["saveMovie"]["copies"].value.trim();
	if(!copies.match(int))  
	{
		alert("Please Enter valid Copies..");
		document.saveMovie.copies.focus(); 
		return false;
	}
	
	var rent=document.forms["saveMovie"]["rent"].value.trim();
	if(!rent.match(int))  
	{
		alert("Please Enter valid Rent");
		document.saveMovie.rent.focus(); 
		return false;
	}
	
	var select=document.forms["saveMovie"]["category"].value.trim();
	if(select=="select"||select=="")
		{
			alert("Please select the value..");
			document.saveMovie.category.focus(); 
			return false;
		}
	
	return true;
}
	

//for updatemember.jsp
function updateDetails()
{
	//for membership id
	var id = document.forms["updateDetails"]["membershipId"].value.trim();
	// var regexObj = /^?([0-9]{3})?[-. ]?([0-9]{2})[-. ]?([0-9]{4})$/;
	var regexObj = /^\(?([0-9]{3})\)?[-. ]?([0-9]{2})[-. ]?([0-9]{4})$/;
	if (!regexObj.test(id)) {
	
		alert("Enter valid membershipID in valid format.. ");
		return false;
	}
	
	//for firstname
	var fname = document.forms["updateDetails"]["fname"].value.trim();
	var letters = /^[a-zA-Z]+$/; 
	if(!fname.match(letters))  
	{
		alert("Please Enter valid Name");
		document.updateDetails.fname.focus(); 
		return false;
	}
	
	//for lastname
	var lname = document.forms["updateDetails"]["lname"].value.trim();
	var letters = /^[a-zA-Z]+$/;
	if(!lname.match(letters))  
	{
		alert("Please Enter valid Name");
		document.updateDetails.lname.focus(); 
		return false;
	}
	
	//for password match
	var password = document.forms["updateDetails"]["password"].value;
	if(password.length!=5)
	{
		alert ("Password length must be of atleast 5 characters..");
		document.updateDetails.password.focus(); 
		return false;
		
	}
	var cpassword = document.forms["updateDetails"]["cpassword"].value;
	if(cpassword!=password)
		{
			alert("Confirm Password must have to same as Password");
			return false;
		}
	
	//for zipcode
	var zipcode = document.forms["updateDetails"]["zipcode"].value.trim();
	var zipcode1 = document.forms["updateDetails"]["zipcode1"].value.trim();
	var zip = /^[0-9]+$/;
	if(!zipcode.match(zip))  
	{
		alert("Please Enter valid Zipcode");
		document.updateDetails.zipcode.focus(); 
		return false;
	}
	
	/*if(!isNaN(zipcode))
		{
			alert("Please Enter Digits for zipcode.");
			return false;
		}*/
	if(zipcode.length!=5)
		{
			alert("Enter zipcode of 5 digits..");
			document.updateDetails.zipcode.focus();
			return false;
		}
	if(zipcode1.length!=0)
		{
			/*if(!isNan(zipcode1))
				{
					alert("Please Enter Digits for zipcode.");
					return false;
				}*/
			if(!zipcode1.match(zip))  
			{
				alert("Please Enter valid Zipcode");
				document.updateDetails.zipcode1.focus(); 
				return false;
			}
			if(zipcode1.length!=4)
				{
					alert("Second part of zipcode is of only 4 digits.");
					return false;
				}
		}
	
	//for Usertype
	var userType = document.forms["updateDetails"]["userType"].value.trim();
	if(userType=="select")
		{
			alert("Please select the User Type..");
			return false;
		}
	
	//for state
	var state = document.forms["updateDetails"]["state"].value;
	if(state=="Select State")
		{
			alert("Please Select State");
			document.updateDetails.state.focus();
			return false;
		}
	
	return true;
	
}

// credit card validation for checkout.jsp
function checkout()
{
	var ccno=document.forms["checkout"]["ccNo"].value;
	if(ccno.length>16 || ccno.length <16)
		{
			alert("Length of credit card number must be of 16..");
			document.checkout.ccNo.focus();
			return false;
		}
//	if(!ccno.isNaN())
//		{
//			alert("Credit Card Number must be in Digits..");
//			document.checkout.ccNo.focus();
//			return false;
//		}
//	
	
	return true;
}

	
	
	
	
	
	