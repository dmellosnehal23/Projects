package entity;


import java.util.Date;
import java.util.Arrays;//SNEHAL

public class Person {

	private long membershipId;
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
	java.util.ArrayList<Transactions>  transactions= new java.util.ArrayList<Transactions>();//SNEHAL
	
	public Person(long membershipId, String firstName, String lastName, String emailId, String password, String address, String city, String state, long zipCode, int userType, Date registrationDate, Date lastLogin)
	{
		this.membershipId = membershipId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.password = password;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.userType = userType;
		//this.rentedMovies = rentedMovies;
		this.registrationDate = registrationDate;
		//this.expiryDate = expiryDate;
		this.lastLogin = lastLogin;
	}
	
	public Person(){
		
	}
	
	public long getMembershipId() {
		return membershipId;
	}
	public void setMembershipId(long membershipId) {
		this.membershipId = membershipId;
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
	public void setTransaction(Transactions transaction)
	{
		//SNEHAL
		this.transactions.add(transaction);
	}
	public Transactions[] getTransactions()
	{
		//SNEHAL
		return Arrays.copyOf(this.transactions.toArray(), this.transactions.toArray().length, Transactions[].class);
	}
	
}
