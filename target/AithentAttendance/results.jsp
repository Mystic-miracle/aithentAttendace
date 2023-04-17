<!DOCTYPE html>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<html lang="en">
<head>
    <title>Aithent Database</title>
    <link rel="Aithent icon" type="x-icon" href="tabg.png">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.1/css/jquery.dataTables.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    
    <style>
    img {
        display: block;
        margin-left: auto;
        margin-right: auto;
      }
      #Name {
    font-family: Times New Roman, Times, serif; /* Set the font for the table */
    border-collapse: collapse; /* Remove the default border */
    width: 100%; /* Set the width of the table to 100% */
}

#Name th, #Name td {
    border: 2px solid #ddd; /* Add a border to the table cells */
    padding: 8px; /* Add padding to the cells */
}

#Name th {
    background-color: #f2f2f2; /* Add a background color to the header cells */
    text-align: center; /* Align the text in the header cells to the left */
}
#Name td:first-child, #Name th:first-child {
    font-weight: bold;
    color: black;
}
#Name tfoot input {
    width: 100%; /* Set the width of the input fields to 100% */
    padding: 12px; /* Add padding to the input fields */
    border: 1px solid #ccc; /* Add a border to the input fields */
    margin-bottom: 12px; /* Add margin to the bottom of the input fields */
}
h1 {
    font-size: 36px; /* Set the font size to 36px */
    font-weight: bold; /* Make the text bold */
    text-align: center; /* Center the text */
    color: #333333; /* Set the text color to dark gray */
    margin-top: 50px; /* Add a margin to the top of the h1 element */
}
 
      </style>
</head>
<%
String id = request.getParameter("userid");
String driver = "com.mysql.jdbc.Driver";
String connectionUrl = "jdbc:mysql://localhost:3306/";
String database = "aithent";
String userid = "root";
String password = "mysql";
try {
Class.forName(driver);
} 
catch (ClassNotFoundException e){
e.printStackTrace();
}
Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
%>
<body>
    <img src="Nobg.png" alt="Logo" class="centre" height="300px" width="690px">
    <div class="context">
        <h1>Attendance Tracker</h1>
    </div>
    
    <table id="Name">

    <thead>
       <th class="filter-th">NAME</th>
        <th class="filter-th">DATE</th>
        <th class="filter-th">MONTH </th>
        <th class="filter-th">YEAR</th>
        <th class="filter-th">DAY</th>
        <th class="filter-th">DAY TYPE</th>
    </thead>
    <thead>
        <tr>
            <th><input type="text" placeholder="Filter NAME" id="nameFilter"></th>
            <th><input type="text" placeholder="Filter DATE" id="dateFilter"></th>           
            <th><input type="text" placeholder="Filter Month TYPE" id="monthFilter"></th>
            <th><input type="text" placeholder="Filter Year TYPE" id="yearFilter"></th>
            <th><input type="text" placeholder="Filter Day of Week TYPE" id="WeekdayFilter"></th>
            <th><input type="text" placeholder="Filter DAY TYPE" id="dayFilter"></th>
        </tr>
    </thead>
    <tbody>
    <%
try{
connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/aithent","root", "mysql");
statement=connection.createStatement();
String sql ="select employeedetails.name ,employeeattendance.date,monthname(employeeattendance.date) as Month,Year(employeeattendance.date) as Year, DayName(employeeattendance.date) as day, employeeattendance.day_type  from employeeattendance,employeedetails where FK_emp=PK_emp;";
resultSet = statement.executeQuery(sql);
while(resultSet.next()){
%>
       
        <tr>
<td><%=resultSet.getString("name") %></td>
<td><%=resultSet.getString("date") %></td>
<td><%=resultSet.getString("month") %></td>
<td><%=resultSet.getString("year") %></td>
<td><%=resultSet.getString("day") %></td>
<td><%=resultSet.getString("day_type") %></td>
        
</tr>
<%
}
connection.close();
} catch (Exception e) {
e.printStackTrace();
}
%>
        
        
    </tbody>
</table>

<script type="text/javascript" src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.min.js">
</script> 
<script type="text/javascript">
$(document).ready(function () {
    $('#example').DataTable();
});</script>
<script type="text/javascript">
    $(document).ready(function(){
        var table = $('#Name').DataTable();

        $("#nameFilter").on("keyup", function() {
            table.column(0).search(this.value).draw();
        });

        $("#dateFilter").on("keyup", function() {
            table.column(1).search(this.value).draw();
        });
        $("#monthFilter").on("keyup", function() {
            table.column(2).search(this.value).draw();
        });
        $("#yearFilter").on("keyup", function() {
            table.column(3).search(this.value).draw();
        });
        $("#WeekdayFilter").on("keyup", function() {
            table.column(4).search(this.value).draw();
        });
        $("#dayFilter").on("keyup", function() {
            table.column(5).search(this.value).draw();
        });
    });
</script>
</body>
</html>