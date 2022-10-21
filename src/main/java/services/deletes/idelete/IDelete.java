package services.deletes.idelete;

public interface IDelete<T> {
    int beginRemove(String sql);
}
