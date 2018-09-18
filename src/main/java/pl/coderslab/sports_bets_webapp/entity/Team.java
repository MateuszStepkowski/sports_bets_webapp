package pl.coderslab.sports_bets_webapp.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String name;

    @ManyToOne
    private Sport sport;

    @ManyToMany(mappedBy = "teams")
    List<League> leagues = new ArrayList<>();

    @Size(min = 1, max = 100)
    private int offensiveRating=50;

    @Size(min = 1, max = 100)
    private int deffensiveRating=50;


    public Team() {
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

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public List<League> getLeagues() {
        return leagues;
    }

    public void setLeagues(List<League> leagues) {
        this.leagues = leagues;
    }

    public int getOffensiveRating() {
        return offensiveRating;
    }

    public void setOffensiveRating(int offensiveRating) {
        this.offensiveRating = offensiveRating;
    }

    public int getDeffensiveRating() {
        return deffensiveRating;
    }

    public void setDeffensiveRating(int deffensiveRating) {
        this.deffensiveRating = deffensiveRating;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return id == team.id &&
                Objects.equals(name, team.name) &&
                Objects.equals(sport, team.sport);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, sport);
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sport=" + sport +
                ", offensiveRating=" + offensiveRating +
                ", deffensiveRating=" + deffensiveRating +
                '}';
    }
}
