package by.epamtc.zotov.finalproject.controller.command.impl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.zotov.finalproject.controller.command.Command;

public class SignUpPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         //TODO fix hardcode path
         request.getRequestDispatcher("WEB-INF/jsp/signUpPage.jsp").forward(request, response);
    }
}