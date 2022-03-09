package tue.shop4cf.process.incoming_alert.delegate;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tue.shop4cf.config.FiwareProperties;
import tue.shop4cf.integration.ngsild.dto.AlertDTO;
import tue.shop4cf.integration.ngsild.service.NgsildAlertService;

@Slf4j
@Component
public class DeleteAlertDelegate implements JavaDelegate {
    @Autowired
    FiwareProperties fiwareProperties;
    @Autowired
    private NgsildAlertService ngsildAlertService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        AlertDTO alertDTO = objectMapper.readValue(delegateExecution.getVariable("IncomingAlert").toString(), AlertDTO.class);

        ngsildAlertService.delete(alertDTO.getId());
    }


}
