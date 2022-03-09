package tue.shop4cf.integration.persistence.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tue.shop4cf.integration.persistence.app.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {
}
