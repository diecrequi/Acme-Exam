package acme.features.inventor.partOf;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.artifact.PartOf;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorPartOfController extends AbstractController<Inventor,PartOf> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorPartOfListToolService	listToolService;

	@Autowired
	protected InventorPartOfShowService	showService;
	
	@Autowired
	protected InventorPartOfDeleteService	deleteService;
	
	@Autowired
	protected InventorPartOfUpdateService	updateService;
	
	@Autowired
	protected InventorPartOfListComponentService listComponentService;
	
	@Autowired
	protected InventorPartOfAddToolService addToolService;
	
	@Autowired
	protected InventorPartOfAddComponentService addComponentService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
//		super.addCommand("list", this.listService);
		super.addCommand("list-component", "list", this.listComponentService);
		super.addCommand("list-tool", "list", this.listToolService);
		super.addCommand("show", this.showService);
		super.addCommand("add-tool", "create", this.addToolService);
		super.addCommand("add-component", "create", this.addComponentService);
		super.addCommand("delete", this.deleteService);
		super.addCommand("update", this.updateService);
	}

}
