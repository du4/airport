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
public class Airport implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AIRPORT_ID")
    private Long id;
    @Column(unique = true, nullable = false)
    private String acronim;
    @Column(unique = true, nullable = false)
    private String name;

    public Airport(String acronim, String name) {
        this.acronim = acronim;
        this.name = name;
    }
}
