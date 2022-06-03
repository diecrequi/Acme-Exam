package acme.features.inventor.newcal;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.artifact.Artifact;
import acme.artifact.ArtifactType;
import acme.entities.newcal.Newcal;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class NewcalCreateService implements AbstractCreateService<Inventor, Newcal> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected NewcalRepository repository;

	// AbstractCreateService<Inventor, Chimpum> interface --------------


	@Override
	public boolean authorise(final Request<Newcal> request) {
		assert request != null;

		return true;
	}

	@Override
	public Newcal instantiate(final Request<Newcal> request) {
		assert request != null;

		Newcal result;

		result = new Newcal();
		result.setComponent(this.repository.findArtifactList(ArtifactType.COMPONENT).get(0));

		return result;
	}

	@Override
	public void bind(final Request<Newcal> request, final Newcal entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		final Date moment= new Date(System.currentTimeMillis() - 1);

		request.bind(entity, errors, "code", "theme", "statement", "period", "share", "furtherInfo", "creationMoment");
		
		entity.setCreationMoment(moment);
		
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
	public void unbind(final Request<Newcal> request, final Newcal entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "theme", "statement", "period", "share", "furtherInfo", "creationMoment");
		model.setAttribute("isNew", true);
		final List<Artifact> listArt = this.repository.findArtifactList(ArtifactType.COMPONENT);
		final Artifact a = new Artifact();
		listArt.add(a);
		model.setAttribute("artifact", listArt);
	}

	@Override
	public void create(final Request<Newcal> request, final Newcal entity) {
		assert request != null;
		assert entity != null;

		final Artifact art=this.repository.findArtifactById(request.getModel().getInteger("artifactId"));
		
		entity.setComponent(art);
		
		this.repository.save(entity);
	}

}
