package acme.features.patron.patronage;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronage.Patronage;
import acme.entities.patronage.PatronageReport;
import acme.framework.repositories.AbstractRepository;
import acme.systemSetting.SystemSetting;
import acme.roles.Inventor;
import acme.roles.Patron;

@Repository
public interface PatronageRepository extends AbstractRepository{
	
	@Query("select a from Patronage a where a.id = :id")
	Patronage findOnePatronageById(int id);

	@Query("select a from Patronage a")
	Collection<Patronage> findManyPatronage();
	
	@Query("select s from SystemSetting s")
	SystemSetting findSystemSetting();

	@Query("select patron from Patron patron where patron.id = :id")
	Patron findPatronById(int id);
	
	@Query("select i from Inventor i")
	List<Inventor> findInvList();
	
	@Query("select i from Inventor i where i.id = :id")
	Inventor findInvById(int id);
	
	@Query("select i from PatronageReport i where i.patronage = :patronage")
	List<PatronageReport> findPR(Patronage patronage);

	@Query("select p from Patronage p where p.code = :code")
	Patronage findOnePatronageByCode(String code);
}
