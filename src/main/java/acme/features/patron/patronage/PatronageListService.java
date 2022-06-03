package acme.features.patron.patronage;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractListService;
import acme.roles.Patron;
import acme.systemSetting.SystemSetting;
import acme.utils.moneyExchange.MoneyExchangeUtils;

@Service
public class PatronageListService implements AbstractListService<Patron, Patronage>{
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected PatronageRepository repository;

		// AbstractListService<Anonymous, Announcement>  interface -------------------------


		@Override
		public boolean authorise(final Request<Patronage> request) {
			assert request != null;

			return true;
		}
		
		@Override
		public Collection<Patronage> findMany(final Request<Patronage> request) {
			assert request != null;

			Collection<Patronage> result;

			result = this.repository.findManyPatronage();

			return result;
		}
		
		@Override
		public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "code", "budget");
			final SystemSetting systemSetting = this.repository.findSystemSetting();
			final Money convertedBudget = MoneyExchangeUtils.computeMoneyExchange(entity.getBudget(), systemSetting.getDefaultCurrency());
			model.setAttribute("convertedBudget", convertedBudget);
		}

}
