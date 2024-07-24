<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import="com.Connection_db.Db_emp_con,java.sql.*,javax.sql.*" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Panel - Customer Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.js"></script>
    <style>
        .hidden-info {
            color: #6c757d;
            font-style: italic;
        }
    </style>
</head>

<body>
    <div id="app" class="container mt-5 ">
    <%  	HttpSession s=request.getSession(); %>
        <h1 class="mb-4">admin:  ${sessionScope.admin}</h1>
        <Form action="admin_emp_create.jsp">
        <button type="submit" class="btn  btn-primary">Add Employee</button>
</Form>
<Form action="Login_emp.jsp">
        <button type="submit" class="btn  btn-primary">Logout</button>
</Form>
        <div class="row">
          
            <div class="col-md-8">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Employee List</h5>
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Emp ID</th>
                                         <th>Name</th>
                                     
                                        <th>MobileNo</th>
                                        <th>email</th>
                                        <th>Date-of-birth</th>
                                          <th>role</th>
                                           <th>Update</th>
                                            <th>Delete</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    
                                                    <%
                
                try {
                	 Connection con=Db_emp_con.connect_data();
                	 PreparedStatement ps=con.prepareStatement("select * from employee_details ; ");

                	     
                	   //  ps.setLong(1, (Long)s.getAttribute("customer"));
                	   
                	   ResultSet val1=ps.executeQuery();
                	    
                    

                    while (val1.next()){
                        out.println("<tr>");
                        out.println("<td>" + val1.getLong(1) + "</td>");
                       out.println("<td>" + val1.getString(2) + "</td>");
                        out.println("<td>" + val1.getString(3) + "</td>");
                        out.println("<td>" + val1.getLong(4) + "</td>");
                        out.println("<td>" + val1.getString(5) + "</td>");
                        out.println("<td>" + val1.getString(6) + "</td>");
                        out.println("<td>" + val1.getDate(7) + "</td>");
                        out.println("<td>");
                        out.println("<a href='emp_ud?account="+val1.getLong(1) +"&val=u'>Update</a>");
                        out.println(" </td>");
                        out.println("<td>");
                        out.println("<a href='emp_ud?account="+ val1.getLong(1)+"&val=d'>delete</a>");
                        out.println("<a href='Admin_emp_view.jsp?account="+ val1.getLong(1)+"&val=d'>View</a>");
                        out.println(" </td>");
                        	 
                        
                        out.println("</tr>");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } 
            %>
                                  
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
       
    </script>
</body>

</html>