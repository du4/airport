package by.it.pvt.du4.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity @Table
public class Flight implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id = 0;
    @Column(unique = true, nullable = false)
    private String flightCode;
    @Column(unique = true, nullable = false)
    private String company;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp departure_time;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp arrival_time;
    @Column
    private Integer plane_id = 0;
    @Column
    private Integer to_id = 0;
    @Column
    private Integer from_id = 0;
    @Column
    private Integer crew_id = 0;
    @Column
    private Integer user_id = 0;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createDate;

}
