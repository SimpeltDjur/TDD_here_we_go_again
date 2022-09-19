import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PestTest {
    @Test
    public void ettTestTest(){ // Kanariefågel
        System.out.println("Magnus does not like to be told what to do!");
        Pest pest = new Pest();
        Assertions.assertEquals(10, pest.pestAdd(3, 7));

    }
}
