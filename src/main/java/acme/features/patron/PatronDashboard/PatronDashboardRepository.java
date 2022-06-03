package acme.features.patron.PatronDashboard;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.datatypes.StatusType;
import acme.entities.patronage.Patronage;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronDashboardRepository extends AbstractRepository {

	@Query("select p from Patronage p where  p.status=:status")
	Collection<Patronage> findPatronage(StatusType status);
}
