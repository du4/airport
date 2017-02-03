package by.it.pvt.du4.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@DiscriminatorValue("pilot")
@Entity @Table
public class Pilot extends Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column
    private String rank;

    public Pilot(Long id, String name, String phoneNumber, Date hired, Date fired, String rank, Set<Flight> flights) {
        super(id, name, phoneNumber, hired, fired, flights);
        this.rank = rank;
    }
}
