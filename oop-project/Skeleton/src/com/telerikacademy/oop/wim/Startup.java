package com.telerikacademy.oop.wim;

import com.telerikacademy.oop.wim.core.WIMEngineImpl;
import com.telerikacademy.oop.wim.models.TeamImpl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Startup {
    public static void main(String[] args) {
        WIMEngineImpl engine = new WIMEngineImpl();
        engine.testStart();


    }
}

