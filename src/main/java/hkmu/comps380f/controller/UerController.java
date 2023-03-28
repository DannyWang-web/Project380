package hkmu.comps380f.controller;
import hkmu.comps380f.dao.PhotoService;
import hkmu.comps380f.exception.AttachmentNotFound;
import hkmu.comps380f.exception.UserNotFound;
import hkmu.comps380f.model.User;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UerController {

    @Resource
    private PhotoService photoService;

    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("addUser", "userForm", new Form());
    }
    public static class Form {
        private String userName;
        private String phoneNumber;
        private String userEmail;
        private String userPassword;
        private String userDescription;

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

        public String getUserDescription() {
            return userDescription;
        }

        public void setUserDescription(String userDescription) {
            this.userDescription = userDescription;
        }
    }

    @PostMapping("/create")
    public View create(Form form) throws IOException  {
        long userId = photoService.createUser(form.getUserName(),
                form.getPhoneNumber(), form.getUserEmail(), form.getUserPassword(),
                form.getUserDescription());
        return new RedirectView("/user/view/" + userId, true);
    }

    @GetMapping("/view/{userId}")
    public String view(@PathVariable("userId") long userId,
                       ModelMap model)
            throws UserNotFound {
        User user = photoService.getUser(userId);
        model.addAttribute("userId", userId);
        model.addAttribute("user", user);
        return "userPage";
    }

//    @GetMapping("/delete/{userId}")
//    public String deleteUser(@PathVariable("userId") long userId)
//            throws UserNotFound {
//        photoService.deleteUser(userId);
//        return "redirect:/user/view";
//    }

    @GetMapping("/{userId}/delete/attachment/{attachment:.+}")
    public String deleteAttachment(@PathVariable("userId") long userId,
                                   @PathVariable("attachment") UUID attachmentId)
            throws UserNotFound, AttachmentNotFound {
        photoService.deleteAttachment(userId, attachmentId);
        return "redirect:/user/view" + userId;
    }

    @ExceptionHandler({UserNotFound.class, AttachmentNotFound.class})
    public ModelAndView error(Exception e) {
        return new ModelAndView("error", "message", e.getMessage());
    }
}
