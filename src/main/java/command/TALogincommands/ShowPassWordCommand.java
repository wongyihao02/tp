package command.TALogincommands;


import command.taskcommands.Command;
import login.TALogin;

public class ShowPassWordCommand implements Command<TALogin> {
    public void execute(String parts, TALogin passwordHolder) {
        System.out.println(passwordHolder.toString());
    }
}
