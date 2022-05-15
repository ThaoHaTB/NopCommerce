package com;

import java.io.File;

public class GlobalConstants {
    public static final long SHORT_TIME_OUT=5;
    public static final long LONGTIME_OUT=5;

    public static final String projectPath= System.getProperty("user.dir");
    public static final String UPLOAD_FOLDER=projectPath+ File.separator+ "uploadFiles"+File.separator;
    public static final String DATA_PROPERTIES=projectPath+ File.separator+ "resources"+File.separator +"Data.properties";
    public static String email="";
    public static String password="";
    public static String newPassword="Ad123@!23";
}
