package acme.features.inventor.artifact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.artifact.Artifact;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;
import acme.systemSetting.SystemSetting;

@Service
public class InventorArtifactUpdateService implements AbstractUpdateService<Inventor, Artifact> {

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

		final SystemSetting s = this.repository.findSystemSetting();
		
		final Artifact artifact = this.repository.findByCode(entity.getCode());
		if(entity.getRetailPrice()!=null) {
		errors.state(request, s.getAcceptedCurrencies().contains(entity.getRetailPrice().getCurrency()) ,
			  "retailPrice", "inventor.artifact.retailPrice.not-able-currency");
		errors.state(request, entity.getRetailPrice().getAmount() > 0, "retailPrice", "inventor.artifact.code.repeated.retailPrice.non-negative");
		}else {
			errors.state(request, entity.getRetailPrice()!=null, "retailPrice", "inventor.artifact.retailPrice.null");
		}
		errors.state(request, artifact == null || artifact.getId()==entity.getId(), "code", "inventor.artifact.code.repeated");
		
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

		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "link", "isPublish");
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
	public void update(final Request<Artifact> request, final Artifact entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	}

}
