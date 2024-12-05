package kr.co.sonystore.controllers.restfulapis;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.sonystore.helpers.FileHelper;
import kr.co.sonystore.helpers.RestHelper;
import kr.co.sonystore.models.Image;
import kr.co.sonystore.models.Product;
import kr.co.sonystore.services.SearchService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
// @RequestMapping("/api/products")
public class SearchRestController {
    @Autowired
    private SearchService searchService;

    @Autowired
    private FileHelper fileHelper;

    @Autowired
    private RestHelper restHelper;


    //제품 목록 조회
    @GetMapping("/api/search/search_result")
    public Map<String, Object> getProductList(
        @RequestParam(value = "keyword", required = false) String keyword
    ) throws Exception {
        Product input = new Product();
        input.setKeyword(keyword);
        
        List<Product> output;
        try {
            output = searchService.selectList(input);
            if (output != null) {
                output.forEach(product -> {
                    if (product.getImages() != null) {
                        product.getImages().forEach(image -> {
                            image.setFilepath(fileHelper.getUrl(image.getFilepath()));
                        });
                    }
                });
            }
        } catch (Exception e) {
            return restHelper.serverError(e);
        }
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("list", output);

        return restHelper.sendJson(data);
    }
}