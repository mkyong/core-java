package com.mkyong.java17.jep415;

import javax.swing.*;
import java.io.*;

public class JEP290_B {

    public static void main(String[] args) throws IOException {

        byte[] bytes = convertObjectToStream(new JComponentExample());
        InputStream is = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(is);

        ois.setObjectInputFilter(createObjectFilter());

        try {
            Object obj = ois.readObject();
            System.out.println("Read obj: " + obj);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // reject all JComponent classes
    private static ObjectInputFilter createObjectFilter() {
        return filterInfo -> {
            Class<?> clazz = filterInfo.serialClass();
            if (clazz != null) {
                return (JComponent.class.isAssignableFrom(clazz))
                        ? ObjectInputFilter.Status.REJECTED
                        : ObjectInputFilter.Status.ALLOWED;
            }
            return ObjectInputFilter.Status.UNDECIDED;
        };
    }

    private static byte[] convertObjectToStream(Object obj) {
        ByteArrayOutputStream boas = new ByteArrayOutputStream();
        try (ObjectOutputStream ois = new ObjectOutputStream(boas)) {
            ois.writeObject(obj);
            return boas.toByteArray();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        throw new RuntimeException();
    }

}
