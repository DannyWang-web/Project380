package hkmu.comps380f.dao;

import hkmu.comps380f.exception.UserNotFound;
import hkmu.comps380f.model.Attachment;
import hkmu.comps380f.model.User;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

import static org.apache.tomcat.util.buf.Ascii.parseLong;

@Service
public class UserManagementService {
    @Resource
    private UserRepository userRepo;


    @Transactional
    public long getUserIdByUserName(String userName) {
        return userRepo.readUserIdByUserName(userName);
    }

    // Below is from Wang v2 ---------------------------------
    @Transactional
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Transactional
    public User getUser(long id)
            throws UserNotFound {
        User user = userRepo.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotFound(id);
        }
        return user;
    }

    @Transactional
    public User getUserByName(String name)
            throws UserNotFound {
        User user = userRepo.findByUserName(name);
        if (user == null) {
            throw new UserNotFound(Long.valueOf(name));
        }
        return user;
    }

    @Transactional(rollbackFor = UserNotFound.class)
    public void deleteUser(long id) throws UserNotFound {
        User deleteduser = userRepo.findById(id).orElse(null);
        if (deleteduser == null) {
            throw new UserNotFound(id);
        }
        userRepo.delete(deleteduser);
    }

    @Transactional
    public String createUser(String userName, String phoneNumber, String userEmail, String userPassword, String[] roles) {
        User users = new User(userName,phoneNumber,userEmail,userPassword,roles);
        userRepo.save(users);
        return users.getUserName();
    }

    @Transactional
    public void createDescription(String Description, String name) throws UserNotFound {
       userRepo.EditUserDescriptionByUserName(Description, name);
    }

}
