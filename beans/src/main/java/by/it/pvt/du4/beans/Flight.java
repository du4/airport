package by.it.pvt.du4.beans;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor

@Entity @Table
public class Flight implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FLIGHT_ID")
    @Getter @Setter
    private Long id;

    @Column(unique = true, nullable = false)
    @Getter @Setter
    private String flightCode;

    @Column(unique = true, nullable = false)
    @Getter @Setter
    private String company;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @Getter @Setter
    private Date departure_time;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @Getter @Setter
    private Date arrival_time;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_PLANE")
    @Getter @Setter
    private Plane plane_id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_TO")
    @Getter @Setter
    private Airport to_id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_FROM")
    @Getter @Setter
    private Airport from_id;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "FK_CREW")
//    private Crew crew_id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_USER")
    @Getter @Setter
    private User user_id;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @Getter @Setter
    private Date createDate;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight)) return false;

        Flight flight = (Flight) o;

        if (id != null ? !id.equals(flight.id) : flight.id != null) return false;
        if (flightCode != null ? !flightCode.equals(flight.flightCode) : flight.flightCode != null) return false;
        if (company != null ? !company.equals(flight.company) : flight.company != null) return false;
        if (departure_time != null ? !departure_time.equals(flight.departure_time) : flight.departure_time != null)
            return false;
        if (arrival_time != null ? !arrival_time.equals(flight.arrival_time) : flight.arrival_time != null)
            return false;
        if (plane_id != null ? !plane_id.equals(flight.plane_id) : flight.plane_id != null) return false;
        if (to_id != null ? !to_id.equals(flight.to_id) : flight.to_id != null) return false;
        if (from_id != null ? !from_id.equals(flight.from_id) : flight.from_id != null) return false;
        if (user_id != null ? !user_id.equals(flight.user_id) : flight.user_id != null) return false;
        return createDate != null ? createDate.equals(flight.createDate) : flight.createDate == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (flightCode != null ? flightCode.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (departure_time != null ? departure_time.hashCode() : 0);
        result = 31 * result + (arrival_time != null ? arrival_time.hashCode() : 0);
        result = 31 * result + (plane_id != null ? plane_id.hashCode() : 0);
        result = 31 * result + (to_id != null ? to_id.hashCode() : 0);
        result = 31 * result + (from_id != null ? from_id.hashCode() : 0);
        result = 31 * result + (user_id != null ? user_id.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }
}
