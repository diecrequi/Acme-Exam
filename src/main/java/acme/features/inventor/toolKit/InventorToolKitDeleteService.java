package acme.features.inventor.toolKit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.artifact.PartOf;
import acme.artifact.ToolKit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorToolKitDeleteService implements AbstractDeleteService<Inventor, ToolKit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorToolKitRepository repository;


	@Override
	public boolean authorise(final Request<ToolKit> request) {
		assert request != null;

		boolean result = true;
		int masterId;
		ToolKit art;
		Inventor inv;

		masterId = request.getModel().getInteger("id");
		art = this.repository.findOneToolKitById(masterId);
		inv = art.getInventor();
		result = request.isPrincipal(inv);
		
		return result;
	}

	@Override
	public void validate(final Request<ToolKit> request, final ToolKit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		
	}

	@Override
	public void bind(final Request<ToolKit> request, final ToolKit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "name", "code", "technology", "description", "retailPrice", "link");
		
	}

	@Override
	public void unbind(final Request<ToolKit> request, final ToolKit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "link", "isPublish");
	}

	@Override
	public ToolKit findOne(final Request<ToolKit> request) {
		assert request != null;
		
		ToolKit result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneToolKitById(id);

		return result;
	}

	@Override
	public void delete(Request<ToolKit> request, ToolKit entity) {
		assert request != null;
		assert entity != null;
		
		Collection<PartOf> partOf = this.repository.findAllPartoOfByToolKit(entity.getId());
		for(PartOf p : partOf) {
			this.repository.delete(p);
		}
		this.repository.delete(entity);
		
	}

	

}