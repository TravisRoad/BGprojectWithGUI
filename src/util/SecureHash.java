package util;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * SHA(Secure Hash Algorithm，安全散列算法），数字签名等密码学应用中重要的工具，
 * 被广泛地应用于电子商务等信息安全领域。虽然，SHA与MD5通过碰撞法都被破解了，
 * 但是SHA仍然是公认的安全加密算法，较之MD5更为安全
 *
 * @author Runoob.com
 */
public class SecureHash {
    public static final String KEY_SHA = "SHA";

    /**
     * 静态方法，用于加密字符串
     *
     * @param inputStr input
     * @return 加密后的字符串
     */
    public static String getResult(String inputStr) {
        BigInteger sha = null;
        byte[] inputData = inputStr.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(KEY_SHA);
            messageDigest.update(inputData);
            sha = new BigInteger(messageDigest.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert sha != null;
        return sha.toString(32);
    }

    /**
     * 测试方法
     * @param args 参数
     */
    public static void main(String[] args) {
        try {
            System.out.println(getResult("LXY"));
            System.out.println(getResult("LXY"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
