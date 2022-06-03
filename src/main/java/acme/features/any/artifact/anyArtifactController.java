package acme.features.any.artifact;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.artifact.Artifact;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class anyArtifactController extends AbstractController<Any, Artifact> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected anyToolListService	listToolService;
	
	@Autowired
	protected anyComponentListService	listComponentService;

	@Autowired
	protected anyArtifactShowService	showService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list-tool","list", this.listToolService);
		super.addCommand("list-component","list", this.listComponentService);
		super.addCommand("show", this.showService);
	}

}
