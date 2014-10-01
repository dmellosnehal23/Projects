package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Cart;
import entity.Person;

import VideoLibrary.ServiceProxy;

/**
 * Servlet implementation class cart
 */
public class cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServiceProxy proxy = new ServiceProxy();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public cart() {
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
		long memberId = Long.parseLong(session.getAttribute("memberId").toString()); 
		int userType= Integer.parseInt(session.getAttribute("userType").toString());

		session.setAttribute("userType", userType);
		try {
			if(formname.equals("remove")){
				
				int cartId = Integer.parseInt(request.getParameter("cartId"));
				proxy.removeMovieFromCart(cartId);
				Cart[] showCart=proxy.displayCart(memberId);
				//	session.setAttribute("userType", userType); // may need to add userType
				session.setAttribute("showCart", showCart);
				RequestDispatcher reqDispatcher = request.getRequestDispatcher("cart.jsp");
				reqDispatcher.forward(request, response);

			}

			if(formname.equals("checkout")){
				
					

				if(userType == 1){
					Cart[] showCart=proxy.displayCart(memberId);
					session.setAttribute("showCart", showCart);
					RequestDispatcher reqDispatcher = request.getRequestDispatcher("checkout.jsp");
					reqDispatcher.forward(request, response);
					
				}

				if(userType == 0){
					
					float amount =proxy.isPaymentDue(memberId);
					
					if (amount == 0){
						Cart[] showCart=proxy.displayCart(memberId);
						session.setAttribute("memberId",memberId);

						session.setAttribute("showCart", showCart);
						RequestDispatcher reqDispatcher = request.getRequestDispatcher("checkoutPremium.jsp");
						reqDispatcher.forward(request, response);						
					}
					
					else
					{
						session.setAttribute("memberId",memberId);
						session.setAttribute("userType",userType);
						session.setAttribute("dueAmount", amount);
						RequestDispatcher reqDispatcher = request.getRequestDispatcher("paymentPremium.jsp");
						reqDispatcher.forward(request, response);

						
					}
					
				}

			}

		}
	    catch (Exception e) {
	    	e.printStackTrace();
		}
		
		
		
	}

}
