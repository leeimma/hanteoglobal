package hanteo.global.board;

import java.util.List;
import java.util.Optional;

public interface CatecoryService {

    public void save(int id, String name, List<Integer> parentIds);

    public Optional<Category> findById(int id);

    public List<Category> findByName(String name);

    public String toJson(Category category) throws Exception;
}
