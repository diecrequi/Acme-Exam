package acme.systemSetting;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SystemSetting extends AbstractEntity{

	
	 //Serialisation identifier -----------------------------------------------

		protected static final long	serialVersionUID	= 1L;
				
		
		
		//Attributes
		
		@Range(min=0,max=100)
		protected Integer weakSpamThreshold;
		
		
		@Range(min=0,max=100)
		protected Integer strongSpamThreshold;
		
		
		protected String defaultCurrency;
		
		protected String acceptedCurrencies;
		
		@NotBlank
		protected String weakSpam;
		
		@NotBlank
		protected String strongSpam;
}
