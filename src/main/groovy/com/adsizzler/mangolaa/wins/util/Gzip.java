package com.adsizzler.mangolaa.wins.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPOutputStream;

/**
 * Created by Ankush on 16/02/17.
 */
public class Gzip {

    /**
     * Compress a String using gzip compression
     * @param str String to compress using gzip compression
     * @return gzip content as byte[]
     */
    public static byte[] compress(final String str) {
        if (!Strings.hasText(str)) {
            throw new IllegalArgumentException("Cannot zip null or empty string");
        }

        try (final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            try (final GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream)) {
                gzipOutputStream.write(str.getBytes(StandardCharsets.UTF_8));
            }
            return byteArrayOutputStream.toByteArray();
        }
        catch(final IOException e) {
            throw new RuntimeException("Failed to zip content", e);
        }
    }


}
