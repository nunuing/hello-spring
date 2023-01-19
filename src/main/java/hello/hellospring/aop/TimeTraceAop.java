package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect         //중간에 intersect해서 확인하고 다시 돌아갈 수 있는 기술
@Component
public class TimeTraceAop {
    @Around("execution(* hello.hellospring..*(..))")            //hello.hellospring package 안에 있는 파일들에 모두 적용하겠다 -> condition 조정 가능
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START : " + joinPoint.toString() + "ms");
        try{
            return joinPoint.proceed();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END : " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
