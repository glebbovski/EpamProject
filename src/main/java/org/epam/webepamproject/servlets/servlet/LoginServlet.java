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
        req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF8");
        userDAO = new AtomicReference<UserDAO>(new UserDAO());

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if(userDAO.get().isUserExists(username)) {
            req.getRequestDispatcher("/WEB-INF/view/tmp.jsp").forward(req, resp);
        }
        //System.out.println(username);

        //userDAO.get().isUserExists("Мария")
        //req.getRequestDispatcher("/WEB-INF/view/tmp.jsp").forward(req, resp);

    }

}
