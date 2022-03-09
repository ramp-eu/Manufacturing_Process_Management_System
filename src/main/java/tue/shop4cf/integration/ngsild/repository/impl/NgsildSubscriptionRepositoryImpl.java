package tue.shop4cf.integration.ngsild.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tue.shop4cf.config.FiwareProperties;
import tue.shop4cf.integration.ngsild.dto.BaseProperty;
import tue.shop4cf.integration.ngsild.dto.EndpointProperty;
import tue.shop4cf.integration.ngsild.dto.NotificationProperty;
import tue.shop4cf.integration.ngsild.dto.SubscriptionDTO;
import tue.shop4cf.integration.ngsild.repository.NgsildSubscriptionRepository;

import java.util.List;
@Slf4j
@Repository
public class NgsildSubscriptionRepositoryImpl implements NgsildSubscriptionRepository<SubscriptionDTO> {

    @Autowired
    private FiwareProperties fiwareProperties;

    @Override
    public void subscribeEntity() {
        final String uri = fiwareProperties.getOrionldUrl()+"/ngsi-ld/v1/subscriptions";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
//headers.set("Link","<https://smartdatamodels.org/context.jsonld>; rel=\"http://www.w3.org/ns/json-ld#context\"; type=\"application/ld+json\"");
        headers.set(HttpHeaders.CONTENT_TYPE, "application/ld+json");
        BaseProperty baseProperty = new BaseProperty();
        baseProperty.setType("Alert");
        BaseProperty[] baseProperties = new BaseProperty[]{baseProperty};
        EndpointProperty endpointProperty= new EndpointProperty();
        endpointProperty.setUri(fiwareProperties.getSubscriptionUrl()+"/alertld");
        endpointProperty.setAccept("application/json");
        NotificationProperty notificationProperty = new NotificationProperty();
        ////notificationProperty.setFormat("keyValues");
        notificationProperty.setEndpointProperty(endpointProperty);
        String[] contextlist= new String[]{"https://smartdatamodels.org/context.jsonld"};
        SubscriptionDTO subscriptionDTO = SubscriptionDTO.builder().description("Notify MPMS for incoming alerts")
                .id("urn:ngsi-ld:Subscription:" + "mpms_alert_subscription")
                .type("Subscription")
                .entities(baseProperties)
                .notificationProperty(notificationProperty)
                .context(contextlist)
                .build();
        log.info("My sub: "+ subscriptionDTO.toString());
        HttpEntity<SubscriptionDTO> httpEntity = new HttpEntity(subscriptionDTO,headers);
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<String>() {});


        //      -----Task entities-----
        BaseProperty task_baseProperty = new BaseProperty();
        task_baseProperty.setType("Task");
        BaseProperty[] task_baseProperties = new BaseProperty[]{task_baseProperty};
        EndpointProperty task_endpointProperty= new EndpointProperty();
        task_endpointProperty.setUri(fiwareProperties.getSubscriptionUrl()+"/taskld");
        task_endpointProperty.setAccept("application/json");
        NotificationProperty task_notificationProperty = new NotificationProperty();
        String[] contextlistT= new String[]{"https://smartdatamodels.org/context.jsonld", "https://raw.githubusercontent.com/shop4cf/data-models/master/docs/shop4cfcontext.jsonld"};
        log.info("Context: "+contextlist.toString());
        task_notificationProperty.setFormat("normalized");
        task_notificationProperty.setEndpointProperty(task_endpointProperty);
        SubscriptionDTO task_subscriptionDTO = SubscriptionDTO.builder().description("Notify MPMS for incoming tasks")
                .id("urn:ngsi-ld:Subscription:" + "mpms_task_subscription")
                .type("Subscription")
                .entities(task_baseProperties)
                .notificationProperty(task_notificationProperty)
                .context(contextlistT)
                .build();
        log.info("Task subscription: "+ task_subscriptionDTO.toString());

//        HttpHeaders headersT = new HttpHeaders();
        HttpEntity<SubscriptionDTO> task_sub_httpEntity = new HttpEntity(task_subscriptionDTO,headers);
        ResponseEntity<String> task_response = restTemplate.exchange(uri, HttpMethod.POST, task_sub_httpEntity, new ParameterizedTypeReference<String>() {});
        log.info(task_response.toString());
        log.info("s");
    }

    @Override
    public List<SubscriptionDTO> fetchSubscription() {
        {
            final String uri = fiwareProperties.getOrionldUrl() + "/ngsi-ld/v1/subscriptions/";

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_TYPE, "application/ld+json");
            HttpEntity httpEntity = new HttpEntity(headers);

            ResponseEntity<List<SubscriptionDTO>> response = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    httpEntity,
                    new ParameterizedTypeReference<List<SubscriptionDTO>>() {
                    }
            );

            List<SubscriptionDTO> list = response.getBody();
            log.info("Subscriptions detected: "+list);
            return list;
        }
    }
}
