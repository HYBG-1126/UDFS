import com.gbaseHD.*;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

public class test {


    public static void main(String[] args) throws NoSuchAlgorithmException {


        HMAC dateFloor = new HMAC();
        byte[] src = {'h','e','l','l','o',' ','w','o','r','l','d'};
        // 示例输入数据和密钥
        String data = "hello world";
        String key = "R/EF98s2xof7m16m+3b4n/0DDZbbTlDERdedJqfhP94=";
        System.out.println(dateFloor.evaluate(data, key));

    }

}
