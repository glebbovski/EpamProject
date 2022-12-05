package org.epam.webepamproject.servlets.servlet;

import org.epam.webepamproject.dao.classes.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    private AtomicReference<UserDAO> userDAO;

    public void init() throws ServletException {
        this.userDAO = new AtomicReference<UserDAO>();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username != null && password != null && userDAO.get().checkCredentials(username, password)) {
            // TODO

        }
    }

}
