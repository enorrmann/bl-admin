package ar.gov.santafe.meduc.bladmin.dao.config;

import ar.gov.santafe.meduc.dto.SimpleDto;
import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Set;
import org.skife.jdbi.v2.SQLStatement;
import org.skife.jdbi.v2.sqlobject.Binder;
import org.skife.jdbi.v2.sqlobject.BinderFactory;
import org.skife.jdbi.v2.sqlobject.BindingAnnotation;

// http://jdbi.org/sql_object_api_argument_binding/
@BindingAnnotation(BindSimpleDto.SimpleDtoBinderFactory.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface BindSimpleDto {

    public static class SimpleDtoBinderFactory implements BinderFactory {

        @Override
        public Binder build(Annotation annotation) {
            return new Binder<BindSimpleDto, SimpleDto>() {
                @Override
                public void bind(SQLStatement q, BindSimpleDto bind, SimpleDto arg) {
                    Set<String> keys = arg.getAtributos().keySet();
                    for (String aKey : keys) {
                        q.bind(aKey, arg.get(aKey));
                    }
                }
            };
        }
    }
}