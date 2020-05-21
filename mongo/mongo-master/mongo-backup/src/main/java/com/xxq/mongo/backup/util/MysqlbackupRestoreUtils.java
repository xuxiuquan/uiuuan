package com.xxq.mongo.backup.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @ Author     ：XuXiuquan.
 * @ Date       ：Created in 13:45 2020/4/25
 * @ Description：数据备份工具类
 * @ Modified By：
 * @Version : 1.0
 */
@Slf4j
public class MysqlbackupRestoreUtils {

    /**
     *  mysql通过命令备份数据库（https://www.cnblogs.com/xcxc/archive/2013/01/30/2882840.html）
     * @param host 数据库IP地址
     * @param userName 数据库用户名
     * @param password 数据库密码
     * @param backupFolderPath 备份的路径
     * @param fileName 备份的文件名
     * @param database 数据库实例名
     * @return
     * @throws Exception
     */
    public static boolean backup(String host, String userName, String password, String backupFolderPath, String fileName, String database) throws InterruptedException, IOException {
        char i = 13;
        File backupFolderFile = new File(backupFolderPath);
        if(!backupFolderFile.exists()){
            //如果目录不存在则创建目录
            backupFolderFile.mkdir();
        }
        if(!backupFolderPath.endsWith(File.separator)&&!backupFolderPath.endsWith("/")){
            backupFolderPath = backupFolderPath + File.separator;
        }
        String backupFilePath = backupFolderPath + fileName;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mysqldump --opt ").append(" --add-drop-database ").append(" --add-drop-table ");
        stringBuilder.append(" -h").append(host).append(" -u").append(userName).append(" -p").append(password).append(" ").append(database);
        stringBuilder.append(" > ").append(backupFilePath).append(" --default-character-set=utf8 ");

        String filePath = backupFolderPath+"\\"+"backup.bat";
        try{
            //如果module文件夹下没有bar.txt就会创建该文件
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
            bw.write(stringBuilder.toString());	//在创建好的文件中写入"Hello bar"
            bw.close();				//关闭文件
        }catch(IOException e) {
            e.printStackTrace();
        }


        //Process process = Runtime.getRuntime().exec(getCommand(stringBuilder.toString()));
        //调用外部执行exe文件的java api
        Process process = Runtime.getRuntime().exec(filePath);

        if(process.waitFor()==0){
            //0表示线程正常中止
            log.info("数据已经备份到"+backupFilePath+"文件中");
            return true;
        }
        return false;
    }

    /**
     *
     * @param restoreFilePath 数据库备份的脚本路径
     * @param host 数据库IP地址
     * @param userName 数据库用户名
     * @param password 数据库密码
     * @param database 数据库实例名
     * @return
     * @throws Exception
     */
    public static boolean restore(String restoreFilePath, String host, String userName, String password, String database) throws IOException, InterruptedException {
        File restoreFile = new File(restoreFilePath);
        if (restoreFile.isDirectory()){
            for(File file:restoreFile.listFiles()){
                if(file.exists()&&file.getPath().endsWith(".sql")){
                    restoreFilePath = file.getAbsolutePath();
                    break;
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mysql -h").append(host).append(" -u").append(userName).append(" -p").append(password);
        stringBuilder.append(" ").append(database).append(" < ").append(restoreFilePath);
        Process process = Runtime.getRuntime().exec(getCommand(stringBuilder.toString()));
        if(process.waitFor()==0){
            //0表示线程正常中止
            log.info("数据已从"+restoreFilePath+"导入到"+database+"数据库中");
            return true;
        }
        return false;
    }

    /**
     * 打开cmd的命令
     * @param command
     * @return
     */
    private static String[] getCommand(String command) {
        String os = System.getProperty("os.name");
        String shell = "/bin/bash";
        String c = "-k";
        //如果是window系统
        if(os.toLowerCase().startsWith("win")){
            shell = "cmd";
            c = "/c";
        }
        String[] cmd = { shell, c, command};
        return cmd;
    }

    public static void main(String[] args) throws Exception {
        String host = "localhost";
        String userName = "root";
        String password = "root";
        String database = "mongo";
        String database_backup = "mongo_backup";

        System.out.println("开始备份");
        String backupFolderPath = "C:\\DemoProject\\com.uiuuan\\mongo\\mongo-master\\mongo-backup\\src\\main\\resources\\backup";
        String fileName = "mango1.sql";
         backup(host, userName, password, backupFolderPath, fileName, database);
        System.out.println("备份成功");

        System.out.println("开始还原");
        String restoreFilePath = "C:\\DemoProject\\mongo-backup\\src\\main\\resources";
       // restore(restoreFilePath, host, userName, password, database_backup);
        System.out.println("还原成功");

    }
}
