package com.axelor.apps.base.db.repo;

import com.axelor.apps.base.db.Company;
import com.axelor.db.JpaRepository;
import com.axelor.db.Query;

public class CompanyRepository extends JpaRepository<Company> {

	public CompanyRepository() {
		super(Company.class);
	}

	public Company findByCode(String code) {
		return Query.of(Company.class)
				.filter("self.code = :code")
				.bind("code", code)
				.fetchOne();
	}

	public Company findByName(String name) {
		return Query.of(Company.class)
				.filter("self.name = :name")
				.bind("name", name)
				.fetchOne();
	}

	@Override
	public void remove(Company entity) {

		if (entity.getAccountConfig() != null) {
			entity.getAccountConfig().setCompany(null);
		}

		if (entity.getBankPaymentConfig() != null) {
			entity.getBankPaymentConfig().setCompany(null);
		}

		if (entity.getHrConfig() != null) {
			entity.getHrConfig().setCompany(null);
		}

		if (entity.getStockConfig() != null) {
			entity.getStockConfig().setCompany(null);
		}

		if (entity.getPurchaseConfig() != null) {
			entity.getPurchaseConfig().setCompany(null);
		}

		if (entity.getCrmConfig() != null) {
			entity.getCrmConfig().setCompany(null);
		}

		if (entity.getSaleConfig() != null) {
			entity.getSaleConfig().setCompany(null);
		}

		if (entity.getSupplyChainConfig() != null) {
			entity.getSupplyChainConfig().setCompany(null);
		}

		if (entity.getProductionConfig() != null) {
			entity.getProductionConfig().setCompany(null);
		}
		super.remove(entity);
	}

}

