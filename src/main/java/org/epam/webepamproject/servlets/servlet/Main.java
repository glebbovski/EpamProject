package org.epam.webepamproject.servlets.servlet;

import org.epam.webepamproject.dao.jdbc.mysql.UserDAO;

import java.util.concurrent.atomic.AtomicReference;

public class Main {

    public static void main(String[] args) {
        AtomicReference<UserDAO> userDAO = new AtomicReference<UserDAO>(new UserDAO());

        System.out.println(userDAO.get().isUserExists("Мария"));
    }
}
