package com.gogogo.core.classLoader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 加密文件
 *
 * @author HUZHAOYANG
 * @version 1.0
 * @date 2019/9/15 9:11
 */
public class Caeser {
    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            System.out.println("USAGE:java classLoader.Caesar in out key");
            return;
        }
        upCryp(args);
    }

    private static void cryp(String[] args) throws IOException {
        try (FileInputStream in = new FileInputStream(args[0]); FileOutputStream out = new FileOutputStream(args[1])) {
            int key = Integer.parseInt(args[2]);
            int ch;
            while ((ch = in.read()) != -1) {
                byte c = (byte) (ch + key);
                out.write(c);
            }
        }
    }

    private static void upCryp(String[] args) throws IOException {
        try (FileInputStream in = new FileInputStream(args[0]); FileOutputStream out = new FileOutputStream(args[1])) {
            int key = Integer.parseInt(args[2]);
            int ch;
            while ((ch = in.read()) != -1) {
                byte c = (byte) (ch - key);
                out.write(c);
            }
        }
    }
}
