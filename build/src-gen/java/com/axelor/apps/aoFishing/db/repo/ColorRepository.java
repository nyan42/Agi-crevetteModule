package com.axelor.apps.aoFishing.db.repo;

import com.axelor.apps.aoFishing.db.Color;
import com.axelor.db.JpaRepository;

public class ColorRepository extends JpaRepository<Color> {

	public ColorRepository() {
		super(Color.class);
	}

}

