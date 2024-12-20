<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="com.robert.election_machine.dao.Question" %>
<jsp:useBean id="question" class="com.robert.election_machine.dao.Question" scope="request"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><%= question.getId() > 0 ? "Edit Question" : "Add Question" %></title>
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

        h1 {
            text-align: center;
            margin-top: 100px;
            font-size: 2.5em;
            color: white;
        }

        .form-container {
            width: 40%;
            margin: 20px auto;
            background-color: white;
            color: black;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            box-sizing: border-box;
        }

        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
            font-size: 1.1em;
        }

        input[type="text"] {
            width: calc(100% - 20px);
            padding: 8px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
            font-size: 1em;
        }

        button {
            background-color: #D9534F;
            color: white;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 5px;
            font-size: 1.2em;
            display: block;
            margin: 20px auto 0;
            text-align: center;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #C9302C;
        }
    </style>
</head>
<body>

    <!-- Heading -->
    <h1><%= question.getId() > 0 ? "Edit Question" : "Add Question" %></h1>

    <!-- Form Container -->
    <div class="form-container">
        <form method="post" action="<%= question.getId() > 0 ? "edit-question" : "add-question" %>">
            <% if (question.getId() > 0) { %>
                <input type="hidden" name="id" value="<%= question.getId() %>">
            <% } %>
            <label for="text">Question Text:</label>
            <input type="text" id="text" name="text" 
                   value="<%= question.getText() != null ? question.getText() : "" %>" required>
            <button type="submit">Save</button>
        </form>
    </div>
</body>
</html>
