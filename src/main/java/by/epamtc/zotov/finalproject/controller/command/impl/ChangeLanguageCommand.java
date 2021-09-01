package by.epamtc.zotov.finalproject.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.zotov.finalproject.controller.atribute.JSPAtributes;
import by.epamtc.zotov.finalproject.controller.command.Command;

public class ChangeLanguageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String originalURL = request.getParameter(JSPAtributes.PAGE);
        String desiredLanguage = request.getParameter(JSPAtributes.LANG);
        request.getSession().setAttribute(JSPAtributes.LANGUAGE, desiredLanguage);
        response.sendRedirect(originalURL);
    }
}