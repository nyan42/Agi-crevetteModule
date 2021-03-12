package com.axelor.apps.aoFishing.db;

import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.NameColumn;
import com.axelor.db.annotations.Widget;
import com.google.common.base.MoreObjects;

@Entity
@Table(name = "AO_FISHING_ICE_QUALIFICATION", indexes = { @Index(columnList = "aoQuantity") })
public class IceQualification extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AO_FISHING_ICE_QUALIFICATION_SEQ")
	@SequenceGenerator(name = "AO_FISHING_ICE_QUALIFICATION_SEQ", sequenceName = "AO_FISHING_ICE_QUALIFICATION_SEQ", allocationSize = 1)
	private Long id;

	@Widget(title = "Quantity")
	@NameColumn
	private String aoQuantity;

	@Widget(title = "Description")
	private String aoDescription;

	@Widget(title = "Attributes")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "json")
	private String attrs;

	public IceQualification() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getAoQuantity() {
		return aoQuantity;
	}

	public void setAoQuantity(String aoQuantity) {
		this.aoQuantity = aoQuantity;
	}

	public String getAoDescription() {
		return aoDescription;
	}

	public void setAoDescription(String aoDescription) {
		this.aoDescription = aoDescription;
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
		if (!(obj instanceof IceQualification)) return false;

		final IceQualification other = (IceQualification) obj;
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
			.add("aoQuantity", getAoQuantity())
			.add("aoDescription", getAoDescription())
			.omitNullValues()
			.toString();
	}
}
