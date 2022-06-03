package acme.features.authenticated.announcements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.announcement.Announcement;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class authenticatedAnnouncementsShowService implements AbstractShowService<Authenticated, Announcement> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected authenticatedAnnouncementsRepository repository;

	// AbstractShowService<Anonymous, Announcement> interface --------------------------

	@Override
	public boolean authorise(final Request<Announcement> request) {
		assert request != null;

		boolean result;
		int id;
		Announcement Announcement;

		id = request.getModel().getInteger("id");
		Announcement = this.repository.findOneAnnouncementById(id);
		result = Announcement.isFlag();

		return result;
	}

	@Override
	public void unbind(final Request<Announcement> request, final Announcement entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationMoment", "body", "flag", "link");
	}

	@Override
	public Announcement findOne(final Request<Announcement> request) {
		assert request != null;

		Announcement result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneAnnouncementById(id);

		return result;
	}

}
