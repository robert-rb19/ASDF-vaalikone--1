<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="com.robert.election_machine.dao.Candidate, java.util.List, com.robert.election_machine.dao.Question" %>
<jsp:useBean id="candidate" type="com.robert.election_machine.dao.Candidate" scope="request"/>
<jsp:useBean id="questions" type="java.util.List<com.robert.election_machine.dao.Question>" scope="request"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Candidate</title>
    <style>
        body {
            font-family: "Times New Roman", Times, serif;
            background-image: url('images/background.jpg');
            background-size: cover;
            color: white;
            margin: 0;
        }
        .container {
            width: 50%;
            margin: 100px auto;
            background: white;
            color: black;
            padding: 20px;
            border-radius: 8px;
            box-sizing: border-box;
        }
        h1 { 
            text-align: center; 
        }
        label { 
            display: block; 
            margin-top: 10px; 
            font-weight: bold;
        }
        input, textarea {
            width: calc(100% - 20px);
            padding: 8px;
            margin-top: 5px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 1em;
        }
        textarea {
            resize: vertical;
            height: 80px;
        }
        button {
            background-color: #D9534F;
            color: white;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 5px;
            margin-top: 15px;
            font-size: 1em;
        }
        button:hover {
            background-color: #C9302C;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1><%= (candidate.getId() == 0) ? "Add Candidate" : "Edit Candidate" %></h1>
        <form method="post" action="<%= (candidate.getId() == 0) ? "add-candidate" : "edit-candidate" %>">
            <input type="hidden" name="id" value="<%= candidate.getId() %>">
            <label>Name:</label>
            <input type="text" name="name" value="<%= candidate.getName() != null ? candidate.getName() : "" %>">
            <label>Surname:</label>
            <input type="text" name="surname" value="<%= candidate.getSurname() != null ? candidate.getSurname() : "" %>">
            <label>Party:</label>
            <input type="text" name="party" value="<%= candidate.getParty() != null ? candidate.getParty() : "" %>">
            <label>Interests:</label>
            <textarea name="interests"><%= candidate.getInterests() != null ? candidate.getInterests() : "" %></textarea>
            <label>Motto:</label>
            <textarea name="motto"><%= candidate.getMotto() != null ? candidate.getMotto() : "" %></textarea>

            <%-- Questions --%>
            <h3>Answers:</h3>
            <% for (Question question : (List<Question>) questions) { %>
                <label><%= question.getText() %>:</label>
                <input type="number" name="answer_<%= question.getId() %>" min="1" max="5" 
                       value="<%= candidate.getAnswers().getOrDefault(question.getId(), 0) %>">
            <% } %>

            <button type="submit">Save</button>
        </form>
    </div>
</body>
</html>
