package pl.coderslab.sports_bets_webapp.model;

import pl.coderslab.sports_bets_webapp.entity.Bet;
import pl.coderslab.sports_bets_webapp.entity.Event;

public class EventForViewModel {

    private Event event;

    private Bet[] bets = new Bet[3];

    public EventForViewModel(Event event, Bet[] bets) {
        this.event = event;
        this.bets = bets;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Bet[] getBets() {
        return bets;
    }

    public void setBets(Bet[] bets) {
        this.bets = bets;
    }
}
