package acme.features.inventor.partOf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.artifact.PartOf;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorPartOfUpdateService implements AbstractUpdateService<Inventor, PartOf> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorPartOfRepository repository;


	@Override
	public boolean authorise(final Request<PartOf> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void validate(final Request<PartOf> request, final PartOf entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void bind(final Request<PartOf> request, final PartOf entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "id", "quantity", "artifact", "artifact.type", "toolkit");
		
	}

	@Override
	public void unbind(final Request<PartOf> request, final PartOf entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "id", "quantity", "artifact", "artifact.type", "toolkit");
		model.setAttribute("toolkitIsPublish", entity.getToolkit().isPublish());
	}

	@Override
	public PartOf findOne(final Request<PartOf> request) {
		assert request != null;
		
		PartOf result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOnePartOfById(id);

		return result;
	}

	@Override
	public void update(final Request<PartOf> request, final PartOf entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	}

}
