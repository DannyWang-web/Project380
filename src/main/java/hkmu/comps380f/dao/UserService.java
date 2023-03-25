package hkmu.comps380f.dao;

import hkmu.comps380f.exception.AttachmentNotFound;
import hkmu.comps380f.exception.UserNotFound;
import hkmu.comps380f.model.Attachment;
import hkmu.comps380f.model.User;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Resource
    private UserRepository userRepository;

    @Resource
    private AttachmentRepository aRepo;

    @Transactional
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User getUser(long id)
            throws UserNotFound {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotFound(id);
        }
        return user;
    }

    @Transactional
    public Attachment getAttachment(long userId, UUID attachmentId)
            throws UserNotFound, AttachmentNotFound {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new UserNotFound(userId);
        }
        Attachment attachment = aRepo.findById(attachmentId).orElse(null);
        if (attachment == null) {
            throw new AttachmentNotFound(attachmentId);
        }
        return attachment;
    }

    @Transactional(rollbackFor = UserNotFound.class)
    public void delete(long id) throws UserNotFound {
        User deleteduser = userRepository.findById(id).orElse(null);
        if (deleteduser == null) {
            throw new UserNotFound(id);
        }
        userRepository.delete(deleteduser);
    }

    @Transactional(rollbackFor = AttachmentNotFound.class)
    public void deleteAttachment(long userId, UUID attachmentId)
            throws UserNotFound, AttachmentNotFound {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new UserNotFound(userId);
        }
        for (Attachment Attachment : user.getAttachments()) {
            if (Attachment.getId().equals(attachmentId)) {
                user.deleteAttachment(Attachment);
                userRepository.save(user);
                return;
            }
        }
        throw new AttachmentNotFound(attachmentId);
    }

    @Transactional
    public long createuser(String customerName, String comment,
                             String body, List<MultipartFile> attachments)
            throws IOException {
        User user = new User();
        user.setUserName(customerName);
        user.setComment(comment);
        user.setBody(body);

        for (MultipartFile filePart : attachments) {
            Attachment Attachment = new Attachment();
            Attachment.setName(filePart.getOriginalFilename());
            Attachment.setMimeContentType(filePart.getContentType());
            Attachment.setContents(filePart.getBytes());
            Attachment.setUser(user);
            if (Attachment.getName() != null && Attachment.getName().length() > 0
                    && Attachment.getContents() != null
                    && Attachment.getContents().length > 0) {
                user.getAttachments().add(Attachment);
            }

        }
        User saveduser = userRepository.save(user);
        return saveduser.getId();
    }
}
