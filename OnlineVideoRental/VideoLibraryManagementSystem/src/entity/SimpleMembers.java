package entity;

import java.util.Date;

public class SimpleMembers {

	private int simpleMemberId;
	private long membershipId;
	private float rentForMoviesIssued;
	private int totalOutstandingMovies;
	private String firstName;
	private String lastName;
	private String emailId;
	private String password;
	private String address;
	private String city;
	private String state;
	private long zipCode;
	private int userType;
	//private int rentedMovies;
	private Date registrationDate;
	//private Date expiryDate;
	private Date lastLogin;
	
	public SimpleMembers(int simpleMemberId, int membershipId, int rentForMoviesIssued, int totalOutstandingMovies)
	{
		this.simpleMemberId=simpleMemberId;
		this.membershipId = membershipId;
		this.rentForMoviesIssued = rentForMoviesIssued;
		this.totalOutstandingMovies = totalOutstandingMovies;
		
	}
	public SimpleMembers(){
		
	}
	
	public int getSimpleMemberId() {
		return simpleMemberId;
	}
	public void setSimpleMemberId(int simpleMemberId) {
		this.simpleMemberId = simpleMemberId;
	}
	public long getMembershipId() {
		return membershipId;
	}
	public void setMembershipId(long membershipId) {
		this.membershipId = membershipId;
	}
	public float getRentForMoviesIssued() {
		return rentForMoviesIssued;
	}
	public void setRentForMoviesIssued(float rentForMoviesIssued) {
		this.rentForMoviesIssued = rentForMoviesIssued;
	}
	public int getTotalOutstandingMovies() {
		return totalOutstandingMovies;
	}
	public void setTotalOutstandingMovies(int totalOutstandingMovies) {
		this.totalOutstandingMovies = totalOutstandingMovies;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public long getZipCode() {
		return zipCode;
	}
	public void setZipCode(long zipCode) {
		this.zipCode = zipCode;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	
}
