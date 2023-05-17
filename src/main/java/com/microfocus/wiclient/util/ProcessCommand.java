/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microfocus.wiclient.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author bnava
 */
public class ProcessCommand {
    public String  executeComand(String [] cmd, String validation){
        String arg = "";
        StringBuilder sb = new StringBuilder();
        System.out.println(Arrays.toString(cmd));
        try {
            final Process childProcess = new ProcessBuilder().command(cmd).start();
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(childProcess.getInputStream()))) {
                
                String line ;

                while ((line = br.readLine()) != null) {
                    //sb.append(line).append(System.lineSeparator());
                    System.out.println(line);
                    if (line.contains(validation)) {
                         if (line.contains("Scan ID:")){ 
                        arg = line.split(":")[1].replaceAll(" ", "");
                             System.out.println(arg);
                         }
                        else
                        arg = validation;
                    }
                }
                System.out.println("");
            }
            //System.out.println(sb.toString());
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
        
        return arg;
    }
}
