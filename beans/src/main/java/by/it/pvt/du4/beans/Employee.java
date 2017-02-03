package by.it.pvt.du4.beans;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "EMPLOYEE_TYPE",
        discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("employee")
@Entity @Table
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EMPLOYE_ID")
    @Getter @Setter
    private Long id;

    @Column(
            unique = true,
            nullable = false
    )
    @Getter @Setter
    private String name;

    @Column
    @Getter @Setter
    private String phoneNumber;

    @Column
    @Getter @Setter
    @Temporal(TemporalType.DATE)
    private Date hired;

    @Column
    @Getter @Setter
    @Temporal(TemporalType.DATE)
    private Date fired;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "CREW", joinColumns = {@JoinColumn(name = "EMPLOYEE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "FLIGHT_ID")})
    @Getter @Setter
    private Set<Flight> flights = new HashSet<>(0);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        if (id != null ? !id.equals(employee.id) : employee.id != null) return false;
        if (name != null ? !name.equals(employee.name) : employee.name != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(employee.phoneNumber) : employee.phoneNumber != null)
            return false;
        if (hired != null ? !hired.equals(employee.hired) : employee.hired != null) return false;
        return fired != null ? fired.equals(employee.fired) : employee.fired == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (hired != null ? hired.hashCode() : 0);
        result = 31 * result + (fired != null ? fired.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", hired=" + hired +
                ", fired=" + fired +
                '}';
    }
}
