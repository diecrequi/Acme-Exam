package acme.features.patron.patronage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.entities.patronage.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Patron;

@Service
public class PatronageDeleteService implements AbstractDeleteService<Patron, Patronage>{
	
	@Autowired
	protected PatronageRepository repository;

	@Override
	public boolean authorise(Request<Patronage> request) {
		
		
		assert request != null;

		boolean result = true;
		
		
		return result;
	}

	@Override
	public void bind(Request<Patronage> request, Patronage entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors,  "status", "code", "legalStuff", "budget", "startDate", "finishDate", "link");
		
	}

	@Override
	public void unbind(Request<Patronage> request, Patronage entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "status", "code", "legalStuff", "budget", "startDate", "finishDate", "link");
		
		
	}

	@Override
	public Patronage findOne(Request<Patronage> request) {
		assert request != null;

		Patronage result;
		int id;

		id = request.getModel().getInteger("id");
		result = repository.findOnePatronageById(id);

		return result;
	}

	@Override
	public void validate(Request<Patronage> request, Patronage entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void delete(Request<Patronage> request, Patronage entity) {
		assert request != null;
		assert entity != null;
		
		List<PatronageReport> pr=this.repository.findPR(entity);
		for(PatronageReport p:pr) {
			this.repository.delete(p);
		}
		this.repository.delete(entity); 
		
	}
	

}
