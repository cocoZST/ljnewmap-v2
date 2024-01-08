package com.ljnewmap.modules.oss.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;
import com.ljnewmap.common.annotation.LogOperation;
import com.ljnewmap.common.constant.Constant;
import com.ljnewmap.common.exception.ErrorCode;
import com.ljnewmap.common.page.PageData;
import com.ljnewmap.common.utils.RT;
import com.ljnewmap.modules.oss.entity.OssEntity;
import com.ljnewmap.modules.oss.service.OssService;
import com.ljnewmap.modules.security.user.SecurityUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("sys/oss")
@Tag(name = "文件管理")
public class OssController {

    @Autowired
    private OssService ossService;

    @Value("${web.files-path}")
    private String filePath;


    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", in = ParameterIn.QUERY, required = true, schema = @Schema(type = "Integer")),
            @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in = ParameterIn.QUERY, required = true, schema = @Schema(type = "integer")),
            @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in = ParameterIn.QUERY, schema = @Schema(type = "string")),
            @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in = ParameterIn.QUERY, schema = @Schema(type = "string"))
    })
    public RT<PageData<OssEntity>> page(@Parameter(hidden = true) @RequestParam Map<String,Object> params){
        PageData<OssEntity> pageData = ossService.page(params);
        return new RT<PageData<OssEntity>>().ok(pageData);
    }

    @PutMapping("upload")
    @LogOperation("上传")
    @Operation(summary = "上传文件")
    public RT upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new RT().error("文件为空！");
        }

//        FileOutputStream fos = null;
//        try {
//            fos = new FileOutputStream(uploadFile);
//            fos.write(file.getBytes());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                fos.flush();
//                fos.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        String uuid = UUID.randomUUID().toString();

        File uploadFile = new File(filePath + uuid);
//        try {
//            FileUtil.writeBytes(file.getBytes(),uploadFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            FileUtil.writeFromStream(file.getInputStream(),uploadFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//       保存文件信息
        OssEntity ossEntity = new OssEntity();
        ossEntity.setPath(filePath);
        ossEntity.setUuidName(uuid);
        ossEntity.setOriginalName(file.getOriginalFilename());
        ossEntity.setCreator(SecurityUser.getUserId());
        ossEntity.setCreateDate(new Date());

        ossService.save(ossEntity);

        return new RT().ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @LogOperation("删除")
    public RT delete(@RequestBody Long[] ids){
        ossService.removeBatchByIds(Arrays.asList(ids));
        return new RT().ok();
    }
}
