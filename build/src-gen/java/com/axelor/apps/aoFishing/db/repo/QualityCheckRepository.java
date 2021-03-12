package com.axelor.apps.aoFishing.db.repo;

import com.axelor.apps.aoFishing.db.QualityCheck;
import com.axelor.db.JpaRepository;

public class QualityCheckRepository extends JpaRepository<QualityCheck> {

	public QualityCheckRepository() {
		super(QualityCheck.class);
	}

}

