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
    @Column(name = "FLIGHT_ID")
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
    @Column(name = "FK_PLANE")
    private Long plane_id;
    @Column(name = "FK_TO")
    private Long to_id;
    @Column(name = "FK_FROM")
    private Long from_id;
    @Column(name = "FK_CREW")
    private Long crew_id;
    @Column(name = "FK_USER")
    private Long user_id;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

}
