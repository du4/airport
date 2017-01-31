package by.it.pvt.du4.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_PLANE")
    private Plane plane_id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_TO")
    private Airport to_id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_FROM")
    private Airport from_id;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "FK_CREW")
//    private Crew crew_id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_USER")
    private User user_id;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

}
