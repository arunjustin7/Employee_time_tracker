package com.Dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Connection_db.Db_emp_con;



/**
 * Servlet implementation class Login_servlet
 */
@WebServlet("/login")
public class Login_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
 try {   System.out.println(request.getParameter("role").equals("admin"));
         
          
			 
			 con= Db_emp_con.connect_data();
			 PrintWriter out=response.getWriter();
			
			 System.out.println(request.getParameter("usr"));
			 System.out.println();
			
			 System.out.println("hello1");
			 if(request.getParameter("role").equals("admin")) {
				 PreparedStatement ps=con.prepareStatement("select * from Admin_emp_user where username=? and password=?");
				 ps.setString(1, request.getParameter("usr"));
				 ps.setString(2, request.getParameter("password"));
				 ResultSet val=ps.executeQuery();
				
			 if( val.next()) {
				 
				
			 
			 if(val.getString(1).equals(request.getParameter("usr")) && val.getString(2).equals(request.getParameter("password"))) {
//				 RequestDispatcher dis=request.getRequestDispatcher("Admin.jsp");
//				 dis.forward(request, response);
		
				 System.out.println("hello1");
				 HttpSession s= request.getSession();
			
				 s.setAttribute("admin",request.getParameter("usr"));
				 s.setAttribute("ad_password", request.getParameter("password"));
				 response.sendRedirect("Admin_emp.jsp");
			 }
			 }else {
				 response.sendRedirect("Login_emp.jsp");
	
			 }
//			 
			 
		 }else {
			 PreparedStatement ps=con.prepareStatement("select * from employee_details where emp_id=? and password=?");
			 ps.setLong(1, Long.parseLong(request.getParameter("usr")));
			 ps.setString(2, request.getParameter("password"));
			 ResultSet val=ps.executeQuery();
			 System.out.println("hello employee");
			 if( val.next()) {
				 
					
				 
				 if(val.getString(1).equals(request.getParameter("usr")) && val.getString(2).equals(request.getParameter("password"))) {
//					 RequestDispatcher dis=request.getRequestDispatcher("Admin.jsp");
//					 dis.forward(request, response);
			
					 System.out.println("hello1");
					 HttpSession s= request.getSession();
					 
					 s.setAttribute("customer", Long.parseLong(request.getParameter("usr")));
					 s.setAttribute("cus_password", request.getParameter("password"));
					
					 RequestDispatcher dis=request.getRequestDispatcher("employee_tracker.jsp");
      				 dis.forward(request, response);
					 
					
				 }
				 }else {
					 
					 response.sendRedirect("Login_emp.jsp");
		
				 }
			 
		 }
			 }
		
		 
		 catch(Exception e) {
			 System.out.println(e);		 }

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
