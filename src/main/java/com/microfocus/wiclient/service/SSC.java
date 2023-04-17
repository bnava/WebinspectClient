/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microfocus.wiclient.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.login.Configuration;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author bnava
 */
public class SSC {

    Properties properties = new Properties();

    public SSC() {
        try {
            properties.load(new FileInputStream(new File("C:\\WebInspectAutomatization\\env.properties")));
        } catch (IOException ex) {
            Logger.getLogger(SSC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void uploadFpr(String reponame, String branch, String scanid) throws Exception {
        String[] cmd = properties.getProperty("UPLOAD_FPR").split("  ");
        for (int i = 0; i < cmd.length; i++) {
            cmd[i] = cmd[i].replace("  ", " ");
        }

        cmd[9] = cmd[9]+scanid+".fpr\"";
        cmd[11] = reponame + ":" + branch;
        System.out.println(Arrays.toString(cmd));
        //StringBuilder sb = new StringBuilder();
        String artifac_id = "";
        try {
            final Process childProcess = new ProcessBuilder().command(cmd).start();
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(childProcess.getInputStream()))) {

                String line;

                while ((line = br.readLine()) != null) {
                    //sb.append(line);
                    artifac_id +=line;
                    System.out.println(line);
                }
            }
            
                if(artifac_id.isEmpty()||artifac_id == null){
                try (final BufferedReader br = new BufferedReader(new InputStreamReader(childProcess.getErrorStream()))) {

                String line;

                while ((line = br.readLine()) != null) {
                    //sb.append(line + System.lineSeparator());
                    //System.out.println(line);
                }
                
            }
            }else{
                    validUploadFPR(artifac_id);
                }
        } catch (IOException ioe) {
            System.out.println(ioe);
            logoutSSC();
        }

        
    }

    public boolean authSSC() throws Exception {
        String[] cmd = properties.getProperty("AUTH_SSC").split("  ");
        for (int i = 0; i < cmd.length; i++) {
            cmd[i] = cmd[i].replace("  ", " ");
        }

        cmd[10] = properties.getProperty("FORTIFY_SSC_URL");
        cmd[12] = properties.getProperty("FORTIFY_SSC_USERNAME");
        cmd[14] = properties.getProperty("FORTIFY_SSC_PASSWORD");
        System.out.println(Arrays.toString(cmd));
        String validation ="";
        boolean fallo=true;
        try {
            final Process childProcess = new ProcessBuilder().command(cmd).start();
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(childProcess.getInputStream()))) {

                String line;

                while ((line = br.readLine()) != null) {
                    //sb.append(line + System.lineSeparator());
                    System.out.println(line);
                    validation+=line;
                }
                
            }
            
            if(validation.isEmpty()||validation == null){
                try (final BufferedReader br = new BufferedReader(new InputStreamReader(childProcess.getErrorStream()))) {

                String line;

                while ((line = br.readLine()) != null) {
                    //sb.append(line + System.lineSeparator());
                    System.out.println(line);
                    fallo=false;
                }
                
            }
            }
             //System.out.println(sb.toString());
        } catch (Exception ioe) {
            System.out.println("Exception"+ioe);
        }
        return fallo;
    }

    public boolean validaApp(String reponame,String branch) throws Exception {
        String[] cmd = properties.getProperty("VALID_APP").split("  ");
        for (int i = 0; i < cmd.length; i++) {
            cmd[i] = cmd[i].replace("  ", " ");
        }

        cmd[9] = reponame+":"+branch;
        System.out.println(Arrays.toString(cmd));
//        StringBuilder sb = new StringBuilder();
        String validation = "";
        boolean response = false;
        try {
            final Process childProcess = new ProcessBuilder().command(cmd).start();
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(childProcess.getInputStream()))) {

                String line;

                while ((line = br.readLine()) != null) {
                    validation += line;
                    System.out.println(line);
                }
               
            }
            if(validation.isEmpty()||validation == null){
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(childProcess.getErrorStream()))) {

                String line;

                while ((line = br.readLine()) != null) {
                    response = true;
                    //System.out.println(line);
                }
               
            }
            }
                //System.out.println(sb.toString());
        } catch (IOException ioe) {
            System.out.println(ioe);
            logoutSSC();
        }
        return response;
    }

    public void createAppAndVersion(String reponame, String branch) throws Exception {
        String[] cmd = properties.getProperty("CREATE_APPANDVERSION").split("  ");
        for (int i = 0; i < cmd.length; i++) {
            cmd[i] = cmd[i].replace("  ", " ");
        }

        cmd[9] = reponame + ":" + branch;
        System.out.println(Arrays.toString(cmd));
        //StringBuilder sb = new StringBuilder();
        String validation = "";
        try {
            final Process childProcess = new ProcessBuilder().command(cmd).start();
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(childProcess.getInputStream()))) {

                String line;

                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                    validation+=line;
                }
            }
            if(validation.isEmpty()||validation == null){
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(childProcess.getInputStream()))) {

                String line;

                while ((line = br.readLine()) != null) {
                    //System.out.println(line);
                }
            }
            }
                //System.out.println(sb.toString());
        } catch (IOException ioe) {
            System.out.println(ioe);
            logoutSSC();
        }
    }

    public void validUploadFPR(String artifactId) throws Exception {
        String[] cmd = properties.getProperty("UPLOAD_FPR_VALID").split("  ");
        for (int i = 0; i < cmd.length; i++) {
            cmd[i] = cmd[i].replace("  ", " ");
        }

        cmd[9] = artifactId;
        boolean nocomplete = true;
        System.out.println(Arrays.toString(cmd));
        StringBuilder sb = new StringBuilder();
        while (nocomplete) {
            try {
                final Process childProcess = new ProcessBuilder().command(cmd).start();
                try (final BufferedReader br = new BufferedReader(
                        new InputStreamReader(childProcess.getInputStream()))) {

                    String line;

                    while ((line = br.readLine()) != null) {
                        //sb.append(line + System.lineSeparator());
                        if (line.contains("PROCESS_COMPLETE")) {
                            nocomplete = false;
                            System.out.println(line);
                            //System.out.println("PROCESS_COMPLETE");
                        }
                    }
                    
                }
                   // System.out.println(sb.toString());
            } catch (IOException ioe) {
                System.out.println(ioe);
                logoutSSC();
            }
        }
    }

    public void logoutSSC() throws Exception {
        String[] cmd = properties.getProperty("LOGOUT_SESSION_SSC").split("  ");
        for (int i = 0; i < cmd.length; i++) {
            cmd[i] = cmd[i].replace("  ", " ");
        }

        cmd[10] = properties.getProperty("FORTIFY_SSC_USERNAME");
        cmd[12] = properties.getProperty("FORTIFY_SSC_PASSWORD");
        System.out.println(Arrays.toString(cmd));
        StringBuilder sb = new StringBuilder();
        try {
            final Process childProcess = new ProcessBuilder().command(cmd).start();
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(childProcess.getInputStream()))) {

                String line;

                while ((line = br.readLine()) != null) {
                    //sb.append(line + System.lineSeparator());
                    System.out.println(line);
                }
               
            }
                //System.out.println(sb.toString());
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
}
