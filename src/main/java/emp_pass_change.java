

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.Connection_db.Db_emp_con;

/**
 * Servlet implementation class emp_pass_change
 */
@WebServlet("/emp_pass")
public class emp_pass_change extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public emp_pass_change() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  System.out.println(request.getParameter("c_pass"));
		   try {
				 HttpSession s= request.getSession();
		   
			   Connection con=Db_emp_con.connect_data();
				 PreparedStatement ps=con.prepareStatement("update  employee_details  set password= ? where emp_id=?");
				

			
				 ps.setString(1, request.getParameter("c_pass"));
				 ps.setLong(2, (long)s.getAttribute("customer"));
				 s.setAttribute("cus_password", request.getParameter("c_pass") );
				
				 ps.executeUpdate();
				 RequestDispatcher dis=request.getRequestDispatcher("employee_tracker.jsp");
 				 dis.forward(request, response);	
//				 Statement st = miConexion.createStatement();
//			     st.executeUpdate(Query);
//				System.out.print(Long.parseLong("9344518268"));

				
			}
			catch (Exception e) {
				System.out.print(e);

		   
	}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
