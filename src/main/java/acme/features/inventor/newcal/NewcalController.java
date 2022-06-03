package acme.features.inventor.newcal;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.newcal.Newcal;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class NewcalController extends AbstractController<Inventor, Newcal>{
	// Internal state ---------------------------------------------------------

		@Autowired
		protected NewcalListService	listService;

		@Autowired
		protected NewcalShowService	showService;
		
		@Autowired
		protected NewcalUpdateService	updateService;
		
		@Autowired
		protected NewcalCreateService	createService;
		
		@Autowired
		protected NewcalDeleteService	deleteService;

		// Constructors -----------------------------------------------------------


		@PostConstruct
		protected void initialise() {
			super.addCommand("list", this.listService);
			super.addCommand("show", this.showService);
			super.addCommand("update", this.updateService);
			super.addCommand("create", this.createService);
			super.addCommand("delete", this.deleteService);
		}

}
