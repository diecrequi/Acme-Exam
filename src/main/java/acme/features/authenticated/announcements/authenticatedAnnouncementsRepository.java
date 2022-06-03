package acme.features.authenticated.announcements;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.announcement.Announcement;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface authenticatedAnnouncementsRepository extends AbstractRepository {

	@Query("select a from Announcement a where a.id = :id")
	Announcement findOneAnnouncementById(int id);

	@Query("select a from Announcement a where a.flag = true")
	Collection<Announcement> findManyAnnouncement();

}
