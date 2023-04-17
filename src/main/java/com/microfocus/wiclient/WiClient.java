/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microfocus.wiclient;

import com.microfocus.wiclient.service.Report;
import com.microfocus.wiclient.service.SSC;
import com.microfocus.wiclient.service.Scan;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author bnava
 */

public class WiClient {

    public static void main(String[] args) throws InterruptedException, IOException, Exception {
//        System.out.println(Arrays.toString(args));
        String reponame = "";
        String branch = "";
        for (int i = 0; i < args.length; i++) {
            if (args[i].equalsIgnoreCase("--reponame")) {
                reponame = args[i + 1];
            }
            if (args[i].equalsIgnoreCase("--branch")) {
                branch = args[i + 1];
            }
        }
        args = removeElement(args, "--reponame");
        args = removeElement(args, "--branch");
        args = removeElement(args, branch);
        args = removeElement(args, reponame);
        Scan sc = new Scan();
        Report rp = new Report();
        System.out.println("Inicia escaneo");
        String scanid = sc.scan(args);
        rp.generateReportFpr(scanid);
        SSC ssc = new SSC();
        if(ssc.authSSC()){
        if (ssc.validaApp(reponame,branch)) {
            ssc.createAppAndVersion(reponame, branch);
            ssc.uploadFpr(reponame, branch, scanid);
        } else {
            ssc.uploadFpr(reponame, branch, scanid);
        }
        ssc.logoutSSC();
        }
    }

    public static String[] removeElement(String[] arr, String item) {
        return Arrays.stream(arr)
                .filter(s -> !s.equals(item))
                .toArray(String[]::new);
    }
}
