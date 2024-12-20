<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.robert.election_machine.dao.Candidate" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>See Candidates</title>
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
            font-size: 1.2em;
        }

        .navbar a:hover {
            text-decoration: underline;
        }

        h1 {
            text-align: center;
            color: white;
            margin-top: 100px;
            font-size: 2.5em;
        }

        .candidate-container {
            width: 60%;
            margin: 20px auto;
            background-color: white;
            color: black;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            font-size: 1.2em;
        }

        .candidate-name {
            font-weight: bold;
            font-size: 1.5em;
            margin-bottom: 10px;
        }

        .candidate-info {
            margin-bottom: 5px;
        }
    </style>
</head>
<body>
    <!-- Navbar -->
    <div class="navbar">
        <a href="/election_machine/index.html">Home</a>
        <a href="/election_machine/log-in">Log In</a>
    </div>

    <!-- Heading -->
    <h1>See Candidates</h1>

    <!-- Candidate List -->
    <%
        List<Candidate> candidates = (List<Candidate>) request.getAttribute("candidates");
        if (candidates == null || candidates.isEmpty()) {
    %>
        <div class="candidate-container">
            <p>No candidates available.</p>
        </div>
    <%
        } else {
            for (Candidate candidate : candidates) {
    %>
        <div class="candidate-container">
            <div class="candidate-name">
                <%= candidate.getName() %> <%= candidate.getSurname() %>
            </div>
            <div class="candidate-info"><b>Party:</b> <%= candidate.getParty() %></div>
            <div class="candidate-info"><b>Interests:</b> <%= candidate.getInterests() %></div>
            <div class="candidate-info"><b>Motto:</b> <%= candidate.getMotto() %></div>
        </div>
    <%
            }
        }
    %>
</body>
</html>
