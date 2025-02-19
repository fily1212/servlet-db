package it.unito.serverdb1;

import java.io.*;
import java.util.List;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    StudentDAO studentDAO;
    public void init() {
        studentDAO = new StudentDAO();
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        List<Studente> studenteList = studentDAO.getStudents();
        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + studenteList + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}