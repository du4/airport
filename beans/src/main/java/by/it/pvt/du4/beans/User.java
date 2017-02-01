package by.it.pvt.du4.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity  @Table
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID", unique = true)
    @Getter @Setter
    private Long id;

    @Column(
            unique = true,
            nullable = false
    )
    @Getter @Setter
    private String login;

    @Column(
            unique = true,
            nullable = false
    )
    @Getter @Setter
    private String email;

    @Column
    @Getter @Setter
    private String pass;

    @ManyToOne
    @JoinColumn(name = "FK_ROLE")
    @Getter @Setter
    private Role role;

    @Column(insertable = true, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Getter @Setter
    private Date createdDate;

    @Column(insertable = false, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @Getter @Setter
    private Date updatedDate;

    @OneToMany(mappedBy = "user_id")
    @Getter @Setter
    private Set<Flight> flights= new HashSet<>();

    public User(String login) {
        this.login = login;
        this.role = new Role();
        this.createdDate = new Timestamp(1000*(System.currentTimeMillis()/1000));
    }

    public User(String login, Long id) {
        this.login = login;
        this.role = new Role(id);
        this.createdDate = new Timestamp(1000*(System.currentTimeMillis()/1000));
    }

    public User(String login, String email, String  pass, Role role, Date createDate) {
        this.login = login;
        this.email = email;
        this.pass = pass;
        this.role = role;
        this.createdDate = createDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", role=" + role.getName() +
                '}';
    }
}
