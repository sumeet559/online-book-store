package com.training.topaz;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class addStockServlet
 */
public class addStockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addStockServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DatabaseConnection databaseConnection = new DatabaseConnection();
		String category=request.getParameter("categorySelect");	
		String pub=request.getParameter("pubSelect");
		String author=request.getParameter("authorSelect");
		System.out.println("line38:"+author);
		String title=request.getParameter("abc");
		int stock=Integer.parseInt(request.getParameter("stockInput"));
		
		boolean stockAdded = false;
		try {
			stockAdded = databaseConnection.updateStock(category,pub,author,stock,title);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(stockAdded){
			System.out.println("Stock is updated");
			response.setContentType("application/json");
			//			out.print("Hello2");
			response.setHeader("Cache-Control", "no-cache");
			response.setStatus(HttpServletResponse.SC_OK);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String message = "1";

			String resultStrJson = gson.toJson(message);
			//System.out.println("before out.print");
			PrintWriter out = response.getWriter();
			out.print("{\"message\":"+resultStrJson+"}");
			out.close();
			out.flush();
		}
		else {
			System.out.println("Stock failed to update");
			response.setContentType("application/json");
			//			out.print("Hello2");
			response.setHeader("Cache-Control", "no-cache");
			response.setStatus(HttpServletResponse.SC_OK);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String message = "0";

			String resultStrJson = gson.toJson(message);
			//System.out.println("before out.print");
			PrintWriter out = response.getWriter();
			out.print("{\"message\":"+resultStrJson+"}");
			out.close();
			out.flush();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
