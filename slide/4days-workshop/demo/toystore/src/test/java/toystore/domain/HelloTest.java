package toystore.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class HelloTest {

    @Test
    public void shouldReturnSomkiat() {
        Hello hello = new Hello("somkiat");
        assertEquals("somkiat", hello.getMessage());
    }

}