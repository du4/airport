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
public class Crew implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id=0;
    @Column
    private Integer pilot1_id;
    @Column
    private Integer pilot2_id;
    @Column
    private Integer airhostess1_id;
    @Column
    private Integer airhostess2_id;

}
