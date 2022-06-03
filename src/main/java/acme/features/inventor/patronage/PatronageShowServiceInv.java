package acme.features.inventor.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class PatronageShowServiceInv implements AbstractShowService<Inventor, Patronage>{
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected PatronageRepositoryInv repository;

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

			request.unbind(entity, model, "id", "status", "code", "legalStuff", "budget", "startDate", "link", "isPublish");
			model.setAttribute("patronId", entity.getPatron().getUserAccount().getId());
			model.setAttribute("esInventor", true);
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
