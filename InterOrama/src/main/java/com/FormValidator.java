package com;
 
import java.util.Map;
 
import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;
/*
 * Used in the Forms of the WebPage for validation of passwords and emails. 
 */
public class FormValidator extends AbstractValidator {
     
    public void validate(ValidationContext ctx) {
        Map<String,Property> beanProps = ctx.getProperties(ctx.getProperty().getBase());
         
        validatePasswords(ctx, (String)beanProps.get("password").getValue(), (String)ctx.getValidatorArg("retypedPassword"));
        validateEmail(ctx, (String)beanProps.get("email").getValue());
    }
     
    private void validatePasswords(ValidationContext ctx, String password, String retype) { 
        if(password == null || retype == null ) {
            this.addInvalidMessage(ctx, "password", "Please insert a new password.");
        }else if(!password.equals(retype)) {
            this.addInvalidMessage(ctx, "password", "Different passwords. Please check again.");
        	
        }
    }
     
     private void validateEmail(ValidationContext ctx, String email) {
        if(email == null || !email.matches(".+@.+\\.[a-z]+")) {
            this.addInvalidMessage(ctx, "email", "Invalid e-mail");            
        }
    }
}