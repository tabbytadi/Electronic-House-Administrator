//package org.example.validator;
//
//import javax.validation.Constraint;
//import javax.validation.Payload;
//import java.lang.annotation.Retention;
//import java.lang.annotation.Target;
//
//import static java.lang.annotation.ElementType.FIELD;
//import static java.lang.annotation.RetentionPolicy.RUNTIME;
//
//@Target(FIELD)
//@Retention(RUNTIME)
//@Constraint(validatedBy = InvalidNamesValidator.class)
//public @interface InvalidNames {
//    String message() default "Invalid name";
//
//    Class<?>[] groups() default {};
//
//    Class<? extends Payload>[] payload() default {};
//}
