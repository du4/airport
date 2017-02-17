package by.it.pvt.du4.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Cacheable
@Entity
@Table
public class Role implements Serializable {
    public static final Long ADMINISTRATOR_ROLE = 1L;
    public static final Long DISPATCHER_ROLE = 2L;
    public static final Long  USER_ROLE = 3L;
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROLE_ID")
    @Getter  @Setter
    private Long id ;

    @Column(
            unique = true,
            nullable = false
    )
    @Getter @Setter
    private String name;

    @OneToMany(mappedBy = "role")
    @Getter @Setter
    private Set<User>  users = new HashSet<>();

    @OneToMany(mappedBy = "role_id")
    @Getter @Setter
    private Set<Permission>  permissions= new HashSet<>();

    //<editor-fold desc="Getter+setter+equals+hashCode">
    public Role(Long id) {
        this.id = id;
    }
    public Role(String role) {
        this.name = role;
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
