package com.training.topaz;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
     
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
	      String password=request.getParameter("password");
	      System.out.println("username and password : "+username+","+password);
	      response.setContentType("text/html");
	      
	      DatabaseConnection databaseConnection = new DatabaseConnection();
	      
	      Customer customer= databaseConnection.loginValidate(username,password);
	      
	      RequestDispatcher dispatcher =null;
	      
	      PrintWriter out = response.getWriter();
	     HttpSession session=request.getSession();
	     
	     session.setAttribute("customer", customer);
	      
	      if(customer!=null) //forward to home.jsp
	      {
	    	 
	    	  dispatcher = getServletContext().getRequestDispatcher("/home.jsp");

	    	  if (dispatcher != null) {
	    		  dispatcher.forward(request, response);
	    	  	}
	      }
	      else // include to login.jsp
	      {
	    	  
	    	  dispatcher = getServletContext().getRequestDispatcher("/login.html");

	      	  if (dispatcher != null) {
	      		  dispatcher.include(request, response);
	      	  	}
	    	  
	      }

	}
}
