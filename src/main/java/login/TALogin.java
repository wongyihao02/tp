package login;

import java.util.HashMap;
import java.util.Map;

public class TALogin {

    private String passWord;

    public TALogin(String passWord) {
        this.passWord = passWord;
    }

    public String getPassWord() {
        return this.passWord;
    }

    public boolean passwordCorrect(String input) {
        return input.equals(this.passWord);
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "The password is " + this.passWord;
    }
}
