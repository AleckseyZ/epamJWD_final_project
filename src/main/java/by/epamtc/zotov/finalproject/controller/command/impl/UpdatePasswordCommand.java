package by.epamtc.zotov.finalproject.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.zotov.finalproject.controller.Validator;
import by.epamtc.zotov.finalproject.controller.atribute.CommandPaths;
import by.epamtc.zotov.finalproject.controller.atribute.JSPAtributes;
import by.epamtc.zotov.finalproject.controller.command.Command;
import by.epamtc.zotov.finalproject.exception.ServiceException;
import by.epamtc.zotov.finalproject.service.ServiceFactory;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class UpdatePasswordCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isSuccessful = false;
        String targetId = request.getParameter(JSPAtributes.TARGET_ID);
        String password = request.getParameter(JSPAtributes.PASSWORD);
        String newPassword = request.getParameter(JSPAtributes.NEW_PASSWORD);
        String oldPassword = request.getParameter(JSPAtributes.REPEAT_PASSWORD);

        if (Validator.getInstance().validateNumber(targetId) && Validator.getInstance().validatePassword(password)
                && password.equals(oldPassword) && Validator.getInstance().validatePassword(newPassword)) {
            try {
                if (ServiceFactory.getUserService().updatePassword(Integer.parseInt(targetId), password.toCharArray(),
                        newPassword.toCharArray(), oldPassword.toCharArray())) {
                    isSuccessful = true;
                }
            } catch (ServiceException e) {
                logger.error("Exception while updating user's email", e);
            }
        }
        if (!isSuccessful) {
            request.getSession().setAttribute(JSPAtributes.FAILURE, true);
        }
        response.sendRedirect(CommandPaths.ACCOUNT_PAGE);
    }
}