package com.hanatour.hats.test.plugins.autoparams;


import autoparams.AutoSource;
import autoparams.customization.Customization;
import org.junit.jupiter.params.ParameterizedTest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ParameterizedTest
@AutoSource
@Customization(DomainCustomization.class)
public @interface ParameterizedTestExt {
}
