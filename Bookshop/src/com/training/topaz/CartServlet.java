package com.training.topaz;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CartServlet
 */
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
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
		
		Customer customer= (Customer)session.getAttribute("customer");
		System.out.println(customer.getFname());
		
		String remove=request.getParameter("remove");
		String temp=request.getParameter("cartid");
		String username=customer.getUsername();
		int itemid=-1,qty=-1;
		if(remove == null)
		{ itemid=Integer.parseInt(request.getParameter("itemid"));
		 qty=Integer.parseInt(request.getParameter("quantity"));
		}
		
		System.out.println("qty:"+qty+"itemid"+itemid);
		Item item=getItem(itemid);
		
		
		int cartid=-1;
		if (temp!=null)
			cartid=Integer.parseInt(temp);
		boolean removeflag=false;
		if(remove!=null && remove.equalsIgnoreCase("true"))
		{
			removeflag=true;
		}
		//removing from cart
		if(removeflag && cartid!=-1 )
		{
			DatabaseConnection databaseConnection=new DatabaseConnection();
			databaseConnection.removeFromCart(cartid);
			
			List<Cart> listCartItems= databaseConnection.getCartItems(username);
			List<Item> listItems=new ArrayList<Item>();
			for (Cart cart : listCartItems) {
				Item item2=new Item();
				item2=databaseConnection.getItemByID(cart.getItemNo());
				listItems.add(item2);
			}
			request.setAttribute("listCartItems", listCartItems);
			request.setAttribute("listItems", listItems);
			request.getRequestDispatcher("/cart.jsp").forward(request, response);

		}
		else //adding to cart
		{
			DatabaseConnection databaseConnection=new DatabaseConnection();
			int returnqty=isSameItem(itemid,username);
			if(returnqty != -1)
			{
					try {
						qty=qty+returnqty;
						databaseConnection.updateCart(itemid,username,qty);
						RequestDispatcher dispatcher =request.getRequestDispatcher("/item.jsp");
						dispatcher.include(request, response);
						PrintWriter out=response.getWriter();
						out.println("<div id=added>Item added to cart</div>");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			else
			{	
				
				databaseConnection.addToCart(customer.getUsername(),itemid,qty,item.getCost());
				RequestDispatcher dispatcher =request.getRequestDispatcher("/item.jsp");
				dispatcher.include(request, response);
				PrintWriter out=response.getWriter();
				out.println("<div id=added>Item added to cart</div>");
			}
		}
		
	}
	private int isSameItem(int itemId,String username){
		DatabaseConnection databaseConnection=new DatabaseConnection();
		return databaseConnection.checkSameItem(itemId, username);
	}
	
	
	private Item getItem(int itemNo) {
	
		// TODO Auto-generated method stub
		DatabaseConnection dbConnection=new DatabaseConnection();
		Item item=dbConnection.getItemByID(itemNo);
		return item;
		

	}

}
