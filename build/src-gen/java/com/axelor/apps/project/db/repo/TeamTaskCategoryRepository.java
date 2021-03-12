package com.axelor.apps.project.db.repo;

import com.axelor.apps.project.db.TeamTaskCategory;
import com.axelor.db.JpaRepository;
import com.axelor.db.Query;

public class TeamTaskCategoryRepository extends JpaRepository<TeamTaskCategory> {

	public TeamTaskCategoryRepository() {
		super(TeamTaskCategory.class);
	}

	public TeamTaskCategory findByName(String name) {
		return Query.of(TeamTaskCategory.class)
				.filter("self.name = :name")
				.bind("name", name)
				.fetchOne();
	}

}

