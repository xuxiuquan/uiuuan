package com.xxq.mongo.backup.service.impl;

import com.xxq.mongo.backup.service.MysqlBackupService;
import com.xxq.mongo.backup.util.MysqlbackupRestoreUtils;
import org.springframework.stereotype.Service;

/**
 * @ Author     ：XuXiuquan.
 * @ Date       ：Created in 13:41 2020/4/25
 * @ Description：
 * @ Modified By：
 * @Version : 1.0
 */
@Service
public class MysqlBackupServiceImpl implements MysqlBackupService {
    @Override
    public boolean backup(String host, String userName, String password, String backupFolderPath, String fileName, String database) throws Exception {
        return MysqlbackupRestoreUtils.backup(host,userName,password,backupFolderPath,fileName,database);
    }

    @Override
    public boolean restore(String restoreFilePath, String host, String userName, String password, String database) throws Exception {
        return MysqlbackupRestoreUtils.restore(restoreFilePath,host,userName,password,database);
    }
}
