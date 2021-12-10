package ml.magicalattacker.finalapp;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class Base64Utils {

    public static String setKey(String encodeWord) throws UnsupportedEncodingException {

        return new String(Base64.decode(encodeWord, Base64.NO_WRAP), StandardCharsets.UTF_8);

    }

}
