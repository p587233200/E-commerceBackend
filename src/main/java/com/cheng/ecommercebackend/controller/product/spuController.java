package com.cheng.ecommercebackend.controller.product;


import com.cheng.ecommercebackend.entity.product.SPU.SPUItem;
import com.cheng.ecommercebackend.entity.product.SPU.SpuImage;
import com.cheng.ecommercebackend.entity.product.SPU.SpuSaleAttr;
import com.cheng.ecommercebackend.service.product.SPUService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/product")
@CrossOrigin(origins = "http://localhost:5173")  // 允許前端的來源
@RequiredArgsConstructor
public class spuController {
    private final SPUService spuService;
    //取得SPU當頁資料
    @GetMapping("/{page}/{limit}")
    public ResponseEntity<Map<String,Object>> getSpuItem(@PathVariable int page, @PathVariable int limit, @RequestParam String category3Id) {
        Page<SPUItem> spuPage= spuService.getSpuItems(page, limit, category3Id);
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

    //取得spu圖片List
    @GetMapping("/spuImageList/{spuId}")
    public ResponseEntity<Map<String,Object>> getSpuImageList(@PathVariable String spuId) {
        List<SpuImage> data = spuService.findImagesBySPUId(spuId);
        Map<String, Object> body = new HashMap<>();
        body.put("code", 200);
        body.put("message", "成功取得資料");
        body.put("data", data);
        body.put("ok", true);
        return ResponseEntity.ok(body);
    }
    //取得spu屬性List
    @GetMapping("/spuSaleAttrList/{spuId}")
    public ResponseEntity<Map<String,Object>> getSpuSaleAttrList(@PathVariable String spuId) {
        List<SpuSaleAttr> data= spuService.findAttrSaleListBySpuId(spuId);
        Map<String, Object> body = new HashMap<>();
        body.put("code", 200);
        body.put("message", "成功取得資料");
        body.put("data", data);
        body.put("ok", true);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/baseSaleAttrList")
    public ResponseEntity<Map<String,Object>> getBaseSaleAttrList() {
        List<SpuSaleAttr> allSpuSaleAttrList = spuService.getAllSpuSaleAttrList();
        List<Map> data = new ArrayList<>();
        for (SpuSaleAttr spuSaleAttr : allSpuSaleAttrList) {
            data.add(Map.of("id",spuSaleAttr.getId(),"name",spuSaleAttr.getSaleAttrName()));
        }
        Map<String, Object> body = new HashMap<>();
        body.put("code", 200);
        body.put("message", "成功取得資料");
        body.put("data", data);
        body.put("ok", true);
        return ResponseEntity.ok(body);
    }
    //新增或修改SpuItem
    @PostMapping("/saveSpuInfo")
    public ResponseEntity<Map<String,Object>> addSpuItem(@RequestBody SPUItem spuItem) {
        SPUItem data = spuService.saveNewSpuItem(spuItem);
        Map<String, Object> body = new HashMap<>();
        body.put("code", 200);
        body.put("message", "成功");
        body.put("data", data);
        body.put("ok", true);
        return ResponseEntity.ok(body);
    }

    @PostMapping("/updateSpuInfo")
    public ResponseEntity<Map<String,Object>> UpdateSpuItem(@RequestBody SPUItem spuItem) {
        SPUItem data = spuService.updateSpuItem(spuItem);
        Map<String, Object> body = new HashMap<>();
        body.put("code", 200);
        body.put("message", "成功");
        body.put("data", data);
        body.put("ok", true);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/deleteSpu/{spuId}")
    public ResponseEntity<Map<String,Object>> delete(@PathVariable String spuId) {
        Boolean success = spuService.deleteSpuItem(spuId);
        Map<String, Object> body = new HashMap<>();
        body.put("code", success? 200: 400);
        body.put("message", "成功");
        body.put("data", success? "成功刪除資料" : "刪除失敗，找不到ID");
        body.put("ok", success);
        return ResponseEntity.ok(body);
    }
}
