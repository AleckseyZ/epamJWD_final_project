package by.epamtc.zotov.finalproject.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.zotov.finalproject.controller.command.Command;
import by.epamtc.zotov.finalproject.controller.command.CommandFactory;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String commandName = request.getParameter("command");
        Command command = CommandFactory.getCommand(commandName);
        
        if (command == null) {
            request.getRequestDispatcher(PagePath.NOT_FOUND_PAGE).forward(request, response);
        } else {
            command.execute(request, response);
        }
    }
}