package com.example.jeedemo.web;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.validator.FacesValidator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "dateValidator")
public class DateValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		
		Date date = (Date) value;
        Date today = new Date();
        
        System.out.println("dupa");
        System.out.println(date);
        System.out.println(today);
        

		
		if(!(date.equals(today)) && date.before(today)){
			FacesMessage message = new FacesMessage();
			message.setDetail("Data nie może być wcześniejsza niż dzisiaj");
			message.setSummary("Data nie może być wcześniejsza niż dzisiaj");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
			
		}

	}

}
