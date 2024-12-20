<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.robert.election_machine.dao.Question" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Take a Test</title>
    <style>
        body {
            font-family: "Times New Roman", Times, serif;
            background-image: url("images/background.jpg");
            background-size: cover;
            background-position: center;
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

        /* Page Heading */
        h1 {
            text-align: center;
            margin-top: 120px; 
            font-size: 2.5em;
        }

        /* Question Container */
        .question-container {
            width: 60%;
            margin: 20px auto;
            background-color: white;
            color: black;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }

        /* Results Button */
        .results-button {
            display: block;
            margin: 30px auto;
            background-color: #D9534F;
            color: white;
            border: none;
            padding: 10px 20px;
            font-size: 1.2em;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .results-button:hover {
            background-color: #C9302C;
        }
    </style>
</head>
<body>
    <!-- Navbar -->
    <div class="navbar">
        <a href="index.html">Home</a>
        <a href="log-in">Log In</a>
    </div>

    <!-- Page Heading -->
    <h1>Take a Test</h1>

    <!-- Test Form -->
    <form method="post" action="results">
        <%
            List<Question> questions = (List<Question>) request.getAttribute("questions");
            if (questions == null || questions.isEmpty()) {
        %>
            <div class="question-container">No questions available.</div>
        <%
            } else {
                for (Question question : questions) {
        %>
            <div class="question-container">
                <p><b><%= question.getText() %></b></p>
                <div>
                    <% for (int i = 1; i <= 5; i++) { %>
                        <label>
                            <input type="radio" name="question_<%= question.getId() %>" value="<%= i %>" required>
                            <%= i %>
                        </label>
                    <% } %>
                </div>
            </div>
        <%
                }
            }
        %>
        <!-- Submit Button -->
        <button type="submit" class="results-button">See Results</button>
    </form>
</body>
</html>
