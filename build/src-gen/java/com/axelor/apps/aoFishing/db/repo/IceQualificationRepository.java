package com.axelor.apps.aoFishing.db.repo;

import com.axelor.apps.aoFishing.db.IceQualification;
import com.axelor.db.JpaRepository;

public class IceQualificationRepository extends JpaRepository<IceQualification> {

	public IceQualificationRepository() {
		super(IceQualification.class);
	}

}

