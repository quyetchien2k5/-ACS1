package model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class data {

    public static String oldMenuId;
    public static String oldCounterName;
    public static  String email_patern = "^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$";

    public static String pathAvatar;
    public static String pathMenuImage;
    public static String currentManager;
    public static String encodePassword(String password)
    {
        try {

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodeHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for(byte b : encodeHash) {
                String hex = String.format("%02x", b);
                hexString.append(hex);
            }

            return hexString.toString();

        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

}
