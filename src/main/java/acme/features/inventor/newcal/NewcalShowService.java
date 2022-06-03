package acme.features.inventor.newcal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.artifact.Artifact;
import acme.artifact.ArtifactType;
import acme.entities.newcal.Newcal;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class NewcalShowService implements AbstractShowService<Inventor, Newcal>{
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected NewcalRepository repository;

		// AbstractShowService<Inventor, Chimpum> interface --------------------------

		@Override
		public boolean authorise(final Request<Newcal> request) {
			assert request != null;

			return true;
		}

		@Override
		public void unbind(final Request<Newcal> request, final Newcal entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "code", "theme", "statement", "period", "share", "furtherInfo", "creationMoment");
			model.setAttribute("isNew", false);
			final List<Artifact> listArt = this.repository.findArtifactList(ArtifactType.COMPONENT);
			Artifact a = new Artifact();
			listArt.add(0, a);
			a = entity.getComponent();
			if(entity.getComponent() != null) {
				listArt.add(0, a);
			}
			
			model.setAttribute("artifact", listArt);
		}

		@Override
		public Newcal findOne(final Request<Newcal> request) {
			assert request != null;

			Newcal result;
			int id;

			id = request.getModel().getInteger("id");
			result = this.repository.findOneNewcalById(id);

			return result;
		}

		

}
