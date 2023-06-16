package hkmu.comps380f.dao;

import hkmu.comps380f.exception.AttachmentNotFound;
import hkmu.comps380f.exception.UserNotFound;
import hkmu.comps380f.model.Attachment;
import hkmu.comps380f.model.Comment;
import hkmu.comps380f.model.User;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PhotoService {
    @Resource
    private UserRepository userRepo;
    @Resource
    private AttachmentRepository aRepo;
    @Resource
    private CommentRepository cRepo;

    @Transactional
    public Attachment getAttachment(long userId, UUID attachmentId)
            throws UserNotFound, AttachmentNotFound {
        User user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            throw new UserNotFound(userId);
        }
        Attachment attachment = aRepo.findById(attachmentId).orElse(null);
        if (attachment == null) {
            throw new AttachmentNotFound(attachmentId);
        }
        return attachment;
    }

    @Transactional(rollbackFor = AttachmentNotFound.class)
    public void deleteAttachment(long userId, UUID attachmentId)
            throws UserNotFound, AttachmentNotFound {
        User user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            throw new UserNotFound(userId);
        }
        for (Attachment Attachment : user.getAttachmentList()) {
            if (Attachment.getAttachmentId().equals(attachmentId)) {
                user.deleteAttachment(Attachment);
                userRepo.save(user);
                return;
            }
        }
        throw new AttachmentNotFound(attachmentId);
    }

    // below is from Jiang ==================================================================
    @Transactional
    public List<Comment>getComments(){
        return cRepo.findAll();
    }

    @Transactional
    public Attachment getAttachment(UUID attachmentId)
            throws AttachmentNotFound {
        Attachment attachment = aRepo.findById(attachmentId).orElse(null);
        if (attachment == null) {
            throw new AttachmentNotFound(attachmentId);
        }
        return attachment;
    }

    @Transactional
    public List<Attachment> getAllAttachments() {
        return aRepo.findAll();
    }

    @Transactional
    public UUID addPhoto(MultipartFile attachments, String date, String description, User user)
            throws IOException{
        Attachment attachment = new Attachment();
        attachment.setAttachmentName(attachments.getOriginalFilename());
        attachment.setAttachmentContentType(attachments.getContentType());
        attachment.setAttachmentContent(attachments.getBytes());
        attachment.setAttachmentDescription(description);
        attachment.setUser(user);
        attachment.setCreateTime(date);
        Attachment savedAttachment = aRepo.save(attachment);
        return savedAttachment.getAttachmentId(); // for redirect and send as parameter for pathVariable
    }
    // NEW function : add comment
    //TODO: save User Name, capture userId
//    @Transactional
//    public UUID addComment(UUID attachmentId,String cContent,String userName) throws AttachmentNotFound {
//        Attachment attachment = aRepo.findById(attachmentId).orElse(null);
//        if (attachment == null) {
//            throw new AttachmentNotFound(attachmentId);
//        }
//        Comment comment = new Comment();
//        comment.setCommentContent(cContent);
//        comment.setUserName(userName);
//        comment.setAttachmentId(attachmentId);
//        attachment.getComments().add(comment);
//        Comment savedComment = cRepo.save(comment);
//        return savedComment.getId();
//    }

    @Transactional(rollbackFor = AttachmentNotFound.class)
    public void addComment(UUID attachmentId, String cContent,String userName) throws AttachmentNotFound {
        Attachment attachment = aRepo.findById(attachmentId).orElse(null);
        if (attachment == null) {
            throw new AttachmentNotFound(attachmentId);
        }
        Comment comment = new Comment();
        comment.setCommentContent(cContent);
        comment.setUserName(userName);
        comment.setAttachment(attachment);
        attachment.getCommentList().add(comment);
        cRepo.save(comment);
    }
}
