package com.training.topaz;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class viewCustomersServlet
 */
public class viewCustomersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewCustomersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DatabaseConnection databaseConnection=new DatabaseConnection();
		List<Customer> list=new ArrayList<Customer>();
		try {
			list = databaseConnection.getCustomerList();
			JSONArray ja = new JSONArray();
			JSONObject search = new JSONObject();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.setStatus(HttpServletResponse.SC_OK);
			for (int i=0;i<list.size();i++){
				JSONObject jObj = new JSONObject();
				jObj.put("username", list.get(i).getUsername());
				jObj.put("fname", list.get(i).getFname());
				jObj.put("lname", list.get(i).getLname());
				jObj.put("email", list.get(i).getEmail());
				ja.add(jObj);
			}
			//search.put("search", ja);
			System.out.println(ja);
			PrintWriter out = response.getWriter();
			out.print(ja);
			out.close();
			out.flush();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
