package hanteo.global.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class TestDataInit {

    private final CatecoryService categoryService;

    /**
     * 확인용 초기 데이터 추가
     */
    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        log.info("test data init");
        // 최상위 카테고리 추가
        categoryService.save(100, "남자", null);
        categoryService.save(200, "여자", null);

        // 두 번째 레벨 카테고리 추가
        categoryService.save(30, "엑소", List.of(100));
        categoryService.save(40, "방탄소년단", List.of(100));
        categoryService.save(50, "블랙핑크", List.of(200));

        // 세 번째 레벨 카테고리 추가
        categoryService.save(1, "공지사항", List.of(30));
        categoryService.save(2, "첸", List.of(30));
        categoryService.save(3, "백현", List.of(30));
        categoryService.save(4, "시우민", List.of(30));
        categoryService.save(5, "공지사항", List.of(40));
        categoryService.save(6, "익명게시판", List.of(40, 50));
        categoryService.save(7, "뷔", List.of(40));
        categoryService.save(8, "공지사항", List.of(50));
        categoryService.save(6, "익명게시판", List.of(50));
        categoryService.save(9, "로제", List.of(50));
    }

}
