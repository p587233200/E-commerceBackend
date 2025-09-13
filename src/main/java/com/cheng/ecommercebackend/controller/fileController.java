package com.cheng.ecommercebackend.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/product")
@CrossOrigin(origins = "http://localhost:5173")  // 允許前端的來源

public class fileController {

    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads";;

    @PostMapping("/fileUpload")
    public Map<String, Object> upload(@RequestParam("file") MultipartFile file) {
        Map<String, Object> result = new HashMap<>();
//        System.out.println(UPLOAD_DIR);
        try {
            // 確保 uploads 目錄存在
            File dir = new File(UPLOAD_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            // 模擬儲存 (這裡應該是存到雲端 / 本地資料夾)
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            File dest = new File(dir, fileName);
            file.transferTo(dest);

            String fileUrl = "http://localhost:8080/uploads/" + fileName;

            result.put("code", 200);
            result.put("message", "上傳成功");
            result.put("data", fileUrl);
            result.put("ok", true);
            return result;
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", "上傳失敗: " + e.getMessage());
            result.put("ok", false);
            System.out.println("error"+ e);
            return result;
        }
    }
}
