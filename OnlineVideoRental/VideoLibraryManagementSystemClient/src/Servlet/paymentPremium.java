package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Cart;

import VideoLibrary.ServiceProxy;

/**
 * Servlet implementation class paymentPremium
 */
public class paymentPremium extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServiceProxy proxy = new ServiceProxy();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public paymentPremium() {
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

		try {
			if(formname.equals("paymentPremium")){
				
				float dueAmount = Float.parseFloat((session.getAttribute("dueAmount").toString())); 

				boolean result=proxy.dopayment(memberId, dueAmount);
				
				if (result==true){
					
					Cart[] showCart=proxy.displayCart(memberId);
					session.setAttribute("memberId",memberId);
					session.setAttribute("showCart", showCart);
					RequestDispatcher reqDispatcher = request.getRequestDispatcher("checkoutPremium.jsp");
					reqDispatcher.forward(request, response);
				}
			}
			}
	
	    catch (Exception e) {
	    	e.printStackTrace();
		}

	}
}
