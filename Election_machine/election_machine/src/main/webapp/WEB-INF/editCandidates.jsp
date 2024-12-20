<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List, java.util.Map, com.robert.election_machine.dao.Candidate, com.robert.election_machine.dao.Question" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Candidates</title>
    <style>
        body {
            font-family: "Times New Roman", Times, serif;
            background-image: url('images/background.jpg');
            background-size: cover;
            color: white;
            margin: 0;
            padding: 0;
        }

        .navbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 20px;
            background-color: rgba(0, 0, 0, 0.5);
            position: fixed;
            top: 0;
            width: 100%;
            z-index: 1000;
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
            margin-top: 120px;
            font-size: 2.5em;
        }

        .candidate-container {
            background-color: white;
            color: black;
            width: 60%;
            margin: 20px auto;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }

        .candidate-buttons form {
            display: inline-block;
            margin: 0 10px;
        }

        button {
            background-color: #D9534F;
            color: white;
            border: none;
            padding: 10px 15px;
            font-size: 1em;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #C9302C;
        }

        .add-button {
            display: block;
            margin: 30px auto;
            text-align: center;
        }

        .back-button {
            text-align: center;
            margin-top: 30px;
        }
    </style>
</head>
<body>
    <!-- Navbar -->
    <div class="navbar">
        <a href="index.html">Home</a>
        <a href="log-in">Log Out</a>
    </div>

    <!-- Page Heading -->
    <h1>Edit Candidates</h1>

    <% List<Candidate> candidates = (List<Candidate>) request.getAttribute("candidates"); %>
    <% List<Question> questions = (List<Question>) request.getAttribute("questions"); %>
    <% if (candidates != null) {
        for (Candidate candidate : candidates) { %>
            <div class="candidate-container">
                <h2><%= candidate.getName() + " " + candidate.getSurname() %></h2>
                <p><b>Party:</b> <%= candidate.getParty() %></p>
                <p><b>Interests:</b> <%= candidate.getInterests() %></p>
                <p><b>Motto:</b> <%= candidate.getMotto() %></p>
                <p><b>Answers:</b> 
                <ul>
                    <% for (Question question : questions) { 
                        int questionId = question.getId();
                        String questionText = question.getText();
                        Integer answer = candidate.getAnswers().get(questionId);
                    %>
                        <li>
                            <b><%= questionText %></b>: <%= (answer != null) ? answer : "No Answer" %>
                        </li>
                    <% } %>
                </ul>
                <div class="candidate-buttons">
                    <form action="edit-candidate" method="get">
                        <input type="hidden" name="id" value="<%= candidate.getId() %>">
                        <button type="submit">Edit</button>
                    </form>
                    <form action="edit-candidates" method="post">
                        <input type="hidden" name="id" value="<%= candidate.getId() %>">
                        <button type="submit">Delete</button>
                    </form>
                </div>
            </div>
    <%  }} %>

    <!-- Add Button -->
    <div class="add-button">
        <form action="add-candidate" method="get">
            <button type="submit">Add Candidate</button>
        </form>
    </div>

    <!-- Back Button -->
    <div class="back-button">
        <a href="dashboard"><button>Back</button></a>
    </div>
</body>
</html>
