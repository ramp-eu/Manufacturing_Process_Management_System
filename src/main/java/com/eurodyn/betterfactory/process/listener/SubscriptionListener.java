package com.eurodyn.betterfactory.process.listener;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SubscriptionListener {

    //public static int counter= 0;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService=processEngine.getRuntimeService();
        MessageCorrelationResult result = runtimeService.createMessageCorrelation("StartSubscriptionEntity")
                .correlateWithResult();
    }
}
