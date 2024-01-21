package com.tu.mnagement.util;

import java.io.Serializable;
import java.time.LocalDate;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import com.tu.mnagement.repository.UserInfoRepo;


public class EmployeeIdGenerate implements IdentifierGenerator{
    
	@Autowired
    UserInfoRepo userInfoRepo;
	
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
			
		LocalDate currentDate = LocalDate.now();
        String yearMonth = currentDate.getYear() + String.format("%02d", currentDate.getMonthValue());
        
        Long maxNumber =1L; //userInfoRepo.findMaxNumberByYearMonth(yearMonth);
        
        if (maxNumber == null) {
            maxNumber = 1L;
        } else {
            maxNumber++;
        }
        
        return yearMonth + String.format("%04d", maxNumber);
		
	}

}
