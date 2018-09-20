package pl.coderslab.sports_bets_webapp.service.impl;

import org.springframework.stereotype.Service;
import pl.coderslab.sports_bets_webapp.service.DecimalToStringService;

import java.math.BigDecimal;

@Service
public class DecimalToStringServiceImpl implements DecimalToStringService {
    @Override
    public BigDecimal parse(String string) {

        string.replaceAll(",", ".");
        try {
            return new BigDecimal(string);
        }catch (NumberFormatException e){
            return null;
        }
    }
}
