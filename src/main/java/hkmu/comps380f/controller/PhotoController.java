package hkmu.comps380f.controller;

import hkmu.comps380f.dao.PhotoService;
import hkmu.comps380f.dao.UserManagementService;
import hkmu.comps380f.exception.AttachmentNotFound;
import hkmu.comps380f.exception.UserNotFound;
import hkmu.comps380f.model.Attachment;
import hkmu.comps380f.model.User;
import hkmu.comps380f.view.DownloadingView;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.From;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/photo")
public class PhotoController {
    @Resource
    private PhotoService pService;
    @Resource
    private UserManagementService uService;

    @GetMapping("/index")
    public String index(ModelMap model) {
        model.addAttribute("userDatabase", uService.getUsers());
        model.addAttribute("attachmentDatabase", pService.getAllAttachments());
        return "index";
    }

    //From jiang ==========================================================================================
    @GetMapping("/addPhoto")
    public ModelAndView createPhoto() {
        return new ModelAndView("addPhoto", "photoFormModel", new photoForm());
    }

    public static class photoForm {
        private MultipartFile attachments;
        private String description;

        // Setter and getter
        public MultipartFile getAttachments() {
            return attachments;
        }

        public void setAttachments(MultipartFile attachments) {
            this.attachments = attachments;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    // Wang: add Date, add principal object to get userId by userName
    @PostMapping("/addPhoto")
    public View create(photoForm form,  Principal principal) throws IOException, UserNotFound {

        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        System.out.println(); // 输出已经格式化的现在时间（24小时制）

        User currentUser = uService.getUser(uService.getUserIdByUserName(principal.getName()));

//        UUID attachmentId = pService.addPhoto(form.getAttachments(),
//                sdf.format(date), form.getDescription(), userid);

        UUID attachmentId = pService.addPhoto(form.getAttachments(),
                sdf.format(date), form.getDescription(), currentUser);

        return new RedirectView("/photo/view/" + attachmentId, true);
    }

    @GetMapping("/addComment/{attachmentId}")
    public ModelAndView addCommentModelAndView(@PathVariable UUID attachmentId, ModelMap model) {
        model.addAttribute(attachmentId);
        return new ModelAndView("addComment", "cform", new commentForm());
    }

    public static class commentForm {
        private String cContent;
        private String userName;

        public String getcContent() {
            return cContent;
        }

        public void setcContent(String cContent) {
            this.cContent = cContent;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }

    @PostMapping("/addComment/{attachmentId}")
    public View addComment_post(commentForm form,@PathVariable UUID attachmentId,Principal principal) throws AttachmentNotFound {
        pService.addComment(attachmentId,form.getcContent(), principal.getName());
        return new RedirectView("/photo/view/" + attachmentId, true);
    }

    @GetMapping("/view/{attachmentId}")
    public String photoPage(@PathVariable("attachmentId") UUID attachmentId,
                       ModelMap model)
            throws AttachmentNotFound, UserNotFound {
        Attachment attachment = pService.getAttachment(attachmentId);
        model.addAttribute("attachment", attachment);
        model.addAttribute("commentDatabase", pService.getComments());
        return "photoPage";
    }

    @GetMapping("attachment/download/{attachment:.+}")
    public View downloadPhotoPage(@PathVariable("attachment") UUID attachmentId)
            throws UserNotFound, AttachmentNotFound {
        Attachment attachment = pService.getAttachment(attachmentId);
        return new DownloadingView(attachment.getAttachmentName(),
                attachment.getAttachmentContentType(), attachment.getAttachmentContent());
    }

    @GetMapping("/user/{userId}/attachment/{attachment:.+}")
    public View download(@PathVariable("userId") long userId,
                         @PathVariable("attachment") UUID attachmentId)
            throws UserNotFound, AttachmentNotFound {
        Attachment attachment = pService.getAttachment(userId, attachmentId);
        return new DownloadingView(attachment.getAttachmentName(),
                    attachment.getAttachmentContentType(), attachment.getAttachmentContent());
    }

    @ExceptionHandler({UserNotFound.class, AttachmentNotFound.class})
    public ModelAndView error(Exception e) {
        return new ModelAndView("error", "message", e.getMessage());
    }

}

