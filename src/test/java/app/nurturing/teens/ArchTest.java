package app.nurturing.teens;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("app.nurturing.teens");

        noClasses()
            .that()
            .resideInAnyPackage("app.nurturing.teens.service..")
            .or()
            .resideInAnyPackage("app.nurturing.teens.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..app.nurturing.teens.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
