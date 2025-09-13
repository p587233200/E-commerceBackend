package com.cheng.ecommercebackend.seeder;


import com.cheng.ecommercebackend.entity.product.SPU.SPUItem;
import com.cheng.ecommercebackend.entity.product.SPU.SaleAttrValue;
import com.cheng.ecommercebackend.entity.product.SPU.SpuImage;
import com.cheng.ecommercebackend.entity.product.SPU.SpuSaleAttr;
import com.cheng.ecommercebackend.repository.spu.SpuItemRepository;
import com.cheng.ecommercebackend.repository.spu.SpuSaleAttrRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SPUSeeder implements CommandLineRunner {
    private final SpuItemRepository spuItemRepository;
    private final SpuSaleAttrRepository spuSaleAttrRepository;
    @Override
    public void run(String... args) throws Exception {
//        spuItemRepository.deleteAll();
        if(spuItemRepository.count() == 0){
            createSPU();
        }
    }
    private void createSPU() {
        SPUItem spu1 = new SPUItem("Apple", "以iPhone系列聞名，是全球領先的智慧手機品牌之一。 ", "111", "Apple");
        SpuSaleAttr spuSaleAttr1 = new SpuSaleAttr(spu1,"999","顏色");
        SaleAttrValue saleAttrValue1 = new SaleAttrValue("999","粉色","顏色","",spuSaleAttr1);
        SaleAttrValue saleAttrValue2 = new SaleAttrValue("999","紅色","顏色","",spuSaleAttr1);
        SaleAttrValue saleAttrValue3 = new SaleAttrValue("999","白色","顏色","",spuSaleAttr1);

        SaleAttrValue saleAttrValue4 = new SaleAttrValue("999","黑色","顏色","",spuSaleAttr1);
        SaleAttrValue saleAttrValue5 = new SaleAttrValue("999","藍色","顏色","",spuSaleAttr1);
        SaleAttrValue saleAttrValue6 = new SaleAttrValue("999","綠色","顏色","",spuSaleAttr1);



        spuSaleAttr1.setSpuSaleAttrValueList(List.of(saleAttrValue1,saleAttrValue2,saleAttrValue3,saleAttrValue4,saleAttrValue5,saleAttrValue6));

        SpuSaleAttr spuSaleAttr2 = new SpuSaleAttr(spu1,"888","容量");
        SaleAttrValue saleAttrValue7 = new SaleAttrValue("888","128G","容量","",spuSaleAttr2);
        SaleAttrValue saleAttrValue8 = new SaleAttrValue("888","256G","容量","",spuSaleAttr2);
        SaleAttrValue saleAttrValue9 = new SaleAttrValue("888","512G","容量","",spuSaleAttr2);
        SaleAttrValue saleAttrValue10 = new SaleAttrValue("888","1TB","容量","",spuSaleAttr2);

        spuSaleAttr2.setSpuSaleAttrValueList(List.of(saleAttrValue7,saleAttrValue8,saleAttrValue9,saleAttrValue10));

        SpuImage spuImage1 = new SpuImage("Iphone 13","https://cdsassets.apple.com/live/SZLF0YNV/images/sp/111872_iphone13-colors-480.png",spu1);
        SpuImage spuImage2 = new SpuImage("Iphone 13","https://m.media-amazon.com/images/I/7199VOAdkRL.jpg",spu1);
        SpuImage spuImage3 = new SpuImage("Iphone 13","https://img.91app.com/webapi/imagesV3/Original/SalePage/7668729/0/638735783690870000?v=1",spu1);
        spu1.setSpuImageList(List.of(spuImage1,spuImage2,spuImage3));
        spu1.setSpuSaleAttrList(List.of(spuSaleAttr1,spuSaleAttr2));
        spuItemRepository.save(spu1);

        SPUItem spu2 = new SPUItem( "ASUS", "以ROG系列電競手機和Zenfone系列聞名", "111", "1",  List.of(),List.of());
//        SpuSaleAttr spuSaleAttr3 = new SpuSaleAttr("3",spu2,"777","尺寸");
//        spuSaleAttrRepository.save(spuSaleAttr3);
        SPUItem spu3 = new SPUItem( "Samsung", "全球領先的手機品牌，尤其在Android手機市場占有重要地位。 ", "111", "1",  List.of(),List.of());
        SPUItem spu4 = new SPUItem( "Sony", "推出過Xperia系列手機", "111", "1",  List.of(),List.of());
        SPUItem spu5 = new SPUItem( "Google", "Google Pixel系列是其代表性手機", "111", "1",  List.of(),List.of());
        SPUItem spu6 = new SPUItem( "小米 ", "提供多樣化的Android手機，包括紅米Redmi和POCO系列。 ", "112", "1",  List.of(),List.of());
        SPUItem spu7 = new SPUItem( "Vivo ", "提供多種智慧型手機，是Andriod陣營的常見品牌。", "112", "1",  List.of(),List.of());
        spuItemRepository.save(spu2);
        spuItemRepository.save(spu3);
        spuItemRepository.save(spu4);
        spuItemRepository.save(spu5);

        spuItemRepository.save(spu6);
        spuItemRepository.save(spu7);

    }
}
