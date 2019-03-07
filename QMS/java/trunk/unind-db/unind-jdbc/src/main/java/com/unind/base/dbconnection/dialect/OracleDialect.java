package com.unind.base.dbconnection.dialect;

import java.sql.Types;

import org.hibernate.dialect.Oracle10gDialect;
import org.hibernate.type.StandardBasicTypes;

public class OracleDialect extends Oracle10gDialect {

	@Override
	protected void registerReverseHibernateTypeMappings() {
		super.registerReverseHibernateTypeMappings();
		registerHibernateType( Types.NVARCHAR, StandardBasicTypes.STRING.getName() );
	}
}
