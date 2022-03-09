package tue.shop4cf.integration.ngsild.service;

import org.springframework.stereotype.Service;
import tue.shop4cf.integration.ngsild.dto.AssetDTO;
import tue.shop4cf.integration.rest.CBRESTTemplate;
import tue.shop4cf.integration.ngsild.dto.TaskDTO;

@Service
public class CBOperationsService {

    private final CBRESTTemplate orionLDCBRESTTemplate;

    public CBOperationsService(CBRESTTemplate orionLDCBRESTTemplate) {
        this.orionLDCBRESTTemplate = orionLDCBRESTTemplate;
    }

    public void postAsset(AssetDTO asset) {
        orionLDCBRESTTemplate.postAsset(asset);
    }

    public void postTask(TaskDTO task) {
        orionLDCBRESTTemplate.postTask(task);
    }


    public Object getVersion() {
        return orionLDCBRESTTemplate.getVersion();
    }

}
