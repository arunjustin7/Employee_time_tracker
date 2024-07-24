

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.Connection_db.Db_emp_con;

/**
 * Servlet implementation class emp_barchart_servlet
 */
@WebServlet("/emp_barchart")
public class emp_barchart_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public emp_barchart_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 response.setContentType("application/json");
	        PrintWriter out = response.getWriter();
	        JSONObject jsonResponse = new JSONObject();

	        HttpSession session = request.getSession(false);
	        if (session != null) {
	            System.out.print("first");
	            Long employeeName = (long) session.getAttribute("customer");

	            if (employeeName != null ) {
	            	  System.out.print("second");
	                try {
	                	
	                	HttpSession s=request.getSession();
	                    System.out.print(s.getAttribute("customer")+"this is....");
	                    Connection con = Db_emp_con.connect_data();
	                    String query = "SELECT DATE(task_time) AS task_date, SUM(TIME_TO_SEC(TIMEDIFF(end_task_time, task_time))) AS total_duration "
	                            + "FROM Employee_task "
	                            + "WHERE task_time >= CURDATE() - INTERVAL 5 DAY "
	                            + "AND emp_id = ? "
	                            + "GROUP BY DATE(task_time) "
	                            + "ORDER BY DATE(task_time) ASC"; 


	                    PreparedStatement pst = con.prepareStatement(query);
	                    pst.setLong(1, (long) s.getAttribute("customer"));
	                    ResultSet rs = pst.executeQuery();

	                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	                    while (rs.next()) {
	                        String taskDate = sdf.format(rs.getDate("task_date"));
	                        int totalDurationInSeconds = rs.getInt("total_duration");
	                        jsonResponse.put(taskDate, totalDurationInSeconds);
	                    }

	                    rs.close();
	                    pst.close();
	                    con.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                    jsonResponse.put("error", e.getMessage());
	                } 
	            } else {
	                jsonResponse.put("error", "Employee name not found in session");
	            }
	        } else {
	            jsonResponse.put("error", "Session not found");
	        }

	        out.print(jsonResponse.toString());
	        out.flush();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
