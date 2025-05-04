<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Access Denied</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f8f8;
            margin: 0;
            padding: 0;
            text-align: center;
        }

        .container {
            margin-top: 100px;
            padding: 20px;
        }

        h1 {
            color: red;
            font-size: 28px;
        }

        p {
            color: #555;
            font-size: 16px;
            margin: 20px 0;
        }

        a.button {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-size: 14px;
        }

        a.button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Access Denied</h1>
    <p>You do not have permission to access this page.</p>
    <a href="${pageContext.request.contextPath}/pages/Home.jsp" class="button">Return to Home</a>
</div>

</body>
</html>
