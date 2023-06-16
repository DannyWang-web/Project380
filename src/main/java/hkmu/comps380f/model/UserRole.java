package hkmu.comps380f.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id")
    private int id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "role")
    private String role;

    @Column(name = "user_id",insertable = false, updatable = false)
    private long userId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UserRole() {
    }
    public UserRole(User user, String role, String userName) {
        this.user = user;
        this.role = role;
        this.username = userName;
    }

    // getters and setters of all properties
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
