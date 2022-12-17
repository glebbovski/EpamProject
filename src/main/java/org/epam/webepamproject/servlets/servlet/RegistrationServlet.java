package org.epam.webepamproject.servlets.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.epam.webepamproject.dao.jdbc.mysql.UserDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

@WebServlet(name = "registrationServlet", value = "/registration")
public class RegistrationServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(RegistrationServlet.class);
    private AtomicReference<UserDAO> userDAO;
    private static final String WRONG_REGISTRATION_DATA_ATTRIBUTE = "wrongRegistrationData";
    private static final String USER_EXISTS = "User with this username exists already";

    private static final String PASSWORDS_DO_NOT_MATCH = "Passwords do not match";

    public void init() throws ServletException {
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");
        req.setAttribute(WRONG_REGISTRATION_DATA_ATTRIBUTE, "");
        req.getRequestDispatcher("/WEB-INF/view/pages/registrationPage.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");

        if(userDAO == null) {
            userDAO = new AtomicReference<UserDAO>(new UserDAO());
        }

        String username = req.getParameter("username");

        if (!userDAO.get().isUserExists(username)) {
            String password = req.getParameter("password");
            String pswrepeat = req.getParameter("psw-repeat");
            if (password.equals(pswrepeat)) {

                ServletContext servletContext = getServletContext();
                servletContext.setAttribute("username", username);
                userDAO.get().create(username, password);
                resp.sendRedirect(req.getContextPath() + "/homePage");

            } else {
                req.setAttribute(WRONG_REGISTRATION_DATA_ATTRIBUTE, PASSWORDS_DO_NOT_MATCH);
                req.getRequestDispatcher("/WEB-INF/view/pages/registrationPage.jsp").forward(req, resp);
                logger.warn(req.getAttribute(WRONG_REGISTRATION_DATA_ATTRIBUTE));
            }

        } else {
            req.setAttribute(WRONG_REGISTRATION_DATA_ATTRIBUTE, USER_EXISTS);
            req.getRequestDispatcher("/WEB-INF/view/pages/registrationPage.jsp").forward(req, resp);
            logger.warn(req.getAttribute(WRONG_REGISTRATION_DATA_ATTRIBUTE));
        }

    }
}
