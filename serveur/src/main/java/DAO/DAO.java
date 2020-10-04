package DAO;

public interface DAO<T>{

	public boolean create(T obj);
	public T read(int id);
	public boolean update(T obj);
	public boolean delete(T obj);

	
}
