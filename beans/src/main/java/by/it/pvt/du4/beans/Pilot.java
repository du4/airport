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

@Entity @Table
public class Pilot implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private Date birthDay;

    public Pilot(String name, String phoneNumber, Date birthDay) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthDay = birthDay;
    }
}
