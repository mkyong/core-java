package com.mkyong.java19.jep424;

import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.foreign.SymbolLookup;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;

import static java.lang.foreign.ValueLayout.*;

public class JEP424_SORT {

    public static void main(String[] args) throws Throwable {

        // 1. Find foreign function on the C library path
        Linker linker = Linker.nativeLinker();
        SymbolLookup stdlib = linker.defaultLookup();

        // 2. Allocate on-heap memory to store strings
        String[] javaStrings = {"d", "z", "b", "c", "a"};

        // 3. Allocate off-heap memory to store pointers
        SegmentAllocator allocator = SegmentAllocator.implicitAllocator();
        MemorySegment offHeap = allocator.allocateArray(ValueLayout.ADDRESS, javaStrings.length);

        // 4. Copy the strings from on-heap to off-heap
        for (int i = 0; i < javaStrings.length; i++) {

            // Allocate a string off-heap, then store a pointer to it
            MemorySegment cString = allocator.allocateUtf8String(javaStrings[i]);
            offHeap.setAtIndex(ValueLayout.ADDRESS, i, cString);
        }

        MethodHandle radixSort = linker.downcallHandle(
                stdlib.lookup("radixsort").orElseThrow(),
                FunctionDescriptor.ofVoid(ADDRESS, JAVA_INT, ADDRESS, JAVA_CHAR));

        // 5. Sort the off-heap data by calling the foreign function
        radixSort.invoke(offHeap, javaStrings.length, MemoryAddress.NULL, '\0');

        // 6. Copy the (reordered) strings from off-heap to on-heap
        for (int i = 0; i < javaStrings.length; i++) {
            MemoryAddress cStringPtr = offHeap.getAtIndex(ValueLayout.ADDRESS, i);
            javaStrings[i] = cStringPtr.getUtf8String(0);
        }

        //print sort result
        for (String javaString : javaStrings) {
            System.out.println(javaString);
        }

    }

}
