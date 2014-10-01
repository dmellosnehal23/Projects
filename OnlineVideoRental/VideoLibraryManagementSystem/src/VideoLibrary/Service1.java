package VideoLibrary;

import entity.Cart;
import entity.Movie;
import entity.Person;
import entity.PremiumMembers;
import entity.SimpleMembers;
import entity.Transactions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.jws.WebService;

@WebService
public class Service1 {

	DatabaseConnection db = new DatabaseConnection();
	Statement stmt = null;

	Validations obj = new Validations();

	//&& obj.validZipCode(zipCode)
	public String createMember(long memberId, String fName, String lName,
			String emailId, String pwd, String address, String city,
			String state, long zipCode, Integer userType,
			Integer monthlySubFee, Integer totalOutstandingMovies,
			Integer rentForMoviesIssued) throws SQLException {

		if (obj.validString(fName) && obj.validString(lName)
				&& obj.validEmail(emailId) && obj.validNumericString(pwd)
				&& obj.validNumericString(address) && obj.validString(city)
				&& obj.validString(state) 
				&& obj.validInt(userType) && obj.validInt(monthlySubFee)
				&& obj.validInt(totalOutstandingMovies)
				&& obj.validInt(rentForMoviesIssued)) {

			String result;
			result = db.createMember(memberId, fName, lName, emailId, pwd,
					address, city, state, zipCode, userType, monthlySubFee,
					totalOutstandingMovies, rentForMoviesIssued);
			return result;
		}

		return "false";

	}

	public Person login(String emailId, String pwd) throws SQLException {
		if(obj.validEmail(emailId)&&obj.validNumericString(pwd))
		{
			Person person;
			person = db.login(emailId, pwd);
			return person;
			
		}
		return null;
	}

	public Person memberFromID(long membershipId) throws SQLException {
		if(obj.validSSN(membershipId))
		{
			Person person;
			person = db.memberFromID(membershipId);
			return person;
		}
		
		return null;
	}

	public boolean updatePerson(long membershipId, String firstName,
			String lastName, String emailId, String password, String address,
			String city, String state, long zipCode) throws SQLException {
		
		if(obj.validSSN(membershipId)&&obj.validString(firstName)&&obj.validString(lastName)&&obj.validEmail(emailId)&&obj.validNumericString(password)&&obj.validNumericString(address)&&obj.validString(city)&&obj.validString(state)&&obj.validZipCode(zipCode))
		{

		// System.out.println("inside service");
		return db.updatePerson(membershipId, firstName, lastName, emailId,
				password, address, city, state, zipCode);
		}
		return false;
	}

	public Movie[] displayMovies() throws SQLException {
		// return Movies from Movie table
		return db.displayMovies();
	}

	public PremiumMembers[] displayPremiumMembers() throws SQLException {
		return db.displayPremiumMembers();
	}

	public Person[] displayPerson() throws SQLException {
		return db.displayPerson();
	}

	public SimpleMembers[] displaySimpleMembers() throws SQLException {

		return db.displaySimpleMembers();
	}

	public boolean createMovie(String movieName, String movieBanner,
			Date movieReleaseDate, float movieRent, String movieCategory,
			int availableCopies) throws SQLException {
		if(obj.validString(movieName)&&obj.validString(movieBanner)&&obj.validString(movieCategory)&&obj.validInt(availableCopies))
		{

		return db.createMovie(movieName, movieBanner, movieReleaseDate,
				movieRent, movieCategory, availableCopies);
		}
		return false;

	}

	public boolean deleteMovie(int movieId, String movieName) throws SQLException {
		if(obj.validInt(movieId)&&obj.validString(movieName))
		{
			return db.deleteMovie(movieId, movieName);
		}
		return false;
	}

	public boolean deleteMember(long membershipId) throws SQLException {
		
		if(obj.validSSN(membershipId))
		{

		boolean result;
		result = db.deleteMember(membershipId);
		return result;
		}
		return false;

	}

	public boolean updateMovie(int movieId, String movieName,
			String movieBanner, Date movieReleaseDate, float movieRent,
			String movieCategory, int availableCopies) throws SQLException {
		if(obj.validString(movieName)&&obj.validString(movieBanner)&&obj.validString(movieCategory)&&obj.validInt(availableCopies))
		{

		return db.updateMovie(movieId, movieName, movieBanner,
				movieReleaseDate, movieRent, movieCategory, availableCopies);
		}
		return false;

	}

	public Movie[] updateMovieRequest(int movieId) throws SQLException {
		
		
		if(obj.validInt(movieId))
		{
				return db.updateMovieRequest(movieId);
		}
		return null;
	}

	public Movie[] searchMovie(String attributeName, String attributeValue) throws SQLException {
		if(obj.validString(attributeValue)&&obj.validString(attributeName))
		{
		System.out.println("In Service- search movie");
		Movie movieInfo[];
		movieInfo = db.searchMovie(attributeName, attributeValue);

		return movieInfo;
		}
		return null;
	}

	public Person[] searchPerson(String attributeName, String attributeValue) throws SQLException {
		if(obj.validString(attributeName)&&obj.validString(attributeValue))
		{
			Person personInfo[];
			personInfo = db.searchPerson(attributeName, attributeValue);
			
			return personInfo;
		}
		return null;
	}

	public boolean addToCart(int movieId, long memberId) throws SQLException {
		if(obj.validInt(movieId)&&obj.validSSN(memberId))
		{
			return db.addToCart(movieId,memberId );
		}
		return false;
	}

	public boolean dopayment(long membershipId, float dueAmount) throws SQLException {
		if(obj.validSSN(membershipId))
		{
			return db.dopayment(membershipId, dueAmount);
		}
		return false;
	}

	public Movie getMovieInformationWithIssuedByUsers(int movieId) throws SQLException {
		if(obj.validInt(movieId))
		{
			return db.getMovieInformationWithIssuedByUsers(movieId);
		}
		return null;
	}

	public Cart[] displayCart(long membershipId) throws SQLException {
		if(obj.validSSN(membershipId))
		{
			return db.displayCart(membershipId);
		}
		return null;
	}

	public boolean removeMovieFromCart(int cartId) throws SQLException {
		if(obj.validInt(cartId))
		{
			boolean result;
			result = db.removeMovieFromCart(cartId);
			return result;
		}
		return false;
	}

	public float isPaymentDue(long membershipId) throws SQLException {
		if(obj.validSSN(membershipId))
		{
			float amount;
			amount = db.isPaymentDue(membershipId);
		
			 return amount;
		}
		return 0;					//check servlet side that if 0 then put exception
	}

	public boolean emptyCart(long membershipId) throws SQLException {
		if(obj.validSSN(membershipId))
		{
			boolean result;
			result = db.emptyCart(membershipId);
			return result;
		}
		return false;
	}

	public Transactions[] issuedMovieList(long membershipId) throws SQLException {
		if(obj.validSSN(membershipId))
		{
			return db.issuedMovieList(membershipId);
		}
		return null;
	}
	

	public boolean rentMovie(Cart[] cart_details_array, long membershipId,
			int userType) throws SQLException {
		if(obj.validSSN(membershipId))
		{

		return db.rentMovie(cart_details_array, membershipId, userType);
		}
		return false;
	}

	public boolean returnMovie(int movieId, long membershipId, int userType)
			throws SQLException {
		
		if(obj.validInt(movieId)&&obj.validSSN(membershipId))
		{

		boolean result = false;
		result = db.returnMovie(movieId, membershipId, userType);
		System.out.println(result);
		return result;
		}
		return false;
	}

	public Person getUserInformationWithIssuedMovies(long membershipId) throws SQLException {
		if(obj.validSSN(membershipId))
		{


		return db.getUserInformationWithIssuedMovies(membershipId);
	}
		return null;

}}
