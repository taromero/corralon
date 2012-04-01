package ar.com.seminario.corralon.common.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class exampleAspect {
	
	@Before("execution(* *..*Service.*(*,*))")
	public void loguearServiciosEnConsola(JoinPoint point){
		String clazz = point.getTarget().getClass().getSimpleName();
		String method = point.getSignature().getName();
		Object[] args = point.getArgs();
		System.out.println("Service: " + clazz + "." + method + "(" + args[0] + ", " + args[1] + ")");
	}
	
	@Before("execution(* *..*DAO.*(*,*))")
	public void loguearDaosEnConsola(JoinPoint point){
		String clazz = point.getTarget().getClass().getSimpleName();
		String method = point.getSignature().getName();
		Object[] args = point.getArgs();
		System.out.println("      ╚DAO: " + clazz + "." + method + "(" + args[0] + ", " + args[1] + ")");
	}
	
}
