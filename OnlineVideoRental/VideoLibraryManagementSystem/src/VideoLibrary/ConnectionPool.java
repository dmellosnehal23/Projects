package VideoLibrary;

import java.sql.*;
import java.util.*;
//Reference: http://stackoverflow.com/questions/1448974/connection-pooling-with-java-and-mysql-in-tomcat-web-application

public class ConnectionPool implements Runnable {
	int initialallowableConnections = 100;
  private String SQLdriver, SQLurl, SQLusername, SQLpassword;   
  private Vector connectionsAvailable, connectionsBusy;
  
  
  private boolean waitIncaseBusy;
  
  static private ConnectionPool instance_pool;
  //int connectionCallCount = 0;  
  private int maxallowableConnections;
  
  private boolean pendingConnection = false;

  private ConnectionPool(){
    this.SQLdriver = "com.mysql.jdbc.Driver";
    this.waitIncaseBusy = true;
    this.SQLusername = "root";
    this.SQLpassword = "";
    this.SQLurl = "jdbc:mysql://localhost/videolibrary";
    this.maxallowableConnections = 1000;
    if (initialallowableConnections > maxallowableConnections) {
    	initialallowableConnections = maxallowableConnections;
    }
    connectionsAvailable = new Vector(initialallowableConnections);
    connectionsBusy = new Vector();
    for(int i=0; i<initialallowableConnections; i++) {
      try {
    	  connectionsAvailable.addElement(createNewConnection());
	} catch (SQLException e) {
		e.printStackTrace();
	}
    }
    
  }
  
  public synchronized Connection getConnection()throws SQLException {
	  System.out.println("WOW .. Connectionpool class -> getConnection is called");		  
	    if (!connectionsAvailable.isEmpty()) {
	      Connection connection_existing = (Connection)connectionsAvailable.lastElement();
	      int Index_Last = connectionsAvailable.size() - 1;
	      connectionsAvailable.removeElementAt(Index_Last);
	      if (connection_existing.isClosed()) {
	        notifyAll(); 
	        return(getConnection());
	      } else {
	    	  connectionsBusy.addElement(connection_existing);
	        return(connection_existing);
	      }
	    } else {	      
	      if ((Connections_total() < maxallowableConnections) && !pendingConnection) {
	        createBackgroundConnection();
	      } else if (!waitIncaseBusy) {
	        throw new SQLException("Oops Connection limit has reached");
	      }
	     try {
	        wait();
	      } catch(InterruptedException ie) {}
	      
	      return(getConnection());
	    }
	  }
  
  public synchronized int Connections_total() {
	    return(connectionsAvailable.size() + connectionsBusy.size());
  }
  
  static public ConnectionPool getInstance(){
	  if (instance_pool == null){
		  instance_pool = new ConnectionPool();
	  }
	  return instance_pool;
  }
  
  private Connection createNewConnection() throws SQLException {
	    try {
	      Class.forName(SQLdriver);	      
	      Connection newConnection = DriverManager.getConnection(SQLurl, SQLusername, SQLpassword);
	      return(newConnection);
	    } catch(ClassNotFoundException cnfe) {
	    	throw new SQLException("Oops sorry..Could't find class for driver: " + SQLdriver);
	    }
	  }

  public void run() {
    try {
      Connection connection = createNewConnection();
      synchronized(this) {
    	  connectionsAvailable.addElement(connection);
    	  pendingConnection = false;
        notifyAll();
      }
    } catch(Exception e) { 
     
    }
  }

  public synchronized void free(Connection connection) {
	 System.out.println("You are in Connectionpool class -> Free method is called...");
	 connectionsBusy.removeElement(connection);
    connectionsAvailable.addElement(connection);
    notifyAll(); 
  }
  public synchronized void allConnections_Close() {
    connections_Close(connectionsAvailable);
    connectionsAvailable = new Vector();
    connections_Close(connectionsBusy);
    connectionsBusy = new Vector();
  }

  private void connections_Close(Vector connections) {
    try {
      for(int i=0; i<connections.size(); i++) {
        Connection connection =
          (Connection)connections.elementAt(i);
        if (!connection.isClosed()) {
          connection.close();
        }
      }
    } catch(SQLException se) {
      
    }
  }

  private void createBackgroundConnection() {
	  pendingConnection = true;
    try {
      Thread connectNewThread = new Thread(this);
      connectNewThread.start();
    } catch(OutOfMemoryError oome) {
      
    }
  }
  
}
