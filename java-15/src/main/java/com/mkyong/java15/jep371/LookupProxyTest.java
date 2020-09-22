package com.mkyong.java15.jep371;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Base64;

public class LookupProxyTest {

    /*
    package com.mkyong.java15.jep371;

        public class LookUpProxy{

            public static Integer lookup() {
                return 1;
            }
        }
    }
     */
    static final String CLASS_IN_BASE64 =
            "yv66vgAAADcAFQoABAANCgAOAA8HABAHABEBAAY8aW5pdD4BAAMoKV" +
            "YBAARDb2RlAQAPTGluZU51bWJlclRhYmxlAQAGbG9va3VwAQAVKClM" +
            "amF2YS9sYW5nL0ludGVnZXI7AQAKU291cmNlRmlsZQEAEExvb2tVcF" +
            "Byb3h5LmphdmEMAAUABgcAEgwAEwAUAQAkY29tL21reW9uZy9qYXZh" +
            "MTUvamVwMzcxL0xvb2tVcFByb3h5AQAQamF2YS9sYW5nL09iamVjdA" +
            "EAEWphdmEvbGFuZy9JbnRlZ2VyAQAHdmFsdWVPZgEAFihJKUxqYXZh" +
            "L2xhbmcvSW50ZWdlcjsAIQADAAQAAAAAAAIAAQAFAAYAAQAHAAAAHQ" +
            "ABAAEAAAAFKrcAAbEAAAABAAgAAAAGAAEAAAADAAkACQAKAAEABwAA" +
            "AB0AAQAAAAAABQS4AAKwAAAAAQAIAAAABgABAAAABgABAAsAAAACAAw=";

    public static void main(String[] args) throws Throwable {

        //byte[] array = Files.readAllBytes(Paths.get("/home/mkyong/test/LookUpProxy.class"));
        //String s = Base64.getEncoder().encodeToString(array);
        //System.out.println(s);

        testHiddenClass();
        //testDefineAnonymousClass();

    }

    /*public static void testDefineAnonymousClass() throws Throwable{

        byte[] classInBytes = Base64.getDecoder().decode(CLASS_IN_BASE64);

        Field theUnsafe = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        sun.misc.Unsafe unsafe = (sun.misc.Unsafe)theUnsafe.get(null);

        // @Deprecated(since = "15", forRemoval = false)
        Class<?> proxy = unsafe.defineAnonymousClass(
                LookupProxyTest.class, classInBytes, null);

        System.out.println(proxy.getName());

        MethodHandle mh = MethodHandles.lookup().findStatic(proxy,
                "lookup",
                MethodType.methodType(Integer.class));

        Integer status = (Integer) mh.invokeExact();
        System.out.println(status);

    }*/

    // create a hidden class and run its static method
    public static void testHiddenClass() throws Throwable {

        byte[] classInBytes = Base64.getDecoder().decode(CLASS_IN_BASE64);

        Class<?> proxy = MethodHandles.lookup()
                .defineHiddenClass(classInBytes,
                        true, MethodHandles.Lookup.ClassOption.NESTMATE)
                .lookupClass();

        // output: class com.mkyong.java15.jep371.LookUpProxy/0x0000000800b94440
        System.out.println(proxy.getName());

        MethodHandle mh = MethodHandles.lookup().findStatic(proxy,
                "lookup",
                MethodType.methodType(Integer.class));

        Integer status = (Integer) mh.invokeExact();
        System.out.println(status);

    }

}
