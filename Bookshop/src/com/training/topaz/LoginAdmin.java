package com.training.topaz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginAdmin
 */
public class LoginAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		DatabaseConnection dbConnection = new DatabaseConnection();
		try {
			boolean isvalid = dbConnection.validateAdmin(username,password);
			RequestDispatcher dispatcher =null;
			if (isvalid){
				
			      System.out.println("isvalid = true");

			      	List<Customer> listCustomers=dbConnection.getApprovalRequests();
			      	request.setAttribute("approvals", listCustomers);
			      	HttpSession session=request.getSession();
			      	
			      	
			    	  dispatcher = getServletContext().getRequestDispatcher("/Pending.jsp");

			    	  if (dispatcher != null) {
			    		  dispatcher.forward(request, response);
			    	  	}
			}
			      else // include to login.jsp
			      {
			    	  System.out.println("isvalid false");
			    	  
			    	  dispatcher = getServletContext().getRequestDispatcher("/login.html");

			      	  if (dispatcher != null) {
			      		  dispatcher.include(request, response);
			      	  	}
			    	  
			      }
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
