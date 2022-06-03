package acme.features.inventor.patronageReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorPatronageReportShowService implements AbstractShowService<Inventor, PatronageReport> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorPatronageReportRepository repository;

	@Override
	public boolean authorise(Request<PatronageReport> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public PatronageReport findOne(Request<PatronageReport> request) {
		assert request != null;
		
		PatronageReport result;
		int id = request.getModel().getInteger("id");
		result = this.repository.findOneUserPatronageReportById(id);
		return result;
	}

	@Override
	public void unbind(Request<PatronageReport> request, PatronageReport entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "sequenceNumber", "creationMoment", "memorandum", "link");
	}

	
}
