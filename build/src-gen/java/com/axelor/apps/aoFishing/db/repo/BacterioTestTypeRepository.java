package com.axelor.apps.aoFishing.db.repo;

import com.axelor.apps.aoFishing.db.BacterioTestType;
import com.axelor.db.JpaRepository;

public class BacterioTestTypeRepository extends JpaRepository<BacterioTestType> {

	public BacterioTestTypeRepository() {
		super(BacterioTestType.class);
	}

}

