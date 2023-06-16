package hkmu.comps380f.exception;

public class UserRoleException extends Exception{
    public UserRoleException(String role) {
        super("User role is" + role + " , can not browse this page.");
    }
}
