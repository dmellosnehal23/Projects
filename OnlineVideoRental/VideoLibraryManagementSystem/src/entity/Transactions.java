package entity;

import java.util.Date;

public class Transactions {

	private long transactionId; 
	private long membershipId;
	private int movieId;
	private Date issueDate;
	private Date dueDate;
	private Date actualReturnDate;
	private float fineAmount;
	private String movieName;
	private Movie movie; //SNEHAL
	
	public Transactions(long transactionId, long membershipId, int movieId, Date issueDate, Date dueDate, Date actualReturnDate, float fineAmount)
	{
		this.transactionId = transactionId;
		this.membershipId = membershipId;
		this.movieId = movieId;
		this.issueDate = issueDate;
		this.dueDate = dueDate;
		this.actualReturnDate = actualReturnDate;
		this.fineAmount = fineAmount;
	}
	public Transactions(){
		
	}
	
	public long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}
	public long getMembershipId() {
		return membershipId;
	}
	public void setMembershipId(long membershipId) {
		this.membershipId = membershipId;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Date getActualReturnDate() {
		return actualReturnDate;
	}
	public void setActualReturnDate(Date actualReturnDate) {
		this.actualReturnDate = actualReturnDate;
	}
	public float getFineAmount() {
		return fineAmount;
	}
	public void setFineAmount(float fineAmount) {
		this.fineAmount = fineAmount;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public Movie getMovie() {
		//SNEHAL
		return movie;
	}
	public void setMovie(Movie movie) {
		//SNEHAL
		this.movie = movie;
		//String mname = this.getMovie().getMovieName();
	}
	
	
	
}
