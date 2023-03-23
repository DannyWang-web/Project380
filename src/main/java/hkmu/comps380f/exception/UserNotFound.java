package hkmu.comps380f.exception;

public class UserNotFound extends Exception {
    public UserNotFound(long id) {
        super("User " + id + " does not exist.");
    }
}
