package by.epamtc.zotov.finalproject.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.epamtc.zotov.finalproject.controller.command.impl.CardMenuCommand;
import by.epamtc.zotov.finalproject.controller.command.impl.CatalogueCommand;
import by.epamtc.zotov.finalproject.controller.command.impl.ChangeLanguageCommand;
import by.epamtc.zotov.finalproject.controller.command.impl.HomePageCommand;
import by.epamtc.zotov.finalproject.controller.command.impl.LoanMenuCommand;
import by.epamtc.zotov.finalproject.controller.command.impl.LogInCommand;
import by.epamtc.zotov.finalproject.controller.command.impl.LogInPageCommand;
import by.epamtc.zotov.finalproject.controller.command.impl.LogOutCommand;
import by.epamtc.zotov.finalproject.controller.command.impl.SearchCatalogueCommand;
import by.epamtc.zotov.finalproject.controller.command.impl.SignUpCommand;
import by.epamtc.zotov.finalproject.controller.command.impl.SignUpPageCommand;

//Unhardcore names
public class CommandFactory {
    private static Map<String, Command> commandMap = new HashMap<>();
    
    static {
        commandMap.put("changeLanguage", new ChangeLanguageCommand());
        commandMap.put("homePage", new HomePageCommand());
        commandMap.put("logInPage", new LogInPageCommand());
        commandMap.put("logIn", new LogInCommand());
        commandMap.put("logOut", new LogOutCommand());
        commandMap.put("signUpPage", new SignUpPageCommand());
        commandMap.put("signUp", new SignUpCommand());
        commandMap.put("catalogue", new CatalogueCommand());
        commandMap.put("findBooks", new SearchCatalogueCommand());
        commandMap.put("cardMenu", new CardMenuCommand());
        commandMap.put("loanMenu", new LoanMenuCommand());
    }

    private CommandFactory(){};

    public static Command getCommand(String name) {
        return commandMap.get(name);
    }
}