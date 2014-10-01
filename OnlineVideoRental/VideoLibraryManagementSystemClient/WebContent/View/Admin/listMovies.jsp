<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Movies</title>
<%@ page import=  "entity.Movie"%>
<%@ page import=  "java.util.Date"%>
<%@ page import=  "java.text.SimpleDateFormat"%>
<%@ page import=  "java.util.Calendar"%>


<%
	HttpSession sess = request.getSession(false);

	Movie[] listMovies	= (Movie[])sess.getAttribute("displayMovies");
 %>

</head>
<body>

        <H2 ALIGN="CENTER"> List of Movies</H2> 
        
        <TABLE BORDER=1 ALIGN=CENTER>
          <TH>Number<TH>Movie Name</TH>
          <TH>Banner</TH>
          <TH>Release Date</TH>
          <TH>Category</TH>
          <TH>Rent($)</TH>
          <TH>Copies Availble</TH>
           <TH>Update</TH>        
          <TH>Delete</TH></TR></TR>

<% 
for(int i=0;i<listMovies.length;i++){ %>

<form name='updateMovie' action='update' method='post' >
<input type='hidden' name='formname' value='updateMovie'>
 <input type='hidden' name='movieId' value=<%=listMovies[i].getMovieId()%>>
<% 
	Calendar TimeStop = listMovies[i].getMovieReleaseDate();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String TimeStop_Str = sdf.format(TimeStop.getTime());
%>
	<TR>
	   
		<TD><%=(i+1) %>	</TD>
		<TD> <%=listMovies[i].getMovieName() %>
		<TD><%=listMovies[i].getMovieBanner()%>	
		<TD><%= TimeStop_Str %>
		<TD><%=listMovies[i].getMovieCategory()%>	
		<TD><%=listMovies[i].getMovieRent()%>	
		<TD><%=listMovies[i].getAvailableCopies()%>	
		
		<TD><input type='submit' value='Update'></td></form>
		
		<form name='deleteMovie' action='delete' method='post' >
   	    <input type='hidden' name='movieId' value=<%=listMovies[i].getMovieId()%>>
		<input type='hidden' name='movieName' value=<%=listMovies[i].getMovieName() %>>

		<input type='hidden' name='formname' value='deleteMovie'>
		<TD><input type='submit' value='Delete'></td></tr></form>

		
<% 
  }

%>

</body>
</html>
