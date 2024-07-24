<%@ page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.Connection_db.Db_emp_con,java.sql.*,javax.sql.*" %>
<!DOCTYPE html>
<html lang="en">

<head>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.4.0/jspdf.umd.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf-autotable/3.5.13/jspdf.plugin.autotable.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Employee Dashboard</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f0f0f0;
    }

    .container {
        max-width: 800px;
        margin: 0 auto;
        padding: 20px;
    }

    .header {
        background-color: #3498db;
        color: white;
        padding: 20px;
        text-align: center;
    }

    .dashboard {
        background-color: white;
        border-radius: 5px;
        padding: 20px;
        margin-top: 20px;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    }

    .balance {
        font-size: 24px;
        font-weight: bold;
        margin-bottom: 20px;
    }

    .button {
        background-color: #3498db;
        border: none;
        color: white;
        padding: 10px 20px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        margin: 4px 2px;
        cursor: pointer;
        border-radius: 5px;
    }

    .transactions {
        margin-top: 20px;
    }

    table {
        width: 100%;
        border-collapse: collapse;
    }

    th,
    td {
        text-align: left;
        padding: 8px;
        border-bottom: 1px solid #ddd;
    }

    .modal {
        display: none;
        position: fixed;
        z-index: 1;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        overflow: auto;
        background-color: rgba(0, 0, 0, 0.4);
    }

    .modal-content {
        background-color: #fefefe;
        margin: 15% auto;
        padding: 20px;
        border: 1px solid #888;
        width: 300px;
        border-radius: 5px;
    }

    .close {
        color: #aaa;
        float: right;
        font-size: 28px;
        font-weight: bold;
        cursor: pointer;
    }

    /* Styling for the dialog */
    .dialog-overlay {
        display: none;
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, 0.5);
        z-index: 1000;
    }

    .dialog-box {
        position: fixed;
        background-color: white;
        width: 300px;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        z-index: 1010;
    }

    body {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        display: flex;
        justify-content: center;
        align-items: center;
        margin: 0;
        padding: 20px;
        min-height: 100vh;
    }

    .container {
        width: 90%;
        max-width: 1200px;
    }

    h1 {
        color: #333;
        text-align: center;
        margin-bottom: 30px;
        font-size: 2.5em;
    }

    table {
        width: 100%;
        border-collapse: separate;
        border-spacing: 0 15px;
    }

    th,
    td {
        padding: 20px;
        text-align: left;
        border-bottom: 2px solid #ddd;
        font-size: 1.1em;
    }

    th {
        background-color: #6e8efb;
        color: white;
        text-transform: uppercase;
        letter-spacing: 1px;
    }

    tr:hover {
        background-color: #f5f5f5;
        transform: scale(1.02);
        transition: all 0.3s ease;
    }

    .status {
        padding: 8px 15px;
        border-radius: 20px;
        font-weight: bold;
    }

    .completed {
        background-color: #4CAF50;
        color: white;
    }

    .pending {
        background-color: #FFC107;
        color: black;
    }

    .failed {
        background-color: #F44336;
        color: white;
    }
</style>

<script>
document.addEventListener("DOMContentLoaded", function() {
fetch('emp_barchart')
.then(response => response.json())
.then(data => {
    console.log(data);
    if (data.error) {
        console.error('Error fetching bar data:', data.error);
        return;
    }

    const sortedData = Object.entries(data).sort((a, b) => new Date(a[0]) - new Date(b[0]));

    const ctxBar = document.getElementById('taskBarChart').getContext('2d');
    const chartDataBar = {
        labels: sortedData.map(entry => entry[0]), 
        datasets: [{
            label: 'Hours worked per day (last 5 days)',
            data: sortedData.map(entry => entry[1] / 3600), 
            backgroundColor: 'rgba(54, 162, 235, 0.2)',
            borderColor: 'rgba(54, 162, 235, 1)',
            borderWidth: 1
        }]
    };

    const configBar = {
        type: 'bar',
        data: chartDataBar,
        options: {
            responsive: true,
            scales: {
                y: {
                    beginAtZero: true,
                    title: {
                        display: true,
                        text: 'Hours'
                    }
                }
            },
            plugins: {
                legend: {
                    display: false
                },
                title: {
                    display: true,
                    text: 'Hours worked per day (Bar Chart)'
                },
                tooltip: {
                    callbacks: {
                        label: function(tooltipItem) {
                            const value = tooltipItem.raw;
                            return value.toFixed(2) + ' hours';
                        }
                    }
                }
            }
        }
    };

    new Chart(ctxBar, configBar);
})
.catch(error => console.error('Error fetching bar data:', error));

});
document.addEventListener("DOMContentLoaded", function() {
    fetch('emp_dur')
        .then(response => response.json())
        .then(data => {
            const ctx = document.getElementById('myPieChart').getContext('2d');
            new Chart(ctx, {
                type: 'pie',
                data: {
                    labels: data.labels,
                    datasets: [{
                        label: 'Work Duration (hours)',
                        data: data.durations,
                        backgroundColor: [
                            'rgba(255, 99, 132, 0.2)',
                            'rgba(54, 162, 235, 0.2)',
                            'rgba(255, 206, 86, 0.2)',
                            'rgba(75, 192, 192, 0.2)',
                            'rgba(153, 102, 255, 0.2)',
                            'rgba(255, 159, 64, 0.2)'
                        ],
                        borderColor: [
                            'rgba(255, 99, 132, 1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 206, 86, 1)',
                            'rgba(75, 192, 192, 1)',
                            'rgba(153, 102, 255, 1)',
                            'rgba(255, 159, 64, 1)'
                        ],
                        borderWidth: 1
                    }]
                },
                options: {
                    responsive: true,
                    plugins: {
                        legend: {
                            position: 'top',
                        },
                        tooltip: {
                            callbacks: {
                                label: function(tooltipItem) {
                                    return tooltipItem.label + ': ' + tooltipItem.raw + ' hours';
                                }
                            }
                        }
                    }
                }
            });
        })
        .catch(error => console.error('Error fetching data:', error));
});
</script>
</head>

<body>
<%
try {
    HttpSession s = request.getSession();
    Connection con = Db_emp_con.connect_data();
    PreparedStatement ps = con.prepareStatement("select * from employee_details where emp_id=? ");

    ps.setLong(1, Long.parseLong(request.getParameter("account")));
   

    ResultSet val = ps.executeQuery();
    int i = 0;
    if (val.next()) {
        s.setAttribute("user_name", val.getString(3));
        s.setAttribute("cus_data", val);
        s.setAttribute("emp_role", val.getString(6));
        i++;
    } else {
        System.out.print("error in val retrieving customer data ");
    }
} catch (Exception e) {
    System.out.print(e);
}
%>
<div class="container">
    <div class="header">
        ${sessionScope.cus_data.getString(3)}
    </div>
    <div class="dashboard">
        <form action="Task_create.jsp">
            <button type="submit" class="button" id="openDialog">Add Task</button>
        </form>
        <button class="button" id="openDialog2">Change password</button>
       
        <form action="Login_emp.jsp">
            <button class="button" type="submit">Logout</button>
        </form>

        <div class="dialog-overlay" id="dialogOverlay2">
            <div class="dialog-box">
                <form action="emp_pass" method="post">
                    <p>Enter the new password:</p>
                    <input type="text" name="c_pass">
                    <br><br>
                    <button type="submit" id="submitValue2">Submit</button>
                </form>
            </div>
        </div>
        <div>
            <canvas id="myPieChart" width="300" height="300"></canvas>
        </div>
        <div class="chart-container">
    <canvas id="taskBarChart" width="300" height="200"></canvas>
</div>
        <table id="transactionTable">
            <thead>
                <tr>
                    <th>Project</th>
                    <th>Day</th>
                    <th>Task Category</th>
                    <th>Description</th>
                    <th>Start Time</th>
                    <th>End Time</th>
                </tr>
            </thead>
            <tbody>
                <%
                try {
                    Connection con = Db_emp_con.connect_data();
                    PreparedStatement ps = con.prepareStatement("select * from Employee_task where emp_id=? ");
                    HttpSession s = request.getSession();
                    ps.setLong(1, Long.parseLong(request.getParameter("account")));

                    ResultSet val1 = ps.executeQuery();

                    while (val1.next()) {
                        out.println("<tr>");
                        out.println("<td>" + val1.getString(4) + "</td>");
                        out.println("<td>" + val1.getInt(5) + "</td>");
                        out.println("<td>" + val1.getString(6) + "</td>");
                        out.println("<td>" + val1.getString(7) + "</td>");
                        out.println("<td>" + val1.getObject(8) + "</td>");
                        out.println("<td>" + val1.getObject(9) + "</td>");
                        out.println("<td>");
                        out.println("<a href='emp_task_ud?account=" + val1.getInt(10) + "&val=u'>Update</a>");
                        out.println("</td>");
                        out.println("<td>");
                        out.println("<a href='emp_task_ud?account=" + val1.getInt(10) + "&val=d'>delete</a>");
                        out.println("</td>");
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
<script>
const openDialogButton2 = document.getElementById('openDialog2');
const dialogOverlay2 = document.getElementById('dialogOverlay2');
const submitButton2 = document.getElementById('submitValue2');

openDialogButton2.addEventListener('click', function() {
    dialogOverlay2.style.display = 'block';
});

submitButton2.addEventListener('click', function() {
    dialogOverlay2.style.display = 'none';
});


</script>
</body>

</html>
