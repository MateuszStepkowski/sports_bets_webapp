package pl.coderslab.sports_bets_webapp.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class JmsSubscriber {

    JmsTemplate jmsTemplate;

    @Autowired
    public JmsSubscriber(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public String receive(String destinationQueue){
        return (String)jmsTemplate.receiveAndConvert(destinationQueue);
    }
}