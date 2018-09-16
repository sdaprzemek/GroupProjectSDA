package pl.sda.commons;

import org.junit.Assert;
import org.junit.Test;

public class PasswordUtilsTest {

    PasswordUtils passwordUtils = new PasswordUtils();
    String password = "OriginalPassword$1";

    @Test()
    public void generatePasswordTest30() {

        String salt = PasswordUtils.getSalt(30);

        String codedPassword = PasswordUtils.generateSecurePassword(password, salt);
        Assert.assertTrue(PasswordUtils.verifyUserPassword(password, codedPassword, salt));
    }

    @Test()
    public void generatePasswordTest100() {

        String salt = PasswordUtils.getSalt(100);

        String codedPassword = PasswordUtils.generateSecurePassword(password, salt);
        Assert.assertTrue(PasswordUtils.verifyUserPassword(password, codedPassword, salt));
    }

    @Test()
    public void failPasswordTest() {

        String salt = PasswordUtils.getSalt(30);

        String codedPassword = PasswordUtils.generateSecurePassword(password, salt);
        salt = salt + "1";
        Assert.assertFalse(PasswordUtils.verifyUserPassword(password, codedPassword, salt));
    }
}
