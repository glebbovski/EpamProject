package org.epam.webepamproject.servlets.servlet;


import org.epam.webepamproject.dao.jdbc.mysql.UserDAO;

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

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/pages/loginPage.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");
        userDAO = new AtomicReference<UserDAO>(new UserDAO());

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // TODO here
        if(userDAO.get().isUserExists(username) && userDAO.get().isCredentialsValid(username, password)) {
            req.getRequestDispatcher("/WEB-INF/view/pages/adminPage.jsp").forward(req, resp);
        } else {
//            PrintWriter writer = resp.getWriter();
//            writer.println("Not valid");
            req.getRequestDispatcher("/WEB-INF/view/pages/registrationPage.jsp").forward(req, resp);
        }
    }

}
