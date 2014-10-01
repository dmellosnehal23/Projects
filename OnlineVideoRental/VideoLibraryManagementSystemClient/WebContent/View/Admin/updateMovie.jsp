<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Movie</title>
<%@ page import=  "entity.Movie"%>
<%@ page import=  "java.util.Date"%>
<%@ page import=  "java.text.SimpleDateFormat"%>
<%@ page import=  "java.util.Calendar"%>

<%
	HttpSession sess = request.getSession(false);

	Movie[] listMovies	= (Movie[])sess.getAttribute("displayMovies");
	
	
	Calendar TimeStop = listMovies[0].getMovieReleaseDate();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String TimeStop_Str = sdf.format(TimeStop.getTime());
%>

 
</head>
<body>
                 <form name="saveMovie" method="post" action="update">
                 <input type='hidden' name='formname' value='saveMovie'>
                 
                <input type='hidden' name='movieId' value="<%=listMovies[0].getMovieId() %>">
                  
                  	 <table align="center">
                        
                     <tr><td>      
                    <label class="contact"><strong>Movie Name:</strong></label></td><td>
                    <input type="text" class="contact_input" name="movieName" value="<%=listMovies[0].getMovieName() %>" /></td></tr>
                    
                    <tr><td> 
                    <label class="contact"><strong>Release Date:</strong></label></td><td>
                    <input type="text" class="contact_input" name="releaseDate" value="<%=TimeStop_Str %>" /></td></tr>
                    </div>

					<tr><td>
                    <label class="contact"><strong>Movie Category:</strong></label></td><td>
                    <select name="category">
                    <option value=<%=listMovies[0].getMovieCategory() %>><%=listMovies[0].getMovieCategory() %></option>
                    <option value="Action">Action</option>
                    <option value="Animation">Animation</option>
                    <option value="Comedy">Comedy</option>
                    <option value="Documentary">Documentary</option>
                    <option value="Drama">Drama</option>
                    <option value="Family">Family</option>
                    <option value="Horror">Horror</option>
                    <option value="Romance">Romance</option>
                    <option value="Sci-Fi">Sci-Fi</option>
                    </select></td></tr>
					  
                    <tr><td>
                    <label class="contact"><strong>Movie Banner:</strong></label></td><td>
                    <input type="text" class="contact_input" name="banner" value="<%=listMovies[0].getMovieBanner() %>"/></td></tr>
                    
                    <tr><td>
                    <label class="contact"><strong>Available Copies:</strong></label></td><td>
                    <input type="text" class="contact_input" name="copies" value="<%=listMovies[0].getAvailableCopies() %>"/></td></tr>
                    <tr><td>
                    <label class="contact"><strong>Rent Amount:</strong></label></td><td>
                    <input type="text" class="contact_input" name="rent" value="<%=listMovies[0].getMovieRent() %>"/></td></tr>
                    

					<tr></tr><tr>
					<td><input type="submit" name="signup" value="Submit" class="contact" />
                    <input type="submit" name="signup" value="Reset" class="contact" />
                    <input type="submit" name="signup" value="Cancel" class="contact" />
					</td></tr>                    
                </form>
                                     
</body>
</html>