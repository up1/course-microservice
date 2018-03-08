package toystore.controller;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import toystore.domain.Hello;

@RestController
public class HelloController {

//    @Autowired
//    private MeterRegistry meterRegistry;

    @GetMapping("/hello/{name}")
    public Hello sayHi(@PathVariable String name) {

//        if("somkiat".equals(name)) {
//            meterRegistry.counter("somkiat").increment();
//        } else if("up1".equals(name)) {
//            meterRegistry.counter("up1").increment();
//        }else {
//            meterRegistry.counter("other").increment();
//        }

        Hello hello =
                new Hello(String.format("Hello %s", name));
        return hello;
    }
}
