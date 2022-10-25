import services.deletes.delete.DeleteRepository;
import services.deletes.idelete.IDeleteRepository;
import services.fetchs.fetch.FoundRepository;
import services.fetchs.ifetch.IFoundRepository;
import services.inserts.iinsert.IMultiRowInsert;
import services.inserts.iinsert.ISingleRowInsert;
import services.inserts.insert.MultiInsert;
import services.inserts.insert.SingleInsert;
import services.updates.iupdate.IUpdateRepository;
import services.updates.update.UpdateRepository;

import java.util.List;

public class FUIDRepository<T> implements FUIDRepositoryInterface<T>{
    T object;
   public FUIDRepository(T object_){
        this.object = object_;
    }
    @Override
    public int insertSingleRow(T object) {
        ISingleRowInsert<T> _singleRowInsert = new SingleInsert<>();
        return _singleRowInsert.insertRow(object);
    }

    @Override
    public int insertMultiRow(List<T> objectList) {
        IMultiRowInsert<T> _multiInsert = new MultiInsert<>();
        return _multiInsert.insertMultiRow(objectList);
    }

    @Override
    public List<T> foundAll() {
        IFoundRepository<T> _TFoundRepository = new FoundRepository<>(this.object);
        return _TFoundRepository.foundAll();
    }

    @Override
    public List<T> foundAllById(long id) {
        IFoundRepository<T> _TFoundRepository = new FoundRepository<>(this.object);
        return _TFoundRepository.foundById(id);
    }

    @Override
    public List<T> foundAllBySqlCommandQuery(String sqlCommand) {
        IFoundRepository<T> _TFoundRepository = new FoundRepository<>(this.object);
        return _TFoundRepository.foundBySqlCommand(sqlCommand);
    }

    @Override
    public int deleteById(long id) {
        IDeleteRepository<T> _TDeleteRepository = new DeleteRepository<>(this.object);
        return _TDeleteRepository.removeById(id);
    }

    @Override
    public int deleteBySqlCommand(String sqlCommand) {
        IDeleteRepository<T> _TDeleteRepository = new DeleteRepository<>(this.object);
        return _TDeleteRepository.removeBySqlCommand(sqlCommand);    }

    @Override
    public int updateById(long id) {
        IUpdateRepository<T> _TUpdateRepository = new UpdateRepository<>(this.object);
        return _TUpdateRepository.updateById(id);
    }

    @Override
    public int updateBySqlCommand(String sqlCommand) {
        IUpdateRepository<T> _TUpdateRepository = new UpdateRepository<>(this.object);
        return _TUpdateRepository.updateBySqlCommand(sqlCommand);
    }
}
