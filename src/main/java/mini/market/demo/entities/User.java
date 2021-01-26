package mini.market.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @Column(name = "user_id")
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String user_id;

    @Column(name = "username")
    @Length(min = 4, message="Company name must have at least 4 characters")
    @NotEmpty(message="Company name should not be empty")
    private String username;

    @Column(name = "password")
    @Length(min = 4, message="Password must have at least 4 characters")
    @NotEmpty(message="Password should not be empty")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "enabled")
    private boolean enabled;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
/*
* CREATE TABLE `users` (
        `user_id` VARCHAR(45) NOT NULL,
        `username` varchar(45) NOT NULL,
        `password` varchar(64) NOT NULL,
        `role` varchar(45) NOT NULL,
        `enabled` tinyint(4) DEFAULT NULL,
        PRIMARY KEY (`user_id`)
        );
* */