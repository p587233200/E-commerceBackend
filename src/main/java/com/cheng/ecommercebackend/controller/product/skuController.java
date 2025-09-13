package com.cheng.ecommercebackend.controller.product;

import com.cheng.ecommercebackend.entity.product.SKU.SkuItem;
import com.cheng.ecommercebackend.service.product.SkuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/product")
@CrossOrigin(origins = "http://localhost:5173")  // 允許前端的來源
@RequiredArgsConstructor
public class skuController {
    private final SkuItemService skuItemService;

    @PostMapping("/saveSkuInfo")
    public ResponseEntity<Map<String,Object>> createSku(@RequestBody SkuItem skuItem) {
        Map<String, Object> body = new HashMap<>();
        body.put("code", 200);
        body.put("message", "成功取得資料");
        body.put("data", skuItemService.saveNewSkuItem(skuItem));
        body.put("ok", true);
        return ResponseEntity.ok(body);

    }

    @GetMapping("/findBySpuId/{spuId}")
    public ResponseEntity<Map<String,Object>> getSkuItemListBySpuId(@PathVariable String spuId) {
        Map<String, Object> body = new HashMap<>();
        body.put("code", 200);
        body.put("message", "成功取得資料");
        body.put("data", skuItemService.getSkuItemListBySpuId(spuId));
        body.put("ok", true);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/list/{page}/{limit}")
    public ResponseEntity<Map<String,Object>> getSkuListByPageAndLimit(@PathVariable int page, @PathVariable int limit) {
        Page<SkuItem> spuPage = skuItemService.getSkuListByPageAndLimit(page,limit);
        Map<String,Object> records = new HashMap<>();
        records.put("records", spuPage.getContent());
        records.put("total", spuPage.getTotalElements());
        records.put("pages", spuPage.getTotalPages());
        records.put("current", page);
        records.put("size", limit);


        Map<String, Object> body = new HashMap<>();
        body.put("code", 200);
        body.put("message", "成功取得資料");
        body.put("data", records);
        body.put("ok", true);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/getSkuInfo/{skuId}")
    public ResponseEntity<Map<String,Object>> getSkuInfo(@PathVariable String skuId) {

        Map<String, Object> body = new HashMap<>();
        body.put("code", 200);
        body.put("message", "成功取得資料");
        body.put("data", skuItemService.getSkuInfo(skuId));
        body.put("ok", true);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/deleteSku/{skuId}")
    public ResponseEntity<Map<String,Object>> delete(@PathVariable String skuId) {
        Boolean success = skuItemService.deleteSkuItemById(skuId);
        Map<String, Object> body = new HashMap<>();
        body.put("code", success?200:400);
        body.put("message", success?"成功刪除資料":"刪除資料失敗");
        body.put("data", success? "成功刪除資料" : "刪除失敗，找不到ID");
        body.put("ok", success);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/onSale/{skuId}")
    public ResponseEntity<Map<String,Object>> changeOnSale(@PathVariable String skuId) {
        skuItemService.changeOnSale(skuId);
        Map<String, Object> body = new HashMap<>();
        body.put("code", 200);
        body.put("message", "成功取得資料");
        body.put("data", "");
        body.put("ok", true);
        return ResponseEntity.ok(body);
    }
    @GetMapping("/cancelSale/{skuId}")
    public ResponseEntity<Map<String,Object>> changeCancelSale(@PathVariable String skuId) {
        skuItemService.changeCancelSaleSale(skuId);
        Map<String, Object> body = new HashMap<>();
        body.put("code", 200);
        body.put("message", "成功取得資料");
        body.put("data", "");
        body.put("ok", true);
        return ResponseEntity.ok(body);
    }

}
