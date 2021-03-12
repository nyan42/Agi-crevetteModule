package com.axelor.apps.aoFishing.db;

import java.math.BigDecimal;
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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.axelor.apps.stock.db.TrackingNumber;
import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.Widget;
import com.google.common.base.MoreObjects;

@Entity
@Table(name = "AO_FISHING_QUALITY_CHECK", indexes = { @Index(columnList = "ao_tracking_number") })
public class QualityCheck extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AO_FISHING_QUALITY_CHECK_SEQ")
	@SequenceGenerator(name = "AO_FISHING_QUALITY_CHECK_SEQ", sequenceName = "AO_FISHING_QUALITY_CHECK_SEQ", allocationSize = 1)
	private Long id;

	@Widget(title = "Tracking number")
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private TrackingNumber aoTrackingNumber;

	@Widget(title = "Product code")
	@NotNull
	private String aoProductCode;

	@Widget(title = "Quantity")
	private Long aoQuantity = 0L;

	@Widget(title = "Quantity remaining")
	private String aoQuantityRemaining;

	@Widget(title = "Dehulling")
	private BigDecimal aoDehulling = BigDecimal.ZERO;

	@Widget(title = "Melanose")
	private BigDecimal aoMelanose = BigDecimal.ZERO;

	@Widget(title = "Net weight")
	private BigDecimal aoPoidsNet = BigDecimal.ZERO;

	@Widget(title = "Brut weight")
	private BigDecimal aoPoidsBrut = BigDecimal.ZERO;

	@Widget(title = "Soft")
	private BigDecimal aoSoft = BigDecimal.ZERO;

	@Widget(title = "Raw burst head")
	private BigDecimal aoRawBurstHead = BigDecimal.ZERO;

	@Widget(title = "Broken")
	private BigDecimal aoBroken = BigDecimal.ZERO;

	@Widget(title = "Green paws")
	private BigDecimal aoGreenPaws = BigDecimal.ZERO;

	@Widget(title = "Gills defect")
	private BigDecimal aoGillsDefect = BigDecimal.ZERO;

	@Widget(title = "Scar")
	private BigDecimal aoScar = BigDecimal.ZERO;

	@Widget(title = "Cooked burst head")
	private BigDecimal aoCookedBurstHead = BigDecimal.ZERO;

	@Widget(title = "Cooked defect")
	private BigDecimal aoCookDefect = BigDecimal.ZERO;

	@Widget(title = "Defect quantity")
	private BigDecimal aoDefectQuantity = BigDecimal.ZERO;

	@Widget(title = "Carto")
	private BigDecimal aoCarto = BigDecimal.ZERO;

	@Widget(title = "Red head")
	private BigDecimal aoRedHead = BigDecimal.ZERO;

	@Widget(title = "Red paws")
	private BigDecimal aoRedPaws = BigDecimal.ZERO;

	@Widget(title = "Good")
	private BigDecimal aoGood = BigDecimal.ZERO;

	@Widget(title = "Caliber")
	private Long aoCaliber = 0L;

	@Widget(title = "Uniformity")
	private BigDecimal aoUniformity = BigDecimal.ZERO;

	@Widget(title = "Shrimp number")
	private BigDecimal aoNumber = BigDecimal.ZERO;

	@Widget(title = "Observation")
	private String aoObservation;

	@Widget(title = "Attributes")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "json")
	private String attrs;

	public QualityCheck() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public TrackingNumber getAoTrackingNumber() {
		return aoTrackingNumber;
	}

	public void setAoTrackingNumber(TrackingNumber aoTrackingNumber) {
		this.aoTrackingNumber = aoTrackingNumber;
	}

	public String getAoProductCode() {
		return aoProductCode;
	}

	public void setAoProductCode(String aoProductCode) {
		this.aoProductCode = aoProductCode;
	}

	public Long getAoQuantity() {
		return aoQuantity == null ? 0L : aoQuantity;
	}

	public void setAoQuantity(Long aoQuantity) {
		this.aoQuantity = aoQuantity;
	}

	public String getAoQuantityRemaining() {
		return aoQuantityRemaining;
	}

	public void setAoQuantityRemaining(String aoQuantityRemaining) {
		this.aoQuantityRemaining = aoQuantityRemaining;
	}

	public BigDecimal getAoDehulling() {
		return aoDehulling == null ? BigDecimal.ZERO : aoDehulling;
	}

	public void setAoDehulling(BigDecimal aoDehulling) {
		this.aoDehulling = aoDehulling;
	}

	public BigDecimal getAoMelanose() {
		return aoMelanose == null ? BigDecimal.ZERO : aoMelanose;
	}

	public void setAoMelanose(BigDecimal aoMelanose) {
		this.aoMelanose = aoMelanose;
	}

	public BigDecimal getAoPoidsNet() {
		return aoPoidsNet == null ? BigDecimal.ZERO : aoPoidsNet;
	}

	public void setAoPoidsNet(BigDecimal aoPoidsNet) {
		this.aoPoidsNet = aoPoidsNet;
	}

	public BigDecimal getAoPoidsBrut() {
		return aoPoidsBrut == null ? BigDecimal.ZERO : aoPoidsBrut;
	}

	public void setAoPoidsBrut(BigDecimal aoPoidsBrut) {
		this.aoPoidsBrut = aoPoidsBrut;
	}

	public BigDecimal getAoSoft() {
		return aoSoft == null ? BigDecimal.ZERO : aoSoft;
	}

	public void setAoSoft(BigDecimal aoSoft) {
		this.aoSoft = aoSoft;
	}

	public BigDecimal getAoRawBurstHead() {
		return aoRawBurstHead == null ? BigDecimal.ZERO : aoRawBurstHead;
	}

	public void setAoRawBurstHead(BigDecimal aoRawBurstHead) {
		this.aoRawBurstHead = aoRawBurstHead;
	}

	public BigDecimal getAoBroken() {
		return aoBroken == null ? BigDecimal.ZERO : aoBroken;
	}

	public void setAoBroken(BigDecimal aoBroken) {
		this.aoBroken = aoBroken;
	}

	public BigDecimal getAoGreenPaws() {
		return aoGreenPaws == null ? BigDecimal.ZERO : aoGreenPaws;
	}

	public void setAoGreenPaws(BigDecimal aoGreenPaws) {
		this.aoGreenPaws = aoGreenPaws;
	}

	public BigDecimal getAoGillsDefect() {
		return aoGillsDefect == null ? BigDecimal.ZERO : aoGillsDefect;
	}

	public void setAoGillsDefect(BigDecimal aoGillsDefect) {
		this.aoGillsDefect = aoGillsDefect;
	}

	public BigDecimal getAoScar() {
		return aoScar == null ? BigDecimal.ZERO : aoScar;
	}

	public void setAoScar(BigDecimal aoScar) {
		this.aoScar = aoScar;
	}

	public BigDecimal getAoCookedBurstHead() {
		return aoCookedBurstHead == null ? BigDecimal.ZERO : aoCookedBurstHead;
	}

	public void setAoCookedBurstHead(BigDecimal aoCookedBurstHead) {
		this.aoCookedBurstHead = aoCookedBurstHead;
	}

	public BigDecimal getAoCookDefect() {
		return aoCookDefect == null ? BigDecimal.ZERO : aoCookDefect;
	}

	public void setAoCookDefect(BigDecimal aoCookDefect) {
		this.aoCookDefect = aoCookDefect;
	}

	public BigDecimal getAoDefectQuantity() {
		return aoDefectQuantity == null ? BigDecimal.ZERO : aoDefectQuantity;
	}

	public void setAoDefectQuantity(BigDecimal aoDefectQuantity) {
		this.aoDefectQuantity = aoDefectQuantity;
	}

	public BigDecimal getAoCarto() {
		return aoCarto == null ? BigDecimal.ZERO : aoCarto;
	}

	public void setAoCarto(BigDecimal aoCarto) {
		this.aoCarto = aoCarto;
	}

	public BigDecimal getAoRedHead() {
		return aoRedHead == null ? BigDecimal.ZERO : aoRedHead;
	}

	public void setAoRedHead(BigDecimal aoRedHead) {
		this.aoRedHead = aoRedHead;
	}

	public BigDecimal getAoRedPaws() {
		return aoRedPaws == null ? BigDecimal.ZERO : aoRedPaws;
	}

	public void setAoRedPaws(BigDecimal aoRedPaws) {
		this.aoRedPaws = aoRedPaws;
	}

	public BigDecimal getAoGood() {
		return aoGood == null ? BigDecimal.ZERO : aoGood;
	}

	public void setAoGood(BigDecimal aoGood) {
		this.aoGood = aoGood;
	}

	public Long getAoCaliber() {
		return aoCaliber == null ? 0L : aoCaliber;
	}

	public void setAoCaliber(Long aoCaliber) {
		this.aoCaliber = aoCaliber;
	}

	public BigDecimal getAoUniformity() {
		return aoUniformity == null ? BigDecimal.ZERO : aoUniformity;
	}

	public void setAoUniformity(BigDecimal aoUniformity) {
		this.aoUniformity = aoUniformity;
	}

	public BigDecimal getAoNumber() {
		return aoNumber == null ? BigDecimal.ZERO : aoNumber;
	}

	public void setAoNumber(BigDecimal aoNumber) {
		this.aoNumber = aoNumber;
	}

	public String getAoObservation() {
		return aoObservation;
	}

	public void setAoObservation(String aoObservation) {
		this.aoObservation = aoObservation;
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
		if (!(obj instanceof QualityCheck)) return false;

		final QualityCheck other = (QualityCheck) obj;
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
			.add("aoProductCode", getAoProductCode())
			.add("aoQuantity", getAoQuantity())
			.add("aoQuantityRemaining", getAoQuantityRemaining())
			.add("aoDehulling", getAoDehulling())
			.add("aoMelanose", getAoMelanose())
			.add("aoPoidsNet", getAoPoidsNet())
			.add("aoPoidsBrut", getAoPoidsBrut())
			.add("aoSoft", getAoSoft())
			.add("aoRawBurstHead", getAoRawBurstHead())
			.add("aoBroken", getAoBroken())
			.add("aoGreenPaws", getAoGreenPaws())
			.omitNullValues()
			.toString();
	}
}
