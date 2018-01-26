package com.iu.aop.transfer;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CardCheck2 {
	@Around("execution(* com.iu.aop.transfer..Transport.*.())")
	public void check(){
		System.out.println("ㄸㄸㄸㄸㄸㄸㄸㄸㄸ");
	}
	
	@Before("execution(* com.iu.aop.transfer..Transport.*.())")
	public void check2(){
		System.out.println("ㅁㅁㅁㅁㅁㅁㅁㅁ");
		
	}
}
