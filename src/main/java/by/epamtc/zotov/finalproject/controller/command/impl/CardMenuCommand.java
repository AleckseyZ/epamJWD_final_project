package by.epamtc.zotov.finalproject.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.zotov.finalproject.controller.atribute.PagePaths;
import by.epamtc.zotov.finalproject.controller.command.Command;

public class CardMenuCommand implements Command {
    //TODO MAYBE REFRACTOR AWAY
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(PagePaths.CARD_MENU).forward(request, response);      
    }
}