package com.cheng.ecommercebackend.controller.product;


import com.cheng.ecommercebackend.entity.product.category.AttrInfoList;
import com.cheng.ecommercebackend.entity.product.category.Category;
import com.cheng.ecommercebackend.service.product.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@Slf4j
@RestController
@RequestMapping("/api/admin/product")
@CrossOrigin(origins = "http://localhost:5173")  // 允許前端的來源
@RequiredArgsConstructor
public class categoryController {
    private final CategoryService categoryService;

    //取得分類第一層
    @GetMapping("/getCategory1")
    public Map<String, Object> getCategory1() {
        List<Category> data = categoryService.getCategory1();
        return Map.of("code", 200, "message", "成功取得資料", "data", data, "ok", true);
    }
    //取得分類第二層
    @GetMapping("/getCategory2/{category1Id}")
    public Map<String, Object> getCategory2(@PathVariable String category1Id) {
        List<Category> data = categoryService.getCategory2(category1Id);
        return Map.of("code", 200, "message", "成功取得資料", "data", data, "ok", true);
    }
    //取得分類第三層
    @GetMapping("/getCategory3/{category2Id}")
    public Map<String, Object> getCategory3(@PathVariable String category2Id) {
        List<Category> data = categoryService.getCategory3(category2Id);
        return Map.of("code", 200, "message", "成功取得資料", "data", data, "ok", true);
    }
    //獲取屬性質
    @GetMapping("/attrInfoList/{category1Id}/{category2Id}/{category3Id}")
    public Map<String, Object> getAttrInfoList(@PathVariable String category1Id,
                                               @PathVariable String category2Id,
                                               @PathVariable String category3Id) {
        List<AttrInfoList> data = categoryService.getAttrInfoListByCategory(category1Id, category2Id, category3Id);
        return Map.of("code", 200, "message", "成功取得資料", "data", data, "ok", true);
    }
    //新增屬性
    @PostMapping("/saveAttrInfo")
    public Map<String, Object> saveAttrInfo(@RequestBody AttrInfoList attrInfoList) {
        AttrInfoList saved = categoryService.saveAttrInfo(attrInfoList);
        return Map.of("code", 200, "message", "成功保存資料", "data", saved, "ok", true);
    }
    //刪除屬性
    @DeleteMapping("/deleteAttr/{attrId}")
    public ResponseEntity<Map<String, Object>> deleteAttr(@PathVariable String attrId) {
        boolean success = categoryService.deleteAttr(attrId);
        Map<String, Object> body =Map.of(
                "code", success ? 200 : 400,
                "message", success ? "成功刪除資料" : "刪除失敗，找不到ID",
                "data", "",
                "ok", success
        );
        return ResponseEntity.status(success ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(body);
    }
}
