package com.xxq.mongo.backup.service;

/**
 * @ Author     ：XuXiuquan.
 * @ Date       ：Created in 13:36 2020/4/25
 * @ Description：MYSQL命令行备份恢复服务
 * @ Modified By：
 * @Version : 1.0
 */
public interface MysqlBackupService {
    /**
     *  备份数据库
     * @param host 数据库IP地址
     * @param userName 数据库用户名
     * @param password 数据库密码
     * @param backupFolderPath 备份的路径
     * @param fileName 备份的文件名
     * @param database 数据库实例名
     * @return
     * @throws Exception
     */
    boolean backup(String host,String userName,String password,String backupFolderPath,String fileName,String database) throws Exception;

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
    boolean restore(String restoreFilePath,String host,String userName,String password,String database) throws Exception;
}
