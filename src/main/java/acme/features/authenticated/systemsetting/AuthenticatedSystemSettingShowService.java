
package acme.features.authenticated.systemsetting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractShowService;
import acme.systemSetting.SystemSetting;

@Service
public class AuthenticatedSystemSettingShowService implements AbstractShowService<Authenticated, SystemSetting> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedSystemSettingRepository repository;

	// AbstractCreateService<Authenticated, SystemSetting> ---------------------------


	@Override
	public boolean authorise(final Request<SystemSetting> request) {
		assert request != null;

		boolean result;

		result = request.getPrincipal().hasRole(Authenticated.class);

		return result;
	}

	@Override
	public SystemSetting findOne(final Request<SystemSetting> request) {
		assert request != null;

		SystemSetting result;

		result = this.repository.findOneSystemSetting();

		return result;
	}

	@Override
	public void unbind(final Request<SystemSetting> request, final SystemSetting entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "acceptedCurrencies", "defaultCurrency");
	}

}
