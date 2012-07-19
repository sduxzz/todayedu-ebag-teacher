package com.todayedu.ebag.teacher.Database.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ java.lang.annotation.ElementType.FIELD })
public @interface Column {
	
	public abstract String name();
	
	public abstract String type() default "";
	
	public abstract int length() default 0;
	
	/* the foreign key of this column.eg. tableName(attributeName) */
	public abstract String foreignKey() default "";
}