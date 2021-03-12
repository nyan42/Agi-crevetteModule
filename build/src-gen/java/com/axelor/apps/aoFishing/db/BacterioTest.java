package com.axelor.apps.aoFishing.db;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.NameColumn;
import com.axelor.db.annotations.Widget;
import com.google.common.base.MoreObjects;

@Entity
@Table(name = "AO_FISHING_BACTERIO_TEST", indexes = { @Index(columnList = "aoName"), @Index(columnList = "ao_bacterio_test_type") })
public class BacterioTest extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AO_FISHING_BACTERIO_TEST_SEQ")
	@SequenceGenerator(name = "AO_FISHING_BACTERIO_TEST_SEQ", sequenceName = "AO_FISHING_BACTERIO_TEST_SEQ", allocationSize = 1)
	private Long id;

	@Widget(title = "Name")
	@NameColumn
	private String aoName;

	@Widget(title = "Type")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private BacterioTestType aoBacterioTestType;

	@Widget(title = "Result")
	private String aoResult;

	@Widget(title = "Date")
	private LocalDate aoDate;

	@Widget(title = "Attributes")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "json")
	private String attrs;

	public BacterioTest() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getAoName() {
		return aoName;
	}

	public void setAoName(String aoName) {
		this.aoName = aoName;
	}

	public BacterioTestType getAoBacterioTestType() {
		return aoBacterioTestType;
	}

	public void setAoBacterioTestType(BacterioTestType aoBacterioTestType) {
		this.aoBacterioTestType = aoBacterioTestType;
	}

	public String getAoResult() {
		return aoResult;
	}

	public void setAoResult(String aoResult) {
		this.aoResult = aoResult;
	}

	public LocalDate getAoDate() {
		return aoDate;
	}

	public void setAoDate(LocalDate aoDate) {
		this.aoDate = aoDate;
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
		if (!(obj instanceof BacterioTest)) return false;

		final BacterioTest other = (BacterioTest) obj;
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
			.add("aoName", getAoName())
			.add("aoResult", getAoResult())
			.add("aoDate", getAoDate())
			.omitNullValues()
			.toString();
	}
}
