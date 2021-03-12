package com.axelor.apps.aoFishing.db;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
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
@Table(name = "AO_FISHING_FISHING", indexes = { @Index(columnList = "ao_tracking_number"), @Index(columnList = "ao_ice"), @Index(columnList = "ao_color") })
public class Fishing extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AO_FISHING_FISHING_SEQ")
	@SequenceGenerator(name = "AO_FISHING_FISHING_SEQ", sequenceName = "AO_FISHING_FISHING_SEQ", allocationSize = 1)
	private Long id;

	@Widget(title = "Tracking number")
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private TrackingNumber aoTrackingNumber;

	@Widget(title = "Sampling hour")
	private LocalTime aoSamplingHour;

	@Widget(title = "Receiving hour")
	private LocalTime aoReceivingHour;

	@Widget(title = "Fishing start hour")
	private LocalTime aoFishingStartHour;

	@Widget(title = "Fishing end hour")
	private LocalTime aoFishingEndHour;

	@Widget(title = "Fishing date")
	private LocalDate aoFishingDate;

	@Widget(title = "Ice")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private IceQualification aoIce;

	@Widget(title = "Shrimp temperature")
	private BigDecimal aoShrimpTemp = BigDecimal.ZERO;

	@Widget(title = "Cleanliness")
	private Boolean aoCleanliness = Boolean.FALSE;

	@Widget(title = "Chemical calibration")
	private BigDecimal aoChemicalCalibration = BigDecimal.ZERO;

	@Widget(title = "Observation")
	private String aoObservation;

	@Widget(title = "Color")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Color aoColor;

	@Widget(title = "Darken")
	private BigDecimal aoDarken = BigDecimal.ZERO;

	@Widget(title = "Closed Meta")
	private Long aoClosedMeta = 0L;

	@Widget(title = "Meta1")
	private Long aoMeta1 = 0L;

	@Widget(title = "Meta2")
	private Long aoMeta2 = 0L;

	@Widget(title = "Meta3")
	private Long aoMeta3 = 0L;

	@Widget(title = "Meta4")
	private Long aoMeta4 = 0L;

	@Widget(title = "Ended meta")
	private Long aoEndedMeta = 0L;

	@Widget(title = "Smell")
	private BigDecimal aoSmell = BigDecimal.ZERO;

	@Widget(title = "Dehulling")
	private BigDecimal aoDehulling = BigDecimal.ZERO;

	@Widget(title = "Certification")
	private Boolean aoCertification = Boolean.FALSE;

	@Widget(title = "Standard unit")
	private Long aoStandardUnit = 0L;

	@Widget(title = "Good")
	private BigDecimal aoGood = BigDecimal.ZERO;

	@Widget(title = "Premium")
	private BigDecimal aoPremium = BigDecimal.ZERO;

	@Widget(title = "Cheap")
	private BigDecimal aoCheap = BigDecimal.ZERO;

	@Widget(title = "Caliber")
	private Long aoCaliber = 0L;

	@Widget(title = "Soft")
	private BigDecimal aoSoft = BigDecimal.ZERO;

	@Widget(title = "Carto")
	private BigDecimal aoCarto = BigDecimal.ZERO;

	@Widget(title = "Melanose")
	private BigDecimal aoMelanose = BigDecimal.ZERO;

	@Widget(title = "Gills defect")
	private BigDecimal aoGillsDefect = BigDecimal.ZERO;

	@Widget(title = "Red head start")
	private BigDecimal aoRedHeadStart = BigDecimal.ZERO;

	@Widget(title = "Red head")
	private BigDecimal aoRedHead = BigDecimal.ZERO;

	@Widget(title = "Raw burst head")
	private BigDecimal aoRawBurstHead = BigDecimal.ZERO;

	@Widget(title = "Red paws")
	private BigDecimal aoRedPaws = BigDecimal.ZERO;

	@Widget(title = "Green paws")
	private BigDecimal aoGreenPaws = BigDecimal.ZERO;

	@Widget(title = "Small scar")
	private BigDecimal aoSmallScar = BigDecimal.ZERO;

	@Widget(title = "Big scar")
	private BigDecimal aoBigScar = BigDecimal.ZERO;

	@Widget(title = "Distorted")
	private BigDecimal aoDistorted = BigDecimal.ZERO;

	@Widget(title = "Broken")
	private BigDecimal aoBroken = BigDecimal.ZERO;

	@Widget(title = "Batch state code")
	private Boolean aoBatchStateCode = Boolean.FALSE;

	@Widget(title = "Bacterio test")
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<BacterioTest> aoBacterio;

	@Widget(title = "Attributes")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "json")
	private String attrs;

	public Fishing() {
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

	public LocalTime getAoSamplingHour() {
		return aoSamplingHour;
	}

	public void setAoSamplingHour(LocalTime aoSamplingHour) {
		this.aoSamplingHour = aoSamplingHour;
	}

	public LocalTime getAoReceivingHour() {
		return aoReceivingHour;
	}

	public void setAoReceivingHour(LocalTime aoReceivingHour) {
		this.aoReceivingHour = aoReceivingHour;
	}

	public LocalTime getAoFishingStartHour() {
		return aoFishingStartHour;
	}

	public void setAoFishingStartHour(LocalTime aoFishingStartHour) {
		this.aoFishingStartHour = aoFishingStartHour;
	}

	public LocalTime getAoFishingEndHour() {
		return aoFishingEndHour;
	}

	public void setAoFishingEndHour(LocalTime aoFishingEndHour) {
		this.aoFishingEndHour = aoFishingEndHour;
	}

	public LocalDate getAoFishingDate() {
		return aoFishingDate;
	}

	public void setAoFishingDate(LocalDate aoFishingDate) {
		this.aoFishingDate = aoFishingDate;
	}

	public IceQualification getAoIce() {
		return aoIce;
	}

	public void setAoIce(IceQualification aoIce) {
		this.aoIce = aoIce;
	}

	public BigDecimal getAoShrimpTemp() {
		return aoShrimpTemp == null ? BigDecimal.ZERO : aoShrimpTemp;
	}

	public void setAoShrimpTemp(BigDecimal aoShrimpTemp) {
		this.aoShrimpTemp = aoShrimpTemp;
	}

	public Boolean getAoCleanliness() {
		return aoCleanliness == null ? Boolean.FALSE : aoCleanliness;
	}

	public void setAoCleanliness(Boolean aoCleanliness) {
		this.aoCleanliness = aoCleanliness;
	}

	public BigDecimal getAoChemicalCalibration() {
		return aoChemicalCalibration == null ? BigDecimal.ZERO : aoChemicalCalibration;
	}

	public void setAoChemicalCalibration(BigDecimal aoChemicalCalibration) {
		this.aoChemicalCalibration = aoChemicalCalibration;
	}

	public String getAoObservation() {
		return aoObservation;
	}

	public void setAoObservation(String aoObservation) {
		this.aoObservation = aoObservation;
	}

	public Color getAoColor() {
		return aoColor;
	}

	public void setAoColor(Color aoColor) {
		this.aoColor = aoColor;
	}

	public BigDecimal getAoDarken() {
		return aoDarken == null ? BigDecimal.ZERO : aoDarken;
	}

	public void setAoDarken(BigDecimal aoDarken) {
		this.aoDarken = aoDarken;
	}

	public Long getAoClosedMeta() {
		return aoClosedMeta == null ? 0L : aoClosedMeta;
	}

	public void setAoClosedMeta(Long aoClosedMeta) {
		this.aoClosedMeta = aoClosedMeta;
	}

	public Long getAoMeta1() {
		return aoMeta1 == null ? 0L : aoMeta1;
	}

	public void setAoMeta1(Long aoMeta1) {
		this.aoMeta1 = aoMeta1;
	}

	public Long getAoMeta2() {
		return aoMeta2 == null ? 0L : aoMeta2;
	}

	public void setAoMeta2(Long aoMeta2) {
		this.aoMeta2 = aoMeta2;
	}

	public Long getAoMeta3() {
		return aoMeta3 == null ? 0L : aoMeta3;
	}

	public void setAoMeta3(Long aoMeta3) {
		this.aoMeta3 = aoMeta3;
	}

	public Long getAoMeta4() {
		return aoMeta4 == null ? 0L : aoMeta4;
	}

	public void setAoMeta4(Long aoMeta4) {
		this.aoMeta4 = aoMeta4;
	}

	public Long getAoEndedMeta() {
		return aoEndedMeta == null ? 0L : aoEndedMeta;
	}

	public void setAoEndedMeta(Long aoEndedMeta) {
		this.aoEndedMeta = aoEndedMeta;
	}

	public BigDecimal getAoSmell() {
		return aoSmell == null ? BigDecimal.ZERO : aoSmell;
	}

	public void setAoSmell(BigDecimal aoSmell) {
		this.aoSmell = aoSmell;
	}

	public BigDecimal getAoDehulling() {
		return aoDehulling == null ? BigDecimal.ZERO : aoDehulling;
	}

	public void setAoDehulling(BigDecimal aoDehulling) {
		this.aoDehulling = aoDehulling;
	}

	public Boolean getAoCertification() {
		return aoCertification == null ? Boolean.FALSE : aoCertification;
	}

	public void setAoCertification(Boolean aoCertification) {
		this.aoCertification = aoCertification;
	}

	public Long getAoStandardUnit() {
		return aoStandardUnit == null ? 0L : aoStandardUnit;
	}

	public void setAoStandardUnit(Long aoStandardUnit) {
		this.aoStandardUnit = aoStandardUnit;
	}

	public BigDecimal getAoGood() {
		return aoGood == null ? BigDecimal.ZERO : aoGood;
	}

	public void setAoGood(BigDecimal aoGood) {
		this.aoGood = aoGood;
	}

	public BigDecimal getAoPremium() {
		return aoPremium == null ? BigDecimal.ZERO : aoPremium;
	}

	public void setAoPremium(BigDecimal aoPremium) {
		this.aoPremium = aoPremium;
	}

	public BigDecimal getAoCheap() {
		return aoCheap == null ? BigDecimal.ZERO : aoCheap;
	}

	public void setAoCheap(BigDecimal aoCheap) {
		this.aoCheap = aoCheap;
	}

	public Long getAoCaliber() {
		return aoCaliber == null ? 0L : aoCaliber;
	}

	public void setAoCaliber(Long aoCaliber) {
		this.aoCaliber = aoCaliber;
	}

	public BigDecimal getAoSoft() {
		return aoSoft == null ? BigDecimal.ZERO : aoSoft;
	}

	public void setAoSoft(BigDecimal aoSoft) {
		this.aoSoft = aoSoft;
	}

	public BigDecimal getAoCarto() {
		return aoCarto == null ? BigDecimal.ZERO : aoCarto;
	}

	public void setAoCarto(BigDecimal aoCarto) {
		this.aoCarto = aoCarto;
	}

	public BigDecimal getAoMelanose() {
		return aoMelanose == null ? BigDecimal.ZERO : aoMelanose;
	}

	public void setAoMelanose(BigDecimal aoMelanose) {
		this.aoMelanose = aoMelanose;
	}

	public BigDecimal getAoGillsDefect() {
		return aoGillsDefect == null ? BigDecimal.ZERO : aoGillsDefect;
	}

	public void setAoGillsDefect(BigDecimal aoGillsDefect) {
		this.aoGillsDefect = aoGillsDefect;
	}

	public BigDecimal getAoRedHeadStart() {
		return aoRedHeadStart == null ? BigDecimal.ZERO : aoRedHeadStart;
	}

	public void setAoRedHeadStart(BigDecimal aoRedHeadStart) {
		this.aoRedHeadStart = aoRedHeadStart;
	}

	public BigDecimal getAoRedHead() {
		return aoRedHead == null ? BigDecimal.ZERO : aoRedHead;
	}

	public void setAoRedHead(BigDecimal aoRedHead) {
		this.aoRedHead = aoRedHead;
	}

	public BigDecimal getAoRawBurstHead() {
		return aoRawBurstHead == null ? BigDecimal.ZERO : aoRawBurstHead;
	}

	public void setAoRawBurstHead(BigDecimal aoRawBurstHead) {
		this.aoRawBurstHead = aoRawBurstHead;
	}

	public BigDecimal getAoRedPaws() {
		return aoRedPaws == null ? BigDecimal.ZERO : aoRedPaws;
	}

	public void setAoRedPaws(BigDecimal aoRedPaws) {
		this.aoRedPaws = aoRedPaws;
	}

	public BigDecimal getAoGreenPaws() {
		return aoGreenPaws == null ? BigDecimal.ZERO : aoGreenPaws;
	}

	public void setAoGreenPaws(BigDecimal aoGreenPaws) {
		this.aoGreenPaws = aoGreenPaws;
	}

	public BigDecimal getAoSmallScar() {
		return aoSmallScar == null ? BigDecimal.ZERO : aoSmallScar;
	}

	public void setAoSmallScar(BigDecimal aoSmallScar) {
		this.aoSmallScar = aoSmallScar;
	}

	public BigDecimal getAoBigScar() {
		return aoBigScar == null ? BigDecimal.ZERO : aoBigScar;
	}

	public void setAoBigScar(BigDecimal aoBigScar) {
		this.aoBigScar = aoBigScar;
	}

	public BigDecimal getAoDistorted() {
		return aoDistorted == null ? BigDecimal.ZERO : aoDistorted;
	}

	public void setAoDistorted(BigDecimal aoDistorted) {
		this.aoDistorted = aoDistorted;
	}

	public BigDecimal getAoBroken() {
		return aoBroken == null ? BigDecimal.ZERO : aoBroken;
	}

	public void setAoBroken(BigDecimal aoBroken) {
		this.aoBroken = aoBroken;
	}

	public Boolean getAoBatchStateCode() {
		return aoBatchStateCode == null ? Boolean.FALSE : aoBatchStateCode;
	}

	public void setAoBatchStateCode(Boolean aoBatchStateCode) {
		this.aoBatchStateCode = aoBatchStateCode;
	}

	public Set<BacterioTest> getAoBacterio() {
		return aoBacterio;
	}

	public void setAoBacterio(Set<BacterioTest> aoBacterio) {
		this.aoBacterio = aoBacterio;
	}

	/**
	 * Add the given {@link BacterioTest} item to the {@code aoBacterio}.
	 *
	 * @param item
	 *            the item to add
	 */
	public void addAoBacterio(BacterioTest item) {
		if (getAoBacterio() == null) {
			setAoBacterio(new HashSet<>());
		}
		getAoBacterio().add(item);
	}

	/**
	 * Remove the given {@link BacterioTest} item from the {@code aoBacterio}.
	 *
 	 * @param item
	 *            the item to remove
	 */
	public void removeAoBacterio(BacterioTest item) {
		if (getAoBacterio() == null) {
			return;
		}
		getAoBacterio().remove(item);
	}

	/**
	 * Clear the {@code aoBacterio} collection.
	 *
	 */
	public void clearAoBacterio() {
		if (getAoBacterio() != null) {
			getAoBacterio().clear();
		}
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
		if (!(obj instanceof Fishing)) return false;

		final Fishing other = (Fishing) obj;
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
			.add("aoSamplingHour", getAoSamplingHour())
			.add("aoReceivingHour", getAoReceivingHour())
			.add("aoFishingStartHour", getAoFishingStartHour())
			.add("aoFishingEndHour", getAoFishingEndHour())
			.add("aoFishingDate", getAoFishingDate())
			.add("aoShrimpTemp", getAoShrimpTemp())
			.add("aoCleanliness", getAoCleanliness())
			.add("aoChemicalCalibration", getAoChemicalCalibration())
			.add("aoObservation", getAoObservation())
			.add("aoDarken", getAoDarken())
			.add("aoClosedMeta", getAoClosedMeta())
			.omitNullValues()
			.toString();
	}
}
