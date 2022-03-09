package tue.shop4cf.integration.persistence.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tue.shop4cf.integration.persistence.app.model.TaskDef;

@Repository
public interface TaskDefRepository extends JpaRepository<TaskDef,Long> {
}
