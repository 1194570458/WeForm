package com.weform.utils;

import com.fasterxml.jackson.core.util.BufferRecycler;
import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author: Kason
 * @Date: 2018/12/24 20:49
 */
@Slf4j
public class ImageUtil {

    public static String getBase64(String path,String json) {
        OutputStreamWriter out = null;
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            out = new OutputStreamWriter(conn.getOutputStream());
            out.write(json);
            out.flush();

            ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
            int ch;
            try {
                while ((ch = conn.getInputStream().read()) != -1) {
                    bytestream.write(ch);
                }
            } catch (IOException e) {
                log.error("【图片工具】 msg={}", e.getMessage());
                e.printStackTrace();
            }
            byte[] program = bytestream.toByteArray();
            BASE64Encoder encoder = new BASE64Encoder();
            String binary = encoder.encodeBuffer(program).trim();
            return binary;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


}
