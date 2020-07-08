package pl.sadowski.teaipracadomowatydzien7.aspects;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Controller;
import pl.sadowski.teaipracadomowatydzien7.model.Car;

import java.util.List;

@Log4j2
@Aspect
@Controller
public class LogObjects {

    @Pointcut("execution(@pl.sadowski.teaipracadomowatydzien7.aspects.LogMethod * *.*(..))")
    protected void logObjects() {
    }

//    @AfterReturning(value = "logObjects() && @annotation(logReturnment)", returning = "lista")
//    public void doSo(JoinPoint joinPoint, LogReturnment logReturnment, List<Car> lista) throws InterruptedException {
//        long start = System.currentTimeMillis();
//        if(logReturnment.logDateOfExecution()) {
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy_hh:mm:ss z");
//            log.info(simpleDateFormat.format(new Date()));
//        }
//        lista.forEach(log::info);
//        log.warn("Method "+ joinPoint.getSignature().getName() +" executed in:  " + System.currentTimeMillis() +"           "+ start);
//    }
//}

    @Around(value = "logObjects() && @annotation(logMethod)")
    public Object doSoo(ProceedingJoinPoint joinPoint, LogMethod logMethod) throws Throwable {
        long start = 0;
        if (logMethod.logExecutionTime()) {
            start = System.currentTimeMillis();
        }
        Object retVal = joinPoint.proceed();
        if (logMethod.logObjects()) {
            List<Car> cars = (List<Car>) retVal;
            cars.forEach(log::info);
        }
        if (logMethod.logExecutionTime()) {
            log.info("Method   " + joinPoint.getSignature().getName() + " execution took: " + (System.currentTimeMillis() - start));
        }
return retVal;
    }
}

