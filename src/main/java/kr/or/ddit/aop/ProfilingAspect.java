package kr.or.ddit.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class ProfilingAspect {

	private static final Logger logger = LoggerFactory.getLogger(ProfilingAspect.class);
	
	@Pointcut("execution(* kr.or.ddit..service.*.*(..))")
	public void dummy() {}
	
	@Around("dummy()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		
		long start = System.currentTimeMillis();
		logger.debug("Profiling Aspect around method startTime : {}", start);
		
		Object[] methodArgs = joinPoint.getArgs();
		Object returnObject = joinPoint.proceed(methodArgs);
		
		long  end = System.currentTimeMillis();
		logger.debug("Profiling Aspect around method endTime : {}", end);
		
		logger.debug("Profiling Aspect around method resultTime : {}", end - start);
		return returnObject; 
	}
}
