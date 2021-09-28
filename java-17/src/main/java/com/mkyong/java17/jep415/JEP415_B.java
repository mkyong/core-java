package com.mkyong.java17.jep415;

import java.io.*;
import java.util.function.BinaryOperator;

public class JEP415_B {

    static class PrintFilterFactory implements BinaryOperator<ObjectInputFilter> {

        @Override
        public ObjectInputFilter apply(
                ObjectInputFilter currentFilter, ObjectInputFilter nextFilter) {

            System.out.println("Current filter: " + currentFilter);
            System.out.println("Requested filter: " + nextFilter);

            // Returns a filter that merges the status of a filter and another filter
            return ObjectInputFilter.merge(nextFilter, currentFilter);

            // some logic and return other filters
            // reject all JComponent classes
            /*return filterInfo -> {
                Class<?> clazz = filterInfo.serialClass();
                if (clazz != null) {
                    if(JComponent.class.isAssignableFrom(clazz)){
                        return ObjectInputFilter.Status.REJECTED;
                    }
                }
                return ObjectInputFilter.Status.ALLOWED;
            };*/

        }
    }

    public static void main(String[] args) throws IOException {

        // Set a filter factory
        PrintFilterFactory filterFactory = new PrintFilterFactory();
        ObjectInputFilter.Config.setSerialFilterFactory(filterFactory);

        // create a maxdepth and package filter
        ObjectInputFilter filter1 =
                ObjectInputFilter.Config.createFilter("com.mkyong.java17.jep415.*;java.base/*;!*");
        ObjectInputFilter.Config.setSerialFilter(filter1);

        // Create a filter to allow String.class only
        ObjectInputFilter intFilter = ObjectInputFilter.allowFilter(
                cl -> cl.equals(String.class), ObjectInputFilter.Status.REJECTED);

        // if pass anything other than String.class, hits filter status: REJECTED
        //byte[] byteStream =convertObjectToStream(99);

        // Create input stream
        byte[] byteStream =convertObjectToStream("hello");
        InputStream is = new ByteArrayInputStream(byteStream);
        ObjectInputStream ois = new ObjectInputStream(is);

        ois.setObjectInputFilter(intFilter);

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