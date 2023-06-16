package hkmu.comps380f.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="attachmentTable")
public class Attachment {
    @Id
    @GeneratedValue
    @ColumnDefault("random_uuid()")
    @Column(name = "attachment_id")
    private UUID attachmentId;

    @Column(name = "attachment_name")
    private String attachmentName;

    @Column(name = "attachment_content_type")
    private String attachmentContentType;

    @Column(name = "attachment_content")
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private byte[] attachmentContent;

    @Column(name = "user_id", insertable=false, updatable=false)
    private long userId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "attachment_description")
    private String attachmentDescription;

    @Column(name = "commentList_creat_time")
    private String createTime;

    @OneToMany(mappedBy = "attachment", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    @Column(name = "attachment_commentList")
    private List<Comment> commentList = new ArrayList<Comment>();

    public Attachment() {
    }

    //Getter and setter
    public UUID getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(UUID attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getAttachmentContentType() {
        return attachmentContentType;
    }

    public void setAttachmentContentType(String attachmentContentType) {
        this.attachmentContentType = attachmentContentType;
    }

    public byte[] getAttachmentContent() {
        return attachmentContent;
    }

    public void setAttachmentContent(byte[] attachmentContent) {
        this.attachmentContent = attachmentContent;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAttachmentDescription() {
        return attachmentDescription;
    }

    public void setAttachmentDescription(String attachmentDescription) {
        this.attachmentDescription = attachmentDescription;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

}
