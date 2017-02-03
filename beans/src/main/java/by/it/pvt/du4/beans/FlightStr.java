package by.it.pvt.du4.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class FlightStr {
    private Long id;
    private String flightCode;
    private String company;
    private Date departure_time;
    private Date arrival_time;
    private String plane;
    private String to;
    private String from;
    private String crew;
    private String user;
    private Date created_date;


    @Override
    public String toString() {
        return "FlightStr{" +
                "id=" + id +
                ", flightCode='" + flightCode + '\'' +
                ", company='" + company + '\'' +
                ", departure_time=" + departure_time +
                ", arrival_time=" + arrival_time +
                ", plane='" + plane + '\'' +
                ", to='" + to + '\'' +
                ", from='" + from + '\'' +
                ", user='" + user + '\'' +
                ", created_date=" + created_date +
                '}';
    }
}
