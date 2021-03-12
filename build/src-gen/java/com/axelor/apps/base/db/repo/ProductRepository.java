package com.axelor.apps.base.db.repo;

import com.axelor.apps.base.db.Product;
import com.axelor.db.JpaRepository;
import com.axelor.db.Query;

public class ProductRepository extends JpaRepository<Product> {

	public ProductRepository() {
		super(Product.class);
	}

	public Product findByCode(String code) {
		return Query.of(Product.class)
				.filter("self.code = :code")
				.bind("code", code)
				.fetchOne();
	}

	public Product findByName(String name) {
		return Query.of(Product.class)
				.filter("self.name = :name")
				.bind("name", name)
				.fetchOne();
	}

	@Override
	public void remove(Product entity) {

		if (entity.getConfigurator() != null) {
			entity.getConfigurator().setProduct(null);
		}

		super.remove(entity);
	}

	// PRODUCT TYPE SELECT
	public static final String PRODUCT_TYPE_SERVICE = "service";
	public static final String PRODUCT_TYPE_STORABLE = "storable";

	// SALE SUPPLY SELECT
	public static final int SALE_SUPPLY_FROM_STOCK = 1;
	public static final int SALE_SUPPLY_PURCHASE = 2;
	public static final int SALE_SUPPLY_PRODUCE = 3;

	public static final String PROCUREMENT_METHOD_BUY = "buy";
	public static final String PROCUREMENT_METHOD_PRODUCE = "produce";
	public static final String PROCUREMENT_METHOD_BUYANDPRODUCE = "buyAndProduce";

	public static final int COST_TYPE_STANDARD = 1;
	public static final int COST_TYPE_LAST_PURCHASE_PRICE = 2;
	public static final int COST_TYPE_AVERAGE_PRICE = 3;
	public static final int COST_TYPE_LAST_PRODUCTION_PRICE = 4;

	// PRODUCT SUB-TYPE SELECT
	public static final int PRODUCT_SUB_TYPE_FINISHED_PRODUCT = 1;
	public static final int PRODUCT_SUB_TYPE_SEMI_FINISHED_PRODUCT = 2;
	public static final int PRODUCT_SUB_TYPE_COMPONENT = 3;

	// PRICE METHOD SELECT
	public static final int PRICE_METHOD_FORECAST = 1;
	public static final int PRICE_METHOD_REAL = 2;

	// COMPONENTS VALUATION METHOD SELECt
	public static final int COMPONENTS_VALUATION_METHOD_AVERAGE = 1;

	public static final int COMPONENTS_VALUATION_METHOD_COST = 2;
}

