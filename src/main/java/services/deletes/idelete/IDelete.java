package services.deletes.idelete;

/** the core of removing values form database table */
public interface IDelete {
    int beginRemove(String sql);
}
