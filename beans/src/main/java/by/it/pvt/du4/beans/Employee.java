package by.it.pvt.du4.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "EMPLOYEE_TYPE",
        discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("employee")
@Entity @Table
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EMPLOYE_ID")
    private Long id;
    @Column(
            unique = true,
            nullable = false
    )
    private String name;

    @Column
    private String phoneNumber;

    @Column
    @Temporal(TemporalType.DATE)
    private Date hired;

    @Column
    @Temporal(TemporalType.DATE)
    private Date fired;


}
