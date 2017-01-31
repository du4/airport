package by.it.pvt.du4.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity @Table
public class Command implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COMMAND_ID", unique = true)
    private Long id;
    @Column(
            unique = true,
            nullable = false
    )
    private String name;

    @OneToMany(mappedBy = "command_id")
    private Set<Permission> permissions = new HashSet<>();

    public Command(String name) {
        this.name = name;
    }
}
