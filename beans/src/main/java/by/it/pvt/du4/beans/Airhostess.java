package by.it.pvt.du4.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data

@NoArgsConstructor
@DiscriminatorValue("airhostess")
@Entity @Table
public class Airhostess extends Employee implements Serializable{

    @Column
    @Temporal(TemporalType.DATE)
    private Date birthDay;

    public Airhostess(Long id, String name, String phoneNumber, Date hired, Date fired, Date birthDay) {
        super(id, name, phoneNumber, hired, fired);
        this.birthDay = birthDay;
    }
}
