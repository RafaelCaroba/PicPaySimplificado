package com.picpaysimplificado.picpaysimplificado.services;

import com.picpaysimplificado.picpaysimplificado.dtos.AuthorizationDTO;
import com.picpaysimplificado.picpaysimplificado.model.exception.TransactionAuthorizationException;
import com.picpaysimplificado.picpaysimplificado.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;


@Service
public class AuhtorizationService {

    @Autowired
    private RestTemplate restTemplate;



    public boolean authorizeTransaction(User sender, BigDecimal value) {
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity(
                "https://util.devi.tools/api/v2/authorize", Map.class);

        if (authorizationResponse.getStatusCode() == HttpStatus.OK) {
            if (authorizationResponse.getBody().get("status").toString().equals("success")) return true;
        }
        return false;
    }

}
