package by.epamtc.zotov.finalproject.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.zotov.finalproject.controller.command.Command;

public class ChangeLanguageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String originalURL = request.getParameter("page");
        String desiredLanguage = request.getParameter("lang");
        request.getSession().setAttribute("language", desiredLanguage);
        response.sendRedirect(originalURL);
    }
}