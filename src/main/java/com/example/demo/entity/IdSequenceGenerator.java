package com.example.demo.entity;

import java.io.Serializable;

import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

public class IdSequenceGenerator extends SequenceStyleGenerator {

	public static final String VALUE_PREFIX_PARAMETER = "valuePrefix";
	public static final String VALUE_PREFIX_DEFAULT = "";
	private String valuePrefix;

	public static final String NUMBER_FORMAT_PARAMETER = "numberFormat";
	public static final String NUMBER_FORMAT_DEFAULT = "%d";
	private String numberFormat;

	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

		if (object instanceof User) {
			User user = (User) object;
			final String  roleSubString =user.getRole().substring(0, 3);
			final String createdAtSubString=user.getCreatedAt().substring(8, 10);
			final String sexeSubString=user.getSexe().substring(0, 1);
					valuePrefix = roleSubString
					+ createdAtSubString + sexeSubString;
		}
		return valuePrefix + String.format(numberFormat, super.generate(session, object));
	}

	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
		super.configure(LongType.INSTANCE, params, serviceRegistry);
		valuePrefix = ConfigurationHelper.getString(VALUE_PREFIX_PARAMETER, params, VALUE_PREFIX_DEFAULT);
		numberFormat = ConfigurationHelper.getString(NUMBER_FORMAT_PARAMETER, params, NUMBER_FORMAT_DEFAULT);
	}
}