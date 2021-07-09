/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mini.apploader;

import org.mini.gui.GCallBack;
import org.mini.zip.Zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 * @author gust
 */
public class AppLoader {

    static final String APP_INFO_FILE = "/appinfo.properties";
    static final String APP_LIST_FILE = "/applist.properties";
    static final String APP_CONFIG = "config.txt";
    static final String APP_DIR = "/apps/";
    static final String APP_DATA_DIR = "/appdata/";
    static final String TMP_DIR = "/tmp/";
    static final String EXAMPLE_APP_FILE = "ExApp.jar";
    static final String KEY_BOOT = "boot";
    static final String KEY_LANGUAGE = "language";
    static Properties appinfo = new Properties();
    static Properties applist = new Properties();

    /**
     *
     */
    public static void onSurfaceCreated() {

        //System.out.println("start loader");
        //
        checkDir();
        loadProp(APP_INFO_FILE, appinfo);
        loadProp(APP_LIST_FILE, applist);

        for (String s : getAppList()) {
            try {
                if (!isJarExists(s)) {
                    removeApp(s);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        saveProp(APP_LIST_FILE, applist);

        copyExApp();
        String bootApp = appinfo.getProperty(KEY_BOOT);
        if (bootApp == null || !isJarExists(bootApp)) {
            setBootApp(EXAMPLE_APP_FILE);
            bootApp = EXAMPLE_APP_FILE;
        }
        runApp(bootApp);
    }

    static void checkDir() {
        File f = new File(GCallBack.getInstance().getAppSaveRoot() + APP_DIR);
        if (!f.exists()) {
            f.mkdirs();
        }

        f = new File(GCallBack.getInstance().getAppSaveRoot() + APP_DATA_DIR);
        if (!f.exists()) {
            f.mkdirs();
        }
        //clear tmp files
        f = new File(GCallBack.getInstance().getAppSaveRoot() + TMP_DIR);
        deleteTree(f);
        //check tmp dir
        f = new File(GCallBack.getInstance().getAppSaveRoot() + TMP_DIR);
        if (!f.exists()) {
            f.mkdirs();
        }
    }

    static public String getTmpDirPath() {
        return GCallBack.getInstance().getAppSaveRoot() + TMP_DIR;
    }

    static public String getAppJarPath(String jarName) {
        String s = GCallBack.getInstance().getAppSaveRoot() + APP_DIR + jarName;
        return s;
    }

    static public String getAppDataPath(String jarName) {
        String s = GCallBack.getInstance().getAppSaveRoot() + APP_DATA_DIR + jarName + "/";
        File f = new File(s);
        if (!f.exists()) {
            f.mkdirs();
        }
        return s;
    }

    static void copyExApp() {
        String srcPath = GCallBack.getInstance().getAppResRoot() + "/resfiles/" + EXAMPLE_APP_FILE;
        //if (!applist.contains(EXAMPLE_APP_FILE) || !isJarExists(srcPath)) {
        addApp(EXAMPLE_APP_FILE, srcPath);
        //System.out.println("copy exapp");
        //}
    }

    public static void loadProp(String fname, Properties prop) {
        try {
            File f = new File(GCallBack.getInstance().getAppSaveRoot() + fname);
            if (f.exists()) {
                FileInputStream fis = new FileInputStream(f);
                prop.load(fis);
                //System.out.println(fname + " size: " + prop.size());
                fis.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void saveProp(String name, Properties prop) {
        try {
            File f = new File(GCallBack.getInstance().getAppSaveRoot() + name);

            FileOutputStream fos = new FileOutputStream(f);
            prop.store(fos, "");
            fos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getBootApp() {
        String defaultApp = appinfo.getProperty(KEY_BOOT);
        return defaultApp;
    }

    public static void setBootApp(String jarName) {
        appinfo.put(KEY_BOOT, jarName);
        saveProp(APP_INFO_FILE, appinfo);
    }

    public static boolean isJarExists(String jarName) {
        File f = new File(getAppJarPath(jarName));
        return f.exists();
    }

    public static Class getApplicationClass(String jarName) {
        try {
            String className = getAppConfig(jarName, "app");
            if (className != null && className.length() > 0) {
                //System.out.println("className:" + className);

                StandalongGuiAppClassLoader sgacl = new StandalongGuiAppClassLoader(getAppJarPath(jarName), ClassLoader.getSystemClassLoader());
                Thread.currentThread().setContextClassLoader(sgacl);
                Class c = sgacl.loadClass(className);

                return c;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static byte[] getApplicationIcon(String jarName) {
        String iconName = getAppConfig(jarName, "icon");
        String jarFullPath = getAppJarPath(jarName);
        return Zip.getEntry(jarFullPath, iconName);
    }

    public static String getApplicationDesc(String jarName) {
        String desc = getAppConfig(jarName, "desc");
        if (desc == null) {
            desc = "No description.";
        } else {
            desc = desc.replace("\\n", "\n");
        }
        return desc;
    }

    public static String getApplicationName(String jarName) {
        String name = getAppConfig(jarName, "name");
        if (name == null) {
            name = jarName;
        }
        return name;
    }

    public static String getApplicationVersion(String jarName) {
        String v = getAppConfig(jarName, "version");
        if (v == null) {
            v = "";
        }
        return v;
    }

    public static String getApplicationUpgradeurl(String jarName) {
        String url = getAppConfig(jarName, "upgradeurl");
        if (url == null) {
            url = "";
        }
        return url;
    }

    public static long getApplicationFileSize(String jarName) {
        String path = getAppJarPath(jarName);
        File f = new File(path);
        if (f.exists()) {
            return f.length();
        }
        return -1;
    }

    static String getAppConfig(String jarName, String key) {
        try {
            String jarFullPath = getAppJarPath(jarName);
            File f = new File(jarFullPath);
            if (f.exists()) {
                //System.out.println("jar path:" + jarFullPath + "  " + key);
                byte[] b = Zip.getEntry(jarFullPath, APP_CONFIG);
                //System.out.println("b=" + b);
                if (b != null) {

                    String s = new String(b, "utf-8");
                    //System.out.println("file contents :" + s);
                    s = s.replace("\r", "\n");
                    String[] ss = s.split("\n");
                    for (String line : ss) {
                        int pos = line.indexOf("=");
                        if (pos > 0) {
                            String k = line.substring(0, pos).trim();
                            String v = line.substring(pos + 1).trim();
                            if (k.equalsIgnoreCase(key)) {
                                return v;
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    static public List<String> getAppList() {
        List<String> list = new ArrayList();
        for (Enumeration e = applist.keys(); e.hasMoreElements(); ) {
            try {
                String s = (String) e.nextElement();
                list.add(s.trim());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }

    static public void putAppList(List<String> list) {
        //clear old
        applist.clear();
        for (String s : list) {
            applist.put(s, "");
        }
    }

    public static void runApp(String jarName) {
        try {

            Class c = getApplicationClass(jarName);
            if (c != null) {
                c.newInstance();
                //app.setSaveRoot(getAppDataPath(jarName));
                //GCallBack.getInstance().setApplication(app);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
           // GCallBack.getInstance().setApplication(null);
        } finally {
        	//
        }
    }

    public static void addApp(String jarName, String srcJarFullPath) {
        try {
            applist.put(jarName, "");
            //copy file
            //System.out.println("copy from: " + jarPath + "  to :" + getAppJarPath(jarName));
            FileInputStream fis = new FileInputStream(srcJarFullPath);
            FileOutputStream fos = new FileOutputStream(getAppJarPath(jarName));
            byte[] b = new byte[1024];
            int read;
            while ((read = fis.read(b)) != -1) {
                fos.write(b, 0, read);
            }
            fis.close();
            fos.close();

            saveProp(APP_INFO_FILE, appinfo);
            saveProp(APP_LIST_FILE, applist);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void addApp(String jarName, byte[] jarData) {
        try {
            if (jarName != null && jarData != null && jarData.length > 0) {
                applist.put(jarName, "");
                //copy file
                //System.out.println("add from: " + jarData.length + "  to :" + getAppJarPath(jarName));
                FileOutputStream fos = new FileOutputStream(getAppJarPath(jarName));
                fos.write(jarData);
                fos.close();
                saveProp(APP_INFO_FILE, appinfo);
                saveProp(APP_LIST_FILE, applist);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void removeApp(String jarName) {
        applist.remove(jarName);
        //delete jar
        String jarFullPath = getAppJarPath(jarName);
        File f = new File(jarFullPath);
        if (f.exists()) {
            f.delete();
        }
        //delete data
        f = new File(getAppDataPath(jarName));
        deleteTree(f);

        saveProp(APP_INFO_FILE, appinfo);
        saveProp(APP_LIST_FILE, applist);
    }


    static void deleteTree(File f) {
        if (f.isDirectory()) {
            File[] files = f.listFiles();
            for (File sf : files) {
                //System.out.println("file:" + sf.getAbsolutePath());
                deleteTree(sf);
            }
        }
        boolean s = f.delete();
        //System.out.println("delete " + f.getAbsolutePath() + " state:" + s);
    }
}
