package com.Dao;



import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Connection_db.Db_emp_con;

/**
 * Servlet implementation class emp_task_update_delete
 */
@WebServlet("/emp_task_ud")
public class emp_task_update_delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public emp_task_update_delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
System.out.print("hello");
		
		try {
			if(request.getParameter("val").equals("d")) {
				System.out.print("delete");
				System.out.print(request.getParameter("account"));
				Connection con=Db_emp_con.connect_data();
				PreparedStatement ps=con.prepareStatement("delete from  Employee_task where  taskid=?;");
			
				ps.setLong(1, Long.parseLong(request.getParameter("account")));

				ps.executeUpdate();		
				response.sendRedirect("employee_tracker.jsp");
				
			}
			else {
				System.out.print("update");
				System.out.print(request.getParameter("account"));
				System.out.println("error");
				request.setAttribute("c_accountno", request.getParameter("account"));
				 RequestDispatcher dis=request.getRequestDispatcher("emp_task_edit.jsp");
  				 dis.forward(request, response);
			}
			
		}
		catch(Exception e){
			System.out.print(e);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.print("hello");
		try {
			 HttpSession s=request.getSession();
			 Connection con =Db_emp_con.connect_data();
			 PreparedStatement ps=con.prepareStatement("update Employee_task set  project=?,day=?,task_category=?,description=?,task_time=?,end_task_time=? where emp_id=?");
	

		
			
			 ps.setString(1, request.getParameter("project"));
			
			 ps.setLong(2, Integer.parseInt(request.getParameter("day")));
			 ps.setString(3, request.getParameter("taskcategory"));
			 ps.setString(4, request.getParameter("description"));
			 LocalDateTime eventDate = LocalDateTime.parse(request.getParameter("time1"));
			 ps.setObject(5, eventDate);
	     		LocalDateTime eventDate2 = LocalDateTime.parse(request.getParameter("time2"));
			 ps.setObject(6,  eventDate2);

			
			
			 ps.setLong(7, (long) s.getAttribute("current_cus"));
			 ps.executeUpdate();
			System.out.println("hree is code");
			System.out.println(s.getAttribute("current_cus"));
		}
		catch(Exception e){
			System.out.print(e);
			
		}
		
		response.sendRedirect("employee_tracker.jsp");
	}

}
