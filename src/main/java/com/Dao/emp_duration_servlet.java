package com.Dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import com.Connection_db.Db_emp_con;

/**
 * Servlet implementation class emp_duration_servlet
 */
@WebServlet("/emp_dur")
public class emp_duration_servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public emp_duration_servlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JSONArray labels = new JSONArray();
        JSONArray durations = new JSONArray();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
        	HttpSession s=request.getSession();
        	System.out.print(s.getAttribute("customer"));
       
            con=Db_emp_con.connect_data();
            pst = con.prepareStatement("SELECT task_category, SUM(TIME_TO_SEC(TIMEDIFF(end_task_time, task_time)) / 3600) AS total_duration FROM Employee_task WHERE emp_id=? GROUP BY task_category;");
            pst.setLong(1, (Long) s.getAttribute("customer"));           
            rs = pst.executeQuery();

            while (rs.next()) {
                labels.put(rs.getString("task_category"));
                durations.put(rs.getDouble("total_duration"));
            }

            JSONObject json = new JSONObject();
            json.put("labels", labels);
            json.put("durations", durations);
            response.getWriter().print(json.toString());

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().print("{\"error\": \"Database driver not found.\"}");
        }  finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
