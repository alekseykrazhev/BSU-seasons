package com.example.lab2_part2.DAO;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.example.lab2_part2.Model.Tenant;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Tenant.class)
public abstract class Tenant_ {

	public static volatile SingularAttribute<Tenant, String> name;
	public static volatile SingularAttribute<Tenant, Integer> id;

	public static final String NAME = "name";
	public static final String ID = "id";

}
