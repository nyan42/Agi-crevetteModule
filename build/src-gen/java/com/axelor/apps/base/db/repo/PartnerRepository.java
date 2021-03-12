package com.axelor.apps.base.db.repo;

import com.axelor.apps.base.db.Partner;
import com.axelor.db.JpaRepository;
import com.axelor.db.Query;

public class PartnerRepository extends JpaRepository<Partner> {

	public PartnerRepository() {
		super(Partner.class);
	}

	public Partner findByName(String name) {
		return Query.of(Partner.class)
				.filter("self.name = :name")
				.bind("name", name)
				.fetchOne();
	}

	public Partner findByPartnerSeq(String partnerSeq) {
		return Query.of(Partner.class)
				.filter("self.partnerSeq = :partnerSeq")
				.bind("partnerSeq", partnerSeq)
				.fetchOne();
	}

	@Override
	public void remove(Partner entity) {

		if (entity.getLinkedUser() != null) {
			entity.getLinkedUser().setPartner(null);
		}

		if (entity.getEmployee() != null) {
			entity.getEmployee().setContactPartner(null);
		}

		super.remove(entity);
	}

	public static final int PARTNER_TYPE_COMPANY = 1;
	public static final int PARTNER_TYPE_INDIVIDUAL = 2;

	public static final int PARTNER_TITLE_M = 1;
	public static final int PARTNER_TITLE_MS = 2;
	public static final int PARTNER_TITLE_DR = 3;
	public static final int PARTNER_TITLE_PROF = 4;

	// TYPE SELECT
	public static final int CHARGING_BACK_TYPE_IDENTICALLY = 1;
	public static final int CHARGING_BACK_TYPE_PRICE_LIST = 2;
	public static final int CHARGING_BACK_TYPE_PERCENTAGE = 3;
}

