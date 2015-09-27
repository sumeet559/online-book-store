package com.training.topaz;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class searchByStock
 */
public class searchByStock extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchByStock() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("inside doGET");
		int stock = Integer.parseInt(request.getParameter("input"));
		DatabaseConnection dbConnection = new DatabaseConnection();
		try {
			List<Item> list = dbConnection.searchByStock(stock);
			//System.out.println(list);
			JSONArray ja = new JSONArray();
			//JSONObject search = new JSONObject();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.setStatus(HttpServletResponse.SC_OK);
			for (int i=0;i<list.size();i++){
				JSONObject jObj = new JSONObject();
				jObj.put("title", list.get(i).getTitle());
				jObj.put("stock", list.get(i).getStock());
				jObj.put("cost", list.get(i).getCost());
				ja.add(jObj);
			}
			System.out.println(ja);
			PrintWriter out = response.getWriter();
			//search.put("search", ja);
			out.print(ja);
			out.close();
			out.flush();
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
