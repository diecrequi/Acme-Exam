package acme.features.patron.patronage;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.datatypes.StatusType;
import acme.entities.patronage.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;
import acme.roles.Patron;


@Service
public class PatronageCreateService implements AbstractCreateService<Patron, Patronage>{
	
	@Autowired
	protected PatronageRepository repository;
	
		
	// AbstractCreateService<Patron, Patronage> interface ---------------------
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		

		request.bind(entity, errors,"status", "code", "legalStuff", "budget", "startDate", "finishDate", "link");
		
	}
	
	

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		
		request.unbind(entity, model, "status", "code", "legalStuff", "budget", "startDate", "finishDate", "link");
		model.setAttribute("isNew", true);
		model.setAttribute("inventor", this.repository.findInvList());
		

	}
	
	

	@Override
	public Patronage instantiate(final Request<Patronage> request) {
		assert request != null;
		
		Patronage result;
	
		
		result = new Patronage();
		final int patronId = request.getPrincipal().getActiveRoleId();
		final Patron patron = this.repository.findPatronById(patronId);
		
		
	
		result.setPatron(patron);
		result.setInventor(this.repository.findInvList().get(1));
		result.setStatus(StatusType.PROPOSED);
		result.setPublish(false);
		
		return result;
	}

	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		Patronage p = this.repository.findOnePatronageByCode(entity.getCode());
		errors.state(request, p==null, "code", "patron.code.repeted");
		
		errors.state(request, entity.getBudget().getAmount() > 0, "budget", "patron.budget.non-negative");
		
		errors.state(request, entity.getFinishDate().after(entity.getStartDate()), "finishDate", "patron.finishDate.order-error");

		final LocalDateTime startDate = entity.getStartDate().toInstant()
		      .atZone(ZoneId.systemDefault())
		      .toLocalDateTime();
		
		final LocalDateTime finishDate = entity.getFinishDate().toInstant()
		      .atZone(ZoneId.systemDefault())
		      .toLocalDateTime();
		
		errors.state(request, Duration.between(startDate, finishDate).toDays() > 30, "finishDate", "patron.finishDate.duration-error");
	}

	@Override
	public void create(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;
		
		final Inventor inv=this.repository.findInvById(request.getModel().getInteger("inventorId"));
		entity.setInventor(inv);
		this.repository.save(entity);
	}
	
	
	
	
	
	

	

}
