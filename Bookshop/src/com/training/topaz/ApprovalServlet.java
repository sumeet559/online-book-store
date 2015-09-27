package com.training.topaz;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ApprovalServlet
 */
public class ApprovalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApprovalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action=request.getParameter("approve");
		String username=request.getParameter("username");
		System.out.println("username: "+username);
		List<Customer> listCustomers;
		
		int descision=0;
		DatabaseConnection dBDatabaseConnection=new DatabaseConnection();
		if(action.equalsIgnoreCase("true"))
		{
			
			try {
				dBDatabaseConnection.setApprovalRequest(username);
				listCustomers = dBDatabaseConnection.getApprovalRequests();
				request.setAttribute("approvals", listCustomers);
				request.getRequestDispatcher("/Pending.jsp").forward(request, response);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			try {
				dBDatabaseConnection.setRejectRequest(username);
				listCustomers = dBDatabaseConnection.getApprovalRequests();
				request.setAttribute("approvals", listCustomers);
				request.getRequestDispatcher("/AdminPage.jsp").forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
		
	}

}
