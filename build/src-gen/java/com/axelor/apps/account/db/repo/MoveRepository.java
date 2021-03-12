package com.axelor.apps.account.db.repo;

import com.axelor.apps.account.db.Move;
import com.axelor.db.JpaRepository;

public class MoveRepository extends JpaRepository<Move> {

	public MoveRepository() {
		super(Move.class);
	}

	// STATUS SELECT
	public static final int STATUS_NEW = 1;
	public static final int STATUS_DAYBOOK = 2;
	public static final int STATUS_VALIDATED = 3;
	public static final int STATUS_CANCELED = 4;

	// TECHNICAL ORIGIN SELECT
	public static final int TECHNICAL_ORIGIN_ENTRY = 1;
	public static final int TECHNICAL_ORIGIN_AUTOMATIC = 2;
	public static final int TECHNICAL_ORIGIN_TEMPLATE = 3;
	public static final int TECHNICAL_ORIGIN_IMPORT = 4;

	// REVERSE DATE OF REVERSION
	public static final int DATE_OF_REVERSION_TODAY = 1;
	public static final int DATE_OF_REVERSION_ORIGINAL_MOVE_DATE = 2;
	public static final int DATE_OF_REVERSION_CHOOSE_DATE = 3;

	// FUNCTIONAL ORIGIN SELECT
	public static final int FUNCTIONAL_ORIGIN_OPENING = 1;
	public static final int FUNCTIONAL_ORIGIN_CLOSURE = 2;
}

