package acme.entities.patronage;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class PatronageReport extends AbstractEntity{
	
	
	// Serialisation identifier -----------------------------------------------

	protected static final long		serialVersionUID	= 1L;
	
	//Attributes patronageReport
	
	@NotBlank
	protected String sequenceNumber;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	protected Date creationMoment;
	
	@NotBlank
	@Length(min=1,max=255)
	protected String memorandum;
	
	@URL
	protected String link;
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Patronage patronage;
	
	
}
