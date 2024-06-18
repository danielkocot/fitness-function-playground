package de.codecentric.fitnessfunctionplayground;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.lang.syntax.elements.ClassesShouldConjunction;
import com.tngtech.archunit.library.Architectures;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

public class MyArchitectureTest {

    @Test
    public void packageStructureTest() {

        JavaClasses importedJavaClasses = new ClassFileImporter().importPackages("de.codecentric.fitnessfunctionplayground");

        ArchRule rule = classes().that().areAnnotatedWith(Controller.class)
                .or().haveNameMatching(".*Controller")
                .should().resideInAPackage("..controller..");

        rule.check(importedJavaClasses);
    }

   /* @Test
    public void layeredArchitectureTest() {
        JavaClasses javaClasses = new ClassFileImporter().importPackages("de.codecentric.fitnessfunctionplayground");
        try {
            Architectures.LayeredArchitecture layeredArchitecture =
                    layeredArchitecture()
                            .consideringAllDependencies()
                            .layer("Controller").definedBy("..controller..")
                            .layer("Service").definedBy("..service..")
                            .layer("Persistence").definedBy("..persistence..")
                            .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
                            .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller")
                            .whereLayer("Persistence").mayOnlyBeAccessedByLayers("Service");
            layeredArchitecture.check(javaClasses);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/

    @Test
    public void restControllerAnnotationTest() {
        JavaClasses javaClasses = new ClassFileImporter().importPackages("de.codecentric.fitnessfunctionplayground");
        ArchRule rule = classes().that().resideInAPackage("..controller..").should().beAnnotatedWith(RestController.class);
        rule.check(javaClasses);
    }


}
