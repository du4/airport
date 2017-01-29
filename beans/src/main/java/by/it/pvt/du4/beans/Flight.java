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
public class Flight implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    private String flightCode;
    @Column(unique = true, nullable = false)
    private String company;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date departure_time;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date arrival_time;
    @Column
    private Long plane_id;
    @Column
    private Long to_id;
    @Column
    private Long from_id;
    @Column
    private Long crew_id;
    @Column
    private Long user_id;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

}
