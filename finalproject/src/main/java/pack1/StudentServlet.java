package pack1;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String contact = request.getParameter("contact");
        String email = request.getParameter("email");
        String fatherName = request.getParameter("fatherName");
        String course = request.getParameter("course");

        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db", "root", "Stephen@21");
            PreparedStatement stmt;

            switch (action) {
                case "add":
                    stmt = conn.prepareStatement("INSERT INTO studentsmanage (id, name, contact, email, father_name, course) VALUES (?, ?, ?, ?, ?, ?)");
                    stmt.setString(1, id);
                    stmt.setString(2, name);
                    stmt.setString(3, contact);
                    stmt.setString(4, email);
                    stmt.setString(5, fatherName);
                    stmt.setString(6, course);
                    stmt.executeUpdate();
                    request.setAttribute("message", "✅ Student added successfully!");
                    break;

                case "update":
                    stmt = conn.prepareStatement("UPDATE studentsmanage SET name=?, contact=?, email=?, father_name=?, course=? WHERE id=?");
                    stmt.setString(1, name);
                    stmt.setString(2, contact);
                    stmt.setString(3, email);
                    stmt.setString(4, fatherName);
                    stmt.setString(5, course);
                    stmt.setString(6, id);
                    stmt.executeUpdate();
                    request.setAttribute("message", "✅ Student updated successfully!");
                    break;

                case "delete":
                    stmt = conn.prepareStatement("DELETE FROM studentsmanage WHERE id=?");
                    stmt.setString(1, id);
                    stmt.executeUpdate();
                    request.setAttribute("message", "❌ Student deleted successfully!");
                    break;

                case "search":
                    stmt = conn.prepareStatement("SELECT * FROM studentsmanage WHERE id=?");
                    stmt.setString(1, id);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        Student s = new Student();
                        s.setId(rs.getString("id"));
                        s.setName(rs.getString("name"));
                        s.setContact(rs.getString("contact"));
                        s.setEmail(rs.getString("email"));
                        s.setFatherName(rs.getString("father_name"));
                        s.setCourse(rs.getString("course"));
                        request.setAttribute("student", s);
                        request.setAttribute("message", "🔍 Student found!");
                    } else {
                        request.setAttribute("message", "⚠️ Student not found.");
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "❗ Database Error: " + e.getMessage());
        } finally {
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }

        request.getRequestDispatcher("StudentManagement.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
