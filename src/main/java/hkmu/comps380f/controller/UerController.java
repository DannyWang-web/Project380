package hkmu.comps380f.controller;
import hkmu.comps380f.dao.PhotoService;
import hkmu.comps380f.dao.UserManagementService;
import hkmu.comps380f.dao.UserService;
import hkmu.comps380f.exception.AttachmentNotFound;
import hkmu.comps380f.exception.UserRoleException;
import hkmu.comps380f.exception.UserNotFound;
import hkmu.comps380f.exception.UserRoleException;
import hkmu.comps380f.model.Comment;
import hkmu.comps380f.model.User;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UerController {

    @Resource
    private PhotoService photoService;
    @Resource
    private UserManagementService umService;
    @Resource
    private PhotoService pService;

    @GetMapping({"", "/", "/list"})
    public String list(ModelMap model) throws UserRoleException {
        List<Comment> commentList = pService.getComments();

        model.addAttribute("commentList", commentList);
        model.addAttribute("userDatabase", umService.getUsers());

        return "listUser";
    }

    public static class Form {
        private String userName;
        private String phoneNumber;
        private String userEmail;
        private String userPassword;
        private String[] roles;

        // Getters and Setters of customerName, comment, body, attachments
        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getUserEmail() {
            return userEmail;
        }

        public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
        }

        public String getUserPassword() {
            return userPassword;
        }

        public void setUserPassword(String userPassword) {
            this.userPassword = userPassword;
        }

        public String[] getRoles() { return roles;}

        public void setRoles(String[] roles) { this.roles = roles;}
    }


    @GetMapping("/view/{userId}")
    public String view(@PathVariable("userId") long userId,
                       ModelMap model)
            throws UserNotFound {
        User User = umService.getUser(userId);
        model.addAttribute("User", User);
        return "userPage";
    }

    @GetMapping("/delete/{userId}")
    public String deleteUser(@PathVariable("userId") long userId)
            throws UserNotFound {
        umService.deleteUser(userId);
        return "redirect:/user/list";
    }

    @GetMapping("/{userId}/delete/attachment/{attachment:.+}")
    public String deleteAttachment(@PathVariable("userId") long userId,
                                   @PathVariable("attachment") UUID attachmentId)
            throws UserNotFound, AttachmentNotFound {
        photoService.deleteAttachment(userId, attachmentId);
        return "redirect:/user/view" + userId;
    }

    @ExceptionHandler({UserNotFound.class, AttachmentNotFound.class, UserRoleException.class})
    public ModelAndView error(Exception e) {
        return new ModelAndView("error", "message", e.getMessage());
    }
}
