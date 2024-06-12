package hanteo.global.board;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.List;


@Slf4j
@SpringBootTest
class CategoryServiceTest {

    @Autowired
    CategoryMemoryService categoryService;

    @TestConfiguration
    static class InitTxTestConfig {
        @Bean
        TestDataInit testDataInit(CatecoryService catecoryService) {
            return new TestDataInit(catecoryService);
        }
    }


    @Test
    void FindAll() {
        List<Category> categories = categoryService.findAll();
        ListLog(categories);
    }

    @Test
    void idToJson() throws Exception {
        Category categoryById = categoryService.findById(100).get();
        String json = categoryService.toJson(categoryById);
        log.info("카테고리 검색 및 json = {} ", json);

        Assertions.assertThat(categoryById.getName())
                .isEqualTo("남자");
    }

    @Test
    void nameToJson() {
        List<Category> categories = categoryService.findByName("익명게시판");
        ListLog(categories);
    }

    void ListLog(List<Category> categories){
        for (Category category : categories) {
            try {
                String json = categoryService.toJson(category);
                log.info("카테고리 검색 및 json = {} ", json);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}