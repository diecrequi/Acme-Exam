package acme.features.any.artifact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.artifact.Artifact;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class anyArtifactShowService implements AbstractShowService<Any, Artifact> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected anyArtifactRepository repository;

	// AbstractShowService<Anonymous, Artifact> interface --------------------------

	@Override
	public boolean authorise(final Request<Artifact> request) {
		assert request != null;

		return true;
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
		result = this.repository.findOneToolById(id);

		return result;
	}

}
