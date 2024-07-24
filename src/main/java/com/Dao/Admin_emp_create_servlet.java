package com.Dao;



import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Connection_db.Db_emp_con;


/**
 * Servlet implementation class Admin_emp_create_servlet
 */
@WebServlet("/Admin_op")
public class Admin_emp_create_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin_emp_create_servlet() {
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
		try {
			Connection con= Db_emp_con.connect_data();
			 PreparedStatement ps=con.prepareStatement("insert into employee_details values(?,?,?,?,?,?,?)");
			 Random random = new Random();

			 int rand = random.nextInt(10000000,999999999);
			 ps.setInt(1, rand);
			 ps.setString(2, "123");
			 ps.setString(3, request.getParameter("full-name"));
	
			 ps.setLong(4, Long.parseLong(request.getParameter("mobile")));
			 ps.setString(5, request.getParameter("email"));
			 ps.setString(6, request.getParameter("role"));

			 ps.setString(7, request.getParameter("dob"));

			 ps.executeUpdate();
			 response.sendRedirect("Admin_emp.jsp");
//			 Statement st = miConexion.createStatement();
//		     st.executeUpdate(Query);
//			System.out.print(Long.parseLong("9344518268"));

			
		}
		catch (Exception e) {
			System.out.print(e);
		}
			}
	}


