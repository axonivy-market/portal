package com.axonivy.portal.selenium.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.extension.ExtendWith;

import ch.ivyteam.ivy.environment.IvyTest;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.PARAMETER })
@Inherited
@IvyTest
@ExtendWith(PortalWebTestExtension.class)
public @interface PortalWebTest {

}