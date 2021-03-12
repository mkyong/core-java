package com.mkyong.java16.jep389;

import jdk.incubator.foreign.CLinker;
import jdk.incubator.foreign.FunctionDescriptor;
import jdk.incubator.foreign.LibraryLookup;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.nio.file.Path;
import java.util.Optional;

public class JEP389 {

    public static void main(String[] args) throws Throwable {

        Path path = Path.of("/home/mkyong/projects/core-java/java-16/hello.so");

        LibraryLookup libraryLookup = LibraryLookup.ofPath(path);

        Optional<LibraryLookup.Symbol> optionalSymbol = libraryLookup.lookup("printHello");
        if (optionalSymbol.isPresent()) {

            LibraryLookup.Symbol symbol = optionalSymbol.get();

            FunctionDescriptor functionDescriptor = FunctionDescriptor.ofVoid();

            MethodType methodType = MethodType.methodType(Void.TYPE);

            MethodHandle methodHandle = CLinker.getInstance().downcallHandle(
                    symbol.address(),
                    methodType,
                    functionDescriptor);
            methodHandle.invokeExact();

        }

    }
}