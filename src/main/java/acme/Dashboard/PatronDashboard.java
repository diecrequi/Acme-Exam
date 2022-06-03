package acme.Dashboard;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.util.Pair;

import acme.datatypes.StatusType;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PatronDashboard implements Serializable{
	
	
	// Serialisation identifier -----------------------------------------------

		protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
		protected Map<StatusType,Integer> totalNumberOfPatronages;
		protected Map<Pair<StatusType, String>,Double> avgBudget;
		protected Map<Pair<StatusType, String>,Double> deviationBudget;
		protected Map<Pair<StatusType, String>,Double> maxBudget;
		protected Map<Pair<StatusType, String>,Double> minBudget;
		
	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
	
}
