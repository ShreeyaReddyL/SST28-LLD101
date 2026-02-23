public interface MenuRepository {
    void add(MenuItem item);
    MenuItem get(String id);
}