package com.axelor.apps.message.db.repo;

import com.axelor.apps.message.db.EmailAddress;
import com.axelor.db.JpaRepository;
import com.axelor.db.Query;

public class EmailAddressRepository extends JpaRepository<EmailAddress> {

	public EmailAddressRepository() {
		super(EmailAddress.class);
	}

	public EmailAddress findByName(String name) {
		return Query.of(EmailAddress.class)
				.filter("self.name = :name")
				.bind("name", name)
				.fetchOne();
	}

	public EmailAddress findByAddress(String address) {
		return Query.of(EmailAddress.class)
				.filter("self.address = :address")
				.bind("address", address)
				.fetchOne();
	}

	@Override
	public void remove(EmailAddress entity) {

		if (entity.getPartner() != null) {
			entity.getPartner().setEmailAddress(null);
		}

		if (entity.getLead() != null) {
			entity.getLead().setEmailAddress(null);
		}
		super.remove(entity);
	}

}

