package acme.features.any.partOf;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.artifact.PartOf;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class anyPartOfController extends AbstractController<Any,PartOf> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected anyPartOfListService	listService;

	@Autowired
	protected anyPartOfShowService	showService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
	}

}
