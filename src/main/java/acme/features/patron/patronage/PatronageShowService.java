package acme.features.patron.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronageShowService implements AbstractShowService<Patron, Patronage>{
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected PatronageRepository repository;

		// AbstractShowService<Anonymous, Announcement> interface --------------------------

		@Override
		public boolean authorise(final Request<Patronage> request) {
			assert request != null;

			return true;
		}

		@Override
		public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "id", "status", "code", "legalStuff", "budget", "startDate", "finishDate", "link", "isPublish");
			model.setAttribute("inventorId", entity.getInventor().getUserAccount().getId());
			model.setAttribute("esInventor", false);
		}

		@Override
		public Patronage findOne(final Request<Patronage> request) {
			assert request != null;

			Patronage result;
			int id;

			id = request.getModel().getInteger("id");
			result = this.repository.findOnePatronageById(id);

			return result;
		}

		

}
