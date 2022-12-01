package org.epam.webepamproject;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    @Override
    public void init() {
        message = "Hello from Servlet!";
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");

        // Hello
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");

        String path = "/tmp.html";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);


    }


    public void destroy() {
    }
}