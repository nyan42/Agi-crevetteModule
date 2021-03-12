package com.axelor.apps.stock.db;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.axelor.apps.aoFishing.db.Fishing;
import com.axelor.apps.base.db.Product;
import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.NameColumn;
import com.axelor.db.annotations.Widget;
import com.google.common.base.MoreObjects;

@Entity
@Table(name = "STOCK_TRACKING_NUMBER", uniqueConstraints = { @UniqueConstraint(columnNames = { "product", "trackingNumberSeq" }) }, indexes = { @Index(columnList = "product"), @Index(columnList = "trackingNumberSeq") })
public class TrackingNumber extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STOCK_TRACKING_NUMBER_SEQ")
	@SequenceGenerator(name = "STOCK_TRACKING_NUMBER_SEQ", sequenceName = "STOCK_TRACKING_NUMBER_SEQ", allocationSize = 1)
	private Long id;

	@Widget(title = "Product")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Product product;

	@Widget(title = "Warranty expiration date", readonly = true)
	private LocalDate warrantyExpirationDate;

	@Widget(title = "Perishable expiration date", readonly = true)
	private LocalDate perishableExpirationDate;

	@Widget(title = "Tracking Nbr.")
	@NameColumn
	@NotNull
	private String trackingNumberSeq;

	@Widget(title = "Counter")
	@Digits(integer = 10, fraction = 10)
	private BigDecimal counter = BigDecimal.ZERO;

	private Boolean isFishing = Boolean.FALSE;

	@Widget(title = "Fishing list")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "aoTrackingNumber", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Fishing> fishingList;

	@Widget(title = "Attributes")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "json")
	private String attrs;

	public TrackingNumber() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public LocalDate getWarrantyExpirationDate() {
		return warrantyExpirationDate;
	}

	public void setWarrantyExpirationDate(LocalDate warrantyExpirationDate) {
		this.warrantyExpirationDate = warrantyExpirationDate;
	}

	public LocalDate getPerishableExpirationDate() {
		return perishableExpirationDate;
	}

	public void setPerishableExpirationDate(LocalDate perishableExpirationDate) {
		this.perishableExpirationDate = perishableExpirationDate;
	}

	public String getTrackingNumberSeq() {
		return trackingNumberSeq;
	}

	public void setTrackingNumberSeq(String trackingNumberSeq) {
		this.trackingNumberSeq = trackingNumberSeq;
	}

	public BigDecimal getCounter() {
		return counter == null ? BigDecimal.ZERO : counter;
	}

	public void setCounter(BigDecimal counter) {
		this.counter = counter;
	}

	public Boolean getIsFishing() {
		return isFishing == null ? Boolean.FALSE : isFishing;
	}

	public void setIsFishing(Boolean isFishing) {
		this.isFishing = isFishing;
	}

	public List<Fishing> getFishingList() {
		return fishingList;
	}

	public void setFishingList(List<Fishing> fishingList) {
		this.fishingList = fishingList;
	}

	/**
	 * Add the given {@link Fishing} item to the {@code fishingList}.
	 *
	 * <p>
	 * It sets {@code item.aoTrackingNumber = this} to ensure the proper relationship.
	 * </p>
	 *
	 * @param item
	 *            the item to add
	 */
	public void addFishingListItem(Fishing item) {
		if (getFishingList() == null) {
			setFishingList(new ArrayList<>());
		}
		getFishingList().add(item);
		item.setAoTrackingNumber(this);
	}

	/**
	 * Remove the given {@link Fishing} item from the {@code fishingList}.
	 *
 	 * @param item
	 *            the item to remove
	 */
	public void removeFishingListItem(Fishing item) {
		if (getFishingList() == null) {
			return;
		}
		getFishingList().remove(item);
	}

	/**
	 * Clear the {@code fishingList} collection.
	 *
	 * <p>
	 * If you have to query {@link Fishing} records in same transaction, make
	 * sure to call {@link javax.persistence.EntityManager#flush() } to avoid
	 * unexpected errors.
	 * </p>
	 */
	public void clearFishingList() {
		if (getFishingList() != null) {
			getFishingList().clear();
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
		if (!(obj instanceof TrackingNumber)) return false;

		final TrackingNumber other = (TrackingNumber) obj;
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
			.add("warrantyExpirationDate", getWarrantyExpirationDate())
			.add("perishableExpirationDate", getPerishableExpirationDate())
			.add("trackingNumberSeq", getTrackingNumberSeq())
			.add("counter", getCounter())
			.add("isFishing", getIsFishing())
			.omitNullValues()
			.toString();
	}
}
