package acme.features.patron.PatronDashboard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.Dashboard.PatronDashboard;
import acme.datatypes.StatusType;
import acme.entities.patronage.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronDashboardShowService implements AbstractShowService<Patron, PatronDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronDashboardRepository repository;

	// AbstractShowService<Anonymous, Announcement> interface --------------------------

	@Override
	public boolean authorise(final Request<PatronDashboard> request) {
		assert request != null;

		

		return true;
	}

	@Override
	public void unbind(final Request<PatronDashboard> request, final PatronDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		model.setAttribute("patronDashboard", entity);
		final List<String> currencies = new ArrayList<>();
		currencies.add("EUR");
		currencies.add("USD");
		currencies.add("GBP");
		model.setAttribute("currencies", currencies);
		request.unbind(entity, model);
	}

	


	@Override
	public PatronDashboard findOne(final Request<PatronDashboard> request) {
		final PatronDashboard result= new PatronDashboard();

		final Map<StatusType, Integer> mStatCurr= new HashMap<StatusType, Integer>();
		final Map<Pair<StatusType, String>, Double> mAvg= new HashMap<Pair<StatusType, String>, Double>();
		final Map<Pair<StatusType, String>, Double> mDesviation= new HashMap<Pair<StatusType, String>, Double>();
		final Map<Pair<StatusType, String>, Double> mMax= new HashMap<Pair<StatusType, String>, Double>();
		final Map<Pair<StatusType, String>, Double> mMin= new HashMap<Pair<StatusType, String>, Double>();
		
		for (int i=0; i<3;i++) {
			final Collection<Patronage> patronages= this.repository.findPatronage(StatusType.values()[i]);
			final int n= patronages.size();
			
			int nEur= 0;
			int nUsd= 0;
			int nGbp= 0;
			Double totalEur= 0.;
			Double totalUsd= 0.;
			Double totalGbp= 0.;
			
			Double desviationEur= 0.;
			Double desviationUsd= 0.;
			Double desviationGbp= 0.;
			
//			final List<Patronage> pl= new ArrayList<Patronage>(patronages);
			
			Double maxEur=0.;
			Double maxUsd=0.;
			Double maxGbp=0.;
			Double minEur=Double.MAX_VALUE;
			Double minUsd=Double.MAX_VALUE;
			Double minGbp=Double.MAX_VALUE;
			
			for(final Patronage p: patronages) {
				final Double prize = p.getBudget().getAmount(); 
				switch (p.getBudget().getCurrency()) {
				case "EUR":
					totalEur+=prize;
					nEur++;
					
					maxEur= prize>maxEur?prize:maxEur;
					minEur= prize<minEur?prize:minEur;
					
					break;
				case "USD":
					totalUsd+=prize;
					nUsd++;

					maxUsd= prize>maxUsd?prize:maxUsd;
					minUsd= prize<minUsd?prize:minUsd;
					
					break;
				case "GBP":
					totalGbp+=prize;
					nGbp++;

					maxGbp= prize>maxGbp?prize:maxGbp;
					minGbp= prize<minGbp?prize:minGbp;
					
					break;
				}
			}
			
			for(final Patronage p: patronages) {
				switch (p.getBudget().getCurrency()) {
				case "EUR":
					desviationEur+=(p.getBudget().getAmount() - totalEur!=0?totalEur/nEur:0.)
						  *(p.getBudget().getAmount() - totalEur!=0?totalEur/nEur:0.);
					break;
				case "USD":
					desviationUsd+=(p.getBudget().getAmount() - totalUsd!=0?totalUsd/nUsd:0.)
						  *(p.getBudget().getAmount() - totalUsd!=0?totalUsd/nUsd:0.);
					break;
				case "GBP":
					desviationGbp+=(p.getBudget().getAmount() - totalGbp!=0?totalGbp/nGbp:0.)
						  *(p.getBudget().getAmount() - totalGbp!=0?totalGbp/nGbp:0.);
					break;
				}
			
			}
			final Pair<StatusType, String> pEur = Pair.of(StatusType.values()[i], "EUR");
			final Pair<StatusType, String> pUsd = Pair.of(StatusType.values()[i], "USD");
			final Pair<StatusType, String> pGbp = Pair.of(StatusType.values()[i], "GBP");
			
			
			mStatCurr.put(StatusType.values()[i], n);
			mAvg.put(pEur, totalEur!=0?totalEur/nEur:0.);
			mAvg.put(pUsd, totalUsd!=0?totalUsd/nUsd:0.);
			mAvg.put(pGbp, totalGbp!=0?totalGbp/nGbp:0.);
			mMax.put(pEur, maxEur);
			mMax.put(pUsd, maxUsd);
			mMax.put(pGbp, maxGbp);
			mMin.put(pEur, minEur==Double.MAX_VALUE?0.:minEur);
			mMin.put(pUsd, minUsd==Double.MAX_VALUE?0.:minUsd);
			mMin.put(pGbp, minGbp==Double.MAX_VALUE?0.:minGbp);
			mDesviation.put(pEur, nEur!=0?Math.sqrt(desviationEur/nEur):0);
			mDesviation.put(pUsd, nUsd!=0?Math.sqrt(desviationUsd/nUsd):0);
			mDesviation.put(pGbp, nGbp!=0?Math.sqrt(desviationGbp/nGbp):0);
			
		}
		
		result.setTotalNumberOfPatronages(mStatCurr);
		result.setAvgBudget(mAvg);
		result.setDeviationBudget(mDesviation);
		result.setMaxBudget(mMax);
		result.setMinBudget(mMin);
		
		return result;
	}

	

}
