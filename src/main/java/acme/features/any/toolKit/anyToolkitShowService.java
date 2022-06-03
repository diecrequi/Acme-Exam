package acme.features.any.toolKit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.artifact.ToolKit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class anyToolkitShowService implements AbstractShowService<Any, ToolKit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected anyToolkitRepository repository;

	@Override
	public boolean authorise(final Request<ToolKit> request) {
		assert request != null;
		
		//check propiedad isDraftMode().
		
		return true;
	}

	@Override
	public ToolKit findOne(final Request<ToolKit> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		
		return this.repository.findOneToolKitById(id);
	}

	@Override
	public void unbind(final Request<ToolKit> request, final ToolKit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "title", "description", "assemblyNotes", "link");
	}



}
