package com.xxq.mongo.backup.datasource;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ Author     ：XuXiuquan.
 * @ Date       ：Created in 13:29 2020/4/25
 * @ Description：
 * @ Modified By：
 * @Version : 1.0
 */
@Component
@ConfigurationProperties(prefix = "mongo.backup.datasource")
@Setter
@Getter
public class BackupDataSourceProperties {
    private String host;
    private String userName;
    private String password;
    private String database;
    private String backupFolder;
}
