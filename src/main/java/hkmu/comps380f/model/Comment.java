package hkmu.comps380f.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.UUID;

@Entity
@Table(name="commentTable")
public class Comment {

    @Id
    @GeneratedValue
    @ColumnDefault("random_uuid()")
    @Column(name = "comment_id")
    private UUID commentId;

    @Column(name = "commentContent")
    private String commentContent;

    @Column(name="userName")
    private String userName;

    @Column(name = "attachment_id", insertable=false, updatable=false)
    private UUID attachmentId;
    @ManyToOne
    @JoinColumn(name="attachment_id")
    private Attachment attachment;

    // getter setter


    public UUID getCommentId() {
        return commentId;
    }

    public void setCommentId(UUID commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UUID getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(UUID attachmentId) {
        this.attachmentId = attachmentId;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }
}
