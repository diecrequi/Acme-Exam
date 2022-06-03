package acme.features.inventor.patronageReport;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronage.Patronage;
import acme.entities.patronage.PatronageReport;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorPatronageReportRepository extends AbstractRepository {

	@Query("select pr from PatronageReport pr where pr.id = :id")
	PatronageReport findOneUserPatronageReportById(int id);

	@Query("select pr from PatronageReport pr where pr.patronage.id = :id")
	Collection<PatronageReport> findAllPatronageReportByPatronageId(int id);
	
	@Query("select i from Patronage i where i.id = :id")
	Patronage findOnePatronageById(int id);

}
