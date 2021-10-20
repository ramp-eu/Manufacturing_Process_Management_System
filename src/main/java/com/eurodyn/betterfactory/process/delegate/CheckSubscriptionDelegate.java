package com.eurodyn.betterfactory.process.delegate;

import com.eurodyn.betterfactory.process.constant.CommonProcessVariables;
import com.eurodyn.betterfactory.process.dto.EntityDTO;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CheckSubscriptionDelegate  implements JavaDelegate {

    @Value("${entityType}")
    private List<String> entityTypes;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<EntityDTO> entities = (List<EntityDTO>) delegateExecution.getVariable("entities");

        List<String> subscribedEntities = entities.stream().filter(type -> null!=type.getType()).map(EntityDTO::getType).collect(Collectors.toList());
        List<String> entitiesToSubscribe = entityTypes.stream().filter(entity -> ! subscribedEntities.contains(entity)).collect(Collectors.toList());


        if(entitiesToSubscribe != null && !entitiesToSubscribe.isEmpty()){
            delegateExecution.setVariable(  "entity_subscribed", CommonProcessVariables.NO);
            delegateExecution.setVariable(  "entitiesToSubscribe", entitiesToSubscribe);
        }
        else{
            delegateExecution.setVariable("entity_subscribed", CommonProcessVariables.YES);
        }

    }
}
