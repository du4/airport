package by.it.pvt.du4.beans;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;


@NoArgsConstructor

@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@DiscriminatorValue("airhostess")
@Entity @Table
public class Airhostess extends Employee implements Serializable{

    @Column
    @Temporal(TemporalType.DATE)
    @Getter
    @Setter
    private Date birthDay;

    public Airhostess(Long id, String name, String phoneNumber, Date hired, Date fired, Date birthDay, Set<Flight> flights) {
        super(id, name, phoneNumber, hired, fired, flights);
        this.birthDay = birthDay;
    }
}
