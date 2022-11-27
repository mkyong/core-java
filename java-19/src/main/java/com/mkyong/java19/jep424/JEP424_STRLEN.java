package com.mkyong.java19.jep424;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

import static java.lang.foreign.ValueLayout.ADDRESS;
import static java.lang.foreign.ValueLayout.JAVA_LONG;

public class JEP424_STRLEN {

    public static void main(String[] args) throws Throwable {

        String input = "Hello World";

        // 1. Find foreign function on the C library path
        SymbolLookup stdlib = Linker.nativeLinker().defaultLookup();

        // 2. Get a handle to the "strlen" function in the C standard library
        MethodHandle methodHandle = Linker.nativeLinker().downcallHandle(
                stdlib.lookup("strlen").orElseThrow(),
                FunctionDescriptor.of(JAVA_LONG, ADDRESS));

        // 3. Allocate off-heap memory to store strings
        MemorySegment memorySegment = SegmentAllocator
                .implicitAllocator().allocateUtf8String(input);

        // 4. Runs the foreign function "strlen"
        long length = (long) methodHandle.invoke(memorySegment);

        System.out.println("length = " + length);

    }

}
