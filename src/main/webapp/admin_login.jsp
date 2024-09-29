<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Login</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f7f7f7;
            background-image:url(images/login.jpg);
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-size:cover;
            background-repeat:no-repeat;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        form {
            background-color: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            width: 350px;
            max-width: 100%;
            text-align: center;
        }

        label {
            display: block;
            margin-bottom: 10px;
            color: #555;
            font-size: 14px;
        }

        input {
            width: calc(100% - 20px);
            padding: 12px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
            font-size: 14px;
        }

        input[type="submit"] {
            background-color: #4caf50;
            color: white;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        p {
            color: #ff5757;
            font-size: 14px;
            margin-top: 15px;
        }
    </style>
</head>
<body>
    <form action="AdminLoginServlet" method="post">
        <h2>Admin Login</h2>

        <!-- Check for login error and display a message -->
       
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>

        <label for="adminPassword">Password:</label>
        <input type="password" id="password" name="password" required>

        <input type="submit" value="Login" >
        <c:if test="${param.error ne null}">
            <p>Invalid username or password. Please try again.</p>
        </c:if>
        
    </form>
</body>
</html>
