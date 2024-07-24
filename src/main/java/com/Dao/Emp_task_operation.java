package com.Dao;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Connection_db.Db_emp_con;
import com.mysql.cj.protocol.Resultset;



/**
 * Servlet implementation class Emp_task_operation
 */
@WebServlet("/emp_op")
public class Emp_task_operation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Emp_task_operation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
System.out.print(request.getParameter("deposit"));
		
		try {
			
			con=Db_emp_con.connect_data();
			PrintWriter out=response.getWriter();
			HttpSession s=request.getSession();
			System.out.print(request.getParameter("time1")+" "+request.getParameter("time2"));
		
			
			

					
			PreparedStatement ps=con.prepareStatement("insert into Employee_task(emp_id, emp_name, role, project, day, task_category, description, task_time, end_task_time) values(?,?,?,?,?,?,?,?,?)");
			ps.setLong(1, (long) s.getAttribute("customer"));
			ps.setString(2, (String) s.getAttribute("user_name"));
			ps.setString(3,  (String) s.getAttribute("emp_role"));
			ps.setString(4,  request.getParameter("project"));
			ps.setString(5,  request.getParameter("day"));
			ps.setString(6,  request.getParameter("taskcategory"));
			ps.setString(7,  request.getParameter("description"));
//			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		     LocalDateTime eventDate = LocalDateTime.parse(request.getParameter("time1"));
			ps.setObject(8, eventDate);
     		LocalDateTime eventDate2 = LocalDateTime.parse(request.getParameter("time2"));
			ps.setObject(9,  eventDate2);
			

			ps.executeUpdate();			
			
			response.sendRedirect("employee_tracker.jsp");
			
			
		}
		catch(Exception e) {
			System.out.print(e);
		}
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			con=Db_emp_con.connect_data();
			PrintWriter out=response.getWriter();
			HttpSession s=request.getSession();
			System.out.print(request.getParameter("withdraw"));
			
			if(Long.parseLong(request.getParameter("withdraw")) > (long) s.getAttribute("cus_bal")) {
				
			
				 RequestDispatcher dis=request.getRequestDispatcher("customer.jsp");
  				 dis.forward(request, response);				   
				   
			}else {
			PreparedStatement ps=con.prepareStatement("update customer set balance=balance-? where acc_no=?");
			ps.setLong(1, Long.parseLong(request.getParameter("withdraw")));
			ps.setLong(2, (long) s.getAttribute("customer") );

			ps.executeUpdate();	
			ps=con.prepareStatement("insert into transaction values(?,?,?,?,?)");
			ps.setLong(1, (long) s.getAttribute("customer"));
			ps.setString(2, "Withdraw");
			ps.setLong(3, Long.parseLong(request.getParameter("withdraw")));
			ps.setString(4, "success");
			long millis=System.currentTimeMillis();  
	        java.sql.Date date=new java.sql.Date(millis);  
			
			ps.setDate(5, date);

			ps.executeUpdate();		
			System.out.print(s.getAttribute("cus_bal"));}
			response.sendRedirect("customer.jsp");
			
			
		}
		catch(Exception e) {
			System.out.print(e);
		}
	}

}
