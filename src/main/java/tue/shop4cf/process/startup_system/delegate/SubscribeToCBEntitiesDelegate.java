package tue.shop4cf.process.startup_system.delegate;

import tue.shop4cf.integration.ngsild.dto.*;
import tue.shop4cf.process.startup_system.constants.ProcessVariables;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class SubscribeToCBEntitiesDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        final String orionld_url = delegateExecution.getVariable(ProcessVariables.ORIONLD_URL_SUBSCRIPTIONS).toString();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
//        headers.set("Link","<https://smartdatamodels.org/context.jsonld>; rel=\"http://www.w3.org/ns/json-ld#context\"; type=\"application/ld+json\"");
//        headers.set("Content-Type", "application/ld+json");
//        headers.set("Link","<https://raw.githubusercontent.com/shop4cf/data-models/master/docs/shop4cfcontext.jsonld>; rel=\"http://www.w3.org/ns/json-ld#context\"; type=\"application/ld+json\"");

//        List<Link> linkCollection = new ArrayList<>();
//        linkCollection.add(new Link("https://smartdatamodels.org/context.jsonld").withRel("http://www.w3.org/ns/json-ld#context").withType("application/ld+json"));
//        linkCollection.add(new Link("https://raw.githubusercontent.com/shop4cf/data-models/master/docs/shop4cfcontext.jsonld").withRel("http://www.w3.org/ns/json-ld#context").withType("application/ld+json"));
//
////        Links wrapperOfLinks = new Links(linkCollection);
//
//        headers.set("Link", linkCollection.toString());

        headers.set("Link","<https://smartdatamodels.org/context.jsonld>,<https://raw.githubusercontent.com/shop4cf/data-models/master/docs/shop4cfcontext.jsonld>; rel=\"http://www.w3.org/ns/json-ld#context\"; type=\"application/ld+json\"");

//      -----Alert entities-----
        BaseProperty alert_baseProperty = new BaseProperty();
        alert_baseProperty.setType("Alert");
        BaseProperty[] alert_baseProperties = new BaseProperty[]{alert_baseProperty};
        EndpointProperty alert_endpointProperty= new EndpointProperty();
        alert_endpointProperty.setUri(delegateExecution.getVariable(ProcessVariables.ALERT_ENDPOINT_URI).toString());
        alert_endpointProperty.setAccept("application/json");
        NotificationProperty alert_notificationProperty = new NotificationProperty();
        List<String> alert_attributes_lst = new ArrayList<String>();
//        alert_notificationProperty.setAttributes(alert_attributes_lst);
        alert_notificationProperty.setFormat("keyValues");
        alert_notificationProperty.setEndpointProperty(alert_endpointProperty);
        SubscriptionDTO alert_subscriptionDTO = SubscriptionDTO.builder().description(delegateExecution.getVariable(ProcessVariables.ALERT_SUBSCRIPTION_DESCRIPTION).toString())
                .id("urn:ngsi-ld:Subscription:" + delegateExecution.getVariable(ProcessVariables.ALERT_SUBSCRIPTION_IDENTIFIER).toString())
                .type("Subscription")
                .entities(alert_baseProperties)
                .notificationProperty(alert_notificationProperty)
                .build();
        log.info("Alert subscription: "+ alert_subscriptionDTO.toString());
        HttpEntity<SubscriptionDTO> alert_httpEntity = new HttpEntity(alert_subscriptionDTO,headers);
        ResponseEntity<String> alert_response = restTemplate.exchange(orionld_url, HttpMethod.POST, alert_httpEntity, new ParameterizedTypeReference<String>() {});



        //      -----Task entities-----
        BaseProperty task_baseProperty = new BaseProperty();
        task_baseProperty.setType("Task");
        BaseProperty[] task_baseProperties = new BaseProperty[]{task_baseProperty};
        EndpointProperty task_endpointProperty= new EndpointProperty();
        task_endpointProperty.setUri(delegateExecution.getVariable(ProcessVariables.TASK_ENDPOINT_URI).toString());
        task_endpointProperty.setAccept("application/json");
        NotificationProperty task_notificationProperty = new NotificationProperty();
        List<String> task_attributes_lst = new ArrayList<String>();
//        task_notificationProperty.setAttributes(task_attributes_lst);
        task_notificationProperty.setFormat("normalized");
        task_notificationProperty.setEndpointProperty(task_endpointProperty);
        SubscriptionDTO task_subscriptionDTO = SubscriptionDTO.builder().description(delegateExecution.getVariable(ProcessVariables.TASK_SUBSCRIPTION_DESCRIPTION).toString())
                .id("urn:ngsi-ld:Subscription:" + delegateExecution.getVariable(ProcessVariables.TASK_SUBSCRIPTION_IDENTIFIER).toString())
                .type("Subscription")
                .entities(task_baseProperties)
                .notificationProperty(task_notificationProperty)
                .build();
        log.info("Task subscription: "+ task_subscriptionDTO.toString());
//        HttpEntity<SubscriptionDTO> task_httpEntity = new HttpEntity(task_subscriptionDTO,headers);
//        ResponseEntity<String> task_response = restTemplate.exchange(orionld_url, HttpMethod.POST, task_httpEntity, new ParameterizedTypeReference<String>() {});


        //      -----Task status changes-----
        BaseProperty taskstatus_baseProperty = new BaseProperty();
        taskstatus_baseProperty.setType("Task");
        BaseProperty[] taskstatus_baseProperties = new BaseProperty[]{taskstatus_baseProperty};
        List<String> taskstatus_subscr_watched_attributes_lst = new ArrayList<String>();
        taskstatus_subscr_watched_attributes_lst.add("status");
        EndpointProperty taskstatus_endpointProperty= new EndpointProperty();
        taskstatus_endpointProperty.setUri(delegateExecution.getVariable(ProcessVariables.TASK_STATUS_ENDPOINT_URI).toString());
        taskstatus_endpointProperty.setAccept("application/json");
        NotificationProperty taskstatus_notificationProperty = new NotificationProperty();
        List<String> taskstatus_attributes_lst = new ArrayList<String>();
//        taskstatus_notificationProperty.setAttributes(taskstatus_attributes_lst);
        taskstatus_notificationProperty.setFormat("normalized");
        taskstatus_notificationProperty.setEndpointProperty(taskstatus_endpointProperty);
        SubscriptionWADTO taskstatus_subscriptionDTO = SubscriptionWADTO.builder().description(delegateExecution.getVariable(ProcessVariables.TASK_STATUS_SUBSCRIPTION_DESCRIPTION).toString())
                .id("urn:ngsi-ld:Subscription:" + delegateExecution.getVariable(ProcessVariables.TASK_STATUS_SUBSCRIPTION_IDENTIFIER).toString())
                .type("Subscription")
                .entities(taskstatus_baseProperties)
                .watchedAttributes(taskstatus_subscr_watched_attributes_lst)
//                .query("")
                .notificationProperty(taskstatus_notificationProperty)

                .build();
        log.info("Task status subscription: "+ taskstatus_subscriptionDTO.toString());
        HttpEntity<SubscriptionWADTO> taskstatus_httpEntity = new HttpEntity(taskstatus_subscriptionDTO,headers);
        ResponseEntity<String> taskstatus_response = restTemplate.exchange(orionld_url, HttpMethod.POST, taskstatus_httpEntity, new ParameterizedTypeReference<String>() {});

        //      -----Resource-----

    }
}
