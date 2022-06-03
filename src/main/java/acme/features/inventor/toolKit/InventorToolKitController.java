/*
 * InventorToolKitController.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.inventor.toolKit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.artifact.ToolKit;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;


@Controller
//@RequestMapping("/Inventor/ToolKit/")
public class InventorToolKitController extends AbstractController<Inventor, ToolKit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorToolKitListService listService;

	@Autowired
	protected InventorToolKitShowService showService;
	
	@Autowired
	protected InventorToolKitCreateService createService;
	
	@Autowired
	protected InventorToolKitUpdateService updateService;
	
	@Autowired
	protected InventorToolKitDeleteService deleteService;
	
	@Autowired
	protected InventorToolKitPublishService publishService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("create", createService);
		super.addCommand("update", this.updateService);
		super.addCommand("delete", this.deleteService);
		super.addCommand("publish","update", this.publishService);
		
	}

}
