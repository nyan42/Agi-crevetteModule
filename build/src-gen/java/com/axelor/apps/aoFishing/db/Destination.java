package com.axelor.apps.aoFishing.db;

import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.Widget;
import com.google.common.base.MoreObjects;

@Entity
@Table(name = "AO_FISHING_DESTINATION")
public class Destination extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AO_FISHING_DESTINATION_SEQ")
	@SequenceGenerator(name = "AO_FISHING_DESTINATION_SEQ", sequenceName = "AO_FISHING_DESTINATION_SEQ", allocationSize = 1)
	private Long id;

	@Widget(title = "Country Code")
	@NotNull
	private String aoCodeCountry;

	@Widget(title = "Libelle")
	@NotNull
	private String aoLibelle;

	@Widget(title = "Attributes")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "json")
	private String attrs;

	public Destination() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getAoCodeCountry() {
		return aoCodeCountry;
	}

	public void setAoCodeCountry(String aoCodeCountry) {
		this.aoCodeCountry = aoCodeCountry;
	}

	public String getAoLibelle() {
		return aoLibelle;
	}

	public void setAoLibelle(String aoLibelle) {
		this.aoLibelle = aoLibelle;
	}

	public String getAttrs() {
		return attrs;
	}

	public void setAttrs(String attrs) {
		this.attrs = attrs;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (this == obj) return true;
		if (!(obj instanceof Destination)) return false;

		final Destination other = (Destination) obj;
		if (this.getId() != null || other.getId() != null) {
			return Objects.equals(this.getId(), other.getId());
		}

		return false;
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("id", getId())
			.add("aoCodeCountry", getAoCodeCountry())
			.add("aoLibelle", getAoLibelle())
			.omitNullValues()
			.toString();
	}
}
