package org.epam.webepamproject.servlets.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.epam.webepamproject.dao.jdbc.mysql.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

@WebServlet(name = "homePageServlet", value = "/homePage")
public class HomePageServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(HomePageServlet.class);
    private AtomicReference<UserDAO> userDAO;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(userDAO == null) {
            userDAO = new AtomicReference<UserDAO>(new UserDAO());
        }

        System.out.println(userDAO.get().getAllUsers());
        req.getRequestDispatcher("/WEB-INF/view/pages/homePage.jsp").forward(req, resp);
        logger.info("Successful connection to homePage by " + getServletContext().getAttribute("username"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
