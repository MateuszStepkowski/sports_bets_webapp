package pl.coderslab.sports_bets_webapp.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsSubscriber {

    JmsTemplate jmsTemplate;

    @Autowired
    public JmsSubscriber(JmsTemplate jmsTemplate) {
        jmsTemplate.setPubSubDomain(true);
        this.jmsTemplate = jmsTemplate;
    }

    public String receive(String destinationQueue){
        return (String)jmsTemplate.receiveAndConvert(destinationQueue);
    }
}