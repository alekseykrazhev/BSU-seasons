package com.example.lab2_part2.DAO;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.example.lab2_part2.Model.Dispatcher;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Dispatcher.class)
public abstract class Dispatcher_ {

	public static volatile SingularAttribute<Dispatcher, String> name;
	public static volatile SingularAttribute<Dispatcher, Integer> id;

	public static final String NAME = "name";
	public static final String ID = "id";

}
