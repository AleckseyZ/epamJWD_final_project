package by.epamtc.zotov.finalproject.controller;

public class Validator {
    private static volatile Validator instance;
    private String numberRegex="^\\d+$";
    private String passwordRegex="^.{6,}$";
    private String emailRegex="^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    private Validator(){};

    public static Validator getInstance() {
        if (instance == null)
        {
            synchronized (Validator.class)
            {
                if (instance==null)
                instance = new Validator();
            }
        }
        return instance;
    }

    public boolean validateAgainstRegex(String target, String regex) {
        return (target!=null&&target.matches(regex));
    }

    public boolean validatePassword(String password) {
        return validateAgainstRegex(password, passwordRegex);
    }

    public boolean validateNumber(String number) {
        return validateAgainstRegex(number, numberRegex);
    }

    public boolean validateEmail(String email) {
        return validateAgainstRegex(email, emailRegex);
    }
}