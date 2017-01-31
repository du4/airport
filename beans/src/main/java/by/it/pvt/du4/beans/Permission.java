package by.it.pvt.du4.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity @Table
public class Permission implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PERMISSION_ID")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_ROLE")
    private Role role_id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_COMMAND")
    private Command command_id;

    @Column
    private Boolean permission;


}
