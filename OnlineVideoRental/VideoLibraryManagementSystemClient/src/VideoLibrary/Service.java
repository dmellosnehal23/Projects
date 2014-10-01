/**
 * Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package VideoLibrary;

public interface Service extends java.rmi.Remote {
    public entity.Person login(java.lang.String emailId, java.lang.String pwd) throws java.rmi.RemoteException;
    public java.lang.String createMember(long memberId, java.lang.String fName, java.lang.String lName, java.lang.String emailId, java.lang.String pwd, java.lang.String address, java.lang.String city, java.lang.String state, long zipCode, int userType, int monthlySubFee, int totalOutstandingMovies, int rentForMoviesIssued) throws java.rmi.RemoteException;
    public entity.Person memberFromID(long membershipId) throws java.rmi.RemoteException;
    public boolean updatePerson(long membershipId, java.lang.String firstName, java.lang.String lastName, java.lang.String emailId, java.lang.String password, java.lang.String address, java.lang.String city, java.lang.String state, long zipCode) throws java.rmi.RemoteException;
    public entity.Movie[] displayMovies() throws java.rmi.RemoteException;
    public entity.PremiumMembers[] displayPremiumMembers() throws java.rmi.RemoteException;
    public entity.Person[] displayPerson() throws java.rmi.RemoteException;
    public entity.SimpleMembers[] displaySimpleMembers() throws java.rmi.RemoteException;
    public boolean createMovie(java.lang.String movieName, java.lang.String movieBanner, java.util.Date movieReleaseDate, float movieRent, java.lang.String movieCategory, int availableCopies) throws java.rmi.RemoteException;
    public boolean deleteMovie(int movieId, java.lang.String movieName) throws java.rmi.RemoteException;
    public boolean deleteMember(long membershipId) throws java.rmi.RemoteException;
    public boolean updateMovie(int movieId, java.lang.String movieName, java.lang.String movieBanner, java.util.Date movieReleaseDate, float movieRent, java.lang.String movieCategory, int availableCopies) throws java.rmi.RemoteException;
    public entity.Movie[] updateMovieRequest(int movieId) throws java.rmi.RemoteException;
    public entity.Movie[] searchMovie(java.lang.String attributeName, java.lang.String attributeValue) throws java.rmi.RemoteException;
    public entity.Person[] searchPerson(java.lang.String attributeName, java.lang.String attributeValue) throws java.rmi.RemoteException;
    public boolean addToCart(int movieId, long memberId) throws java.rmi.RemoteException;
    public boolean dopayment(long membershipId, float dueAmount) throws java.rmi.RemoteException;
    public entity.Movie getMovieInformationWithIssuedByUsers(int movieId) throws java.rmi.RemoteException;
    public entity.Cart[] displayCart(long membershipId) throws java.rmi.RemoteException;
    public boolean removeMovieFromCart(int cartId) throws java.rmi.RemoteException;
    public float isPaymentDue(long membershipId) throws java.rmi.RemoteException;
    public boolean emptyCart(long membershipId) throws java.rmi.RemoteException;
    public entity.Transactions[] issuedMovieList(long membershipId) throws java.rmi.RemoteException;
    public boolean rentMovie(entity.Cart[] cart_details_array, long membershipId, int userType) throws java.rmi.RemoteException;
    public boolean returnMovie(int movieId, long membershipId, int userType) throws java.rmi.RemoteException;
    public entity.Person getUserInformationWithIssuedMovies(long membershipId) throws java.rmi.RemoteException;
}
