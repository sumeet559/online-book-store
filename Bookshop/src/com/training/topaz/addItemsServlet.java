package com.training.topaz;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.rmi.server.Dispatcher;

/**
 * Servlet implementation class addItemsServlet
 */
public class addItemsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addItemsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String title=request.getParameter("title");
		String author=request.getParameter("author");
		String publication=request.getParameter("publication");
		String category=request.getParameter("category");
		String desc=request.getParameter("desc");
		double cost=Double.parseDouble(request.getParameter("cost"));
		int stock=Integer.parseInt(request.getParameter("stock"));
		Item item = new Item(title,author,publication,category,desc,cost,stock);
		DatabaseConnection dbConnection = new DatabaseConnection();
		try {
			boolean checkAdd = dbConnection.addItems(item);
			RequestDispatcher dispatcher = null;
			if(checkAdd==true) {
				PrintWriter out = response.getWriter();
				System.out.println("Item added");
				dispatcher = getServletContext().getRequestDispatcher("/adminAddItems.jsp");	

				if(dispatcher!=null) {
					dispatcher.include(request, response);
					response.setContentType("text/html"); 
					out.println("<h2 style=\"color:green\">Item Added successfully!</h2>"); 

				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
