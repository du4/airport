package by.it.pvt.du4.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@DynamicUpdate
@Entity
@Table
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID", unique = true)
    @Getter
    @Setter
    private Long id;

    @Column(
            unique = true,
            nullable = false
    )
    @Getter
    @Setter
    private String login;

    @Column(

            unique = true,
            nullable = false
    )
    @Getter
    @Setter
    private String email;

    @Column
    @Getter
    @Setter
    private String pass;

    @ManyToOne
    @JoinColumn(name = "FK_ROLE")
    @Getter
    @Setter
    private Role role;

    @Column(insertable = true, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date createdDate;

    @Column(insertable = false, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date updatedDate;

    @OneToMany(mappedBy = "user_id", cascade = CascadeType.ALL)
    @Getter
    @Setter
    private Set<Flight> flights = new HashSet<>();

    public User(String login) {
        this.login = login;
        this.role = new Role();
        this.createdDate = new Timestamp(1000 * (System.currentTimeMillis() / 1000));
    }

    public User(String login, Long id) {
        this.login = login;
        this.role = new Role(id);
        this.createdDate = new Timestamp(1000 * (System.currentTimeMillis() / 1000));
    }

    public User(String login, String email, String pass, Role role, Date createDate) {
        this.login = login;
        this.email = email;
        this.pass = pass;
        this.role = role;
        this.createdDate = createDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                ", role=" + role +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (pass != null ? !pass.equals(user.pass) : user.pass != null) return false;
        if (role != null ? !role.equals(user.role) : user.role != null) return false;
        if (createdDate.getTime() != user.createdDate.getTime()) return false;
        return updatedDate != null ? updatedDate.equals(user.updatedDate) : user.updatedDate == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (pass != null ? pass.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
