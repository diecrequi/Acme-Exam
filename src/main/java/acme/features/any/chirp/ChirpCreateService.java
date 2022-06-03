/*
 * AnonymousChirpCreateService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.any.chirp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chirp.Chirp;
import acme.features.administrator.systemSetting.AdministratorSystemSettingRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractCreateService;
import acme.systemSetting.SpamValidator;

@Service
public class ChirpCreateService implements AbstractCreateService<Any, Chirp> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChirpRepository repository;
	
	@Autowired
	protected AdministratorSystemSettingRepository strepo;
	

	// AbstractCreateService<Administrator, Chirp> interface --------------


	@Override
	public boolean authorise(final Request<Chirp> request) {
		assert request != null;

		return true;
	}

	@Override
	public Chirp instantiate(final Request<Chirp> request) {
		assert request != null;

		Chirp result;

		result = new Chirp();

		return result;
	}

	@Override
	public void bind(final Request<Chirp> request, final Chirp entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		request.bind(entity, errors, "title", "author", "body", "email");
		entity.setCreationMoment(moment);
	}

	@Override
	public void validate(final Request<Chirp> request, final Chirp entity, final Errors errors) {
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
		
		
		if(!errors.hasErrors("author")){
			errors.state(request, SpamValidator.validate(entity.getAuthor(), this.strepo.findWeakSpamThreshold()
				,this.strepo.findStrongSpamThreshold() ,this.strepo.findWeakSpam() , this.strepo.findStrongSpam()), "title","form.error.spam");
		}
		
		
		
		final boolean spam= request.getModel().getBoolean("checkbox");
		errors.state(request, spam, "checkbox", "any.chirp.confirmation.error");

	}

	@Override
	public void unbind(final Request<Chirp> request, final Chirp entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "author", "body", "email");
		model.setAttribute("isNew", true);
		model.setAttribute("confirmation", false);
		
	}

	@Override
	public void create(final Request<Chirp> request, final Chirp entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
