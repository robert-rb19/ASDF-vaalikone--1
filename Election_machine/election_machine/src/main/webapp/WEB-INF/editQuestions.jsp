<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.robert.election_machine.dao.Question" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Questions</title>
    <style>
        body {
            font-family: "Times New Roman", Times, serif;
            background-image: url('images/background.jpg');
            background-size: cover;
            color: white;
            margin: 0;
        }
        .navbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px 10px;
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
            margin: 0 10px;
            white-space: nowrap;
        }
        h1 {
            text-align: center;
            margin-top: 100px;
            font-size: 2.5em;
        }
        .question-container {
            background-color: white;
            color: black;
            width: 60%;
            margin: 20px auto;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        }
        .question-buttons {
            display: flex;
            justify-content: space-between;
            margin-top: 10px;
        }
        button {
            background-color: #D9534F;
            color: white;
            border: none;
            padding: 10px 20px;
            font-size: 1em;
            cursor: pointer;
            border-radius: 5px;
        }
        button:hover {
            background-color: #C9302C;
        }
        .bottom-buttons {
            text-align: center;
            margin-top: 30px;
        }
    </style>
</head>
<body>
    <!-- Navbar -->
    <div class="navbar">
        <a href="index.html">Home</a>
        <a href="log-in" style="margin-right: 20px;">Log Out</a>
    </div>

    <h1>Edit Questions</h1>

    <!-- Question List -->
    <% List<Question> questions = (List<Question>) request.getAttribute("questions"); %>
    <% if (questions != null) {
        for (Question question : questions) { %>
            <div class="question-container">
                <p><b>Question:</b> <%= question.getText() %></p>
                <div class="question-buttons">
                    <form action="edit-question" method="get">
                        <input type="hidden" name="id" value="<%= question.getId() %>">
                        <button type="submit">Edit</button>
                    </form>
                    <form action="edit-questions" method="post">
                        <input type="hidden" name="id" value="<%= question.getId() %>">
                        <button type="submit">Delete</button>
                    </form>
                </div>
            </div>
    <%  }} else { %>
        <p style="text-align: center; color: white;">No questions available.</p>
    <% } %>

    <!-- Add and Back Buttons -->
    <div class="bottom-buttons">
        <a href="add-question">
            <button>Add</button>
        </a>
        <a href="dashboard">
            <button>Back</button>
        </a>
    </div>
</body>
</html>
