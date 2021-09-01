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

public class UpdateRoleCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isSuccessful = false;
        String targetId = request.getParameter(JSPAtributes.TARGET_ID);
        String userType = request.getParameter(JSPAtributes.USER_TYPE);

        if (Validator.getInstance().validateNumber(targetId) && userType != null) {
            try {
                if (ServiceFactory.getInstance().getUserService().updateStatus(Integer.parseInt(targetId), userType)) {
                    isSuccessful = true;
                }
            } catch (ServiceException e) {
                logger.error("Exception while updating notce", e);
            }
        }
        if(!isSuccessful) {
            request.getSession().setAttribute(JSPAtributes.FAILURE, true);
        }
        
        response.sendRedirect(CommandPaths.ADMIN_MENU);
    }
}