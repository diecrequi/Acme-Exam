package acme.features.inventor.artifact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.artifact.Artifact;
import acme.artifact.ArtifactType;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;
import acme.systemSetting.SystemSetting;

@Service
public class InventorComponentCreateService implements AbstractCreateService<Inventor, Artifact> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorArtifactRepository repository;

	// AbstractCreateService<Inventor, Artifact> interface --------------


	@Override
	public boolean authorise(final Request<Artifact> request) {
		assert request != null;

		return true;
	}

	@Override
	public Artifact instantiate(final Request<Artifact> request) {
		assert request != null;

		Artifact result;

		result = new Artifact();
		final int i = request.getPrincipal().getActiveRoleId();
		final Inventor inv = this.repository.findOneInventorByInventorId(i);
		result.setInventor(inv);
		result.setType(ArtifactType.COMPONENT);
		result.setPublish(false);

		return result;
	}

	@Override
	public void bind(final Request<Artifact> request, final Artifact entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "name", "code", "technology", "description", "retailPrice", "link");
		
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
		errors.state(request, artifact == null, "code", "inventor.artifact.code.repeated");
		
	}

	@Override
	public void unbind(final Request<Artifact> request, final Artifact entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "link");
		model.setAttribute("isNew", true);
		model.setAttribute("isTool", false);
	}

	@Override
	public void create(final Request<Artifact> request, final Artifact entity) {
		assert request != null;
		assert entity != null;

		
		this.repository.save(entity);
	}

}
