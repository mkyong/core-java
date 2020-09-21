package com.mkyong.java15.jep360;

public sealed interface Command permits LoginCommand, LogoutCommand, PluginCommand{
    //...
}
