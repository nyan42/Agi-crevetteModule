package com.axelor.apps.aoFishing.db.repo;

import com.axelor.apps.aoFishing.db.Destination;
import com.axelor.db.JpaRepository;

public class DestinationRepository extends JpaRepository<Destination> {

	public DestinationRepository() {
		super(Destination.class);
	}

}

