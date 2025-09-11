import com.gbaseHD.*;
import org.junit.Test;

import java.text.ParseException;

public class test {

    @Test
    public void test1() {
        _001_Regular testInstance = new _001_Regular();

        System.out.println(testInstance.evaluate(22, 10, 30));
        System.out.println(testInstance.evaluate(22, 10, 20, 30, 40));
    }

    @Test
    public void test2() {
        _002_NumberFloor testInstance = new _002_NumberFloor();

        System.out.println(testInstance.evaluate(123456, 3));

    }

    @Test
    public void test3() throws ParseException {
        _003_DateFloor testInstance = new _003_DateFloor();

        System.out.println(testInstance.evaluate("2021-08-16", "minute"));

    }


    @Test
    public void test4() throws ParseException {
        _004_BlockFront testInstance = new _004_BlockFront();

        System.out.println(testInstance.evaluate("123@163.com"));

    }


    @Test
    public void test5() {
        _005_BlockBack testInstance = new _005_BlockBack();

        System.out.println(testInstance.evaluate("123@345@163.com"));

    }

    @Test
    public void test6() {
        _006_KeepFront testInstance = new _006_KeepFront();

        System.out.println(testInstance.evaluate("021-1234567", 5));

    }

    @Test
    public void test7() {
        _007_KeepBack testInstance = new _007_KeepBack();

        System.out.println(testInstance.evaluate("021-1234567", 5));

    }


    @Test
    public void test8() {
        _008_Mask testInstance = new _008_Mask();

        System.out.println(testInstance.evaluate("浙江省杭州市西湖区胡同口", 1, 2));

    }

    @Test
    public void test9() {
        _009_KeepFrom testInstance = new _009_KeepFrom();

        System.out.println(testInstance.evaluate("浙江省杭州市西湖区胡同口", 6, 9));

    }

    @Test
    public void test10() {
        _010_Cover testInstance = new _010_Cover();

        System.out.println(testInstance.evaluate("浙江省杭州市西湖区胡同口", -1, 5));

    }

    @Test
    public void test11() {
        _011_MaskFrom testInstance = new _011_MaskFrom();

        System.out.println(testInstance.evaluate("浙江省杭州市西湖区胡同口", 5, 2));

    }

    @Test
    public void test12() {
        _012_MaskFront testInstance = new _012_MaskFront();

        System.out.println(testInstance.evaluate("123#456@163.com", "#"));

    }

    @Test
    public void test13() {
        _013_MaskBack testInstance = new _013_MaskBack();

        System.out.println(testInstance.evaluate("123@456@163.com", ""));

    }

    @Test
    public void test14() {
        _014_MaskSpecify testInstance = new _014_MaskSpecify();

        System.out.println(testInstance.evaluate("浙江省杭州市西湖区胡同口", 1, 2, 3));

    }

    @Test
    public void test15() {
        _015_KeepFromSl testInstance = new _015_KeepFromSl();

        System.out.println(testInstance.evaluate("浙江省杭州市西湖区胡同口", 3,3, 3));

    }


    @Test
    public void test28() {
        _028_SM3 testInstance = new _028_SM3();

        System.out.println(testInstance.evaluate("hello world"));
    }

    @Test
    public void test29() {
        _029_HMAC testInstance = new _029_HMAC();

        System.out.println(testInstance.evaluate("hello world",
                "R/EF98s2xof7m16m+3b4n/0DDZbbTlDERdedJqfhP94="));
    }

    @Test
    public void test30() {
        _030_RandomStr testInstance = new _030_RandomStr();

        System.out.println(testInstance.evaluate(5));
    }

    @Test
    public void test31() {
        _031_Random testInstance = new _031_Random();

        System.out.println(testInstance.evaluate(10, 50));
    }

    @Test
    public void test32() {
        _032_Sequence testInstance = new _032_Sequence();

        System.out.println(testInstance.evaluate(23));
    }


    @Test
    public void test33() {
        _033_Blur testInstance = new _033_Blur();

        System.out.println(testInstance.evaluate(0));
    }


    @Test
    public void test34() {
        _034_CharacterScrambling testInstance = new _034_CharacterScrambling();

        System.out.println(testInstance.evaluate("hello world"));
    }

    @Test
    public void test35() {
        _035_PhoneConfusion testInstance = new _035_PhoneConfusion();

        System.out.println(testInstance.evaluate("0571-123456"));
    }


}
