<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="com.robert.election_machine.dao.Candidate" %>
<%
    Candidate bestMatch = (Candidate) request.getAttribute("bestMatch");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Your Results</title>
    <style>
        body {
            font-family: "Times New Roman", Times, serif;
            background-image: url('images/background.jpg');
            background-size: cover;
            background-position: center;
            color: white;
            margin: 0;
            padding: 0;
        }
        /* Navbar Styling */
        .navbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 20px;
            background-color: rgba(0, 0, 0, 0.5);
            position: fixed;
            top: 0; 
            width: 100%;
            box-sizing: border-box;
            z-index: 1000; 
        }
        .navbar a {
            color: white;
            text-decoration: none;
            font-size: 1.2em;
        }
        .navbar a:hover {
            text-decoration: underline;
        }

        /* Results Container */
        .container {
            width: 60%;
            margin: 150px auto 50px;
            background: white;
            color: black;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            text-align: center;
        }
        h1 {
            color: #D9534F;
        }
        p {
            font-size: 1.2em;
            line-height: 1.5;
        }

        /* Try Again Button */
        .button {
            display: inline-block;
            margin-top: 20px;
            background-color: #D9534F;
            color: white;
            font-family: "Times New Roman", Times, serif;
            text-decoration: none;
            padding: 10px 30px;
            font-size: 1.2em;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }
        .button:hover {
            background-color: #C9302C;
        }
    </style>
</head>
<body>
    <!-- Navbar -->
    <div class="navbar">
        <a href="index.html">Home</a>
        <a href="log-out">Log In</a>
    </div>

    <!-- Results Container -->
    <div class="container">
        <% if (bestMatch != null) { %>
            <h1>Your Best Match</h1>
            <p><b>Name:</b> <%= bestMatch.getName() %> <%= bestMatch.getSurname() %></p>
            <p><b>Party:</b> <%= bestMatch.getParty() %></p>
            <p><b>Motto:</b> <%= bestMatch.getMotto() %></p>
            <p><b>Interests:</b> <%= bestMatch.getInterests() %></p>
        <% } else { %>
            <h1>No Match Found</h1>
            <p>Unfortunately, we couldn't find a candidate that matches your answers.</p>
        <% } %>
        <!-- Try Again Button -->
        <a href="test" class="button">Try Again</a>
    </div>
</body>
</html>
