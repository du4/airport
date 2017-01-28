package by.it.pvt.du4.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity  @Table
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id=0;
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
    @Column(insertable = true, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdDate;
    @Column(insertable = false, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp updatedDate;

    public User(String login) {
        this.login = login;
        this.role_id = Role.USER_ROLE;
        this.createdDate = new Timestamp(System.currentTimeMillis()/1000);
    }

    public User(String login, String email, String  pass, Integer role_id, Timestamp createDate) {
        this.login = login;
        this.email = email;
        this.pass = pass;
        this.role_id = role_id;
        this.createdDate = createDate;
    }

}
