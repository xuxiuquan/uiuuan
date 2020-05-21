package com.xxq.mongo.backup.controller;

import com.xxq.mongo.backup.Constants.BackupConstants;
import com.xxq.mongo.backup.datasource.BackupDataSourceProperties;
import com.xxq.mongo.backup.service.MysqlBackupService;
import com.xxq.mongo.backup.util.HttpResult;
import com.xxq.mongo.common.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.xxq.mongo.backup.Constants.BackupConstants.BACKUP_FILE_NAME;
import static com.xxq.mongo.backup.Constants.BackupConstants.DEFAULT_BACKUP_NAME;

/**
 * @ Author     ：XuXiuquan.
 * @ Date       ：Created in 15:26 2020/4/25
 * @ Description：备份还原控制器
 * @ Modified By：
 * @Version : 1.0
 */
@RestController
@RequestMapping("/backup")
public class MysqlBackupController {

    @Autowired
    MysqlBackupService mysqlBackupService;


    @Autowired
    BackupDataSourceProperties properties;

    /**
     * 数据备份接口
     * @return
     */
    @GetMapping("/backup")
    public HttpResult backup(){
        String backuupFolderName =  "backup_" + (new SimpleDateFormat(BackupConstants.DATE_FORMAT).format(new Date()));
        return backup(backuupFolderName);
    }

    private HttpResult backup(String backuupFolderName) {
        String host = properties.getHost();
        String userName = properties.getUserName();
        String password = properties.getPassword();
        String database = properties.getDatabase();
        String backupFolder = properties.getBackupFolder();
        String backupFolderPath = backupFolder + File.separator + backuupFolderName + File.separator;
        String fileName = BACKUP_FILE_NAME;
        try {
            boolean flag = mysqlBackupService.backup(host,userName,password,backupFolderPath,fileName,database);
            if(!flag){
                return HttpResult.error("数据备份失败");
            }
        } catch (Exception e) {
            HttpResult.error(500,e.getMessage());
        }
        return HttpResult.ok();
    }

    /**
     * 数据还原接口
     * @param name
     * @return
     */
    @GetMapping("/restore")
    public HttpResult restore(@RequestParam String name){
        String host = properties.getHost();
        String userName = properties.getUserName();
        String password = properties.getPassword();
        String database = "mongo1";
        String backupFolder = properties.getBackupFolder();
        String restoreFilePath = backupFolder + File.separator + name;
        try {
            mysqlBackupService.restore(restoreFilePath,host,userName,password,database);
        } catch (Exception e) {
            return HttpResult.error(500,e.getMessage());
        }
        return HttpResult.ok();
    }

    @GetMapping("/findRecords")
    public HttpResult findBackupRecords(){
        String restore_file = properties.getBackupFolder() + File.separator + DEFAULT_BACKUP_NAME + File.separator + BACKUP_FILE_NAME;
        if(!new File(restore_file).exists()){
            //初始默认备份
            backup(DEFAULT_BACKUP_NAME);
        }
        List<Map<String,String>> backupRecords = new ArrayList<>();
        File restoreFolderFile = new File(properties.getBackupFolder());
        if(restoreFolderFile.exists()){
            for(File file:restoreFolderFile.listFiles()){
                Map<String,String> backup = new HashMap<>();
                backup.put("name",file.getName());
                backup.put("title",file.getName());
                if(DEFAULT_BACKUP_NAME.equalsIgnoreCase(file.getName())){
                    backup.put("title","系统默认备份");
                }
                backupRecords.add(backup);
            }
        }
        List<Map<String,String>>  sortedList = backupRecords.stream().sorted((o1, o2) -> o1.get("name").compareTo(o2.get("name"))).collect(Collectors.toList());
        return HttpResult.ok().data(sortedList);
    }

    @GetMapping("/delete")
    public HttpResult deleteBackupRecord(@RequestParam String name){
        if(DEFAULT_BACKUP_NAME.equals(name)){
            return HttpResult.error("系统默认备份无法删除");
        }
        String restoreFilePath = properties.getBackupFolder() + File.separator + name;
        try{
            FileUtils.deleteFile(new File(restoreFilePath));
        }catch (Exception e){
            return HttpResult.error(500,e.getMessage());
        }
        return HttpResult.ok();
    }

}
