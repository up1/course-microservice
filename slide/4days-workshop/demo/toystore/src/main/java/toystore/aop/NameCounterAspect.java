package toystore.aop;

import io.micrometer.core.instrument.MeterRegistry;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NameCounterAspect {

    @Autowired
    private MeterRegistry meterRegistry;

    @AfterReturning(pointcut = "execution(* toystore.controller.HelloController.sayHi(String)) && args(name)", argNames = "name")
    public void afterCalledSayHi(String name) {
        if("somkiat".equals(name)) {
            meterRegistry.counter("aop-somkiat").increment();
        } else if("up1".equals(name)) {
            meterRegistry.counter("aop-up1").increment();
        }else {
            meterRegistry.counter("aop-other").increment();
        }
    }

}
