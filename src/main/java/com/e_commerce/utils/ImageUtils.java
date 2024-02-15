package com.e_commerce.utils;

import java.io.IOException;
import java.util.Base64;
import java.util.zip.DataFormatException;

public class ImageUtils {

    /**
     * Questo metodo permette di decomprireme un'immagine in formato byte e trasformarla
     * in stringa per farla viaggiare all'interno di un parametro del json per poi convertirla nell'html del front end
     *
     * @param data
     * @return String
     * @throws IOException
     * @throws DataFormatException
     */
    public static String decompressImage(byte[] data) throws IOException, DataFormatException {
        return new String(Base64.getEncoder().encode(data));
    }
}
