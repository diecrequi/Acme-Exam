package acme.features.inventor.patronage;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronage.Patronage;
import acme.framework.repositories.AbstractRepository;
import acme.systemSetting.SystemSetting;

@Repository
public interface PatronageRepositoryInv extends AbstractRepository{
	
	@Query("select a from Patronage a where a.id = :id")
	Patronage findOnePatronageById(int id);

	@Query("select a from Patronage a")
	Collection<Patronage> findManyPatronage();
	
	@Query("select s from SystemSetting s")
	SystemSetting findSystemSetting();
	@Query("select a from Patronage a where a.inventor.id = :i")
	Collection<Patronage> findManyPatronageByInv(Integer i);
}
