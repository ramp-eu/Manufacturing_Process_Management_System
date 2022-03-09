package tue.shop4cf.integration.ngsild.repository;

import tue.shop4cf.integration.ngsild.dto.NgsildEntityDTO;
import tue.shop4cf.integration.ngsild.dto.TaskDTO;

import java.util.List;
import java.util.Optional;

public interface NgsildEntityRepository<M extends NgsildEntityDTO>   {

    public void save(M m);
    Optional<M> findById(String id);
    public List<M> findAllByType();
    void delete(String id);
    public void update(String id,M m);

}
