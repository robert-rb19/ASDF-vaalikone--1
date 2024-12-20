<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Log In</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <style>
        body {
            font-family: "Times New Roman", Times, serif;
            background-image: url('images/background.jpg');
            background-size: cover;
            background-position: center;
            margin: 0;
            padding: 0;
            color: white;
        }
        .navbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 20px;
            background-color: rgba(0, 0, 0, 0.5);
            position: fixed;
            width: 100%;
            top: 0;
            box-sizing: border-box;
        }
        .navbar a {
            color: white;
            text-decoration: none;
            font-family: "Times New Roman", Times, serif;
            font-size: 1.2em;
            margin: 0 20px;
        }
        .navbar a:last-child {
            margin-right: 20px; 
        }
        .navbar a:hover {
            text-decoration: underline;
        }
        .login-container {
            margin-top: 100px;
            width: 30%;
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            margin-left: auto;
            margin-right: auto;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }
        .login-container h1 {
            text-align: center;
            font-size: 2em;
            color: black;
        }
        .login-container label {
            font-size: 1.2em;
            color: black;
        }
        .login-container input {
            width: 100%;
            max-width: 100%; 
            box-sizing: border-box; 
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 1em;
        }
        .login-container button {
            background-color: #D9534F;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            font-size: 1.2em;
            cursor: pointer;
            width: 100%;
        }
        .login-container button:hover {
            background-color: #C9302C;
        }
        .error-message {
            color: red;
            text-align: center;
            margin-top: 10px;
            font-size: 1em;
        }
    </style>
</head>
<body>
    <div class="navbar">
        <a href="index.html">Home</a>
        <a href="log-in">Log In</a>
    </div>
    <div class="login-container">
        <h1>Log In</h1>
        <form action="log-in" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
            
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            
            <button type="submit">Log In</button>
        </form>
        <% if (request.getAttribute("errorMessage") != null) { %>
            <div class="error-message"><%= request.getAttribute("errorMessage") %></div>
        <% } %>
    </div>
</body>
</html>
