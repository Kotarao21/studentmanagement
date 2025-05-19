<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="pack1.Student" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>📚 Student Management System</title>
    <link rel="stylesheet" href="stylejsp.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
  <center>
            <h2><i class="fas fa-user-graduate"></i> Presidency University Student Management System</h2>
            <p class="tagline">“📚 Manage students efficiently. Empower their future. 🌟”</p>
        </center>
<div class="overlay">
    <div class="container">
      

        <form action="StudentServlet" method="post">
            <label>🆔 Student ID:</label>
            <input type="text" name="id" required>

            <label>👤 Name:</label>
            <input type="text" name="name">

            <label>📞 Contact:</label>
            <input type="text" name="contact">

            <label>📧 Email:</label>
            <input type="text" name="email">

            <label>👨‍💼 Father Name:</label>
            <input type="text" name="fatherName">

            <label>📘 Course:</label>
            <input type="text" name="course">

            <div class="buttons">
                <button type="submit" name="action" value="add">➕ Add</button>
                <button type="submit" name="action" value="update">✏️ Update</button>
                <button type="submit" name="action" value="delete">❌ Delete</button>
                <button type="submit" name="action" value="search">🔍 Search</button>
            </div>
        </form>

        <% if (request.getAttribute("message") != null) { %>
            <p class="message"><%= request.getAttribute("message") %></p>
        <% } %>

        <% Student student = (Student) request.getAttribute("student");
           if (student != null) { %>
            <div class="result">
                <p><strong>👤 Name:</strong> <%= student.getName() %></p>
                <p><strong>📞 Contact:</strong> <%= student.getContact() %></p>
                <p><strong>📧 Email:</strong> <%= student.getEmail() %></p>
                <p><strong>👨‍💼 Father Name:</strong> <%= student.getFatherName() %></p>
                <p><strong>📘 Course:</strong> <%= student.getCourse() %></p>
            </div>
        <% } %>
    </div>
</div>
</body>
</html>