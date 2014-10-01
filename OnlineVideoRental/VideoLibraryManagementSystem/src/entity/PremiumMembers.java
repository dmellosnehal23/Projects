package entity;

import java.util.Date;

public class PremiumMembers {

	private int premiumMemberId;
	private long membershipId;
	private float dueAmount;
	private int totalOutstandingMovies;
	private Date expiryDate;
	private String firstName;
	private String lastName;
	private String emailId;
	private String password;
	private String address;
	private String city;
	private String state;
	private long zipCode;
	private int userType;
	private Date registrationDate;	
	private Date lastLogin;
	
	public PremiumMembers(int premiumMemberId, long membershipId, float dueAmount, int totalOutstandingMovies, Date expiryDate){
		this.premiumMemberId = premiumMemberId;
		this.membershipId = membershipId;
		this.dueAmount = dueAmount;
		this.totalOutstandingMovies = totalOutstandingMovies;		
	}
	public PremiumMembers(){
		
	}
	
	public int getPremiumMemberId() {
		return premiumMemberId;
	}
	public void setPremiumMemberId(int premiumMemberId) {
		this.premiumMemberId = premiumMemberId;
	}
	public long getMembershipId() {
		return membershipId;
	}
	public void setMembershipId(long membershipId) {
		this.membershipId = membershipId;
	}
	public float getDueAmount() {
		return dueAmount;
	}
	public void setDueAmount(float dueAmount) {
		this.dueAmount = dueAmount;
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
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
}
