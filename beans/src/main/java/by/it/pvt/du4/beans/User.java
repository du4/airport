package by.it.pvt.du4.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    @JoinColumn(name = "role_id")
    private Role role;
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "role", joinColumns = {@JoinColumn(name = "USER_ID")},
//                inverseJoinColumns = {@JoinColumn(name = "COMMAND_ID")})
//    private Set<Command> commands = new HashSet<>(0);


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
