package by.it.pvt.du4.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor

public class FlightStr {
    @Getter    @Setter
    private Long id;
    @Getter @Setter
    private String flightCode;
    @Getter @Setter
    private String company;
    @Getter @Setter
    private Date departure_time;
    @Getter @Setter
    private Date arrival_time;
    @Getter @Setter
    private String plane;
    @Getter @Setter
    private String to;
    @Getter @Setter
    private String from;
    @Getter @Setter
    private String user;
    @Getter @Setter
    private Date created_date;
    @Getter @Setter
    private Set<Employee> crew = new HashSet<>(0);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlightStr)) return false;

        FlightStr flightStr = (FlightStr) o;

        if (id != null ? !id.equals(flightStr.id) : flightStr.id != null) return false;
        if (flightCode != null ? !flightCode.equals(flightStr.flightCode) : flightStr.flightCode != null) return false;
        if (company != null ? !company.equals(flightStr.company) : flightStr.company != null) return false;
        if (departure_time != null ? !departure_time.equals(flightStr.departure_time) : flightStr.departure_time != null)
            return false;
        if (arrival_time != null ? !arrival_time.equals(flightStr.arrival_time) : flightStr.arrival_time != null)
            return false;
        if (plane != null ? !plane.equals(flightStr.plane) : flightStr.plane != null) return false;
        if (to != null ? !to.equals(flightStr.to) : flightStr.to != null) return false;
        if (from != null ? !from.equals(flightStr.from) : flightStr.from != null) return false;
        if (user != null ? !user.equals(flightStr.user) : flightStr.user != null) return false;
        return created_date != null ? created_date.equals(flightStr.created_date) : flightStr.created_date == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (flightCode != null ? flightCode.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (departure_time != null ? departure_time.hashCode() : 0);
        result = 31 * result + (arrival_time != null ? arrival_time.hashCode() : 0);
        result = 31 * result + (plane != null ? plane.hashCode() : 0);
        result = 31 * result + (to != null ? to.hashCode() : 0);
        result = 31 * result + (from != null ? from.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (created_date != null ? created_date.hashCode() : 0);
        return result;
    }

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
