package hanteo.global.board;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class Category {

    private int id;
    private String name;
    private List<Integer> parent;
    private List<Integer> children;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
        this.parent = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    public void addChildId(int childId) {
        this.children.add(childId);
    }

    public void addParentId(int parentId) {
        this.parent.add(parentId);
    }

    public ObjectNode toJsonNode(ObjectMapper mapper, CatecoryService categoryService) {
        ObjectNode node = mapper.createObjectNode();
        node.put("id", id);
        node.put("name", name);
        List<ObjectNode> childNodes = new ArrayList<>();
        for (int childId : children) {
            Optional<Category> child = categoryService.findById(childId);
            if (child.isPresent()) {
                childNodes.add(child.get().toJsonNode(mapper, categoryService));
            }
        }
        node.set("children", mapper.valueToTree(childNodes));
        return node;
    }
}
