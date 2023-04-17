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
public class Report {
    public void generateReportFpr(String scanid) {
        String[] cmd = { "cmd", "/c", "cd", "\"C:\\Program Files\\Fortify\\Fortify WebInspect\"", "&&", "WI.exe", "-ix",
                scanid, "-ep", "\"C:\\WebInspectAutomatization\\ReportesFPR\\" + scanid + ".fpr\"" };
        DatosUtil dt = new DatosUtil();
        ProcessCommand process = new ProcessCommand();
        process.executeComand(cmd, dt.ExportFPR);
    }

    public void generateReportPDF(String scanid) {
        String[] cmd = { "cmd", "/c", "cd", "\"C:\\Program Files\\Fortify\\Fortify WebInspect\"", "&&", "WI.exe", "-ix",
                scanid, "-ep", "\"C:\\WebInspectAutomatization\\ReportesFPR\\" + scanid + ".fpr\"" };
        DatosUtil dt = new DatosUtil();
        ProcessCommand process = new ProcessCommand();
        process.executeComand(cmd, dt.ExportFPR);
    }

}