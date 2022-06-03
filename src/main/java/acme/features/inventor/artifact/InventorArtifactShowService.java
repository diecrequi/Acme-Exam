package acme.features.inventor.artifact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.artifact.Artifact;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorArtifactShowService implements AbstractShowService<Inventor, Artifact> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorArtifactRepository repository;

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

		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "link", "isPublish");
		
		model.setAttribute("isNew", false);
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

}
