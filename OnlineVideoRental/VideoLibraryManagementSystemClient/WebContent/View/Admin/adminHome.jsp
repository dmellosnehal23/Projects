<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<form name='allMovies' action='adminMovie' method='post' >	
		<input type='hidden' value='allMovies' name='formname'>
		<input type='submit' value='All Movies'>
		</form>
		
		<form name='addMovie' action='adminMovie' method='post' >
		<input type='submit' value='Add Movie'>	
		<input type='hidden' value='addMovie' name='formname'>
		</form>
		
		<form name='displayAllMembers' action='adminMovie' method='post' >
		<input type='hidden' value='displayAllMembers' name='formname'>
		<input type='submit' value='List All Members'>
		</form>
		
		<form name='displaySimple' action='adminMovie' method='post' >
		<input type='hidden' value='displaySimple' name='formname'>
		<input type='submit' value='List Simple Members'>
		</form>
		
		<form name='displayPremium' action='adminMovie' method='post' >
		<input type='hidden' value='displayPremium' name='formname'>		
		<input type='submit' value='List Premium Members'>
		</form>


<a href="adminMovie?formname='displayPremium'">List Premium Members</a>

		
</form>
</body>
</html>