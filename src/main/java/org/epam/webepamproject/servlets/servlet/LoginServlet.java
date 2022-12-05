package org.epam.webepamproject.servlets.servlet;

import org.epam.webepamproject.dao.classes.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicReference;

@WebServlet(name = "loginServlet", value = "/")
public class LoginServlet extends HttpServlet {

    private AtomicReference<UserDAO> userDAO;

    public void init() throws ServletException {
        this.userDAO = new AtomicReference<UserDAO>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("asdasdasd");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        resp.sendRedirect("/");
    }

}
