package acme.features.any.toolKit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.artifact.ToolKit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface anyToolkitRepository extends AbstractRepository {

	@Query("select t from ToolKit t where t.id = :id")
	ToolKit findOneToolKitById(int id);

	@Query("select t from ToolKit t")
	Collection<ToolKit> findManyToolKits();

//	@Query("select a from ToolKit a where a.flag = true")
//	Collection<Announcement> findManyAnnouncement();
	

}
