//backup without close() (rs,stmt) and half connection pooling and with HASH MAP

package VideoLibrary;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Date;


import entity.Cart;
import entity.Movie;
import entity.Person;
import entity.PremiumMembers;
import entity.SimpleMembers;
import entity.Transactions;

public class temp1 {

	Connection con = null;
	static ResultSet rs;
	Statement stmt = null;
	ConnectionPool connectionPool = null;
	PreparedStatement ps;


	temp1() {
				connectionPool = ConnectionPool.getInstance();
				try {
					con = connectionPool.getConnection();
					stmt = con.createStatement();
					if (!con.isClosed())	
						System.out.println("Successfully Connected!!!");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

	
//	static HashMap<String, Person> emailIdList = new HashMap<String, Person>();	
	
	public Person login(String emailId, String pwd)
	{
		String query;
		Person person = null;

		try {
			
		/*	person = emailIdList.get(emailId);				

			if (person != null) {
				System.out.println("Login: Returning person from HashMap");
				return person;
			}	*/										
			
			query = "select membershipId,firstName, lastName, lastLogin, userType from person where emailId='"+emailId+"' and password='"+pwd+"'";
			System.out.println(query);
			ps= con.prepareStatement(query);
			rs= ps.executeQuery();	


			if (rs.next()) {
				System.out.println("In if condition");
				person = new Person();
				person.setMembershipId(rs.getLong(1));
				

				person.setFirstName(rs.getString(2));
				person.setLastName(rs.getString(3));
				person.setLastLogin(rs.getTimestamp(4));		
				person.setUserType(rs.getInt(5));
				
				//primaryList.put(new Long(person.getMembershipId()), person);	
				//emailIdList.put(person.getEmailId(), person);					
				
				System.out.println(person.getFirstName());
				System.out.println(person.getLastName());
				System.out.println(person.getLastLogin());
				System.out.println(person.getUserType() + "type is ---------");


				query ="UPDATE person SET lastLogin = NOW() WHERE emailId='" + emailId +"'";
				System.out.println(query);
				ps= con.prepareStatement(query);
				if(ps.executeUpdate() == 0)
				{
					System.out.println("Update for lastLogin failed");
					return null;
				}
			}
			else
			{
				System.out.println("Invalid Login");
				return null;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}finally{
			connectionPool.free(con);
		}	
		return person;
	}
//	public String createMember(String fName, String lName, String emailId, String pwd, String address, 
//			String city, String state, long zipCode, Integer userType,
//		   Date expiryDate, Integer monthlySubFee, Integer totalOutstandingMovies, Integer rentForMoviesIssued){
//		String result = "";
//		int membershipId=0;
//		String query="",query1="", query2="";
//		int rowcount1=0, rowcount2=0;
//
//		ps = null;
//		try{
//			query1 = "Insert into person(firstName,lastName, emailId, password, address, city, state, zipCode, " +
//					"userType, registrationDate, expiryDate, lastLogin)" +
//					" values('" +fName +"', '" +lName  +"', '"+emailId+"', '"+pwd+"', '" +address  +"', " + "'" +city  +"', '" 
//					+state+"', '"+zipCode+"', '"+userType+"', now(),now(),now())";
//
//			System.out.println(query1);
//			ps = con.prepareStatement(query1); // create a statement
//			rowcount1 =ps.executeUpdate();
//
//			query = "Select membershipId from person where emailId='"+emailId+"'";
//			System.out.println(query);
//			ps= con.prepareStatement(query);
//			rs= ps.executeQuery();
//			while (rs.next()) {
//				membershipId = rs.getInt(1);
//				System.out.println("membershipId = "+ membershipId);
//			}
//
//			if(userType == 0)
//			{
//				query2 = "Insert into premiumMembers(membershipId,monthlySubFee,totalOutstandingMovies) " +
//									"values(" +membershipId +", '" +monthlySubFee  +"', '" +totalOutstandingMovies  +"')";
//			}
//			else if(userType == 1)
//			{
//				query2 = "Insert into simpleMembers(membershipId,rentForMoviesIssued,totalOutstandingMovies) " +
//						"values("+membershipId +", '" +rentForMoviesIssued  +"', '" +totalOutstandingMovies  +"')";
//			}
//
//			System.out.println(query2);
//		    ps = con.prepareStatement(query2);
//		    rowcount2 =ps.executeUpdate();
//
//		    if(rowcount1 >0 && rowcount2 >0)
//			{
//				result = "true";
//				System.out.println("Insert Successful");
//			}
//			else
//			{
//				result = "false";
//				System.out.println("The data could not be inserted into the database.");
//			}
//		}
//		catch(SQLException e){
//			e.printStackTrace();
//		}finally{
//			connectionPool.free(con);
//		}
//
//		return result;
//	}

	
	public String createMember( String fName, String lName, String emailId, String pwd, String address, 
			String city, String state, long zipCode, Integer userType,
		    float dueAmount, Integer totalOutstandingMovies, Integer rentForMoviesIssued)
	{
		
		String result = "";
		long membershipId=0;
		String query1="", query2="";
		int rowcount1=0, rowcount2=0;

		ps = null;
		try{
		 	//+state+"', '"+zipCode+"', '"+userType+"', '"+registrationDate.toString()+"','"+expiryDate.toString()+"', now())";

			query1 = "Insert into person( firstName,lastName, emailId, password, address, city, state, zipCode, " +
					"userType, registrationDate, lastLogin)" +
					" values('" +fName +"', '" +lName  +"', '"+emailId+"', '"+pwd+"', '" +address  +"', " + "'" +city  +"', '" 
					+state+"', '"+zipCode+"', '"+userType+"', now(),now())";

			System.out.println(query1);
			ps = con.prepareStatement(query1); // create a statement
			rowcount1 =ps.executeUpdate();
			
			
			query2 ="select membershipId from person where emailId ='"+emailId+"'";

			System.out.println(query2);
			ps= con.prepareStatement(query2);
			rs= ps.executeQuery();

			if(rs.next()) {

				membershipId = rs.getInt(1);
				System.out.println("membershipId = "+ membershipId);

			}
		
			if(userType == 0)
			{
				query2 = "Insert into premiumMembers(membershipId,dueAmount,totalOutstandingMovies, expiryDate) " +
									"values( '" +membershipId  +"', '" +dueAmount  +"', '" +totalOutstandingMovies  +"', now())";
			}
			else if(userType == 1)
			{
				query2 = "Insert into simpleMembers(membershipId,rentForMoviesIssued,totalOutstandingMovies) " +
						"values(  '" +membershipId  +"', '" +rentForMoviesIssued  +"', '" +totalOutstandingMovies  +"')";
			}

			System.out.println(query2);
		    ps = con.prepareStatement(query2);
		    rowcount2 =ps.executeUpdate();

		    if(rowcount1 >0 && rowcount2 >0)
			{
				result = "true";
				System.out.println("Insert Successful");
			}
			else
			{
				result = "false";
				System.out.println("The data could not be inserted into the database.");
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}finally{
			connectionPool.free(con);
		}

		return result;
	}

	//static HashMap<Long, Person> primaryList = new HashMap<Long, Person>();		
	
	public Person memberFromID(long membershipId)
	{
		String query;
		Person person = null;

		try {
			
			/*if ((person = primaryList.get(new Long(membershipId))) != null) {			//
				
				System.out.println("Returning person from HashMap..");
				return person;
			}	*/																		//
			
			query = "select membershipId,firstName, lastName, emailId,password,address,city,state,zipCode, userType  from person where membershipId='"+membershipId+"'";
			System.out.println(query);
			ps= con.prepareStatement(query);
			rs= ps.executeQuery();	

			if (rs.next()) {
				System.out.println("In if condition");
				person = new Person();
				person.setMembershipId(rs.getLong("membershipId"));

				person.setFirstName(rs.getString("firstName"));
				person.setLastName(rs.getString("lastName"));
				person.setEmailId(rs.getString("emailId"));
				person.setPassword(rs.getString("password"));
				person.setAddress(rs.getString("address"));
				person.setCity(rs.getString("city"));
				person.setState(rs.getString("state"));
				person.setZipCode(rs.getLong("zipCode"));
				person.setUserType(rs.getInt("userType"));

				//primaryList.put(membershipId, person);		

			}
			else
			{
				System.out.println("Not available");
				return null;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}finally{
			connectionPool.free(con);
		}	
		return person;
	}

	
	public boolean updatePerson(long membershipId, String firstName, String lastName,String emailId, String password, String address, String city, String state, long zipCode){
		int rowcount=0;
		boolean result=false;
		String query;
				
		try {
				ps=(PreparedStatement) con.prepareStatement("update person set firstName=?, lastName=?, emailId=?, password=?, address=?, city=?, state=?, zipCode=? where membershipId=?");
		
				ps.setString(1, firstName);
				ps.setString(2, lastName);
				ps.setString(3, emailId);
				ps.setString(4, password);
				ps.setString(5, address);
				ps.setString(6, city);
				ps.setString(7, state);
				ps.setLong(8, zipCode);
				ps.setLong(9, membershipId);
				rowcount=ps.executeUpdate();
				
				if(rowcount>0)
				{
					result=true;
				}
				
			}
		 catch (SQLException e) {
			e.printStackTrace();
		}finally{
			connectionPool.free(con);
		}
		return result;
	}

	public Movie[] displayMovies(){
		ResultSet rs;
		String query;
		String countQuery;
		int Count = 0;
		int index=0;
		Movie[] movieAll = null;
				
		try {
				query = "select * from movie where availableCopies > 0";
				countQuery = "select count(1) from movie";
				
				rs = stmt.executeQuery(countQuery);
				if (rs.next()) {
					 Count = rs.getInt(1);
				}
				
				movieAll = new Movie[Count];
				
				rs = stmt.executeQuery(query);
				
				while (rs.next()) {
					Movie movie = new Movie();
					movie.setMovieId(rs.getInt("movieId"));
					movie.setMovieName(rs.getString("movieName"));
					movie.setMovieBanner(rs.getString("movieBanner"));
					movie.setMovieReleaseDate(rs.getDate("movieReleaseDate"));
					movie.setMovieRent(rs.getFloat("movieRent"));
					movie.setMovieCategory(rs.getString("movieCategory"));
					movie.setAvailableCopies(rs.getInt("availableCopies"));
					movieAll[index] = movie;
					index++;
				}
			}
		 catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//connectionPool.free(con);
		}
		
		return movieAll;

	}
	
	
	public boolean createMovie(String movieName, String movieBanner, Date movieReleaseDate, float movieRent, String movieCategory, int availableCopies){
		try{
			
			//PreparedStatement ps;
			boolean success;
			ps = con.prepareStatement("insert into movie( movieName, movieBanner, movieReleaseDate, movieRent, movieCategory, availableCopies) VALUES(?,?,?,?,?,?)");
			//ps.setInt(1, movieId);
			ps.setString (1, movieName);
			ps.setString (2, movieBanner);
			ps.setDate(3, (java.sql.Date) movieReleaseDate);
			ps.setFloat(4, movieRent);
			ps.setString(5, movieCategory);
			ps.setInt(6, availableCopies);
			if(ps.executeUpdate()==1){
				success = true;
				return success;
			}		
		
	}catch (SQLException e) {
		e.printStackTrace();
	}
		return false;
	}
	
	public boolean rentMovie(int movieId, String movieName, long membershipId, Date issueDate)
	{
		int rowcount=0;
		ResultSet rs;
		boolean result=false;
		String query;
		int availableCopies=0;
				
		try {
				ps=(PreparedStatement) con.prepareStatement("select availableCopies from movie where movieId=?");
		
				ps.setInt(1,movieId);
				
				rs=ps.executeQuery();
				
				while(rs.next())
				{
					availableCopies=rs.getInt(1);
					System.out.println(availableCopies);
				}
				
				if(availableCopies==0)
				{
					return result=false;
				}
				else
				{
					ps=(PreparedStatement)con.prepareStatement("insert into transactions (membershipId,movieId,issueDate, dueDate) values (?,?,NOW(),DATE_ADD(issueDate,INTERVAL 14 DAY))");
					ps.setLong(1, membershipId);
					ps.setInt(2, movieId);
					
					rowcount=ps.executeUpdate();
					if(rowcount>0)
					{
						ps=(PreparedStatement)con.prepareStatement("update movie set availableCopies=availableCopies-1 where movieId=?");
						ps.setInt(1, movieId);
						rowcount=0;
						ps.executeUpdate();
						
						//update the person table (how many movies rented)
//						ps=(PreparedStatement)con.prepareStatement("update person set rentedMovies=rentedMovies+1 where membershipId=?");
//						ps.setLong(1, membershipId);
//						rowcount=ps.executeUpdate();
//						if(rowcount>0)
//						{
//							result=true;
//						}
					
						
					}
					
				}
				
			}
		 catch (SQLException e) {
			e.printStackTrace();
		}finally{
			connectionPool.free(con);
		}
		return result;
	}

	
	public boolean deleteMovie(int movieId, String movieName)
	{
		int rowcount=0;
		boolean result=false;
		try {
			ps=(PreparedStatement) con.prepareStatement("delete from movie where movieId=? and movieName=?");
			
			ps.setInt(1, movieId);
			ps.setString(2, movieName);
			
			rowcount=ps.executeUpdate();

			if(rowcount>0)
			{
				result=true;
			}

		}
		catch (SQLException e) {
			e.printStackTrace();
		}finally{
			connectionPool.free(con);
		}
		return result;
	}
	
	public boolean deleteMember(long membershipId)
	{
		boolean result=true;
		Integer userType=9999;
		String query1="", query2="", query3="";
		int rowcount1=0, rowcount2=0;
		ps = null;
		try{
			query1 ="select userType from person where membershipId ="+membershipId;

			System.out.println(query1);
			ps= con.prepareStatement(query1);
			rs= ps.executeQuery();

			if(rs.next()) {

				userType = rs.getInt(1);
				System.out.println("userType = "+ userType);


				if(userType == 0)
				{
					query2 = "delete from premiumMembers where membershipId ="+membershipId;
				}
				else if(userType == 1)
				{
					query2 = "delete from simpleMembers where membershipId ="+membershipId;
				}

				System.out.println(query2);
				ps = con.prepareStatement(query2);
				rowcount1 =ps.executeUpdate();

				query3 = "delete from person where membershipId ="+membershipId;

				System.out.println(query3);
				ps = con.prepareStatement(query3);
				rowcount2 =ps.executeUpdate();

				if(rowcount1 >0 && rowcount2 >0)
				{
					result = true;
					System.out.println("Delete Successful");
				}
				else
				{
					result = false;
					System.out.println("The data could not be deleted from the database.");
				}
			}
			else
			{
				result = false;
				System.out.println("Record not found");
			}

		}
		catch(SQLException e){
			e.printStackTrace();
		}finally{
			connectionPool.free(con);
		}

		return result;
	}

	public Person[] displayPerson(){
		String query;
		String countQuery;
		int Count = 0;
		int index=0;
		Person[] personAll = null;
				
		try {
				query = "select * from person";
				countQuery = "select count(1) from person";
				
				ps= con.prepareStatement(countQuery);
				rs= ps.executeQuery();
				
				if (rs.next()) {
					 Count = rs.getInt(1);
				}
				
				personAll = new Person[Count];
				
				ps= con.prepareStatement(query);
				rs= ps.executeQuery();
				
				while (rs.next()) {
					Person person = new Person();
					person.setMembershipId(rs.getLong("membershipId"));
					person.setFirstName(rs.getString("firstName"));
					person.setLastName(rs.getString("lastName"));
					person.setEmailId(rs.getString("emailId"));
					person.setPassword(rs.getString("password"));
					person.setAddress(rs.getString("address"));
					person.setCity(rs.getString("city"));
					person.setState(rs.getString("state"));
					person.setZipCode(rs.getInt("zipCode"));
					person.setUserType(rs.getInt("userType"));
					person.setRegistrationDate(rs.getDate("registrationDate"));
//					person.setExpiryDate(rs.getDate("expiryDate"));
					person.setLastLogin(rs.getDate("lastLogin"));
					personAll[index] = person;
					index++;
					
				}
				
			}
		 catch (SQLException e) {
			e.printStackTrace();
		}
		
		return personAll; 

	}
	public SimpleMembers[] displaySimpleMembers()
	{
		//ResultSet rs;
		String query;
		String countQuery;
		int Count = 0;
		int index=0;
		SimpleMembers[] simpleMembersList = null;
				
		try { 
			// Assuming userType 0 = SimpleMember and 1 = PremiumMember
				

				countQuery = "select count(1) from person where userType = 1";
				query = "select * from person,simplemembers where person.membershipId = simplemembers.membershipId and userType = 1 order by person.membershipId";
				
				ps= con.prepareStatement(countQuery);
				rs= ps.executeQuery();
				
				if (rs.next()) {
					 Count = rs.getInt(1);
				}

				simpleMembersList = new SimpleMembers[Count];
				
				ps= con.prepareStatement(query);
				rs= ps.executeQuery();
				

				
				while (rs.next()) {
					SimpleMembers simpleMember = new SimpleMembers();
					simpleMember.setSimpleMemberId(rs.getInt("simplememberId"));
					simpleMember.setMembershipId(rs.getLong("membershipId"));
					simpleMember.setRentForMoviesIssued(rs.getFloat("rentForMoviesIssued"));
					simpleMember.setTotalOutstandingMovies(rs.getInt("totalOutstandingMovies"));
					
					simpleMember.setFirstName(rs.getString("firstName"));
					simpleMember.setLastName(rs.getString("lastName"));
					simpleMember.setEmailId(rs.getString("emailId"));
					simpleMember.setPassword(rs.getString("password"));
					simpleMember.setAddress(rs.getString("address"));
					simpleMember.setCity(rs.getString("city"));
					simpleMember.setState(rs.getString("state"));
					simpleMember.setZipCode(rs.getInt("zipCode"));
					simpleMember.setUserType(rs.getInt("userType"));
					simpleMember.setRegistrationDate(rs.getDate("registrationDate"));
					simpleMember.setLastLogin(rs.getDate("lastLogin"));
					
					simpleMembersList[index] = simpleMember;
					index++;
				}

			}
		 catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("5");

		return simpleMembersList;
		
	}


	
	public PremiumMembers[] displayPremiumMembers()
	{
		//ResultSet rs;
		String query;
		String countQuery;
		int Count = 0;
		int index=0;
		PremiumMembers[] premiumMembersList = null;
				
		try { 
			// Assuming userType 0 = SimpleMember and 1 = PremiumMember
				
				countQuery = "select count(1) from person where userType = 0";
				query = "select * from person,premiummembers where person.membershipId = premiummembers.membershipId and userType = 0 order by person.membershipId";
				
				ps= con.prepareStatement(countQuery);
				rs= ps.executeQuery();
				
				if (rs.next()) {
					 Count = rs.getInt(1);
				}
				
				premiumMembersList = new PremiumMembers[Count];
				
				ps= con.prepareStatement(query);
				rs= ps.executeQuery();
				
				
				
				while (rs.next()) {
					PremiumMembers premiumMember = new PremiumMembers();
					premiumMember.setPremiumMemberId(rs.getInt("premiumMemberId"));
					premiumMember.setMembershipId(rs.getLong("membershipId"));
					premiumMember.setDueAmount(rs.getFloat("dueAmount"));					
					premiumMember.setTotalOutstandingMovies(rs.getInt("totalOutstandingMovies"));
					premiumMember.setExpiryDate(rs.getDate("expiryDate"));
					
					premiumMember.setFirstName(rs.getString("firstName"));
					premiumMember.setLastName(rs.getString("lastName"));
					premiumMember.setEmailId(rs.getString("emailId"));
					premiumMember.setPassword(rs.getString("password"));
					premiumMember.setAddress(rs.getString("address"));
					premiumMember.setCity(rs.getString("city"));
					premiumMember.setState(rs.getString("state"));
					premiumMember.setZipCode(rs.getInt("zipCode"));
					premiumMember.setUserType(rs.getInt("userType"));
					//premiumMember.setRentedMovies(rs.getInt("rentedMovies"));
					premiumMember.setRegistrationDate(rs.getDate("registrationDate"));					
					premiumMember.setLastLogin(rs.getDate("lastLogin"));
					
					premiumMembersList[index] = premiumMember;
					index++;
				}
			}
		 catch (SQLException e) {
			e.printStackTrace();
		}
		
		return premiumMembersList;
		
	}
	
	

	public boolean updateMovie(int movieId, String movieName, String movieBanner, Date movieReleaseDate, float movieRent, String movieCategory, int availableCopies )
	{
		boolean result=false;
		int rowcount=0;
		String query;
				
		try {
				ps=(PreparedStatement) con.prepareStatement("update movie set movieName=?, movieBanner=?, movieReleaseDate=?, movieRent=?, movieCategory=?, availableCopies=? where movieId=?");
				//query = "update person set firstName='"+firstName+"', lastName='"+lastName+"', emailId='"+emailId+"', password='"+password+"', address='"+address+"', city='"+city+"', state='"+state+"', zipCode='"+zipCode+"' where membershipId="+membershipId;
				
				ps.setString(1, movieName);
				ps.setString(2, movieBanner);
				ps.setDate(3, (java.sql.Date) movieReleaseDate);
				ps.setFloat(4, movieRent);
				ps.setString(5, movieCategory);
				ps.setInt(6, availableCopies);
				ps.setInt(7, movieId);
				rowcount=ps.executeUpdate();
				
				if(rowcount>0)
				{
					result=true;
				}
				
			}
		 catch (SQLException e) {
			e.printStackTrace();
		}finally{
			connectionPool.free(con);
		}
		return result;
	}


	public Movie[] updateMovieRequest(int movieId){
		ResultSet rs;
		String query;
		String countQuery;
		int Count = 0;
		int index=0;
		Movie[] movieAll = null;
				
		try {
			
				query = "select * from movie where movieId=" + movieId;
				countQuery = "select count(1) from movie";
				
				rs = stmt.executeQuery(countQuery);
				if (rs.next()) {
					 Count = rs.getInt(1);
				}
				
				movieAll = new Movie[Count];
				
				rs = stmt.executeQuery(query);
				
				while (rs.next()) {
					Movie movie = new Movie();
					movie.setMovieId(rs.getInt("movieId"));
					movie.setMovieName(rs.getString("movieName"));
					movie.setMovieBanner(rs.getString("movieBanner"));
					movie.setMovieReleaseDate(rs.getDate("movieReleaseDate"));
					movie.setMovieRent(rs.getFloat("movieRent"));
					movie.setMovieCategory(rs.getString("movieCategory"));
					movie.setAvailableCopies(rs.getInt("availableCopies"));
					movieAll[index] = movie;
					index++;
				}
			}
		 catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//connectionPool.free(con);
		}
		
		return movieAll;

	}
	
	public Transactions[] issuedMovieList(long membershipId){

        String query;
        String countQuery;
        int Count = 0;
        int index=0;
        Transactions[] issuedMovie = null;
                
        try {
            
                query = "select * from transactions where  membershipId=" + membershipId + "and  actualReturnDate=null";
                countQuery = "select count(1) from transactions";
                
                rs = stmt.executeQuery(countQuery);
                if (rs.next()) {
                     Count = rs.getInt(1);
                }
                
                issuedMovie = new Transactions[Count];
                
                rs = stmt.executeQuery(query);
                
                while (rs.next()) {
                    Transactions transaction = new Transactions();
                    transaction.setTransactionId(rs.getLong("transactionId"));
                    transaction.setMembershipId(rs.getLong("membershipId"));
                    int tempMovieId = rs.getInt("movieId");
                    transaction.setMovieId(tempMovieId);
                    PreparedStatement ps1 = con.prepareStatement("select movieName from movie where movieId = "+ tempMovieId + ";");
        			ResultSet rs1 = ps1.executeQuery();
        				if (rs1.next()) {
        					 transaction.setMovieName(rs.getString("movieName"));
        				}
                    
                    transaction.setFineAmount(rs.getFloat("fineAmount"));
                    transaction.setIssueDate(rs.getDate("issueDate"));
                    transaction.setDueDate(rs.getDate("dueDATE"));
                    issuedMovie[index] = transaction;
                    index++;
                }
            }
         catch (SQLException e) {
            e.printStackTrace();
        }finally{
            //connectionPool.free(con);
        }
        
        return issuedMovie;

    }

	
	
	public Person[] searchPerson(String attributeName, String attributeValue)
	{
		System.out.println("In Database- search person");
		String query="",query1="";
		int index=0, count=0;
		Person personInfo[] = null;
		try{

			//query = "select count(1) from person where firstname='"+fName+"' OR lastname='"+lName+"' OR emailId='"+emailId+"' OR membershipId="+membershipId; 
			query="select count(1) from person where "+attributeName+" LIKE '"+attributeValue+"%'"; // also check by having % in the beginning
			ps= con.prepareStatement(query);
			rs= ps.executeQuery();
			System.out.println(query);

			if (rs.next()) {
				 count = rs.getInt(1);
			}

			personInfo = new Person[count];

			query1 = "select * from person where "+attributeName+" LIKE '"+attributeValue+"%'"; // also check by having % in the beginning
			ps = con.prepareStatement(query1);
			rs=ps.executeQuery();
			System.out.println(query1);

			while(rs.next())
			{
				Person person = new Person();
				person.setMembershipId(rs.getInt(1));
				person.setFirstName(rs.getString(2));
				person.setLastName(rs.getString(3));
				person.setEmailId(rs.getString(4));
				person.setPassword(rs.getString(5));
				person.setAddress(rs.getString(6));
				person.setCity(rs.getString(7));
				person.setState(rs.getString(8));
				person.setZipCode(rs.getInt(9));
				person.setUserType(rs.getInt(10));
				person.setRegistrationDate(rs.getDate(11));
				person.setLastLogin(rs.getTimestamp(12));
				personInfo[index] = person;
				index++;

//				System.out.println(person.getMembershipId()+" "+person.getFirstName()+" "+person.getLastName()+" "+person.getEmailId()+ " " +
//				" "+person.getPassword()+" "+person.getAddress()+" "+ " "+person.getCity()+" "+person.getState()+" "+person.getZipCode()+ " "+
//					person.isUserType()+" "+person.getRegistrationDate()+" "+person.getLastLogin());
			}

			if(index>0)
			{
				System.out.println("Person found");
			}
			else
			{
				System.out.println("Person not found");
				return null;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}finally{
			connectionPool.free(con);
		}
		return personInfo;
	}

	
	public Movie[] searchMovie(String attributeName, String attributeValue)
	{
		String query="";
		int index=0, count=0;
		Movie[] movieInfo = null;
		try{

			query="select count(1) from movie where "+attributeName+" LIKE '"+attributeValue+"%'"; // also check by having % in the beginning
			ps= con.prepareStatement(query);
			rs= ps.executeQuery();
			System.out.println(query);

			if (rs.next()) {
				 count = rs.getInt(1);
			}

			movieInfo = new Movie[count];

			query = "select * from movie where "+attributeName+" LIKE '"+attributeValue+"%'"; // also check by having % in the beginning
			ps=con.prepareStatement(query);
			rs = ps.executeQuery();

			while(rs.next())
			{
				Movie movie = new Movie();
				movie.setMovieId(rs.getInt(1));
				movie.setMovieName(rs.getString(2));
				movie.setMovieBanner(rs.getString(3));
				movie.setMovieReleaseDate(rs.getDate(4));
				movie.setMovieRent(rs.getFloat(5));
				movie.setMovieCategory(rs.getString(6));
				movie.setAvailableCopies(rs.getInt(7));
				movieInfo[index] = movie;
				index++;

				System.out.println(movie.getMovieId()+" "+movie.getMovieName()+" "+movie.getMovieBanner()+" "+movie.getMovieReleaseDate()+ " " +
						" "+movie.getMovieRent()+" "+movie.getMovieCategory()+" "+ " "+movie.getAvailableCopies());
			}

			if(index>0)
			{
				System.out.println("Movie found");
			}
			else
			{
				System.out.println("Movie not found");
				return null;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}finally{
			connectionPool.free(con);
		}
		return movieInfo;

	}
	
	public float isPaymentDue(long membershipId)
	{
		String query="";
		float dueAmount=0;
		int count=0;
		try{
			query="select dueAmount from premiumMembers where membershipId = "+membershipId;
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();

			if(rs.next())
			{
				dueAmount=rs.getFloat(1);
				count++;
				System.out.println("DueAmount: "+dueAmount);
			}

			if(count==0)
			{
				System.out.println("Member not found");
			}

		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally{
			connectionPool.free(con);
		}
		return dueAmount;
	}

	public boolean removeMovieFromCart(int cartId)//Can also pass combination of membershipId and movieId
	{
		int output=0;
		boolean result=false;
		String query="";
		try{

			query="delete from Cart where CartId="+cartId;
			ps=con.prepareStatement(query);
			output = ps.executeUpdate();

			System.out.println(query);
			if(output > 0)
			{
				result = true;
				System.out.println("Delete Successful");
			}
			else if(output == 0)
			{
				result = false;
				System.out.println("The data could not be deleted from the database.");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally{
			connectionPool.free(con);
		}
		return result;
	}

	
	public Cart[] displayCart(long membershipId) {
		ResultSet rs,rs1;
		PreparedStatement ps,ps1;
		//Movie movie = null;
		Cart[] cartAll = null;
		//Person[] person = null;
		Movie movie = null;
		int index =0;
		try {
			String query, query1, query2;
			int Count=0;
			query = "select * from cart where membershipId = "+ membershipId + ";";
			query1 = "select count(1) from cart where membershipId = "+ membershipId + ";";
			//query2 = "select person.membershipId,person.firstName,person.lastName from transactions,person where transactions.membershipId = person.membershipId and transactions.movieId = "+ movieId+ ";";
			//ResultSet rs;
			
			
			ps1= con.prepareStatement(query1);
			rs1=ps1.executeQuery();
				if (rs1.next()) {
					 Count = rs1.getInt(1);
				}
			
			cartAll = new Cart[Count];
			
			ps= con.prepareStatement(query);
			rs= ps.executeQuery();
			
			while (rs.next()) {	
				Cart cart = new Cart();
				cart.setCartId(rs.getInt("cartId"));
				int tempMovieId = rs.getInt("movieId");
				cart.setMovieId(tempMovieId);
				
				ps1= con.prepareStatement("select * from movie where movieId = "+ tempMovieId + ";");
				rs1=ps1.executeQuery();
					if (rs1.next()) {
						 movie = new Movie();
						 movie.setMovieName(rs1.getString("movieName"));
						 movie.setMovieBanner(rs1.getString("movieBanner"));
						 movie.setMovieRent(rs1.getFloat("movieRent"));
						 movie.setMovieCategory(rs1.getString("movieCategory"));
						 cart.setMovieDetails(movie);
						 
					}
									
					cartAll[index] = cart;
					index++;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cartAll;
			
	}//method


//	public boolean addToCart(Cart[] cart_details_array)
//	{
//		boolean result=false;
//		int rowcount=0;
//		Cart cart_details=new Cart();
//		/*for (int i = 0; i < cart_details_array.length; i++) {
//			cart_details=cart_details_array[i];
//			System.out.println("get membershipid"+cart_details.getMembershipId());
//			System.out.println(cart_details.getMovieId());
//			System.out.println(cart_details);
//		}*/		
//				
//		try {
//			
//				for (int i = 0; i < cart_details_array.length; i++) {
//					rowcount=0;
//					cart_details=cart_details_array[i];
//					ps=(PreparedStatement) con.prepareStatement("Insert into cart (membershipId,movieId) values (?,?)");
//					
//					ps.setLong(1, cart_details.getMembershipId());
//					ps.setInt(2,cart_details.getMovieId());
//					rowcount=ps.executeUpdate();
//					
//					if(rowcount>0)
//					{
//						result=true;
//					}
//				}				
//			}
//		 catch (SQLException e) {
//			e.printStackTrace();
//		}finally{
//			connectionPool.free(con);
//		}
//		
//		return result;
//	}
//
	
	
	public boolean addToCart(int movieId, long memberId)
	{
		boolean result=false;
		int rowcount=0;
		Cart cart_details=new Cart();

		try {
			
//				for (int i = 0; i < cart_details_array.length; i++) {
//					rowcount=0;
//					cart_details=cart_details_array[i];
					ps=(PreparedStatement) con.prepareStatement("Insert into cart (membershipId,movieId) values (?,?)");
					
					ps.setLong(1, memberId);
					ps.setInt(2,movieId);
					rowcount=ps.executeUpdate();
					
					if(rowcount>0)
					{
						result=true;
					}
				//}				
			}
		 catch (SQLException e) {
			e.printStackTrace();
		}finally{
			connectionPool.free(con);
		}
		
		return result;
	}

	public boolean dopayment(long membershipId, float dueAmount)
	{
		boolean result=false;
		int rowcount=0;
		
		int interval=((int)dueAmount/50)*30;
				
		try {
				ps=(PreparedStatement) con.prepareStatement("update premiummembers set dueAmount=0, expirydate=DATE_ADD(NOW(),INTERVAL "+interval+" DAY) where membershipId=?");
				ps.setLong(1, membershipId);
				rowcount=ps.executeUpdate();
				
				if(rowcount>0)
				{
					result=true;
				}
				
			}
		 catch (SQLException e) {
			e.printStackTrace();
		}finally{
			connectionPool.free(con);
		}
		
		return result;
	}

	public Movie getMovieInformationWithIssuedByUsers(int movieId) {
		ResultSet rs,rs1;
		PreparedStatement ps,ps1;
		Movie movie = null;
		Person[] person = null;
		try {
			String query, query1, query2;
			int Count=0;
			query = "select * from movie where movieId = "+ movieId + ";";
			query1 = "select count(1) from transactions where movieId = "+ movieId + ";";
			query2 = "select person.membershipId,person.firstName,person.lastName from transactions,person where transactions.membershipId = person.membershipId and transactions.movieId = "+ movieId+ ";";
			//ResultSet rs;
			ps= con.prepareStatement(query);
			rs= ps.executeQuery();
			if (rs.next()) {	
				movie = new Movie();
				movie.setMovieName(rs.getString("movieName"));
				movie.setMovieBanner(rs.getString("movieBanner"));
				movie.setMovieReleaseDate(rs.getDate("movieReleaseDate"));
				movie.setMovieRent(rs.getFloat("movieRent"));
				movie.setMovieCategory(rs.getString("movieCategory"));
				movie.setAvailableCopies(rs.getInt("availableCopies"));
				
				ps1= con.prepareStatement(query1);
				rs1=ps1.executeQuery();
					if (rs1.next()) {
						 Count = rs1.getInt(1);
					}
				
				person = new Person[Count];
				
				ps1= con.prepareStatement(query2);
				rs1= ps1.executeQuery();
				int index = 0;
				
					while (rs1.next()) {
						Person p = new Person();
						p.setMembershipId(rs1.getLong("membershipId"));
						p.setFirstName(rs1.getString("firstName"));
						p.setLastName(rs1.getString("lastName"));
						person[index] = p;
						index++;
											
					}
					
				movie.setUsers(person);	
					
				ps.close();
				ps1.close();
				con.close();
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movie;
			
	}//method

	public boolean emptyCart(long membershipId)
	{
		int output=0;
		boolean result=false;
		String query="";
		try{

			query="delete from Cart where membershipId="+membershipId;
			ps=con.prepareStatement(query);
			output = ps.executeUpdate();

			System.out.println(query);
			if(output > 0)
			{
				result = true;
				System.out.println("Delete Successful");
			}
			else if(output == 0)
			{
				result = false;
				System.out.println("The data could not be deleted from the database.");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally{
			connectionPool.free(con);
		}
		return result;
	}

	
}
