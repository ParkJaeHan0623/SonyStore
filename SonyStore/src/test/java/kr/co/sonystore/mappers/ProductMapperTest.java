package kr.co.sonystore.mappers;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.sonystore.models.Product;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class ProductMapperTest {
    
    @Autowired
    private ProductMapper productMapper;

    @Test
    @DisplayName("상품 추가 테스트")
    void insertProduct() {
        Product input = new Product();
        input.setTitle("ILCE-7CM2L");
        input.setProddesc("원핸드 컴팩트 풀프레임");
        input.setPrice(3090000);
        input.setType1("camera");
        input.setType2("lens_change");
        input.setType3("풀프레임");
        input.setDate("20230301");
        input.setDetailimage1("../../detailimage1.png");
        input.setDetailimage2("../../detailimage2.png");
        input.setYoutube("sflsfjowjfyoutube.com");
        input.setDetailgif("../../gif.gif");
        input.setDetailspec("../../spec.png");
        input.setSoldout("N");
        input.setSale("N");
        input.setEvent("N");
        
        int output = productMapper.insert(input);
        
        log.debug("output: " + output);
        log.debug("new id: " + input.getProdid());
    }

    @Test
    @DisplayName("비디오 상품 추가 테스트")
    void insertProductv() {
        Product input = new Product();
        input.setTitle("BURANO");
        input.setProddesc("풀프레임 8.6K 시네마 카메라");
        input.setPrice(36990000);
        input.setType1("video");
        input.setType2("camcorder");
        input.setType3("4k_handy");
        input.setDate("20240201");
        input.setDetailimage1("../../detailimage1.png");
        input.setDetailimage2("../../detailimage2.png");
        input.setYoutube("sflsfjowjfyoutube.com");
        input.setDetailgif("../../gif.gif");
        input.setDetailspec("../../spec.png");
        input.setSoldout("N");
        input.setSale("N");
        input.setEvent("N");
        
        int output = productMapper.insert(input);
        
        log.debug("output: " + output);
        log.debug("new id: " + input.getProdid());
    }

    @Test
    @DisplayName("상품 업데이트 테스트")
    void updateProduct() {
        Product input = new Product();
        input.setProdid(8); // 업데이트할 상품의 ID를 설정
        input.setTitle("ILCE-7CM2L");
        input.setProddesc("원핸드 컴팩트 풀프레임");
        input.setPrice(3190000);
        input.setType1("camera");
        input.setType2("lens_change");
        input.setType3("APS-C");
        input.setDate("20230302");
        input.setDetailimage1("../../detailimage1_updated.png");
        input.setDetailimage2("../../detailimage2_updated.png");
        input.setYoutube("updated_youtube.com");
        input.setDetailgif("../../gif_updated.gif");
        input.setDetailspec("../../spec_updated.png");
        input.setSoldout("N");
        input.setSale("Y");
        input.setEvent("Y");
        
        int output = productMapper.update(input);
        
        log.debug("output: " + output);

    }

    @Test
    @DisplayName("상품 삭제 테스트")
    void deleteProduct() {
        Product input = new Product();
        input.setProdid(1); // 삭제할 상품의 ID를 설정
        
        productMapper.deleteImagesByProductId(input);
        productMapper.deleteColorsByProductId(input);
        

        int output = productMapper.delete(input);

        log.debug("output = " + output);

    }

    @Test
    @DisplayName("상품 단일 조회 테스트")
    void selectProduct() {
        Product input = new Product();
        input.setProdid(5); // 조회할 상품의 ID를 설정

        Product output = productMapper.selectItem(input);

        log.debug("output = " + output.toString());
    }

    @Test
    @DisplayName("상품 목록 조회 테스트")
    void selectListProduct() {
        List<Product> output = productMapper.selectList(null);

        for(Product item : output) {
            log.debug("item = " + item.toString());
        }
    }

    @Test
    @DisplayName("상품 타입별 목록 조회 테스트")
    void selectListByType1Product() {
        Product input = new Product();
        input.setType1("카메라"); // 조회할 상품의 타입1을 설정

        List<Product> output = productMapper.selectListByType1(input);

        for(Product item : output) {
            log.debug("item = " + item.toString());
        }
    }

@Test
    @DisplayName("상품id별 상세 조회 테스트")
    void selectItemByProdid() {
        int prodid = 5; // 조회할 상품의 ID를 설정

        Product output = productMapper.selectItemByProdid(prodid);

        log.debug("output = " + output.toString());
    }
}