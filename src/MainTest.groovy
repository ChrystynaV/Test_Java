import org.junit.Assert
import org.junit.FixMethodOrder

//import org.junit.jupiter.api.Test
import org.junit.Test
import org.junit.runner.RunWith;


//@RunWith(SpringRunner.class)
//@SpringBootTest
//@SpringBootTest @FixMethodOrder(SpringRunner.class)
public class MainTest {
    void testMain() {
    }

    @Test
     void testPm() {
        double expected = Main.Pm(0.8, 0.2, 380, 210)
        double actual = 0.8*380-Math.abs(0.2*210);
        Assert.assertEquals(expected, actual, 0.0002);
    }

    @Test
     void testfindM3(){
        double M3 = -16, M31 = 44.5, M32 = 100, p=0.5;
        double actual;

        double expected = Main.findM3(M31, M32, M3, p);

        if (M31 > M32)
         actual = M31 * p;
            else
        actual = M32 * p;
        Assert.assertEquals(expected, actual, 0.0002);
    }
}










