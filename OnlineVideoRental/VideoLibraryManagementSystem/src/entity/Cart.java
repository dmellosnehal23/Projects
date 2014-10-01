package entity;

public class Cart {
	private int cartId;
	private long membershipId;
	private int movieId;
	private Movie movieDetails;
	
	public Cart(int cartId, long membershipId, int movieId){
		this.cartId = cartId;
		this.membershipId = membershipId;
		this.movieId = movieId;
	}
	public Cart(){
		
	}
	
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
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
	public Movie getMovieDetails(){
		return movieDetails;
	}
	public void setMovieDetails(Movie movieDetails){
		this.movieDetails = movieDetails;		
	}	
}
