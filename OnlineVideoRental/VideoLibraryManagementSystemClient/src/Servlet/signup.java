package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import VideoLibrary.ServiceProxy;

/**
 * Servlet implementation class signup
 */
public class signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServiceProxy proxy = new ServiceProxy();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public signup() {
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
		System.out.println("Welcomes");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String qdone ;
		int monthlySubFee =0;
		try {
			
			String membershipId1 = request.getParameter("membershipId"); // Email.

			String[] userId = membershipId1.split("\\-");
			
			membershipId1 = userId.toString();
			StringBuffer st= new StringBuffer();
			for(int i=0;i<userId.length;i++){
			st.append(userId[i]);
			}
			membershipId1 = st.toString();
			
			long memberId =Long.parseLong(membershipId1);

			
			//long membershipId = Long.parseLong(request.getParameter("membershipId")); // Email.
			String emailId = request.getParameter("email"); // Email.
			String password = request.getParameter("password"); // password.
			String firstName  = request.getParameter("fname"); // First Name.
			String lastName = request.getParameter("lname"); // Last Name
			String address = request.getParameter("address"); // Address.
			String city = request.getParameter("city"); // City
			String state = request.getParameter("state"); // State
			long zipCode = Long.parseLong(request.getParameter("zipcode")); // Zip Code.
			int userType= Integer.parseInt(request.getParameter("userType")); // Member type.
			
			
			if (userType == 0) {
				 monthlySubFee= 50;
			}
			else{
				 monthlySubFee= 0;

			}

								
			proxy.setEndpoint("http://localhost:8080/VideoLibraryManagementSystem/services/Service");
					
			
			qdone = proxy.createMember(memberId,firstName, lastName, emailId, password, address, city, state, 
					zipCode, userType,monthlySubFee, 0, 0);

			HttpSession session = request.getSession();

			RequestDispatcher reqDispatcher;
			if (qdone.equals("true")) {
				//session.setAttribute("userSession", session);
				reqDispatcher = request.getRequestDispatcher("login.jsp?flag=1");
				reqDispatcher.forward(request, response);
			} else {
				reqDispatcher = request.getRequestDispatcher("signup.jsp?flag=yes");
				reqDispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}
