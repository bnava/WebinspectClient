/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microfocus.wiclient.service;

import com.microfocus.wiclient.DatosUtil;
import com.microfocus.wiclient.util.ProcessCommand;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author bnava
 */
public class Scan {

    public String scan(String[] args) {
        String scanid="";
        DatosUtil dt = new DatosUtil();
        ProcessCommand process = new ProcessCommand();
        return process.executeComand(args,"Scan ID:");
//        try {
//            final Process childProcess = new ProcessBuilder().command(args).start();
//            try (final BufferedReader br = new BufferedReader(new InputStreamReader(childProcess.getInputStream()))) {
//                StringBuilder sb = new StringBuilder();
//                String line;
//
//                while ((line = br.readLine()) != null) {
//                    sb.append(line + System.lineSeparator());
//                    if (line.contains("Scan ID:")) {
//                        System.out.println("El Scan ID: " + line.split(":")[1].replaceAll(" ", ""));
//                        scanid = line.split(":")[1].replaceAll(" ", "");
//                    }
//                }
//                System.out.println(sb.toString());
//            }
//        } catch (IOException ioe) {
//            System.out.println(ioe);
//        }
//        return scanid;
    }
    
    public void Validascan(String[] args) {
        String scanid="";
        DatosUtil dt = new DatosUtil();
        ProcessCommand process = new ProcessCommand();
        boolean validation=true;
           while(validation){
        try {
            final Process childProcess = new ProcessBuilder().command(args).start();
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(childProcess.getInputStream()))) {
                StringBuilder sb = new StringBuilder();
                String line;

                    while ((line = br.readLine()) != null) {
                        if (line.contains("PROCESS_COMPLETE")) {
                            validation = false;
                            System.out.println(line);
                        }
                    }
                System.out.println(sb.toString());
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
    }
}
