package acme.features.inventor.partOf;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.artifact.Artifact;
import acme.artifact.PartOf;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorPartOfShowService implements AbstractShowService<Inventor,PartOf> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorPartOfRepository repository;

	@Override
	public boolean authorise(final Request<PartOf> request) {
		assert request != null;
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
		
		request.unbind(entity, model, "id", "quantity", "artifact", "artifact.type", "toolkit");
		
		model.setAttribute("toolkitIsPublish", entity.getToolkit().isPublish());
		
		final int i = request.getPrincipal().getActiveRoleId();
		final Inventor inv = this.repository.findOneInventorByInventorId(i);
		final Collection<Artifact> artifacts = this.repository.findArtifactsByTypeInventor(entity.getArtifact().getType(), inv);
		final Collection<Artifact> artifactsInToolkit = this.repository.findArtifactsByTypeToolkit(entity.getArtifact().getType(), entity.getToolkit());
		final List<Artifact> artifactList = artifacts.stream().filter(a -> !artifactsInToolkit.contains(a)).collect(Collectors.toList());
		artifactList.add(0, entity.getArtifact());
		model.setAttribute("artifacts", artifactList);
	}



}
