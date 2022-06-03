package acme.features.administrator.announcements;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.announcement.Announcement;
import acme.features.administrator.systemSetting.AdministratorSystemSettingRepository;
import acme.features.authenticated.announcements.authenticatedAnnouncementsRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractCreateService;
import acme.systemSetting.SpamValidator;

@Service
public class AdministratorAnnouncementsCreateService implements AbstractCreateService<Administrator, Announcement> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected authenticatedAnnouncementsRepository repository;
	
	@Autowired
	protected AdministratorSystemSettingRepository strepo;
	

	// AbstractCreateService<Administrator, Chirp> interface --------------


	@Override
	public boolean authorise(final Request<Announcement> request) {
		assert request != null;

		return true;
	}

	@Override
	public Announcement instantiate(final Request<Announcement> request) {
		assert request != null;

		final Announcement result = new Announcement();

		return result;
	}

	@Override
	public void bind(final Request<Announcement> request, final Announcement entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		final Date moment= new Date(System.currentTimeMillis() - 1);
    
		request.bind(entity, errors, "title", "body", "link");
		entity.setCreationMoment(moment);
		entity.setFlag(true);
	}

	@Override
	public void validate(final Request<Announcement> request, final Announcement entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("title")){
			errors.state(request, SpamValidator.validate(entity.getTitle(), this.strepo.findWeakSpamThreshold()
				,this.strepo.findStrongSpamThreshold() ,this.strepo.findWeakSpam() , this.strepo.findStrongSpam()), "title","form.error.spam");
		}
		
		if(!errors.hasErrors("body")){
			errors.state(request, SpamValidator.validate(entity.getBody(), this.strepo.findWeakSpamThreshold()
				,this.strepo.findStrongSpamThreshold() ,this.strepo.findWeakSpam() , this.strepo.findStrongSpam()), "title","form.error.spam");
		}
		

		final boolean spam= request.getModel().getBoolean("checkbox");
		errors.state(request, spam, "checkbox", "administrator.announcement.confirmation.error");
		

	}

	@Override
	public void unbind(final Request<Announcement> request, final Announcement entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "body", "link");
		model.setAttribute("isNew", true);
	}

	@Override
	public void create(final Request<Announcement> request, final Announcement entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
