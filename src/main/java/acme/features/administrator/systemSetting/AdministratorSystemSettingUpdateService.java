package acme.features.administrator.systemSetting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.HttpMethod;
import acme.framework.controllers.Request;
import acme.framework.controllers.Response;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractUpdateService;
import acme.systemSetting.SystemSetting;

@Service
public class AdministratorSystemSettingUpdateService implements AbstractUpdateService<Administrator, SystemSetting>{
	
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorSystemSettingRepository repository;
		
		
	// AbstractShowService<Administrator, Announcement> interface --------------

	@Override
	public boolean authorise(final Request<SystemSetting> request) {
		boolean result;
		
		result= request.getPrincipal().hasRole(Administrator.class);
		
		return result;
	}

	@Override
	public void bind(final Request<SystemSetting> request, final SystemSetting entity, final Errors errors) {
		assert request !=null;
		assert entity !=null;
		assert errors !=null;
	
		request.bind(entity, errors, "weakSpamThreshold", "strongSpamThreshold", "defaultCurrency", "acceptedCurrencies", "weakSpam", "strongSpam");
	}

	@Override
	public void unbind(final Request<SystemSetting> request, final SystemSetting entity, final Model model) {
		assert request !=null;
		assert entity !=null;
		assert model != null;
		
		request.unbind(entity, model, "weakSpamThreshold", "strongSpamThreshold", "defaultCurrency", "acceptedCurrencies", "weakSpam", "strongSpam");
	}

	@Override
	public SystemSetting findOne(final Request<SystemSetting> request) {
		assert request!= null;
		
		SystemSetting result;
		result = this.repository.findOneSytemSetting();
		return result;
	}

	@Override
	public void validate(final Request<SystemSetting> request, final SystemSetting entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(final Request<SystemSetting> request, final SystemSetting entity) {
		assert request !=null;
		assert entity !=null;
		
		this.repository.save(entity);
	}
	
	
	@Override
	public void onSuccess(final Request<SystemSetting> request, final Response<SystemSetting> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}
	
	

}
