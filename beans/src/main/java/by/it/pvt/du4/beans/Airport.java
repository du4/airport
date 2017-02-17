package by.it.pvt.du4.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
@AllArgsConstructor
@Cacheable
@Entity @Table
public class Airport implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AIRPORT_ID")
    @Getter @Setter
    private Long id;

    @Column(unique = true, nullable = false)
    @Getter @Setter
    private String acronim;

    @Column(unique = true, nullable = false)
    @Getter @Setter
    private String name;

    @OneToMany(mappedBy = "to_id")
    @Getter @Setter
    private Set<Flight> flightsTo= new HashSet<>();

    @OneToMany(mappedBy = "from_id")
    @Getter @Setter
    private Set<Flight> flightsFrom= new HashSet<>();

    public Airport() {
    }

    public Airport(String acronim, String name) {
        this.acronim = acronim;
        this.name = name;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airport)) return false;

        Airport airport = (Airport) o;

        if (id != null ? !id.equals(airport.id) : airport.id != null) return false;
        if (acronim != null ? !acronim.equals(airport.acronim) : airport.acronim != null) return false;
        return name != null ? name.equals(airport.name) : airport.name == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (acronim != null ? acronim.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
