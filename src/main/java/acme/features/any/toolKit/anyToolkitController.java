package acme.features.any.toolKit;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.artifact.ToolKit;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class anyToolkitController extends AbstractController<Any, ToolKit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected anyToolkitListService	listService;

	@Autowired
	protected anyToolkitShowService	showService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
	}

}
