package by.epamtc.zotov.finalproject.controller.command.impl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.zotov.finalproject.controller.PagePath;
import by.epamtc.zotov.finalproject.controller.command.Command;

public class LogInPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         request.getRequestDispatcher(PagePath.LOGIN_PAGE_PATH).forward(request, response);
    }
}