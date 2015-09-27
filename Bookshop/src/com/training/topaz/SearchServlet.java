package com.training.topaz;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchServlet
 */
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
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
		String select=request.getParameter("select1");
		String select1=select.toLowerCase();
		int	 offset=Integer.parseInt(request.getParameter("offset"));
		String category;
		String text=request.getParameter("text");
		if(select1.equalsIgnoreCase("category"))
		{
			category=request.getParameter("select2");
		}
		else{
			System.out.println("in else");
			System.out.println("poffset"+offset);
			request.setAttribute("select1", select1);
			request.setAttribute("text", text);
			List<Item> listItems=dbConnection.getBooksAll(select1,text,offset);
			request.setAttribute("items", listItems);
			/*for (Item item : listItems) {
				System.out.println(item.getTitle());
			}*/
			request.getRequestDispatcher("/search.jsp").forward(request, response);
		}
		
		
		
		
		
		
		
		
		
		
	}

}
