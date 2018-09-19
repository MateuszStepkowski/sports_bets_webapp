package pl.coderslab.sports_bets_webapp.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private Timestamp startDate;

    @NotNull
    @ManyToOne
    private League league;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    private Team teamA;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    private Team teamB;

    @OneToMany(mappedBy = "event")
    private List<Bet> bets = new ArrayList<>();


    private int teamA_pts=0;

    private int teamB_pts=0;


    private Timestamp endDate;

    private int live_duration_time = 0;


    public Event() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLive_duration_time() {
        return live_duration_time;
    }

    public void setLive_duration_time(int live_duration_time) {
        this.live_duration_time = live_duration_time;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Team getTeamA() {
        return teamA;
    }

    public void setTeamA(Team teamA) {
        this.teamA = teamA;
    }

    public Team getTeamB() {
        return teamB;
    }

    public void setTeamB(Team teamB) {
        this.teamB = teamB;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }

    public int getTeamA_pts() {
        return teamA_pts;
    }

    public void setTeamA_pts(int teamA_pts) {
        this.teamA_pts = teamA_pts;
    }

    public int getTeamB_pts() {
        return teamB_pts;
    }

    public void setTeamB_pts(int teamB_pts) {
        this.teamB_pts = teamB_pts;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return getId() == event.getId() &&
                Objects.equals(getStartDate(), event.getStartDate()) &&
                Objects.equals(getLeague(), event.getLeague()) &&
                Objects.equals(getTeamA(), event.getTeamA()) &&
                Objects.equals(getTeamB(), event.getTeamB());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getStartDate(), getLeague(), getTeamA(), getTeamB());
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", league=" + league +
                ", teamA=" + teamA +
                ", teamB=" + teamB +
                ", teamApts=" + teamA_pts +
                ", teamBpts=" + teamB_pts +
                ", endDate=" + endDate +
                '}';
    }
}
