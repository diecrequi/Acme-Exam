package acme.features.patron.PatronDashboard;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.Dashboard.PatronDashboard;
import acme.framework.controllers.AbstractController;
import acme.roles.Patron;

@Controller
public class PatronDashboardController extends AbstractController<Patron, PatronDashboard> {

	// Internal state ---------------------------------------------------------


	@Autowired
	protected PatronDashboardShowService	showService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
	}

}
