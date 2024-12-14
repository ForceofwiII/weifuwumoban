package com.fow.weifuwumoban.controller;


import com.fow.weifuwumoban.entity.SkuImage;
import com.fow.weifuwumoban.service.SkuImageService;
import com.fow.weifuwumoban.utils.R;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
@RequestMapping("/product/image")
public class ImageController {

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

        try {

            // 2. 上传到 MinIO
            String objectName = dateFolder + "_" + originalFilename;
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
                            .build()
            );

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

            // 4. 返回图片 URL
            return R.ok(imageUrl);

        } catch (Exception e) {

            throw new RuntimeException("上传失败");
        }


    }
    }




