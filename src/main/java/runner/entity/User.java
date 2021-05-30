package runner.entity;

import lombok.Data;
import org.dom4j.tree.AbstractEntity;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User extends SuperID {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
	private String authority;

    public User() {
    }

}

