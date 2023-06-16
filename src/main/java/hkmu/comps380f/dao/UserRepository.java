package hkmu.comps380f.dao;
import hkmu.comps380f.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);

    User readUserEntryByUserName(String userName);

    @Query("select u.userId from User u where u.userName = ?1")
    Long readUserIdByUserName(String userName);

    @Modifying
    @Query("update User u set u.userDescription = :Description where u.userName = :name")
    int EditUserDescriptionByUserName(@Param("Description") String Description,
                                   @Param("name") String name);
    @Modifying
    @Query("update User u set u.userEmail = :Email where u.userName = :name")
    int EditUserEmailByUserName(@Param("Email") String Email,
                                      @Param("name") String name);
    @Modifying
    @Query("update User u set u.phoneNumber = :PhoneNumber where u.userName = :name")
    int EditUserPhoneNumberByUserName(@Param("PhoneNumber") String PhoneNumber,
                                @Param("name") String name);
}
