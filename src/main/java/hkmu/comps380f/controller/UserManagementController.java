package hkmu.comps380f.controller;

import hkmu.comps380f.dao.UserManagementService;
import hkmu.comps380f.exception.AttachmentNotFound;
import hkmu.comps380f.exception.UserNotFound;
import hkmu.comps380f.model.Attachment;
import hkmu.comps380f.model.User;
import hkmu.comps380f.validator.UserValidator;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/")
public class UserManagementController {
    @Autowired
    private UserValidator userValidator;

    @Resource
    UserManagementService umService;


    public static class Form {
        @NotEmpty(message="Please enter your user name.")
        private String userName;
        @NotEmpty(message="Please enter your password.")
        @Size(min=6, max=15, message="Your password length must be between {min} and {max}.")
        private String userPassword;
        private String confirm_password;
        @NotEmpty(message="Please select at least one role.")
        private String[] roles;

        private String phoneNumber;
        private String userEmail;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserPassword() {
            return userPassword;
        }

        public void setUserPassword(String userPassword) {
            this.userPassword = userPassword;
        }

        public String getConfirm_password() {
            return confirm_password;
        }

        public void setConfirm_password(String confirm_password) {
            this.confirm_password = confirm_password;
        }

        public String[] getRoles() {
            return roles;
        }

        public void setRoles(String[] roles) {
            this.roles = roles;
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

// getters and setters for all properties
    }

    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("addUser", "User", new Form());
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("User") @Valid Form form, BindingResult result)
            throws IOException {
        userValidator.validate(form, result);//before去检查hasErrors @NotEmpty 先去userValidator检查confirm_password
        if (result.hasErrors()) {
            return "addUser";
        }
        umService.createUser(form.getUserName(), form.getPhoneNumber(), form.getUserEmail(), form.getUserPassword(), form.getRoles());
        return"redirect:/photo/index";

    }

    @GetMapping("/photo/profile/{userName}")
    public String ProfilePage(@PathVariable("userName") String userName,
                            ModelMap model)
            throws UserNotFound, UserNotFound {
        User User = umService.getUserByName(userName);
        model.addAttribute("User", User);
        return "userPage";
    }

    public static class DescriptionForm {
        private String description;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    @GetMapping("/photo/profile/{userName}/DescriptionPage")
    public ModelAndView createDscription() {
        return new ModelAndView("DescriptionPage", "DscriptionFormModel", new UserManagementController.DescriptionForm());
    }

    @PostMapping("/photo/profile/{userName}/DescriptionPage")
    public String createDescription(UserManagementController.DescriptionForm form,@PathVariable("userName") String userName)throws UserNotFound, UserNotFound {
        umService.createDescription(form.getDescription(),userName);
        return"redirect:/photo/index";
    }


}