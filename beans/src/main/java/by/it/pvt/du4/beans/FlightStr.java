package by.it.pvt.du4.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightStr implements Serializable {
    private Integer id = 0;
    private String flightCode;
    private String company;
    private Timestamp departure_time;
    private Timestamp arrival_time;
    private String plane;
    private String to;
    private String from;
    private String crew;
    private String user;
    private Timestamp createDate;


}
