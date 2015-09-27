package com.training.topaz;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
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

		System.out.println("Inside add servlet dopost");
		/*
		PrintWriter out=response.getWriter();
		String act=request.getParameter("action");
		// TODO Auto-generated method stub
		 */
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String email=request.getParameter("email");
		String mobile=(request.getParameter("mobile"));
		String address = request.getParameter("address");

		Customer customer = new Customer();
		customer.setUsername(username);
		customer.setPassword(password);
		customer.setFname(fname);
		customer.setLname(lname);
		customer.setEmail(email);
		customer.setMobile(mobile);
		customer.setAddress(address);
		customer.setFlag(0);


		DatabaseConnection databaseConnection = new DatabaseConnection();
		try {
			databaseConnection.register(customer);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		RequestDispatcher dispatcher =null;

		//PrintWriter out = response.getWriter();

		if(customer!=null) //forward to home.jsp
		{

			dispatcher = getServletContext().getRequestDispatcher("/login.html");

			if (dispatcher != null) {
				dispatcher.forward(request, response);
			}
		}
	

	}

}
