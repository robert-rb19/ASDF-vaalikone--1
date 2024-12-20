<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
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
        .navbar a:hover {
            text-decoration: underline;
        }
        .dashboard-container {
            margin-top: 150px;
            width: 50%;
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            margin-left: auto;
            margin-right: auto;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            text-align: center;
        }
        .dashboard-container h1 {
            text-align: center;
            font-size: 2em;
            color: black;
        }
        .dashboard-buttons {
            display: flex;
            justify-content: center;
            gap: 20px;
            margin-top: 20px;
        }
        .dashboard-buttons a {
            background-color: #D9534F;
            color: white;
            text-decoration: none;
            padding: 15px 30px;
            font-size: 1.2em;
            font-family: "Times New Roman", Times, serif;
            border-radius: 5px;
            border: none;
            transition: background-color 0.3s ease;
        }
        .dashboard-buttons a:hover {
            background-color: #C9302C;
        }
    </style>
</head>
<body>
    <div class="navbar">
        <a href="index.html">Home</a>
        <a href="log-in">Log Out</a>
    </div>
    <div class="dashboard-container">
        <h1>Dashboard</h1>
        <div class="dashboard-buttons">
            <a href="edit-candidates">Edit Candidates</a>
            <a href="edit-questions">Edit Questions</a>
        </div>
    </div>
</body>
</html>
