package com.toutiao.officedict.common.assertUtils;

import com.toutiao.officedict.common.commonmodel.Money;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by jyl on 17/9/9.
 */
public class MoneyNotNullValidator implements ConstraintValidator<MoneyNotNull, Money> {
    @Override
    public void initialize(MoneyNotNull constraintAnnotation) {
    }

    @Override
    public boolean isValid(Money obj, ConstraintValidatorContext context) {

        if(obj==null || obj.getValue()==null){
            return false;
        }

        return true;
    }
}
