import java.util.*;

public class InMemoryMenu implements MenuRepository {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();

    @Override
    public void add(MenuItem item) { menu.put(item.id, item); }

    @Override
    public MenuItem get(String id) { return menu.get(id); }
}