package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "nikegreen";
        int age = 33;
        byte b = 124;
        short sh = 567;
        long lo = 158902345;
        float fl = 1.07f;
        double doub = 1.000070009;
        boolean bool = true;
        char ch = 'T';
        LOG.debug("User info name : {}, age : {}", name, age);
        LOG.error("целые числа : byte = {}, short = {}, int = {}, long {} ", b, sh, age, lo);
        LOG.info("с плавающей точкой числа : float = {}, double = {} ", fl, doub);
        LOG.debug("логический тип : boolean = {} ", bool);
        LOG.warn("символьный : char = {} ", ch);
    }
}
