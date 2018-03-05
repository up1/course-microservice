package hello.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class HelloTest {

    @Test
    public void success_to_create_model_with_constructor() {
        Hello hello = new Hello("Somkiat");
        assertEquals("Somkiat", hello.getMessage());
    }

}


