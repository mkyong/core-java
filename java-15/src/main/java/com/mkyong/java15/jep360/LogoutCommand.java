package com.mkyong.java15.jep360;

// sealed class must have subclasses
public sealed class LogoutCommand implements Command permits LogoutAndDeleteCachedCommand {
}
