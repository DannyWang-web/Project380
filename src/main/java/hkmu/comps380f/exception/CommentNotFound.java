package hkmu.comps380f.exception;

import java.util.UUID;

public class CommentNotFound extends Exception{

    public CommentNotFound(UUID commentId) {
        super("Comment " + commentId + " does not exist.");
    }
}
