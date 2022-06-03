package acme.features.inventor.artifact;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.artifact.Artifact;
import acme.artifact.PartOf;
import acme.entities.newcal.Newcal;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorArtifactDeleteService implements AbstractDeleteService<Inventor, Artifact> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorArtifactRepository repository;


	@Override
	public boolean authorise(final Request<Artifact> request) {
		assert request != null;

		boolean result = true;
		int masterId;
		Artifact art;
		Inventor inv;

		masterId = request.getModel().getInteger("id");
		art = this.repository.findOneArtifactById(masterId);
		inv = art.getInventor();
		result = request.isPrincipal(inv);
		
		return result;
	}

	@Override
	public void validate(final Request<Artifact> request, final Artifact entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		
	}

	@Override
	public void bind(final Request<Artifact> request, final Artifact entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "name", "code", "technology", "description", "retailPrice", "link");
		
	}

	@Override
	public void unbind(final Request<Artifact> request, final Artifact entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "link");
	}

	@Override
	public Artifact findOne(final Request<Artifact> request) {
		assert request != null;
		
		Artifact result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneArtifactById(id);

		return result;
	}

	@Override
	public void delete(final Request<Artifact> request, final Artifact entity) {
		assert request != null;
		assert entity != null;
		
		final Collection<PartOf> partOf = this.repository.findAllPartoOfByArtifact(entity.getId());
		for(final PartOf p : partOf) {
			this.repository.delete(p);
		}
		
		final Newcal newcal = this.repository.findAllNewcal(entity);
		if(newcal != null) {
			newcal.setComponent(null);
			this.repository.save(newcal);
		}
		
		this.repository.delete(entity);
		
	}

	

}
