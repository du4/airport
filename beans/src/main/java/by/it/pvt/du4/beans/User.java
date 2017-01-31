package by.it.pvt.du4.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity  @Table
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID", unique = true)
    private Long id;

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

    @ManyToOne
    @JoinColumn(name = "FK_ROLE")
    private Role role;

    @Column(insertable = true, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(insertable = false, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    public User(String login) {
        this.login = login;
        this.role = new Role();
        this.createdDate = new Timestamp(1000*(System.currentTimeMillis()/1000));
    }

    public User(String login, String email, String  pass, Role role, Timestamp createDate) {
        this.login = login;
        this.email = email;
        this.pass = pass;
        this.role = role;
        this.createdDate = createDate;
    }

}
