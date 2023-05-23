package com.example.lab2_part2.Model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Worker.class)
public abstract class Worker_ {

	public static volatile SingularAttribute<Worker, Boolean> busy;
	public static volatile SingularAttribute<Worker, String> name;
	public static volatile SingularAttribute<Worker, Integer> id_type;
	public static volatile SingularAttribute<Worker, Integer> id;

	public static final String BUSY = "busy";
	public static final String NAME = "name";
	public static final String ID_TYPE = "id_type";
	public static final String ID = "id";

}

