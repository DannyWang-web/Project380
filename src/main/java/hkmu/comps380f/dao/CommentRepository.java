package hkmu.comps380f.dao;
import hkmu.comps380f.exception.CommentNotFound;
import hkmu.comps380f.exception.UserNotFound;
import hkmu.comps380f.model.Comment;
import hkmu.comps380f.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
    @Modifying
    @Query("DELETE FROM Comment c WHERE c.commentId = ?1")
    void deleteCommentByCommentId(UUID commentId);

    @Query("select c.commentContent from Comment c where c.userName = ?1")
    List<String> readCommentByUserName(String userName);

    @Query("select c from Comment c where c.userName = ?1")
    List<Comment> readAllCommentsByUserName(String userName);

//    @Modifying
//    @Query(value = "delete from Comment c where (select c.commentId from Comment c where c.userName = ?1)= ?1")
//    int deleteAllCommentsByUserName(String userName);
////    int deleteAllCommentsByUserName(String userName);
//    @Modifying
//    @Query(value = "delete from Comment c where c.userName = ?1")
//    void deleteAllCommentsByUserName(String userName);

}
