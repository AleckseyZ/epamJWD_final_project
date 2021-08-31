package by.epamtc.zotov.finalproject.controller.command.impl;

import org.apache.logging.log4j.Logger;

import by.epamtc.zotov.finalproject.controller.Validator;
import by.epamtc.zotov.finalproject.controller.atribute.CommandPaths;
import by.epamtc.zotov.finalproject.controller.atribute.JSPAtributes;
import by.epamtc.zotov.finalproject.controller.command.Command;
import by.epamtc.zotov.finalproject.exception.ServiceException;
import by.epamtc.zotov.finalproject.service.ServiceFactory;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;

public class UpdateMailCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isSuccessful = false;
        String targetId = request.getParameter(JSPAtributes.TARGET_ID);
        String email = request.getParameter(JSPAtributes.EMAIL);
        if (Validator.getInstance().validateNumber(targetId) && email != null) {
            try {
                if(ServiceFactory.getUserService().updateEmail(Integer.parseInt(targetId), email)) {
                    isSuccessful=true;
                }
            } catch (ServiceException e) {
                logger.error("Exception while updating user's email", e);
            }
        }
        if(!isSuccessful) {
            request.getSession().setAttribute(JSPAtributes.FAILURE, true);
        }
        response.sendRedirect(CommandPaths.ACCOUNT_PAGE);
    }
}