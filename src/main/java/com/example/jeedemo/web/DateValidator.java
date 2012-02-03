package com.example.jeedemo.web;

import java.util.Calendar;
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
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date today = cal.getTime();
        
        System.out.println("dupa");
        System.out.println(date);
        System.out.println(today);
        System.out.println(!(date.equals(today)));
        System.out.println(date.before(today));
		
		if(date.compareTo(today) < 0){
			FacesMessage message = new FacesMessage();
			message.setDetail("Data musi być z przyszłości");
			message.setSummary("Data musi być z przyszłości");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
			
		}

	}

}
