package by.it.pvt.du4.beans;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Entity @Table
public class Plane implements Serializable {
    private static final Logger LOG = LoggerFactory.getLogger(Plane.class);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PLANE_ID")
    @Getter
    @Setter
    private Long id;

    @Column(
            unique = true,
            nullable = false
    )
    @Getter @Setter
    private String planeName;

    @OneToMany(mappedBy = "plane_id")
    @Getter @Setter
    private Set<Flight> flights= new HashSet<>();

    public Plane(String planeName) {
        this.planeName = planeName;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "id=" + id +
                ", planeName='" + planeName + '\'' +
                '}';
    }

    public static Plane getInstance() {
        LOG.info("public static Plane getInstance()");
        return new Plane("somethingFromGetInstanceFormPlane");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plane)) return false;

        Plane plane = (Plane) o;

        if (id != null ? !id.equals(plane.id) : plane.id != null) return false;
        return planeName != null ? planeName.equals(plane.planeName) : plane.planeName == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (planeName != null ? planeName.hashCode() : 0);
        return result;
    }

    public void init() {
        LOG.info("init of plane");
    }

    public void destroy() {
        LOG.info("destroy of plane");
        this.id = null;
        this.planeName = null;
        this.flights = null;
    }
}
