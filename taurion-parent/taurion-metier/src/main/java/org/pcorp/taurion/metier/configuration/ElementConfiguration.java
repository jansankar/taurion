package org.pcorp.taurion.metier.configuration;

import org.pcorp.taurion.metier.domaine.element.Code;
import org.pcorp.taurion.metier.domaine.element.TypeElement;
import org.pcorp.taurion.metier.type.Integrite;

public class ElementConfiguration {
	private TypeElement typeCode;
	private Code elementCode;
	private Float masse;
	private Integrite integrite;
	private Float consoNrj;
	private String description;
	
	public ElementConfiguration(TypeElement typeCode, Code elementCode, Float masse, Integrite integrite, Float consoNrj,
			String description) {
		super();
		this.typeCode = typeCode;
		this.elementCode = elementCode;
		this.masse = masse;
		this.integrite = integrite;
		this.consoNrj = consoNrj;
		this.description = description;
	}
	
	public TypeElement getTypeCode() {
		return typeCode;
	}

	public Code getElementCode() {
		return elementCode;
	}

	public Float getMasse() {
		return masse;
	}

	public Integrite getIntegrite() {
		return integrite;
	}

	public Float getConsoNrj() {
		return consoNrj;
	}

	public String getDescription() {
		return description;
	}
	
	
	
}
