package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import VideoLibrary.ServiceProxy;
import entity.Cart;
import entity.Movie;
import entity.Person;

/**
 * Servlet implementation class homescreen
 */
public class homescreen extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServiceProxy proxy = new ServiceProxy();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public homescreen() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		doPost(request,response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		VideoLibrary.ServiceProxy proxy = new VideoLibrary.ServiceProxy();
		response.setContentType("text/html");
		String formname = request.getParameter("formname");
		proxy.setEndpoint("http://localhost:8080/VideoLibraryManagementSystem/services/Service");

		HttpSession session = request.getSession(false);	
		//Person  userType1 = (Person)session.getAttribute("userType");
		
		int userType= Integer.parseInt(session.getAttribute("userType").toString());

		try {
			if(formname.equals("addCart")){

		String  Id = session.getAttribute("memberId").toString();
			long memberId = Long.parseLong(Id);
			int movieId = Integer.parseInt(request.getParameter("movieId"));

			boolean result = proxy.addToCart(movieId, memberId); //// 
	
			if (result == true){
			
			
			Cart[] showCart=proxy.displayCart(memberId);
			session.setAttribute("memberId", memberId); // may need to add userType
			session.setAttribute("userType", userType); // may need to add userType

			session.setAttribute("showCart", showCart);
			RequestDispatcher reqDispatcher = request.getRequestDispatcher("cart.jsp");
			reqDispatcher.forward(request, response);
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
