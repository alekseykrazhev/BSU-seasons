package com.example.lab2_part2.Model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(WorkPlan.class)
public abstract class WorkPlan_ {

	public static volatile SingularAttribute<WorkPlan, String> name;
	public static volatile SingularAttribute<WorkPlan, Integer> id_type;
	public static volatile SingularAttribute<WorkPlan, Integer> id;
	public static volatile SingularAttribute<WorkPlan, Worker> worker;

	public static final String NAME = "name";
	public static final String ID_TYPE = "id_type";
	public static final String ID = "id";
	public static final String WORKER = "worker";

}

