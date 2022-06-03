package acme.features.any.partOf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.artifact.PartOf;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class anyPartOfShowService implements AbstractShowService<Any,PartOf> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected anyPartOfRepository repository;

	@Override
	public boolean authorise(final Request<PartOf> request) {
		assert request != null;
		
		//check propiedad isDraftMode().
		
		return true;
	}

	@Override
	public PartOf findOne(final Request<PartOf> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		
		return this.repository.findOnePartOfById(id);
	}

	@Override
	public void unbind(final Request<PartOf> request, final  PartOf entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "quantity", "artifact.name", "artifact.technology", "artifact.description",
						"artifact.retailPrice", "artifact.link", "artifact.type");
	}



}
