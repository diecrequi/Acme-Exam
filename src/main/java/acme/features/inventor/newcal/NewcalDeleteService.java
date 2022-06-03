package acme.features.inventor.newcal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.newcal.Newcal;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class NewcalDeleteService implements AbstractDeleteService<Inventor, Newcal> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected NewcalRepository repository;


	@Override
	public boolean authorise(final Request<Newcal> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void validate(final Request<Newcal> request, final Newcal entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		
	}

	@Override
	public void bind(final Request<Newcal> request, final Newcal entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "theme", "statement", "period", "share", "furtherInfo", "creationMoment");
		
	}

	@Override
	public void unbind(final Request<Newcal> request, final Newcal entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "theme", "statement", "period", "share", "furtherInfo", "creationMoment");
	}

	@Override
	public Newcal findOne(final Request<Newcal> request) {
		assert request != null;
		
		Newcal result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneNewcalById(id);

		return result;
	}

	@Override
	public void delete(final Request<Newcal> request, final Newcal entity) {
		assert request != null;
		assert entity != null;
		
		
		this.repository.delete(entity);
		
	}

	

}
