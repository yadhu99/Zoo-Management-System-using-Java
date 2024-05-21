package com.pu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{ 
            String username = request.getParameter("username");
            String password = request.getParameter("password");
			Class.forName("com.mysql.jdbc.Driver"); 
		    Connection  con=DriverManager.getConnection("jdbc:mysql://localhost:3306/zoo_management?characterEncoding=latin1","root","admin@123"); 
		    PreparedStatement st = con.prepareStatement("SELECT * FROM admins WHERE username = ? AND password = ?");
            st.setString(1, username);
            // Hash password before comparison (refer to secure hashing techniques)
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            PrintWriter out = response.getWriter();


            if (rs.next()) {
               
            	request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                out.println("Invalid username or password!");
            }
		}catch(Exception e){
		      System.out.println(e);} 
		}

	}
