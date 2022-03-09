package tue.shop4cf.integration.ngsild.service;

import org.springframework.stereotype.Service;
import tue.shop4cf.integration.ngsild.dto.NgsildEntityDTO;
@Service
public interface NgsildAlertService<M extends NgsildEntityDTO> {
    void delete(String id);
}
