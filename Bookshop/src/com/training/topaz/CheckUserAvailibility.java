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
 * Servlet implementation class CheckUserAvailibility
 */
public class CheckUserAvailibility extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckUserAvailibility() {
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


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DatabaseConnection databaseConnection = new DatabaseConnection();
		//		response.setContentType("text/html");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		PrintWriter out = response.getWriter();
		//		out.print("Hello");
		boolean isValid=false;
		if(email==null || email==""){
			try {
				isValid = databaseConnection.signupValidation(username);

				//System.out.println(isValid);
				response.setContentType("application/json");
				//			out.print("Hello2");
				response.setHeader("Cache-Control", "no-cache");
				response.setStatus(HttpServletResponse.SC_OK);
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String message = "0";
				if ( isValid) {

					message = "1";

				}
				else{
					message="0";
					//System.out.println(message); 
				}

				String resultStrJson = gson.toJson(message);
				//System.out.println("before out.print");
				out.print("{\"message\":"+resultStrJson+"}");
				out.close();
				out.flush();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				out.close();
				
			}
		}
		else {
			boolean isEmail;
			try {
				isEmail = databaseConnection.validateEmail(email);

				System.out.println("85: "+isEmail);
				response.setContentType("application/json");
				//				out.print("Hello2");
				response.setHeader("Cache-Control", "no-cache");
				response.setStatus(HttpServletResponse.SC_OK);
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String message = "0";
				if ( isEmail) {
					
					message = "1";
					System.out.println("93: "+message);
				}
				else{
					message="0";
					System.out.println("99: "+message); 
				}

				String resultStrJson1 = gson.toJson(message);
				System.out.println(resultStrJson1);
//				System.out.println("before out.print");
				out.print("{\"message\":"+resultStrJson1+"}");
				out.close();
				out.flush();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}try {
			databaseConnection.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 


	}




}

