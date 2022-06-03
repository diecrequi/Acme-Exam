package acme.features.any.partOf;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.artifact.PartOf;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class anyPartOfListService implements AbstractListService<Any,PartOf> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected anyPartOfRepository repository;

	@Override
	public boolean authorise(final Request<PartOf> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public Collection<PartOf> findMany(final Request<PartOf> request) {
		assert request != null;
		
		final int masterId = request.getModel().getInteger("masterId");
		
		return this.repository.findManyPartOfByMasterId(masterId);
		
	}

	@Override
	public void unbind(final Request<PartOf> request, final PartOf entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "quantity", "artifact.name");
	}

	
}
