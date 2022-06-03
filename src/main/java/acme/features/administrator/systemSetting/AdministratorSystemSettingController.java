package acme.features.administrator.systemSetting;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.framework.controllers.AbstractController;
import acme.framework.roles.Administrator;
import acme.systemSetting.SystemSetting;

@Controller
public class AdministratorSystemSettingController  extends AbstractController<Administrator, SystemSetting> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorSystemSettingShowService	showService;

	@Autowired
	protected AdministratorSystemSettingUpdateService updateService;
	
	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("update", this.updateService);
	}
}
