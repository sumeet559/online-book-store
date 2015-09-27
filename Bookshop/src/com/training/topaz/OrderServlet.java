package com.training.topaz;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class OrderServlet
 */
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
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
		HttpSession session=request.getSession();
		List<Cart> listCartItems= (List<Cart>) session.getAttribute("cart");
		
		if(listCartItems.size() == 0)
		{
			request.getRequestDispatcher("/home.jsp").forward(request, response);
		}
		else
		{
		Customer customer=(Customer)session.getAttribute("customer");
		double total=(Double) session.getAttribute("total");
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
	
		DatabaseConnection dbConnection=new DatabaseConnection();
		boolean flag=dbConnection.orderTransaction(customer.getUsername(),total,dateFormat.format(date),listCartItems);
		if(flag)
		{	
			System.out.println("successful");
			request.setAttribute("flag", "success");
		}
		else
		{	System.out.println("not sucessful");
			request.setAttribute("flag", "fail");
		}
		request.getRequestDispatcher("/order.jsp").forward(request, response);
		}
	}

}
