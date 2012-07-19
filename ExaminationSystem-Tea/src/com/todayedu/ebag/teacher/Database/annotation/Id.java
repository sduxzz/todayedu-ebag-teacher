package com.todayedu.ebag.teacher.Database.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ java.lang.annotation.ElementType.FIELD })
public @interface Id {
	
	/* is id need auto increment */
	public abstract boolean isAutoIncrement() default false;
}