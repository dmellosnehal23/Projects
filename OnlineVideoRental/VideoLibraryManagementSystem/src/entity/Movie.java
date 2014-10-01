package entity;

import java.util.Date;

public class Movie {

	private int movieId;
	private String movieName;
	private String movieBanner;
	private Date movieReleaseDate;
	private float movieRent;
	private String movieCategory;
	private int availableCopies;
	private Person[] movieUsers;
	
	public Movie(int movieId, String movieName, String movieBanner, Date movieReleaseDate, float movieRent, String movieCategory, int availableCopies)
	{
		this.movieId = movieId;
		this.movieName = movieName;
		this.movieBanner = movieBanner;
		this.movieReleaseDate = movieReleaseDate;
		this.movieRent = movieRent;
		this.movieCategory = movieCategory;
		this.availableCopies = availableCopies;
	}
	
	public Movie() {
		// TODO Auto-generated constructor stub
	}

	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getMovieBanner() {
		return movieBanner;
	}
	public void setMovieBanner(String movieBanner) {
		this.movieBanner = movieBanner;
	}
	public Date getMovieReleaseDate() {
		return movieReleaseDate;
	}
	public void setMovieReleaseDate(Date movieReleaseDate) {
		this.movieReleaseDate = movieReleaseDate;
	}
	public float getMovieRent() {
		return movieRent;
	}
	public void setMovieRent(float movieRent) {
		this.movieRent = movieRent;
	}
	public String getMovieCategory() {
		return movieCategory;
	}
	public void setMovieCategory(String movieCategory) {
		this.movieCategory = movieCategory;
	}
	public int getAvailableCopies() {
		return availableCopies;
	}
	public void setAvailableCopies(int availableCopies) {
		this.availableCopies = availableCopies;
	}
	public Person[] getUsers(){
		return movieUsers;
	}
	public void setUsers(Person[] movieUsers){
		
		this.movieUsers = movieUsers;
		
	}
	
	
	
}
