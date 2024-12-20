<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Question</title>
    <style>
        body {
            font-family: "Times New Roman", Times, serif;
            background-image: url('images/background.jpg');
            background-size: cover;
            color: white;
            margin: 0;
        }
        h1 {
            text-align: center;
            margin-top: 100px;
            font-size: 2.5em;
        }
        .form-container {
            width: 40%;
            margin: 50px auto;
            background-color: white;
            color: black;
            padding: 20px;
            border-radius: 8px;
        }
        label { display: block; margin-top: 10px; font-size: 1.2em; }
        input {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            font-size: 1em;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        button {
            background-color: #D9534F;
            color: white;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 5px;
            margin-top: 15px;
            font-size: 1.2em;
            display: block;
            width: 100%;
        }
        button:hover {
            background-color: #C9302C;
        }
    </style>
</head>
<body>

    <h1>Add Question</h1>
    <div class="form-container">
        <form method="post" action="add-question">
            <label for="text">Question Text:</label>
            <input type="text" id="text" name="text" placeholder="Enter the question here..." required>
            <button type="submit">Save</button>
        </form>
    </div>
</body>
</html>
