package com.fow.weifuwumoban.controller;


import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fow.weifuwumoban.entity.SkuImage;
import com.fow.weifuwumoban.service.SkuImageService;
import com.fow.weifuwumoban.utils.R;
import com.fow.weifuwumoban.vo.FileVo;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.errors.*;
import io.minio.http.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/product/image")
public class FileController {

    private static final Logger log = LoggerFactory.getLogger(FileController.class);
    @Autowired
    MinioClient minioClient;

    @Autowired
    SkuImageService skuImageService;



    @PostMapping("/upload")
    public R upload(  @RequestParam("file") MultipartFile file) {


        String bucketName = "moban";
        long size = file.getSize();
        String originalFilename = file.getOriginalFilename();
        // 获取当前日期，生成文件夹路径
        String dateFolder = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));



        if (file.isEmpty()) {
            return R.error("上传失败，请重试");
        }

        String uuid = RandomUtil.randomString(16);

        try {

            // 2. 上传到 MinIO
            String objectName = dateFolder + "/" + uuid;
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .stream(file.getInputStream(), size, -1)
                            .contentType(file.getContentType())
                            .build()
            );

            // 获取图片的访问 URL
            String imageUrl = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucketName)
                            .object(objectName)
                            .expiry(7, TimeUnit.DAYS) 
                            .build()
            );

            imageUrl=imageUrl.split("\\?")[0];

            // 3. 保存图片信息到数据库
            SkuImage skuImage = new SkuImage();

            skuImage.setUrl(imageUrl);
            skuImage.setName(originalFilename);
            skuImage.setType(file.getContentType());
            skuImage.setFormat(originalFilename.substring(originalFilename.lastIndexOf(".") + 1));
            skuImage.setUserName("admin");
            skuImage.setCreateTime(LocalDateTime.now());
            skuImage.setUpdateTime(LocalDateTime.now());
            skuImageService.save(skuImage);

            FileVo fileVo = new FileVo();
            BeanUtils.copyProperties(skuImage, fileVo);

            // 4. 返回图片 URL
            return R.ok(fileVo);

        } catch (Exception e) {

            throw new RuntimeException("上传失败");
        }


    }


    @PostMapping("/user/upload")
    public R userUpload(  @RequestParam("file") MultipartFile file) throws IOException {



            String type = FileTypeUtil.getType(file.getInputStream());
            log.info("文件类型{}",type);
            if(!("jpg".equalsIgnoreCase(type) || "png".equalsIgnoreCase(type))){

                return R.error("上传失败，请重试");
            }

            return R.ok();



    }



    @PostMapping("/delete")
    public R delete(@RequestParam("fileUrl") String fileUrl) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        QueryWrapper<SkuImage> skuImageQueryWrapper = new QueryWrapper<>();

        String bucketName = "moban";


        skuImageQueryWrapper.eq("url", fileUrl);
        SkuImage one = skuImageService.getOne(skuImageQueryWrapper);
        if(one ==null)
        {
            throw new RuntimeException("文件不存在");
        }

           String filePath = extractFilePath(fileUrl, bucketName);


            // 1. 从 MinIO 中删除文件
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(bucketName)
                            .object(filePath) // 文件路径，例如：2024-12-16/filename.jpg
                            .build()
            );


            skuImageService.remove(skuImageQueryWrapper);




        return R.ok();
    }


    private String extractFilePath(String url, String bucketName) {
        try {
            // 正则表达式提取文件路径
            String regex = ".*/" + bucketName + "/(.*)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(url);

            if (matcher.find()) {
                return matcher.group(1); // 提取文件路径部分
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // 提取失败返回 null
    }

    }




