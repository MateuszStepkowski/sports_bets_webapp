package pl.coderslab.sports_bets_webapp.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Sport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String name;

    @NotNull
    private int game_duration;

    @OneToMany(mappedBy = "sport")
    List<Team> teams = new ArrayList<>();

    @OneToMany(mappedBy = "sport")
    List<League> leagues = new ArrayList<>();

    @NotNull
    private int points_value;



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

    public List<Team> getTeams() {
        return teams;
    }

    public int getGame_duration() {
        return game_duration;
    }

    public void setGame_duration(int game_duration) {
        this.game_duration = game_duration;
    }

    public int getPoints_value() {
        return points_value;
    }

    public void setPoints_value(int points_value) {
        this.points_value = points_value;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<League> getLeagues() {
        return leagues;
    }

    public void setLeagues(List<League> leagues) {
        this.leagues = leagues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sport sport = (Sport) o;
        return id == sport.id &&
                Objects.equals(name, sport.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Sport{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
