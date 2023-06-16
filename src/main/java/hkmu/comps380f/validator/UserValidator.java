package hkmu.comps380f.validator;
import hkmu.comps380f.dao.UserRepository;
import hkmu.comps380f.model.User;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import hkmu.comps380f.controller.UserManagementController.Form;

@Component
public class UserValidator implements Validator {
    @Resource
    UserRepository userRepo;

    @Override
    public boolean supports(Class<?> type) {
        return Form.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Form user = (Form) o;
        ValidationUtils.rejectIfEmpty(errors, "confirm_password", "",
                "Please confirm your password.");
        if (!user.getUserPassword().equals(user.getConfirm_password())) {
            errors.rejectValue("confirm_password", "", "Password does not match.");
        }
        if (user.getUserName().equals("")) {
            return;
        }
        User userIdentify = userRepo.findByUserName(user.getUserName());
        if (userIdentify != null) {
            errors.rejectValue("userName", "", "User already exists.");
        }
    }
}