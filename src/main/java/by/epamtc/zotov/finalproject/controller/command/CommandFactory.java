package by.epamtc.zotov.finalproject.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.epamtc.zotov.finalproject.controller.command.impl.HomePageCommand;
import by.epamtc.zotov.finalproject.controller.command.impl.LogInPageCommand;
import by.epamtc.zotov.finalproject.controller.command.impl.SignUpPageCommand;

//Unhardcore names
public class CommandFactory {
    private static Map<String, Command> commandMap = new HashMap<>();
    
    static {
        commandMap.put("homePage", new HomePageCommand());
        commandMap.put("logInPage", new LogInPageCommand());
        commandMap.put("signUpPage", new SignUpPageCommand());
    }

    private CommandFactory(){};

    public static Command getCommand(String name) {
        return commandMap.get(name);
    }
}