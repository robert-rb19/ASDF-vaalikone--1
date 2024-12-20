<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Candidate</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="navbar">
        <a href="/election_machine/index.html">Home</a>
        <a href="/election_machine/log-in">Log Out</a>
    </div>
    <h1>Add Candidate</h1>
    <div class="form-container">
        <form action="/election_machine/add-candidate" method="post">
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" required>
            </div>
            <div class="form-group">
                <label for="surname">Surname:</label>
                <input type="text" id="surname" name="surname" required>
            </div>
            <div class="form-group">
                <label for="party">Party:</label>
                <input type="text" id="party" name="party" required>
            </div>
            <div class="form-group">
                <label for="interests">Interests:</label>
                <textarea id="interests" name="interests" rows="3"></textarea>
            </div>
            <div class="form-group">
                <label for="motto">Motto:</label>
                <textarea id="motto" name="motto" rows="3"></textarea>
            </div>
            <button type="submit" class="save-button">Save</button>
        </form>
    </div>
</body>
</html>
