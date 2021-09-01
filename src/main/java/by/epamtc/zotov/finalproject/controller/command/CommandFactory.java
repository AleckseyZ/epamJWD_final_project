package by.epamtc.zotov.finalproject.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.epamtc.zotov.finalproject.controller.atribute.PagePaths;
import by.epamtc.zotov.finalproject.controller.command.impl.AccessPageCommand;
import by.epamtc.zotov.finalproject.controller.command.impl.AccountPageCommand;
import by.epamtc.zotov.finalproject.controller.command.impl.AddBookCommand;
import by.epamtc.zotov.finalproject.controller.command.impl.AddCardCommand;
import by.epamtc.zotov.finalproject.controller.command.impl.AddNoticeCommand;
import by.epamtc.zotov.finalproject.controller.command.impl.BlockCardCommand;
import by.epamtc.zotov.finalproject.controller.command.impl.CatalogueCommand;
import by.epamtc.zotov.finalproject.controller.command.impl.ChangeLanguageCommand;
import by.epamtc.zotov.finalproject.controller.command.impl.HomePageCommand;
import by.epamtc.zotov.finalproject.controller.command.impl.LeaseBookCommand;
import by.epamtc.zotov.finalproject.controller.command.impl.LoanMenuCommand;
import by.epamtc.zotov.finalproject.controller.command.impl.LogInCommand;
import by.epamtc.zotov.finalproject.controller.command.impl.LogOutCommand;
import by.epamtc.zotov.finalproject.controller.command.impl.SearchCatalogueCommand;
import by.epamtc.zotov.finalproject.controller.command.impl.SignUpCommand;
import by.epamtc.zotov.finalproject.controller.command.impl.UpdateBookCommand;
import by.epamtc.zotov.finalproject.controller.command.impl.UpdateBookPageCommand;
import by.epamtc.zotov.finalproject.controller.command.impl.UpdateLoanCommand;
import by.epamtc.zotov.finalproject.controller.command.impl.UpdateMailCommand;
import by.epamtc.zotov.finalproject.controller.command.impl.UpdateNoticeCommand;
import by.epamtc.zotov.finalproject.controller.command.impl.UpdateNoticePageCommand;
import by.epamtc.zotov.finalproject.controller.command.impl.UpdatePasswordCommand;
import by.epamtc.zotov.finalproject.controller.command.impl.UpdateRoleCommand;
import by.epamtc.zotov.finalproject.controller.command.impl.DeleteNoticeCommand;
import by.epamtc.zotov.finalproject.controller.command.impl.FindCardCommand;
import by.epamtc.zotov.finalproject.controller.command.impl.FindLoansCommand;
import by.epamtc.zotov.finalproject.controller.command.impl.FindUserCommand;

//Unhardcore names
public class CommandFactory {
    private static Map<String, Command> commandMap = new HashMap<>();
    
    static {
        commandMap.put("changeLanguage", new ChangeLanguageCommand());
        commandMap.put("homePage", new HomePageCommand());
        commandMap.put("logInPage", new AccessPageCommand(PagePaths.LOGIN_PAGE));
        commandMap.put("logIn", new LogInCommand());
        commandMap.put("logOut", new LogOutCommand());
        commandMap.put("signUpPage", new AccessPageCommand(PagePaths.SIGNUP_PAGE));
        commandMap.put("signUp", new SignUpCommand());
        commandMap.put("catalogue", new CatalogueCommand());
        commandMap.put("findBooks", new SearchCatalogueCommand());
        commandMap.put("cardMenu", new AccessPageCommand(PagePaths.CARD_MENU));
        commandMap.put("findCard", new FindCardCommand());
        commandMap.put("addCardPage", new AccessPageCommand(PagePaths.ADD_CARD_PAGE));
        commandMap.put("addCard", new AddCardCommand());
        commandMap.put("blockCard", new BlockCardCommand());
        commandMap.put("loanMenu", new LoanMenuCommand());
        commandMap.put("findLoans", new FindLoansCommand());
        commandMap.put("addNoticePage", new AccessPageCommand(PagePaths.ADD_NOTICE_PAGE));
        commandMap.put("addNotice", new AddNoticeCommand());
        commandMap.put("updateNoticePage", new UpdateNoticePageCommand());
        commandMap.put("updateNotice", new UpdateNoticeCommand());
        commandMap.put("deleteNotice", new DeleteNoticeCommand());
        commandMap.put("addBookPage", new AccessPageCommand(PagePaths.ADD_BOOK_PAGE));
        commandMap.put("addBook", new AddBookCommand());
        commandMap.put("updateBookPage", new UpdateBookPageCommand());
        commandMap.put("updateBook", new UpdateBookCommand());
        commandMap.put("accountPage", new AccountPageCommand());
        commandMap.put("updateMail", new UpdateMailCommand());
        commandMap.put("updatePassword", new UpdatePasswordCommand());
        commandMap.put("leaseBook", new LeaseBookCommand());
        commandMap.put("updateLease", new UpdateLoanCommand());
        commandMap.put("adminMenu", new AccessPageCommand(PagePaths.ADMIN_MENU));
        commandMap.put("findUser", new FindUserCommand());
        commandMap.put("updateRole", new UpdateRoleCommand());
    }

    private CommandFactory(){};

    public static Command getCommand(String name) {
        return commandMap.get(name);
    }
}