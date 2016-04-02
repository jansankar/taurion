package org.pcorp.taurion.metier.domaine.exception;

import org.pcorp.taurion.metier.domaine.element.Element;

public class NonActiveException extends Exception {
	private Element elementDeclencheur;

	public NonActiveException(Element element) {
		super();
		elementDeclencheur = element;
	}

	@Override
	public String getMessage() {
		StringBuilder sb = new StringBuilder();
		sb.append("element [");
		sb.append(elementDeclencheur.getElementCode());
		sb.append(" - ");
		sb.append(elementDeclencheur.getTypeCode());
		sb.append("] est ");
		sb.append(elementDeclencheur.getEtatActuel());
		return sb.toString();
	}

}
