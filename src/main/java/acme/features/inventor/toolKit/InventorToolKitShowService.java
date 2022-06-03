/*
 * InventorToolKitUpdateService.java
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.artifact.ToolKit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;


@Service
public class InventorToolKitShowService implements AbstractShowService<Inventor, ToolKit>{
	
	@Autowired
	protected InventorToolKitRepository repository;
	
	@Override
	public boolean authorise(final Request<ToolKit> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public ToolKit findOne(final Request<ToolKit> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		
		return this.repository.findOneToolKitById(id);
	}

	@Override
	public void unbind(final Request<ToolKit> request, final ToolKit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "title", "description", "assemblyNotes", "link", "isPublish");
		
	}




}
