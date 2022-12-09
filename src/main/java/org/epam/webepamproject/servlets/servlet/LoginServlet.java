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
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicReference;

@WebServlet(name = "loginServlet", value = "/")
public class LoginServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(LoginServlet.class);
    private AtomicReference<UserDAO> userDAO;
    private static final String WRONG_DATA_ATTRIBUTE = "wrongData";
    private static final String USER_IS_NOT_EXISTS = "User with this username is not exists";
    private static final String WRONG_CREDENTIALS = "Incorrect username or password";

    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(WRONG_DATA_ATTRIBUTE, "");
        req.getRequestDispatcher("/WEB-INF/view/pages/loginPage.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");

        if(userDAO == null) {
            userDAO = new AtomicReference<UserDAO>(new UserDAO());
        }

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // TODO here
        if(userDAO.get().isUserExists(username)) {

            if (userDAO.get().isCredentialsValid(username, password)) {

                if(userDAO.get().isUserAdmin(username)) {

                    logger.info("Successful connection to adminPage");
                    req.getRequestDispatcher("/WEB-INF/view/pages/adminPage.jsp").forward(req, resp);


                } else {

                    logger.info("Successful connection to userPage");
                    req.getRequestDispatcher("/WEB-INF/view/pages/userPage.jsp").forward(req, resp);

                }

            } else {

                req.setAttribute(WRONG_DATA_ATTRIBUTE, WRONG_CREDENTIALS);
                req.getRequestDispatcher("/WEB-INF/view/pages/loginPage.jsp").include(req, resp);
                logger.warn(req.getAttribute(WRONG_DATA_ATTRIBUTE));
            }

        } else {

            req.setAttribute(WRONG_DATA_ATTRIBUTE, USER_IS_NOT_EXISTS);
            req.getRequestDispatcher("/WEB-INF/view/pages/loginPage.jsp").include(req, resp);
            logger.warn(req.getAttribute(WRONG_DATA_ATTRIBUTE));

        }
    }

}
