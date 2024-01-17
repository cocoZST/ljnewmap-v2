package com.ljnewmap.modules.oss.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ZipUtil;
import com.ljnewmap.common.annotation.LogOperation;
import com.ljnewmap.common.constant.Constant;
import com.ljnewmap.common.page.PageData;
import com.ljnewmap.common.utils.RT;
import com.ljnewmap.modules.oss.dto.DataCommonDTO;
import com.ljnewmap.modules.oss.entity.OssEntity;
import com.ljnewmap.modules.oss.service.DataCommonService;
import com.ljnewmap.modules.oss.service.OssService;
import com.ljnewmap.modules.security.user.SecurityUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("sys/oss")
@Tag(name = "文件管理")
public class OssController {

    @Autowired
    private OssService ossService;

    @Autowired
    private DataCommonService dataCommonService;

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
    public RT<PageData<OssEntity>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params) {
        PageData<OssEntity> pageData = ossService.page(params);
        return new RT<PageData<OssEntity>>().ok(pageData);
    }


    @PostMapping("upload")
    @Operation(summary = "文件上传")
    @Parameters({
            @Parameter(name = "name",description = "数据名称",in = ParameterIn.QUERY,required = true,schema = @Schema(type = "String")),
            @Parameter(name = "dataPattern",description = "数据格式",in = ParameterIn.QUERY,required = true,schema = @Schema(type = "String")),
            @Parameter(name = "dataCategory",description = "数据类别",in = ParameterIn.QUERY,schema = @Schema(type = "String")),
            @Parameter(name = "isOpen",description = "是否公开（0不公开、1公开）",in = ParameterIn.QUERY,required = true,schema = @Schema(type = "Integer")),
            @Parameter(name = "description",description = "描述",in = ParameterIn.QUERY,schema = @Schema(type = "String")),
            @Parameter(name = "file",description = "上传文件",in = ParameterIn.QUERY,required = true,schema = @Schema(type = "string",format = "binary",requiredMode = Schema.RequiredMode.REQUIRED)),
            @Parameter(name = "tag",description = "标签",in = ParameterIn.QUERY,schema = @Schema(type = "String"))
    })
    public RT upload(@Parameter(hidden = true) DataCommonDTO dataCommonDTO) {

        dataCommonService.upload(dataCommonDTO);
        return new RT().ok();

//        if (files.isEmpty()) {
//            return new RT().error("文件为空！");
//        }
//        Long userID = SecurityUser.getUserId();

//        for (MultipartFile file : files) {
//            String suffix = FileUtil.getSuffix(file.getOriginalFilename());
//            String uuid = UUID.randomUUID().toString();
//
//            File uploadFile = new File(filePath + userID + "/" + suffix + "/" + uuid);
//
//            if ("zip".equals(suffix)) {
//                File tempDir = new File(filePath + "temp");
//                File tempFile = FileUtil.createTempFile(tempDir);
//                try {
//                    FileUtil.writeFromStream(file.getInputStream(), tempFile);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                ZipUtil.unzip(tempFile, uploadFile);
//                FileUtil.del(tempFile);
//            } else {
//                try {
//                    FileUtil.writeFromStream(file.getInputStream(), uploadFile);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//
//        }

//        String uuid = UUID.randomUUID().toString();
//        File zipFile = new File(filePath + "temp/" + uuid);
//        FileOutputStream outputStream = null;
//        try {
//            outputStream = new FileOutputStream(zipFile);
//            IoUtil.copy(file.getInputStream(), outputStream);
//            File unzipFile = ZipUtil.unzip(zipFile);
//            FileUtil.del(zipFile);
//            List<File> fileList = FileUtil.loopFiles(unzipFile, (file1) -> !file1.getName().startsWith("."));
//            for (File subFile : fileList) {
//                System.out.println(subFile.getPath());
//
//                // TODO 存储文件，记录日志
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                outputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

    }

    @DeleteMapping
    @Operation(summary = "删除")
    @LogOperation("删除")
    public RT delete(@RequestBody Long[] ids) {
        ossService.removeBatchByIds(Arrays.asList(ids));
        return new RT().ok();
    }


    @GetMapping("download")
    @Operation(summary = "下载")
    public void download(HttpServletResponse response){
        Long userID = SecurityUser.getUserId();
        File file = new File(filePath + userID + "/rtf/b5db11a9-334d-49b1-ad48-af308b1930bf");

    }
}

