package acme.features.authenticated.systemsetting;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.framework.controllers.AbstractController;
import acme.framework.roles.Authenticated;
import acme.systemSetting.SystemSetting;

@Controller
public class AuthenticatedSystemSettingController extends AbstractController<Authenticated, SystemSetting> {

	// Internal state ---------------------------------------------------------

		@Autowired
		protected AuthenticatedSystemSettingShowService	showService;

		// Constructors -----------------------------------------------------------


		@PostConstruct
		protected void initialise() {
			super.addCommand("show", this.showService);
		}
}
