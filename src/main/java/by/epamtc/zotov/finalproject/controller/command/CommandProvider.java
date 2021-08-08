package by.epamtc.zotov.finalproject.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.epamtc.zotov.finalproject.controller.command.impl.HomePageCommand;
import by.epamtc.zotov.finalproject.controller.command.impl.LogInPageCommand;

//Unhardcore names
public class CommandProvider {
    private static Map<String, Command> commandMap = new HashMap<>();
    
    static {
        commandMap.put("homePage", new HomePageCommand());
        commandMap.put("logInPage", new LogInPageCommand());
    }

    private CommandProvider(){};

    public static Command getCommand(String name) {
        return commandMap.get(name);
    }
}