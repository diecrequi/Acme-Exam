package acme.features.inventor.newcal;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.artifact.Artifact;
import acme.artifact.ArtifactType;
import acme.entities.newcal.Newcal;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface NewcalRepository extends AbstractRepository{
	
	@Query("select c from Newcal c where c.id = :id")
	Newcal findOneNewcalById(int id);

	@Query("select c from Newcal c")
	Collection<Newcal> findManyNewcal();
	
	@Query("select a from Artifact a LEFT JOIN Newcal c ON c.component=a WHERE c IS NULL AND a.type=:type")
	List<Artifact> findArtifactList(ArtifactType type);
	
	@Query("select a from Artifact a where a.id = :id")
	Artifact findArtifactById(int id);
	
	@Query("select c from Newcal c where c.code = :code")
	Newcal findAnyNewcalByCode(String code);
	
}
