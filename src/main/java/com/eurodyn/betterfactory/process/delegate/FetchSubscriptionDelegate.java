package com.eurodyn.betterfactory.process.delegate;

import com.eurodyn.betterfactory.process.constant.CommonProcessVariables;
import com.eurodyn.betterfactory.process.dto.EntityDTO;
import com.eurodyn.betterfactory.process.dto.SubscriptionDTO;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FetchSubscriptionDelegate implements JavaDelegate {
    public static int countRetry = 0;

    @Value("${subscriptionUrl}")
    private String subUrl;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        try {
                    RestTemplate restTemplate = new RestTemplate();
                    HttpHeaders headers = new HttpHeaders();
                    HttpEntity httpEntity = new HttpEntity(headers);
                    ResponseEntity<List<SubscriptionDTO>> response = restTemplate.exchange(
                            URI.create(subUrl),
                            HttpMethod.GET,
                            httpEntity,
                            new ParameterizedTypeReference<List<SubscriptionDTO>>() {
                            }
                    );

                    List<SubscriptionDTO> list = response.getBody();
                    List<EntityDTO> entities = list.stream().flatMap(e -> e.getSubject().getEntities().stream())
                            .collect(Collectors.toList());

                    delegateExecution.setVariable("entities", entities);
        }
        catch (Exception ex){
            countRetry++;
            if(countRetry<3){
                delegateExecution.setVariable("retry", CommonProcessVariables.NO);
            }
            else{
                delegateExecution.setVariable("retry", CommonProcessVariables.YES);

            }
            throw new org.camunda.bpm.engine.delegate.BpmnError("Fetch-Subscription-Error",ex.getMessage());


        }
    }
}


