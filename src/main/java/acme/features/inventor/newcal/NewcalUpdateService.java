package acme.features.inventor.newcal;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.artifact.Artifact;
import acme.artifact.ArtifactType;
import acme.entities.newcal.Newcal;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class NewcalUpdateService implements AbstractUpdateService<Inventor, Newcal>{

	@Autowired
	protected NewcalRepository repository;

	@Override
	public boolean authorise(final Request<Newcal> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Newcal> request, final Newcal entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "theme", "statement", "period", "share", "furtherInfo", "creationMoment");
		model.setAttribute("isNew", false);
		final List<Artifact> listArt = this.repository.findArtifactList(ArtifactType.COMPONENT);
		Artifact a = new Artifact();
		listArt.add(0, a);
		a = entity.getComponent();
		if(entity.getComponent() != null) {
			listArt.add(0, a);
		}
		
		model.setAttribute("artifact", listArt);
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
	public void bind(final Request<Newcal> request, final Newcal entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "theme", "statement", "period", "share", "furtherInfo", "creationMoment");
	}

	@Override
	public void validate(final Request<Newcal> request, final Newcal entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		errors.state(request, entity.getShare().getAmount() > 0, "share", "inventor.Chimpum.code.repeated.retailPrice.non-negative");
		errors.state(request, entity.getPeriod().after(entity.getCreationMoment()), "period", "inventor.Chimpum.period.order-error");
		
		final LocalDateTime startDate = entity.getPeriod().toInstant()
		      .atZone(ZoneId.systemDefault())
		      .toLocalDateTime();
		
		final LocalDateTime finishDate = entity.getCreationMoment().toInstant()
		      .atZone(ZoneId.systemDefault())
		      .toLocalDateTime();
		errors.state(request, Duration.between(finishDate, startDate).toDays() > 30, "period", "inventor.Chimpum.period.duration-error");
		
		final Newcal ch = this.repository.findAnyNewcalByCode(entity.getCode());
		errors.state(request, ch==null, "code", "inventor.Chimpum.period.code-error");
	}

	@Override
	public void update(final Request<Newcal> request, final Newcal entity) {
		assert request !=null;
		assert entity !=null;
		
		final Artifact art=this.repository.findArtifactById(request.getModel().getInteger("artifactId"));
		
		entity.setComponent(art);
		
		this.repository.save(entity);
		
	}
	
	

}
