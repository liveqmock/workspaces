/****************************************************************
 *  文件名     ：Xvalidator.java
 *  日期         :  2012-8-14
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package jjwu.xdeveloper.xvalidators;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import jjwu.xdeveloper.xvalidators.annotation.IwAnnotation;
import jjwu.xdeveloper.xvalidators.exeception.ValidatorException;
import jjwu.xdeveloper.xvalidators.handlers.Handler;
import jjwu.xdeveloper.xvalidators.util.GetFiledValue;

import org.apache.log4j.Logger;


/**
 * 
 * @类名:   Xvalidator 
 * @描述:  	javaBean字段验证器,用于后台数据交验
 *
 * @作者:   吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2012-10-25上午09:19:38
 * @版本:   1.0
 */
public final class Xvalidator {

	private final static Logger LOGGER = Logger.getLogger(Xvalidator.class);

	private final static String SUFFIX = "Handler";

	private final static String PACKAGE = "jjwu.xdeveloper.xvalidators.annotation";
	
	private final static String HANDLERPACKAGE = "jjwu.xdeveloper.xvalidators.handlers";

	private static Xvalidator validator = new Xvalidator();

	private Xvalidator() {

	}

	public static Xvalidator getInstance() {
		return validator;
	}
	
	public boolean validate(IwAnnotation targetObj) {
		try {
			if (null == targetObj) {
				LOGGER.error("Validate Object is NULL.");
				return false;
			}
			Class<?> currentClass = targetObj.getClass();
			while (currentClass != null) {
				Field[] fields = currentClass.getDeclaredFields();
				for (Field elem : fields) {
					validateField(targetObj, elem);
				}
				Class<?> superClass = currentClass.getSuperclass();
				currentClass = IwAnnotation.class.isAssignableFrom(superClass) ? superClass : null;
			}
		} catch (ValidatorException e) {
			LOGGER.error(e.getMessage());
			return false;
		} catch (Exception e) {
			LOGGER.error("其他异常", e);
			return false;
		}
		return true;
	}

	/**
	 * @param targetObj
	 * @throws ValidatorException
	 */
	public void validateEx(IwAnnotation targetObj) throws ValidatorException {
		try {
			if (null == targetObj) {
				LOGGER.error("Validate Object is NULL.");
				throw new ValidatorException("Validate Object is NULL.");
			}
			Class<?> currentClass = targetObj.getClass();
			while (currentClass != null) {
				Field[] fields = currentClass.getDeclaredFields();
				for (Field elem : fields) {
					validateField(targetObj, elem);
				}
				Class<?> superClass = currentClass.getSuperclass();
				currentClass = IwAnnotation.class.isAssignableFrom(superClass) ? superClass : null;
			}
		} catch (ValidatorException ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw ex;
		} catch (Exception oe) {
			LOGGER.error("其他异常", oe);
		}
	}

	private void validateField(IwAnnotation targetObj, Field field) throws ValidatorException {
		// check whether the field is also IwAnnotation
		if (IwAnnotation.class.isAssignableFrom(field.getType())) {
			Object destValue = null;
			try {
				destValue = GetFiledValue.getFieldValue(targetObj, field.getName());
			} catch (Exception ex) {
				throw new ValidatorException("Get field value or cast value error for field " + field.getName() + " in Class " + targetObj.getClass() + ". Error message: "
						+ ex.getMessage(), ex);
			}
			if (destValue == null) {
				return; // NULL value is allowed.
			} else {
				validate((IwAnnotation) destValue);
			}
		}

		List<Annotation> annotations = getValidateAnnotations(field);
		if (annotations != null && annotations.size() > 0) {
			// loop each field annotations
			for (Annotation annotation : annotations) {
				String annotationName = annotation.annotationType().getName();
				String className = annotationName + SUFFIX;
				className = className.replace(PACKAGE, HANDLERPACKAGE);
				Handler handler = null;
				try {
					handler = (Handler) Class.forName(className).newInstance();
				} catch (Exception ex) {
					LOGGER.error("Can not get the handler for " + ex.getMessage(), ex);
					throw new ValidatorException();
				}
				handler.validate(targetObj, field);
			}
		}
	}

	/**
	 * get self field annotations
	 * 
	 * @param field
	 * @return
	 */
	private List<Annotation> getValidateAnnotations(Field field) {
		List<Annotation> annotations = new ArrayList<Annotation>();
		Annotation[] annos = field.getAnnotations();
		for (Annotation elem : annos) {
			String classToString = elem.annotationType().toString();
			if (classToString.indexOf(PACKAGE) > 0) {
				annotations.add(elem);
			}
		}
		return annotations;
	}
}
