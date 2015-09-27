package com.training.topaz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CartDisplayServlet
 */
public class CartDisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartDisplayServlet() {
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
		DatabaseConnection dbConnection=new DatabaseConnection();
		HttpSession session=request.getSession();
		Customer customer=(Customer)session.getAttribute("customer");
		String username=customer.getUsername();
		
		List<Cart> listCartItems= dbConnection.getCartItems(username);
		List<Item> listItems=new ArrayList<Item>();
		for (Cart cart : listCartItems) {
			Item item=new Item();
			item=dbConnection.getItemByID(cart.getItemNo());
			listItems.add(item);
		}
		request.setAttribute("listCartItems", listCartItems);
		request.setAttribute("listItems", listItems);
		request.getRequestDispatcher("/cart.jsp").forward(request, response);
		
		
		
	}

}
