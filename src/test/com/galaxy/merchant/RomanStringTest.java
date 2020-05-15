package test.com.galaxy.merchant;

import com.galaxy.merchant.util.RomanNumberUtil;
import org.junit.Assert;
import org.junit.Test;

public class RomanStringTest {

    @Test
    public void testRomanToArabic() {
        String roman = "XVI";
        Assert.assertEquals(RomanNumberUtil.romanToArabic(roman), 16);

        String roman1 = "MCMXLIV";
        Assert.assertEquals(RomanNumberUtil.romanToArabic(roman1), 1944);

    }

    @Test
    public void testValidateRomanNumber() {
        String roman = "XVI";
        String roman1 = "MLXXXIII";
        String roman2 = "MIVX";
        String roman3 = "MLXIV";
        Assert.assertTrue(RomanNumberUtil.validateRomanNumber(roman));
        Assert.assertTrue(RomanNumberUtil.validateRomanNumber(roman1));
        Assert.assertTrue(!RomanNumberUtil.validateRomanNumber(roman2));
        Assert.assertTrue(RomanNumberUtil.validateRomanNumber(roman3));
    }
}
