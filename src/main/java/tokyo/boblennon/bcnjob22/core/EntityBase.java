package tokyo.boblennon.bcnjob22.core;

import java.util.Set;
import java.util.UUID;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tokyo.boblennon.bcnjob22.core.execptions.BadRequestException;

public @Getter @Setter @NoArgsConstructor abstract class EntityBase {
    
    @Id
    public UUID id;

    // Second Field validation, this one is for Domain-Entity's feilds
    public void validate() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<EntityBase>> violations = validator.validate(this);
        if (!violations.isEmpty()) {
            BadRequestException badRequestException = new BadRequestException();
            for (ConstraintViolation<EntityBase> violation : violations) {
                badRequestException.addException(violation.getPropertyPath().toString(), violation.getMessage());
            }
            throw badRequestException;
        }
    }
    
    // Validates that the User with the given 'username' does not exist in the database
    // This is done to try to improve performance when dealing with database requests
    public void validate(String key, String value, ExistsByField existsByField) {
        this.validate();
        if (existsByField.exists(value)) {
            BadRequestException badRequestException = new BadRequestException();
            badRequestException.addException(key, String.format("Value %s for key %s is already in the database.", value, key));
            throw badRequestException;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof EntityBase)) {
            return false;
        }
        EntityBase tmp = (EntityBase) obj;
        return tmp.id.equals(this.id);
    }

    @Override
    public int hashCode() {
        return this.id.toString().hashCode();
    }
}
