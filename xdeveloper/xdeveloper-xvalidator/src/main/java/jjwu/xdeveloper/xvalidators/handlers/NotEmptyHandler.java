/****************************************************************
 *  文件名     ： NotEmptyHandler.java
 *  日期         :  2012-8-14
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package jjwu.xdeveloper.xvalidators.handlers;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

import jjwu.xdeveloper.xvalidators.annotation.IwAnnotation;
import jjwu.xdeveloper.xvalidators.annotation.NotEmpty;
import jjwu.xdeveloper.xvalidators.exeception.ValidatorException;
import jjwu.xdeveloper.xvalidators.util.GetFiledValue;


/**
 * 
 * @类名:   NotEmptyHandler 
 * @描述:  	空对象验证处理类
 *
 * @作者:   吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2012-8-14上午09:20:28
 * @版本:   1.0
 */
public class NotEmptyHandler implements Handler {

	/* (non-Javadoc)
	 * @see org.tony.annotation.validators.Handler#validate(org.tony.annotation.validators.IwAnnotation, java.lang.reflect.Field)
	 */
	@Override
	public void validate(IwAnnotation targetObj, Field field) throws ValidatorException {
		// TODO Auto-generated method stub
		if (field.isAnnotationPresent(NotEmpty.class)) {
			checkNotEmpty(targetObj, field);
		}
		
	}
	
	public void checkNotEmpty(IwAnnotation targetObj,Field field) throws ValidatorException{
		NotEmpty validateNotEmpty = field.getAnnotation(NotEmpty.class);
		String message = validateNotEmpty.errmsg();

		Object value = GetFiledValue.getFieldValue(targetObj, field.getName());
		
		//POJO
		if(value == null){
			throw new ValidatorException(message + " But the value of Field[" + field.getName() + "] is Null.");
		}
		//Map
		if(value instanceof Map<?,?>){
			if(((Map<?,?>)value).isEmpty()){
				throw new ValidatorException(message + " But the value of Field[" + field.getName() + "] is Empty.");
			}
		//List & Set	
		}else if(value instanceof Collection<?>){
			if(((Collection<?>)value).isEmpty() || ((Collection<?>)value).size() ==0){
				throw new ValidatorException(message + " But the value of Field[" + field.getName() + "] is Empty.");
			}
		//Other
		}else{
			String temp = value.toString().trim();
			if(temp.isEmpty()||"null".equals(temp)){
				throw new ValidatorException(message + " But the value of Field[" + field.getName() + "] length is Zero or Empty.");
			}
		}
	}
}
