package com.iu.aop.transfer;

import org.aspectj.lang.ProceedingJoinPoint;


public class CardCheck {
	
	public Object check(ProceedingJoinPoint join){
		System.out.println("삑 ---------- 탑승");
		Object object =null;
		try {
			object = join.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("삑 ---------- 하차");
	
		return object;
	}

}
