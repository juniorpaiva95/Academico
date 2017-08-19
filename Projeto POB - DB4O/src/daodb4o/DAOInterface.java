package daodb4o;

import java.util.List;

public interface DAOInterface<T> {
	public void persistir(T obj);
	public T atualizar(T obj);
	public void apagar(T obj) ;
	public void reler(T obj);
	public List<T> listar();

}
