<%@ page import="java.io.*,java.util.*, javax.servlet.*"%>
<%@ page import="javax.servlet.http.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(to right, #2980b9, #6dd5fa, #ffffff);
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .dashboard-container {
            background-color: #fff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            max-width: 800px;
            width: 100%;
            margin: auto;
        }

        h2 {
            color: #2980b9;
            text-align: center;
            margin-bottom: 20px;
            font-size: 28px;
        }

        .dashboard-options {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-around;
        }

        .dashboard-option {
            background-color: #3498db;
            color: #fff;
            padding: 20px;
            text-align: center;
            border-radius: 10px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            margin: 10px;
            width: 200px;
        }

        .dashboard-option:hover {
            background-color: #2980b9;
        }

        h3 {
            margin: 0;
            font-size: 18px;
        }
        #ji
        {
        margin-left:-220px;
           margin-bottom:120px;
           margin-top:-50px;
           font-size:35px;
           color:red;
           font-family:georgia;
      		
        }
        button:hover
        {
        background-color: #2980b9;
        }
      #uk
      {
      width:500px;
      }
    </style>
</head>
<body>
 <div  id="uk">
        <form action="AdminLogoutServlet" method="get">
            <button type="submit" id="ji">Logout</button>
        </form>
    <div class="dashboard-container">
        <h2>Welcome to Admin Dashboard</h2>

        <div class="dashboard-options">
            <div class="dashboard-option" onclick="location.href='contact.html';">
                <h3>Register User</h3>
            </div>

            <div class="dashboard-option" onclick="location.href='Update.html';">
                <h3>Update Data</h3>
            </div>

            <div class="dashboard-option" onclick="location.href='Delete.html';">
                <h3>Delete Record</h3>
            </div>
              <div class="dashboard-option" onclick="location.href='Fetchdata.html';">
                <h3>Fetch Record</h3>
            </div>
        </div>
    </div>

</body>
</html>
