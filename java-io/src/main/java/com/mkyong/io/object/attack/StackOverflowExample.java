package com.mkyong.io.object.attack;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// https://homepages.ecs.vuw.ac.nz/~alex/files/DietrichJezekRasheedTahirPotaninECOOP2017.pdf
public class StackOverflowExample {

    public static void main(String[] args) {
        System.out.println(bomb().length);

        //deserialize(bomb());  // throws StackOverflow

        ObjectInputFilter filter =
                ObjectInputFilter.Config.createFilter(
                        "maxdepth=2;java.base/*;!*");

        deserializeFilter(bomb(), filter);  // java.io.InvalidClassException: filter status: REJECTED

        System.out.println("Done");
    }

    static byte[] bomb() {
        HashMap map = new HashMap();
        List list = new ArrayList();
        map.put(list, "");
        list.add(list);
        return serialize(map);
    }

    public static byte[] serialize(Object o) {
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        try {
            new ObjectOutputStream(ba).writeObject(o);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return ba.toByteArray();
    }


    /*public static Object deserialize(byte[] bytes) {
        try {
            return new ObjectInputStream(
                    new ByteArrayInputStream(bytes)).readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }*/

    public static Object deserializeFilter(byte[] bytes, ObjectInputFilter filter) {

        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bais)) {

            // add filter before readObject
            ois.setObjectInputFilter(filter);

            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }

    }

}
