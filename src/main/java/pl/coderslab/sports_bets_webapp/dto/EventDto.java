package pl.coderslab.sports_bets_webapp.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventDto {

    @NotNull
    @JsonProperty("startDate")
    private LocalDateTime startDate;

    @NotBlank
    @JsonProperty("sport")
    private String sport;

    @NotBlank
    @JsonProperty("country")
    private String country;

    @NotBlank
    @JsonProperty("league")
    private String league;

    @NotBlank
    @JsonProperty("teamA")
    private String teamA;

    @NotBlank
    @JsonProperty("teamB")
    private String teamB;

    @NotNull
    @JsonProperty("teamA_pts")
    private int teamA_pts;

    @NotNull
    @JsonProperty("teamB_pts")
    private int teamB_pts;

    @JsonProperty("endDate")
    private LocalDateTime endDate;


    public EventDto() {
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getTeamA() {
        return teamA;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public String getTeamB() {
        return teamB;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
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

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "EventDto{" +
                "startDate=" + startDate +
                ", sport='" + sport + '\'' +
                ", country='" + country + '\'' +
                ", league='" + league + '\'' +
                ", teamA='" + teamA + '\'' +
                ", teamB='" + teamB + '\'' +
                ", teamA_pts=" + teamA_pts +
                ", teamB_pts=" + teamB_pts +
                ", endDate=" + endDate +
                '}';
    }
}
