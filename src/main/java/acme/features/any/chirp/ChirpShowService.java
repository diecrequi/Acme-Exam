package acme.features.any.chirp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chirp.Chirp;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class ChirpShowService implements AbstractShowService<Any, Chirp>{
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected ChirpRepository repository;

		// AbstractShowService<Anonymous, Announcement> interface --------------------------

		@Override
		public boolean authorise(final Request<Chirp> request) {
			assert request != null;

			return true;
		}

		@Override
		public void unbind(final Request<Chirp> request, final Chirp entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "creationMoment", "title", "author", "body", "email");
			model.setAttribute("isNew", false);
		}

		@Override
		public Chirp findOne(final Request<Chirp> request) {
			assert request != null;

			Chirp result;
			int id;

			id = request.getModel().getInteger("id");
			result = this.repository.findOneChirpById(id);

			return result;
		}

		

}
