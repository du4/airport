package by.it.pvt.du4.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id=0;
    @Column(
            unique = true,
            nullable = false
    )
    private String login;
    @Column(
            unique = true,
            nullable = false
    )
    private String email;
    @Column
    private String pass;
    @Column
    private Integer role_id = 0;
    @Column
    private Timestamp createdDate;

    public User(String login) {
        this.login = login;
        this.role_id = Role.USER_ROLE;
        this.createdDate = new Timestamp((new Date()).getTime());
    }

    public User(String login, String email, String pass, Integer role_id, Timestamp createDate) {
        this.login = login;
        this.email = email;
        this.pass = pass;
        this.role_id = role_id;
        this.createdDate = createDate;
    }
}
