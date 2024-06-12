package hanteo.global.board;

import com.fasterxml.jackson.databind.ObjectMapper;
import hanteo.global.board.Category;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class CategoryMemoryRepository {

    // 메모리 저장
    private static final Map<Integer, Category> categories = new HashMap<>();

    // 시퀀스...
    private static int sequence = 0;

    /**
     * 카테고리 저장
     * @param id
     * @param name
     * @param parentIds
     */
    public void addCategory(int id, String name, List<Integer> parentIds) {
        Category category = new Category(id, name);
        categories.put(id, category);

        if (parentIds != null) {
            for (int parentId : parentIds) {
                Category parent = categories.get(parentId);
                if (parent != null) {
                    parent.addChildId(id);
                    category.addParentId(parentId);
                }
            }
        }
    }

    /**
     * 카테고리 저장
     * @param category
     */
    public void addCategory(Category category) {
        categories.put(category.getId(), category);
    }

    /**
     * 카테고리 식별자 검색
     * @param id
     * @return
     */
    public Category findById(int id) {
        return categories.get(id);
    }

    /**
     * 카테고리명 검색
     * @param name
     * @return
     */
    public List<Category> findByName(String name) {
        return categories.values().stream()
                .filter(category -> category.getName().equals(name))
                .collect(Collectors.toList());
    }

    public List<Category> findAll() {
        return categories.values().stream()
                .collect(Collectors.toList());
    }



}
