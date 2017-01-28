package by.it.pvt.du4.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data

@NoArgsConstructor
@AllArgsConstructor

@Entity @Table
public class Airhostess implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id=0;
    @Column(unique = true, nullable = false)
    private String name;
    @Column
    private Timestamp birthDay;

}
