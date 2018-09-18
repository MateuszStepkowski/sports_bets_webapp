package pl.coderslab.sports_bets_webapp.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String name;

    @ManyToOne
    private Country country;

    @OneToMany(mappedBy = "league")
    List<Event> events = new ArrayList<>();

    @ManyToMany
    List<Team> teams = new ArrayList<>();

    @NotNull
    @ManyToOne
    Sport sport;


    public League() {
    }

    public League(@NotBlank String name, Country country, @NotNull Sport sport) {
        this.name = name;
        this.country = country;
        this.sport = sport;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        League league = (League) o;
        return getId() == league.getId() &&
                Objects.equals(getName(), league.getName()) &&
                Objects.equals(getCountry(), league.getCountry()) &&
                Objects.equals(getSport(), league.getSport());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName(), getCountry(), getSport());
    }

    @Override
    public String toString() {
        return "League{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country=" + country +
                ", sport=" + sport +
                '}';
    }
}
