package acme.features.administrator.AdminDashboard;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.Dashboard.AdminDashboard;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Administrator;

@Controller
public class AdminDashboardController extends AbstractController<Administrator, AdminDashboard> {

	// Internal state ---------------------------------------------------------


	@Autowired
	protected AdminDashboardShowService	showService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
	}

}
