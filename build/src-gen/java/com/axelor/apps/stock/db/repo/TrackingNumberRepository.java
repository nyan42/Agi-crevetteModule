package com.axelor.apps.stock.db.repo;

import com.axelor.apps.stock.db.TrackingNumber;
import com.axelor.db.JpaRepository;
import com.axelor.db.Query;

public class TrackingNumberRepository extends JpaRepository<TrackingNumber> {

	public TrackingNumberRepository() {
		super(TrackingNumber.class);
	}

	public TrackingNumber findBySeq(String trackingNumberSeq) {
		return Query.of(TrackingNumber.class)
				.filter("self.trackingNumberSeq = :trackingNumberSeq")
				.bind("trackingNumberSeq", trackingNumberSeq)
				.fetchOne();
	}

}

