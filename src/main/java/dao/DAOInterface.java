package dao;

import java.util.ArrayList;

public interface DAOInterface <T>{
    public void insert(T t) throws Exception;
    public void update(T t) throws Exception;
    public void delete(T t) throws Exception;
    public ArrayList<T> selectAll();
    public T selectById(T t);
    public ArrayList<T> selectByCondition(String condition);

}
