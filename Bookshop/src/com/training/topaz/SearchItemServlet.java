package com.training.topaz;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
/*import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;*/
import org.json.simple.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class SearchItemServlet
 */
public class SearchItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		DatabaseConnection databaseConnection = new DatabaseConnection();
		try {
	
			
	//		        }
		if(request.getParameter("categorySelect")!=null)
		{
			//System.out.println(request.getParameter("pubSelect"));
			if(request.getParameter("pubSelect")!=null)
			{
				
				if(request.getParameter("authorSelect")!=null)
				{
					List<Item> list = new ArrayList<Item>();
					list=databaseConnection.search(request.getParameter("categorySelect"),request.getParameter("pubSelect"),request.getParameter("authorSelect"));
					//System.out.println("line85:"+list);
					JSONArray ja = new JSONArray();
					JSONObject search = new JSONObject();
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
					//search.put("search", ja);
					out.print(ja);
					out.close();
					out.flush();
					//System.out.println("after author select");
					
				}
				else {
					//System.out.println("Inside ");
					ArrayList<String> list = new ArrayList<String>();
					//System.out.println(request.getParameter("pubSelect"));
					list=databaseConnection.searchAuthor(request.getParameter("categorySelect"),request.getParameter("pubSelect"));
					//System.out.println(list);
					JSONObject jObject = new JSONObject();
					response.setContentType("application/json");
					response.setHeader("Cache-Control", "no-cache");
					response.setStatus(HttpServletResponse.SC_OK);
					
					for (int i=0;i<list.size();i++){
						jObject.put(i, list.get(i));
					}
					out.print(jObject);
					out.close();
					out.flush();
					}
				}
					
			
			else
			{
				//System.out.println("inside else");
				ArrayList<String> list = new ArrayList<String>();
				//System.out.println(request.getParameter("categorySelect"));
				list=databaseConnection.searchPublication(request.getParameter("categorySelect"));
				
				//request.setAttribute("publication", list);
				JSONObject jObject = new JSONObject();
				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-cache");
				response.setStatus(HttpServletResponse.SC_OK);
				
				
				
				for (int i=0;i<list.size();i++){
					jObject.put(i, list.get(i));
				}
			//	System.out.println(jObject);
				out.print(jObject);
				out.close();
				out.flush();
			}
			
		}} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
