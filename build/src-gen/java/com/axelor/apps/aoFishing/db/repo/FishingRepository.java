package com.axelor.apps.aoFishing.db.repo;

import com.axelor.apps.aoFishing.db.Fishing;
import com.axelor.db.JpaRepository;

public class FishingRepository extends JpaRepository<Fishing> {

	public FishingRepository() {
		super(Fishing.class);
	}

}

