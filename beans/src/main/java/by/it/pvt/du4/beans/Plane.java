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
public class Plane implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PLANE_ID")
    private Long id;
    @Column(
            unique = true,
            nullable = false
    )
    private String planeName;

    public Plane(String planeName) {
        this.planeName = planeName;
    }
}
