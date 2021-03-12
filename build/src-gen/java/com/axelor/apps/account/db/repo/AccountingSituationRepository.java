package com.axelor.apps.account.db.repo;

import com.axelor.apps.account.db.AccountingSituation;
import com.axelor.db.JpaRepository;
import com.axelor.db.Query;

public class AccountingSituationRepository extends JpaRepository<AccountingSituation> {

	public AccountingSituationRepository() {
		super(AccountingSituation.class);
	}

	public AccountingSituation findByName(String name) {
		return Query.of(AccountingSituation.class)
				.filter("self.name = :name")
				.bind("name", name)
				.fetchOne();
	}

	@Override
	public void remove(AccountingSituation entity) {

		if (entity.getDebtRecovery() != null) {
			entity.getDebtRecovery().setAccountingSituation(null);
		}

		super.remove(entity);
	}

}

