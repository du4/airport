package by.it.pvt.du4.beans;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity @Table
public class Crew implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CREW_ID")
    private Long id;

    @ElementCollection
    @CollectionTable(name="CREW_MEMBERS", joinColumns=@JoinColumn(name="EMPLOYE_ID"))
    @Column(name="MEMBERS")
    private Set <Employee> crew = new HashSet<>();

    @Column(name = "FK_FLIGHT")
    private Long flight;

    public Set<Employee> getCrew() {
        return crew;
    }

    public void setCrew(Set<Employee> crew) {
        this.crew = crew;
    }

    public Long getFlight() {
        return flight;
    }

    public void setFlight(Long flight) {
        this.flight = flight;
    }
}
