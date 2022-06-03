package acme.features.any.partOf;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.artifact.PartOf;
import acme.artifact.ToolKit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface anyPartOfRepository extends AbstractRepository {

	@Query("select t from ToolKit t where t.id = :id")
	ToolKit findOneToolKitById(int id);

	@Query("select t from ToolKit t")
	Collection<ToolKit> findManyToolKits();

	@Query("select p from PartOf p")
	Collection<PartOf> findManyPartOf();
	
	@Query("select p from PartOf p where p.id = :id")
	PartOf findOnePartOfById(int id);
	
	@Query("select p from PartOf p where p.toolkit.id = :masterId")
	Collection<PartOf> findManyPartOfByMasterId(int masterId);

}
