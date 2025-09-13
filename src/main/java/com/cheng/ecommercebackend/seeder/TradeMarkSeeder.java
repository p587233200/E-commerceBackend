package com.cheng.ecommercebackend.seeder;

import com.cheng.ecommercebackend.entity.TradeMarkItem;
import com.cheng.ecommercebackend.repository.tradeMark.TradeMarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TradeMarkSeeder implements CommandLineRunner {
    private final TradeMarkRepository tradeMarkRepository;

    @Override
    public void run(String... args) throws Exception {
        List<String> tradeMarkName = List.of(
                "Apple",
                "Samsung",
                "Sony",
                "LG",
                "Google",
                "Huawei",
                "Xiaomi",
                "Oppo",
                "Vivo",
                "OnePlus",
                "Microsoft",
                "Dell",
                "HP",
                "Lenovo",
                "Asus",
                "Acer",
                "Nokia",
                "Panasonic",
                "Philips",
                "Canon"
        );
        tradeMarkRepository.deleteAll();
        if (tradeMarkRepository.count() == 0) {
            for (int i = 1; i <= 20; i++) {
                TradeMarkItem item = new TradeMarkItem();
                item.setId(UUID.randomUUID().toString());
                item.setTmName(tradeMarkName.get(i - 1));
                item.setLogoUrl("https://dummyimage.com/100x100/000/fff&text=" + tradeMarkName.get(i - 1));
                tradeMarkRepository.save(item);
            }
            System.out.println("Seeder 已完成：已插入 50 筆品牌資料");
        } else {
            System.out.println("Seeder 已跳過：資料庫已有資料");
        }
    }
}
