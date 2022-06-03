package acme.features.any.artifact;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.artifact.Artifact;
import acme.artifact.ArtifactType;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class anyComponentListService implements AbstractListService<Any, Artifact> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected anyArtifactRepository repository;

	// AbstractListService<Anonymous, Artifact>  interface -------------------------


	@Override
	public boolean authorise(final Request<Artifact> request) {
		assert request != null;

		return true;
	}
	
	@Override
	public Collection<Artifact> findMany(final Request<Artifact> request) {
		assert request != null;

		Collection<Artifact> result;

		result = this.repository.findManyTool(ArtifactType.COMPONENT);

		return result;
	}
	
	@Override
	public void unbind(final Request<Artifact> request, final Artifact entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "description", "retailPrice");
//		final SystemSetting systemSetting = this.repository.findSystemSetting();
//		final Money convertedPrice = MoneyExchangeUtils.computeMoneyExchange(entity.getRetailPrice(), systemSetting.getDefaultCurrency());
//		model.setAttribute("convertedPrice", convertedPrice);
	}

}
