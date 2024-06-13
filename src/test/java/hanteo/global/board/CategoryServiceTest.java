package hanteo.global.board;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
@SpringBootTest
class CategoryServiceTest {

    @Autowired
    private CatecoryService categoryService;

    @TestConfiguration
    static class InitTxTestConfig {
        @Bean
        TestDataInit testDataInit(CatecoryService catecoryService) {
            return new TestDataInit(catecoryService);
        }
    }

    @Test
    void idToJson() throws Exception {
        Category categoryById = categoryService.findById(100).get();
        String json = categoryService.toJson(categoryById);
        log.info("카테고리 검색 및 json = {} ", json);

        assertThat(categoryById.getName()).isEqualTo("남자");
    }

    @Test
    void nameToJson() {
        List<Category> categories = categoryService.findByName("익명게시판");

        assertThat(categories.get(0).getId()).isEqualTo(6);

        ListLog(categories);
    }

    @Test
    void nameToJson2() {
        List<Category> categories = categoryService.findByName("공지사항");

        assertThat(categories.size()).isEqualTo(3);

        ListLog(categories);
    }


    //    @Test
    void FindAll() {
        List<Category> categories = categoryService.findAll();
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