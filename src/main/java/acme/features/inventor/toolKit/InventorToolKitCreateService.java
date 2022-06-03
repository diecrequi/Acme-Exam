package acme.features.inventor.toolKit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.artifact.ToolKit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorToolKitCreateService implements AbstractCreateService<Inventor, ToolKit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorToolKitRepository repository;

	// AbstractCreateService<Inventor, ToolKit> interface --------------


	@Override
	public boolean authorise(final Request<ToolKit> request) {
		assert request != null;

		return true;
	}

	@Override
	public ToolKit instantiate(final Request<ToolKit> request) {
		assert request != null;

		ToolKit result;

		result = new ToolKit();
		
		int inv = request.getPrincipal().getActiveRoleId();
		Inventor inventor = this.repository.findOneInventorById(inv);
		result.setInventor(inventor);
		result.setPublish(false);

		return result;
	}

	@Override
	public void bind(final Request<ToolKit> request, final ToolKit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "title", "description", "assemblyNotes", "link");
		
	}

	@Override
	public void validate(final Request<ToolKit> request, final ToolKit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
	}

	@Override
	public void unbind(final Request<ToolKit> request, final ToolKit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "title", "description", "assemblyNotes", "link");
		model.setAttribute("isNew", true);
	}

	@Override
	public void create(final Request<ToolKit> request, final ToolKit entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
