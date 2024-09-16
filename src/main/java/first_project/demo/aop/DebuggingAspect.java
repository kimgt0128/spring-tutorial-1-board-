package first_project.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect //AOP 클래스: 부가 기능을 주입하는 클래스
@Component // IOC 컨테이너가 해당 객체를 생성 및 관리
@Slf4j
public class DebuggingAspect {
    //대상 메서드 선택: ComponentService#Create()
    @Pointcut("execution(* first_project.demo.service.*Service.*(..))")
    private void cut() {}

    //실행 시점 설정: cut()의 대상이 수행되기 이전
    @Before("cut()")
    public void loggingArgs(JoinPoint joinPoint) { //cut()의 대상 메서드
        //입력값 가져오기
        Object[] args = joinPoint.getArgs();

        //클래스명
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();

        //메서드명
        String methodName = joinPoint.getSignature()
                .getName();

        //입력값 로깅하기
        for(Object obj : args) {
            log.info("{}#{}의 입력값 => {}", className, methodName, obj);
        }

    }

    //실행 시점 설정: cut()에 지정된 대상 호출 성공 후
    @AfterReturning(value = "cut()", returning = "returnObj")
    public void loggingReturnvalue(JoinPoint joinPoint,
                                   Object returnObj) {


        //클래스명
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();

        //메서드명
        String methodName = joinPoint.getSignature()
                .getName();

        //반환값을 로깅
        log.info("{}#{}의 반환값 => {}", className, methodName, returnObj);

    }

}
