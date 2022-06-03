package acme.features.inventor.newcal;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.newcal.Newcal;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class NewcalListService implements AbstractListService<Inventor, Newcal>{
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected NewcalRepository repository;

		// AbstractListService<Patron, Chimpum>  interface -------------------------


		@Override
		public boolean authorise(final Request<Newcal> request) {
			assert request != null;

			return true;
		}
		
		@Override
		public Collection<Newcal> findMany(final Request<Newcal> request) {
			assert request != null;

			Collection<Newcal> result;

			result = this.repository.findManyNewcal();

			return result;
		}
		
		@Override
		public void unbind(final Request<Newcal> request, final Newcal entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "code", "theme", "statement");
		}

}
