package com.kyungjoon.product;

import java.util.Random;
import org.bson.types.ObjectId;
public class test {
    public static void main (String[] args){

        Random random = new Random();
        int nextInt = random.nextInt(256*256*256);
        System.out.println(ObjectId.get().toHexString());
    }
}
