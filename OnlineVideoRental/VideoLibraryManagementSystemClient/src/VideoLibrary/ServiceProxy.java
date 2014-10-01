package VideoLibrary;

public class ServiceProxy implements VideoLibrary.Service {
  private String _endpoint = null;
  private VideoLibrary.Service service = null;
  
  public ServiceProxy() {
    _initServiceProxy();
  }
  
  public ServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initServiceProxy();
  }
  
  private void _initServiceProxy() {
    try {
      service = (new VideoLibrary.ServiceServiceLocator()).getService();
      if (service != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)service)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)service)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (service != null)
      ((javax.xml.rpc.Stub)service)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public VideoLibrary.Service getService() {
    if (service == null)
      _initServiceProxy();
    return service;
  }
  
  public entity.Person login(java.lang.String emailId, java.lang.String pwd) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.login(emailId, pwd);
  }
  
  public java.lang.String createMember(long memberId, java.lang.String fName, java.lang.String lName, java.lang.String emailId, java.lang.String pwd, java.lang.String address, java.lang.String city, java.lang.String state, long zipCode, int userType, int monthlySubFee, int totalOutstandingMovies, int rentForMoviesIssued) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.createMember(memberId, fName, lName, emailId, pwd, address, city, state, zipCode, userType, monthlySubFee, totalOutstandingMovies, rentForMoviesIssued);
  }
  
  public entity.Person memberFromID(long membershipId) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.memberFromID(membershipId);
  }
  
  public boolean updatePerson(long membershipId, java.lang.String firstName, java.lang.String lastName, java.lang.String emailId, java.lang.String password, java.lang.String address, java.lang.String city, java.lang.String state, long zipCode) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.updatePerson(membershipId, firstName, lastName, emailId, password, address, city, state, zipCode);
  }
  
  public entity.Movie[] displayMovies() throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.displayMovies();
  }
  
  public entity.PremiumMembers[] displayPremiumMembers() throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.displayPremiumMembers();
  }
  
  public entity.Person[] displayPerson() throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.displayPerson();
  }
  
  public entity.SimpleMembers[] displaySimpleMembers() throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.displaySimpleMembers();
  }
  
  public boolean createMovie(java.lang.String movieName, java.lang.String movieBanner, java.util.Date movieReleaseDate, float movieRent, java.lang.String movieCategory, int availableCopies) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.createMovie(movieName, movieBanner, movieReleaseDate, movieRent, movieCategory, availableCopies);
  }
  
  public boolean deleteMovie(int movieId, java.lang.String movieName) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.deleteMovie(movieId, movieName);
  }
  
  public boolean deleteMember(long membershipId) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.deleteMember(membershipId);
  }
  
  public boolean updateMovie(int movieId, java.lang.String movieName, java.lang.String movieBanner, java.util.Date movieReleaseDate, float movieRent, java.lang.String movieCategory, int availableCopies) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.updateMovie(movieId, movieName, movieBanner, movieReleaseDate, movieRent, movieCategory, availableCopies);
  }
  
  public entity.Movie[] updateMovieRequest(int movieId) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.updateMovieRequest(movieId);
  }
  
  public entity.Movie[] searchMovie(java.lang.String attributeName, java.lang.String attributeValue) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.searchMovie(attributeName, attributeValue);
  }
  
  public entity.Person[] searchPerson(java.lang.String attributeName, java.lang.String attributeValue) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.searchPerson(attributeName, attributeValue);
  }
  
  public boolean addToCart(int movieId, long memberId) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.addToCart(movieId, memberId);
  }
  
  public boolean dopayment(long membershipId, float dueAmount) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.dopayment(membershipId, dueAmount);
  }
  
  public entity.Movie getMovieInformationWithIssuedByUsers(int movieId) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.getMovieInformationWithIssuedByUsers(movieId);
  }
  
  public entity.Cart[] displayCart(long membershipId) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.displayCart(membershipId);
  }
  
  public boolean removeMovieFromCart(int cartId) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.removeMovieFromCart(cartId);
  }
  
  public float isPaymentDue(long membershipId) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.isPaymentDue(membershipId);
  }
  
  public boolean emptyCart(long membershipId) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.emptyCart(membershipId);
  }
  
  public entity.Transactions[] issuedMovieList(long membershipId) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.issuedMovieList(membershipId);
  }
  
  public boolean rentMovie(entity.Cart[] cart_details_array, long membershipId, int userType) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.rentMovie(cart_details_array, membershipId, userType);
  }
  
  public boolean returnMovie(int movieId, long membershipId, int userType) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.returnMovie(movieId, membershipId, userType);
  }
  
  public entity.Person getUserInformationWithIssuedMovies(long membershipId) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.getUserInformationWithIssuedMovies(membershipId);
  }
  
  
}