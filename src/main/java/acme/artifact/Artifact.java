package acme.artifact;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Artifact extends AbstractEntity{

	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;
	
	
	//Attributes 
	
	
	@NotBlank
	@Length(min=1,max=100)
	protected String name;  
	
	
	
	@Column(unique=true)
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$",  message = "default.error.conversion")
	protected  String code;
	
	
	@NotBlank
	@Length(min=1,max=100)
	protected String technology;
	
	@NotBlank
	@Length(min=1,max=255)
	protected String description;
	
	
	protected Money retailPrice;
	
	@URL
	protected String link;
	
	
	protected ArtifactType type;
	
	@NotNull
	protected boolean isPublish;
	

	//Relationships
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	protected Inventor inventor;
	
	
}
