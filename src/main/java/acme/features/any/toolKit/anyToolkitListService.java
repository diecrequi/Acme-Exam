package acme.features.any.toolKit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.artifact.ToolKit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class anyToolkitListService implements AbstractListService<Any, ToolKit> {

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
	public Collection<ToolKit> findMany(final Request<ToolKit> request) {
		assert request != null;
		
		return this.repository.findManyToolKits();
		
	}

	@Override
	public void unbind(final Request<ToolKit> request, final ToolKit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "title", "description");
	}

	
}
