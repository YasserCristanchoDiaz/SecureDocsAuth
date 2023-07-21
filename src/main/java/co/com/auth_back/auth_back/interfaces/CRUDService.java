package co.com.auth_back.auth_back.interfaces;


import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CRUDService<T>{

    /**
     * Obtiene todos los registros almacenados en la tabla
     * @return lista con los registroscorrespondientes
     * @throws Exception manejo de errores
     */

    public List<T> getAll() throws Exception;


    /**
     * Obtiene los registros a partir de un paginador
     * @param page paginador con la página y los registros
     * @return lista de los registros dentro del paginador
     * @throws Exception manejo de errores al paginar
     */
    public List<T> getAllPageable(Pageable page) throws Exception;

    long countByFilter(T t);

    /**
     * Obtiene un registro apartir de su Id
     * @param Id valor por el que se quiere buscar el registro
     * @return estado de la busqueda en la BD (el registro)
     * @throws Exception manejo de errores al obtener un registro nulo
     */

    public T findById(String Id) throws Exception;


    /**
     * Gaurda el registro
     * @param t el objetoque se desa almacenar en la BD
     * @return el objeto guardado
     * @throws Exception fallo al gaurdar un registro
     */
    public T save(T t) throws Exception;

    /**
     *
     * @param t
     * @param page
     * @return
     */
    public List<T> getAllByFilter(T t, Pageable page);

    /**
     *
     * @param t
     * @return
     */
    public long countByFileter(T t);

    /**
     *Elimina un registro específico
     * @param Id Valor identificador del registro que sedesea eliminar
     * @throws Exception error en cao de que falle laeliminación
     */
    public void delete(String Id) throws Exception;

    /**
     * Obtiene el numero total de registros en una tabla
     * @return cantidad de elementos
     */
    public long count();
}
