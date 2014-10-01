package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Cart;

import VideoLibrary.ServiceProxy;

/**
 * Servlet implementation class checkoutPremium
 */
public class checkoutPremium extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServiceProxy proxy = new ServiceProxy();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checkoutPremium() {
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
		PrintWriter out = response.getWriter();

		VideoLibrary.ServiceProxy proxy = new VideoLibrary.ServiceProxy();
		response.setContentType("text/html");
		String formname = request.getParameter("formname");
		proxy.setEndpoint("http://localhost:8080/VideoLibraryManagementSystem/services/Service");

		HttpSession session = request.getSession(false);	
		long memberId = Long.parseLong(session.getAttribute("memberId").toString()); 	
		int userType= Integer.parseInt(session.getAttribute("userType").toString());

		Cart[] listMovies	= (Cart[])session.getAttribute("showCart");
		
		
		
	
		boolean result =false;
		
		if (( userType==1 && listMovies.length <=2) || ( userType==0 && listMovies.length <=10)) {

		 result =	proxy.rentMovie(listMovies,  memberId, userType);
		 
			if (result == true){
		 	boolean result1= proxy.emptyCart(memberId);	
			RequestDispatcher reqDispatcher = request.getRequestDispatcher("success.jsp");
			reqDispatcher.forward(request, response);
			}
		}
					
			if (result == false)
			{
				
				Cart[] showCart = proxy.displayCart(memberId);
				session.setAttribute("showCart", showCart);

				if (userType==0)
				{
					
					RequestDispatcher reqDispatcher = request.getRequestDispatcher("cart.jsp?issue=Premium");
					reqDispatcher.forward(request, response);
					
				}
				else
				{

					RequestDispatcher reqDispatcher = request.getRequestDispatcher("cart.jsp?issue=Simple");
					reqDispatcher.forward(request, response);			
				}
				
			}
					

			
			}
}
			
//			RequestDispatcher reqDispatcher = request.getRequestDispatcher("cart.jsp?issue=Simple");
//			reqDispatcher.forward(request, response);			
//
			
		
		
		
		

//		if (result == false)
//		{
//			if (userType==0)
//			{
//				RequestDispatcher reqDispatcher = request.getRequestDispatcher("cart.jsp?issue=Premium");
//				reqDispatcher.forward(request, response);
//				
//			}
//			else
//			{
//				RequestDispatcher reqDispatcher = request.getRequestDispatcher("cart.jsp?issue=Simple");
//				reqDispatcher.forward(request, response);			
//			}
//			
//		}
//		else
//		{
		
	//}

//}
