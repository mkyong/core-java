package com.mkyong.java17.jep415;

import javax.swing.*;
import java.io.*;

public class JEP415 {

    public static void main(String[] args) throws IOException {

        byte[] bytes = convertObjectToStream(new JComponentExample());
        InputStream is = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(is);

        // Java 17
        // reject all JComponent classes
        ObjectInputFilter jComponentFilter = ObjectInputFilter.rejectFilter(
                JComponent.class::isAssignableFrom,
                ObjectInputFilter.Status.UNDECIDED);
        ois.setObjectInputFilter(jComponentFilter);

        try {
            Object obj = ois.readObject();
            System.out.println("Read obj: " + obj);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

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
