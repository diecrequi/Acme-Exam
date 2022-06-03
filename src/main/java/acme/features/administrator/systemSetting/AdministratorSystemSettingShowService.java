
package acme.features.administrator.systemSetting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;
import acme.systemSetting.SystemSetting;

@Service
public class AdministratorSystemSettingShowService implements AbstractShowService<Administrator, SystemSetting> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorSystemSettingRepository repository;

	// AbstractShowService<Administrator, Announcement> interface --------------


	@Override
	public boolean authorise(final Request<SystemSetting> request) {
		boolean result;

		result = request.getPrincipal().hasRole(Administrator.class);

		return result;
	}

	@Override
	public SystemSetting findOne(final Request<SystemSetting> request) {
		assert request != null;

		SystemSetting result;
		result = this.repository.findOneSytemSetting();

		return result;
	}

	@Override
	public void unbind(final Request<SystemSetting> request, final SystemSetting entity, final acme.framework.components.models.Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "weakSpamThreshold", "strongSpamThreshold", "defaultCurrency", "acceptedCurrencies", "weakSpam", "strongSpam");
	}

}
