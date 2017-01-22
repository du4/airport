package by.it.pvt.du4.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Plane implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id = 0;
    @Column(
            unique = true,
            nullable = false
    )
    private String planeName;

}
