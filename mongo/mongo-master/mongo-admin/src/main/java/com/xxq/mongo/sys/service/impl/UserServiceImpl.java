package com.xxq.mongo.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxq.mongo.common.PoiUtils;
import com.xxq.mongo.core.page.MybatisPageHelper;
import com.xxq.mongo.core.page.PageRequest;
import com.xxq.mongo.core.page.PageResult;
import com.xxq.mongo.sys.entity.Menu;
import com.xxq.mongo.sys.entity.Role;
import com.xxq.mongo.sys.entity.User;
import com.xxq.mongo.sys.mapper.MenuMapper;
import com.xxq.mongo.sys.mapper.UserMapper;
import com.xxq.mongo.sys.service.IUserService;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户管理 服务实现类
 * </p>
 *
 * @author XuXiuquan
 * @since 2020-04-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    MenuMapper menuMapper;

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest,userMapper);
    }

    @Override
    public Set<String> findPermissions(String name) {
        Set<String> perms = new HashSet<>();
        List<Menu> sysMenus = menuMapper.findByUserName(name);
        for(Menu sysMenu:sysMenus) {
            if(sysMenu.getPerms() != null && !"".equals(sysMenu.getPerms())) {
                perms.add(sysMenu.getPerms());
            }
        }
        return perms;
    }

    @Override
    public List<Role> findUserRoles(Long userId) {
        return userMapper.findUserRoles(userId);
    }

    @Override
    public File createUserExcelFile(PageRequest pageRequest) {
        PageResult pageResult = findPage(pageRequest);
        return createUserExcelFile(pageResult.getContent());
    }

    @Override
    public User findByName(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        String column = "name";
        queryWrapper.lambda().eq(User::getName,username);
        return getOne(queryWrapper);
    }

    private File createUserExcelFile(List<?> records) {
        if (records == null){
            records = new ArrayList<>();
        }
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("sheets");
        XSSFRow row0 = spreadsheet.createRow((short) 0);
        int columnIndex = 0;
        row0.createCell(columnIndex).setCellValue("NO");
        row0.createCell(++columnIndex).setCellValue("ID");
        row0.createCell(++columnIndex).setCellValue("用户名");
        row0.createCell(++columnIndex).setCellValue("昵称");
        row0.createCell(++columnIndex).setCellValue("机构");
        row0.createCell(++columnIndex).setCellValue("角色");
        row0.createCell(++columnIndex).setCellValue("邮箱");
        row0.createCell(++columnIndex).setCellValue("手机号");
        row0.createCell(++columnIndex).setCellValue("状态");
        row0.createCell(++columnIndex).setCellValue("头像");
        row0.createCell(++columnIndex).setCellValue("创建人");
        row0.createCell(++columnIndex).setCellValue("创建时间");
        row0.createCell(++columnIndex).setCellValue("最后更新人");
        row0.createCell(++columnIndex).setCellValue("最后更新时间");
        
        for (int i=0,j=0;i<records.size();i++){
            columnIndex = 0;
            User user = (User)records.get(i);
            XSSFRow row = spreadsheet.createRow((short) ++j);
            row.createCell(columnIndex).setCellValue(j);
            if(user.getId()!=null){
                row.createCell(++columnIndex).setCellValue(user.getId());
            }else {
                row.createCell(++columnIndex);
            }
            if(user.getName()!=null){
                row.createCell(++columnIndex).setCellValue(user.getName());
            }else {
                row.createCell(++columnIndex);
            }
            if(user.getNickName()!=null){
                row.createCell(++columnIndex).setCellValue(user.getNickName());
            }else {
                row.createCell(++columnIndex);
            }
            if(user.getDeptId()!=null){
                row.createCell(++columnIndex).setCellValue(user.getDeptId());
            }else {
                row.createCell(++columnIndex);
            }
            if(true){
                row.createCell(++columnIndex).setCellValue("");
            }else {
                row.createCell(++columnIndex);
            }
            if(user.getEmail()!=null){
                row.createCell(++columnIndex).setCellValue(user.getEmail());
            }else {
                row.createCell(++columnIndex);
            }
            if(user.getMobile()!=null){
                row.createCell(++columnIndex).setCellValue(user.getMobile());
            }else {
                row.createCell(++columnIndex);
            }
            if(user.getStatus()!=null){
                row.createCell(++columnIndex).setCellValue(user.getStatus());
            }else {
                row.createCell(++columnIndex);
            }
            if(user.getAvatar()!=null){
                row.createCell(++columnIndex).setCellValue(user.getAvatar());
            }else {
                row.createCell(++columnIndex);
            }
            if(user.getCreateBy()!=null){
                row.createCell(++columnIndex).setCellValue(user.getCreateBy());
            }else {
                row.createCell(++columnIndex);
            }
            if(user.getCreateTime()!=null){
                row.createCell(++columnIndex).setCellValue(user.getCreateTime());
            }else {
                row.createCell(++columnIndex);
            }
            if(user.getLastUpdateBy()!=null){
                row.createCell(++columnIndex).setCellValue(user.getLastUpdateBy());
            }else {
                row.createCell(++columnIndex);
            }
            if(user.getLastUpdateTime()!=null){
                row.createCell(++columnIndex).setCellValue(user.getLastUpdateTime());
            }else {
                row.createCell(++columnIndex);
            }
        }
        return PoiUtils.createExcelFile(workbook,"用户统计表");
    }
}
