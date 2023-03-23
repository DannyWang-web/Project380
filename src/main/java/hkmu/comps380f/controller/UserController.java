package hkmu.comps380f.controller;

import hkmu.comps380f.dao.UserService;
import hkmu.comps380f.exception.AttachmentNotFound;
import hkmu.comps380f.exception.UserNotFound;
import hkmu.comps380f.model.Attachment;
import hkmu.comps380f.model.User;
import hkmu.comps380f.view.DownloadingView;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    // Controller methods, Form-backing object, ...
    @GetMapping(value = {"", "/list"})
    public String list(ModelMap model) {
        model.addAttribute("userDatabase", userService.getUsers());
        return "list";
    }

    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("add", "userForm", new Form());
    }
    public static class Form {
        private String userName;
        private String comment;
        private String body;
        private List<MultipartFile> attachments;

        // Getters and Setters of customerName, comment, body, attachments
        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public List<MultipartFile> getAttachments() {
            return attachments;
        }

        public void setAttachments(List<MultipartFile> attachments) {
            this.attachments = attachments;
        }
    }

    @PostMapping("/create")
    public View create(Form form) throws IOException {
        long userId = userService.createuser(form.getUserName(),
                form.getComment(), form.getBody(), form.getAttachments());
        return new RedirectView("/user/view/" + userId, true);
    }

    @GetMapping("/view/{userId}")
    public String view(@PathVariable("userId") long userId,
                       ModelMap model)
            throws UserNotFound {
        User user = userService.getUser(userId);
        model.addAttribute("userId", userId);
        model.addAttribute("user", user);
        return "view";
    }

    @GetMapping("/{userId}/attachment/{attachment:.+}")
    public View download(@PathVariable("userId") long userId,
                         @PathVariable("attachment") UUID attachmentId)
            throws UserNotFound, AttachmentNotFound {
        Attachment attachment = userService.getAttachment(userId, attachmentId);
        return new DownloadingView(attachment.getName(),
                    attachment.getMimeContentType(), attachment.getContents());
    }

    @GetMapping("/delete/{userId}")
    public String deleteUser(@PathVariable("userId") long userId)
            throws UserNotFound {
        userService.delete(userId);
        return "redirect:/user/list";
    }

    @GetMapping("/{userId}/delete/{attachment:.+}")
    public String deleteAttachment(@PathVariable("userId") long userId,
                                   @PathVariable("attachment") UUID attachmentId)
            throws UserNotFound, AttachmentNotFound {
        userService.deleteAttachment(userId, attachmentId);
        return "redirect:/user/view/" + userId;
    }

    @ExceptionHandler({UserNotFound.class, AttachmentNotFound.class})
    public ModelAndView error(Exception e) {

        return new ModelAndView("error", "message", e.getMessage());
    }
}

