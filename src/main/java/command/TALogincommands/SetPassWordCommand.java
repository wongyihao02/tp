package command.TALogincommands;

import command.taskcommands.Command;
import login.TALogin;

public class SetPassWordCommand implements Command<TALogin> {
    public void execute(String parts, TALogin passwordHolder) {
        passwordHolder.setPassWord(parts);
        System.out.println(passwordHolder);
    }
}
