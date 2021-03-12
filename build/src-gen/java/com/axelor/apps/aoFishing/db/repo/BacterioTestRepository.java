package com.axelor.apps.aoFishing.db.repo;

import com.axelor.apps.aoFishing.db.BacterioTest;
import com.axelor.db.JpaRepository;

public class BacterioTestRepository extends JpaRepository<BacterioTest> {

	public BacterioTestRepository() {
		super(BacterioTest.class);
	}

}

