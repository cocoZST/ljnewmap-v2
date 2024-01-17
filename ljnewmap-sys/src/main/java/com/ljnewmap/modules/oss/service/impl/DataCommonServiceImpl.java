package com.ljnewmap.modules.oss.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ZipUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljnewmap.modules.oss.dao.DataCommonDao;
import com.ljnewmap.modules.oss.dto.DataCommonDTO;
import com.ljnewmap.modules.oss.entity.DataCommonEntity;
import com.ljnewmap.modules.oss.service.DataCommonService;
import com.ljnewmap.modules.security.user.SecurityUser;
import org.apache.poi.ss.usermodel.DataFormat;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class DataCommonServiceImpl extends ServiceImpl<DataCommonDao, DataCommonEntity> implements DataCommonService {

    @Autowired
    private DataCommonDao dataCommonDao;

    @Value("${web.files-path}")
    private String filesPath;

    @Override
    public void upload(DataCommonDTO dataCommonDTO) {

        // TODO 存储文件 获取当事人 设置文件路径
        Long userId = SecurityUser.getUserId();

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
        String date = format.format(new Date());

        String uuid = UUID.randomUUID().toString();

        MultipartFile file = dataCommonDTO.getFile();
        String fileName = file.getOriginalFilename();
        String suffix = FileUtil.getSuffix(fileName);

        String relativePath = userId + "/" + suffix + "/" + date;
        String fileRelativePath = relativePath + "/" + uuid;
        File uploadFile = new File(filesPath + fileRelativePath);

        if ("zip".equals(suffix)){
            File tempDir = new File(filesPath + "temp");
            File tempFile = FileUtil.createTempFile(tempDir);
            try {
                FileUtil.writeFromStream(file.getInputStream(),tempFile);
                ZipUtil.unzip(tempFile,uploadFile);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                FileUtil.del(tempFile);
            }
        }else {
            try {
                FileUtil.writeFromStream(file.getInputStream(),uploadFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        DataCommonEntity dataCommon = new DataCommonEntity();
        BeanUtils.copyProperties(dataCommonDTO,dataCommon);

        dataCommon.setUrl(fileRelativePath);
        // TODO 获取当事人
//        Date date = new Date();
//        dataCommon.setCreateDate(date);
//        dataCommon.setUpdateDate(date);
        dataCommonDao.insert(dataCommon);
    }
}
