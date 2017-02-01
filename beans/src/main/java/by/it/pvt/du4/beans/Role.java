package by.it.pvt.du4.beans;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table
public class Role implements Serializable {
    public static final Long ADMINISTRATOR_ROLE = 0l;
    public static final Long DISPATCHER_ROLE = 1l;
    public static final Long  USER_ROLE = 2l;
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROLE_ID")
    private Long id ;

    @Column(
            unique = true,
            nullable = false
    )
    private String name;

    @OneToMany(mappedBy = "role")
    private Set<User>  users= new HashSet<>();

    @OneToMany(mappedBy = "role_id")
    private Set<Permission>  permissions= new HashSet<>();

    //<editor-fold desc="Getter+setter+equals+hashCode">
    public Role(String role) {
        this.name = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String role) {
        this.name = role;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;

        Role role1 = (Role) o;

        if (id != null ? !id.equals(role1.id) : role1.id != null) return false;
        return name != null ? name.equals(role1.name) : role1.name == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
    //</editor-fold>
}
