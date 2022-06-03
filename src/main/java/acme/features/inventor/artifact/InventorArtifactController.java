package acme.features.inventor.artifact;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.artifact.Artifact;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorArtifactController extends AbstractController<Inventor, Artifact> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorToolListService	listToolService;
	
	@Autowired
	protected InventorComponentListService	listComponentService;

	@Autowired
	protected InventorArtifactShowService	showService;
	
	@Autowired
	protected InventorToolCreateService	createToolService;
	
	@Autowired
	protected InventorComponentCreateService	createComponentService;
	
	@Autowired
	protected InventorArtifactUpdateService	updateService;
	
	@Autowired
	protected InventorArtifactDeleteService	deleteService;
	
	@Autowired
	protected InventorArtifactPublishService	publishService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list-tool","list", this.listToolService);
		super.addCommand("list-component","list", this.listComponentService);
		super.addCommand("create-tool","create", this.createToolService);
		super.addCommand("create-component","create", this.createComponentService);
		super.addCommand("update", this.updateService);
		super.addCommand("publish","update", this.publishService);
		super.addCommand("delete", this.deleteService);
		super.addCommand("show", this.showService);
	}

}
