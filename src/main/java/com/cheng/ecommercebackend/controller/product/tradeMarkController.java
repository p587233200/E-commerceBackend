package com.cheng.ecommercebackend.controller.product;


import com.cheng.ecommercebackend.entity.TradeMarkItem;
import com.cheng.ecommercebackend.service.product.TradeMarkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/api/admin/product/baseTrademark")
@CrossOrigin(origins = "http://localhost:5173")  // 允許前端的來源
@RequiredArgsConstructor
public class tradeMarkController {

    private final TradeMarkService tradeMarkService;

    // 取得品牌資料，第 page 頁，limit 筆資料
    @GetMapping("/{page}/{limit}")
    public Map<String,Object> getTradeMark(@PathVariable Integer page, @PathVariable Integer limit) {
        // Spring Data 的 page 是從 0 開始，所以要 -1
        Pageable pageable = PageRequest.of(page - 1, limit);
        var tradeMarkPage = tradeMarkService.getTradeMarksPage(pageable);
        System.out.println(tradeMarkPage);

        Map<String, Object> data = new HashMap<>();
        data.put("records", tradeMarkPage.getContent());
        data.put("total", tradeMarkPage.getTotalElements());
        data.put("size", tradeMarkPage.getSize());
        data.put("current", tradeMarkPage.getNumber() + 1);
        data.put("searchCount", true);
        data.put("pages", tradeMarkPage.getTotalPages());

        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "成功取得 tradeMark 資料");
        result.put("data", data);
        result.put("ok", true);

        return result;
    }

    //新增品牌資料
    @PostMapping("/save")
    public Map<String,Object> savaNewTradeMark(@RequestBody TradeMarkItem newItem) {
        tradeMarkService.save(newItem);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "新增品牌成功");
        result.put("ok", true);
        return result;
    }
    //修改品牌資料
    @PutMapping("/update")
    public Map<String,Object> UpdateTradeMark(@RequestBody TradeMarkItem updateItem) {
        Map<String, Object> result = new HashMap<>();
        if (tradeMarkService.update(updateItem).isPresent()) {
            result.put("code", 200);
            result.put("message", "修改品牌成功");
            result.put("ok", true);
        } else {
            result.put("code", 404);
            result.put("message", "找不到該品牌 ID");
            result.put("ok", false);
        }
        return result;
    }
    //刪除品牌資料
    @DeleteMapping("/remove/{id}")
    public Map<String,Object> removeTradeMark(@PathVariable String id) {
        Map<String, Object> result = new HashMap<>();
        if (tradeMarkService.delete(id)) {
            result.put("code", 200);
            result.put("message", "刪除品牌成功");
            result.put("ok", true);
        } else {
            result.put("code", 404);
            result.put("message", "找不到該品牌 ID");
            result.put("ok", false);
        }
        return result;
    }
    //獲取所有品牌
    @GetMapping("")
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("");
    }
    @GetMapping("/getTrademarkList")
    public ResponseEntity<Map<String,Object>> getAllTradeMark() {
        List<TradeMarkItem> allTradeMark = tradeMarkService.getAll();

        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "成功取得 tradeMark 資料");
        result.put("data", allTradeMark);
        result.put("ok", true);

        return ResponseEntity.ok(result);
    }

}
