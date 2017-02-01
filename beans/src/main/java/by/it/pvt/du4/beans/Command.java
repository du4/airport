package by.it.pvt.du4.beans;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity @Table
public class Command implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COMMAND_ID", unique = true)
    @Getter
    @Setter
    private Long id;

    @Column(
            unique = true,
            nullable = false
    )
//    @Basic(fetch = FetchType.EAGER)
    @Getter @Setter
    private String name;

    @OneToMany(mappedBy = "command_id")
    @Getter @Setter
    private Set<Permission> permissions = new HashSet<>();

    public Command(String name) {
        this.name = name;
    }
}
