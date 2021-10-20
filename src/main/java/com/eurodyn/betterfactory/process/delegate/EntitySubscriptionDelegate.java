package com.eurodyn.betterfactory.process.delegate;

import com.eurodyn.betterfactory.process.constant.CommonProcessVariables;
import com.eurodyn.betterfactory.process.dto.*;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Component
public class EntitySubscriptionDelegate  implements JavaDelegate {

    @Value("${notificationUrl}")
    private String notifUrl;
//
//    @Value("${entityType}")
//    private List<String> entityTypes;
    @Value("${subscriptionUrl}")
    private String subUrl;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        List<String> entities = (List<String>) delegateExecution.getVariable("entitiesToSubscribe");
        for(String entityType:entities){
            subscribeEntity(entityType);
        }
    }

    private void subscribeEntity(String entityType) {
        try {
            SubscriptionDTO subscription = new SubscriptionDTO();
            SubjectDTO subj = new SubjectDTO();
            List<EntityDTO> entities = new ArrayList<EntityDTO>();
            EntityDTO entitydto = new EntityDTO();
            entitydto.setIdPattern(CommonProcessVariables.ASTRIK);
            entitydto.setType(entityType);
            entities.add(entitydto);
            subscription.setSubject(subj);
            subj.setEntities(entities);
            UrlDTO urldto = new UrlDTO();
            urldto.setUrl(notifUrl);
            NotificationDTO notificationDto = new NotificationDTO();
            notificationDto.setHttp(urldto);
            subscription.setDescription(CommonProcessVariables.DESC);
            subscription.setNotification(notificationDto);
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<SubscriptionDTO> httpEntity = new HttpEntity<>(subscription);
            ResponseEntity<String> response = restTemplate.exchange(
                    URI.create(subUrl),
                    HttpMethod.POST,
                    httpEntity,
                    new ParameterizedTypeReference<String>() {
                    }
            );
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }
}
