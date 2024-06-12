package hanteo.global.board;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CategoryMemoryService implements CatecoryService {

    private final CategoryMemoryRepository repository;

    @Override
    public void save(int id, String name, List<Integer> parentIds) {
        repository.addCategory(id, name, parentIds);
    }

    @Override
    public Optional<Category> findById(int id) {
        return Optional.ofNullable(repository.findById(id));
    }

    @Override
    public List<Category> findByName(String name) {
        return repository.findByName(name);
    }

    public List<Category> findAll() {
        return repository.findAll();
    }

    /**
     * json 으로 변환
     * @param category
     * @return
     * @throws Exception
     */
    @Override
    public String toJson(Category category) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(category.toJsonNode(mapper, this));
    }


}
