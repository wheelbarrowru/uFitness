package ru.mipt.data.ufitness;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mipt.Application;

@SpringBootTest
class UFitnessApplicationTests {

    @Test
    public void applicationContextTest() {
        Application.main(new String[] {});
    }

}
