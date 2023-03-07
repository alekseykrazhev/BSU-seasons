package com.example.lab2_part2.Model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Application.class)
public abstract class Application_ {

	public static volatile SingularAttribute<Application, Date> date;
	public static volatile SingularAttribute<Application, String> name;
	public static volatile SingularAttribute<Application, Integer> id_type;
	public static volatile SingularAttribute<Application, Integer> id;
	public static volatile SingularAttribute<Application, Dispatcher> dispatcher;
	public static volatile SingularAttribute<Application, Tenant> tenant;

	public static final String DATE = "date";
	public static final String NAME = "name";
	public static final String ID_TYPE = "id_type";
	public static final String ID = "id";
	public static final String DISPATCHER = "dispatcher";
	public static final String TENANT = "tenant";

}

