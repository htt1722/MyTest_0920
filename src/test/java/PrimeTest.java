import core.PrimeCalculator;
import core.impl.QuickPrimeCalculator;
import core.impl.ReduceLoopCountPrimeCalculator;
import org.junit.Assert;
import org.junit.Test;

public class PrimeTest {
    @Test
    public void sum(){
        QuickPrimeCalculator quickPrimeCalculator = new QuickPrimeCalculator();
        // test the method:isPrimeNumber
        Assert.assertEquals(true, quickPrimeCalculator.isPrimeNumber(19));
        Assert.assertNotEquals(true, quickPrimeCalculator.isPrimeNumber(20));

        PrimeCalculator primeCalculator = new QuickPrimeCalculator();
        // test the quick calculator method:sum
        Assert.assertEquals(17, primeCalculator.sum(10));

        primeCalculator = new ReduceLoopCountPrimeCalculator();
        // test the reduce loop count calculator method:sum
        Assert.assertEquals(17, primeCalculator.sum(10));
    }
}
