package co.com.auth_back.auth_back.interfaces;

import co.com.auth_back.auth_back.constants.RoutesConstants;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Interface que genera los metodosbasicos del crud y sus respectivas rutas
 * @param <T> Clase o entidad para generar el crud
 */

@RestController
public interface CRUDController<T>{
    /**
     * Ruta para obtener todos los registros de una tabla
     * @return lista con todos los registros
     * @throws Exception
     */

    @GetMapping(RoutesConstants.GET_ALL_ROUTE)
    public List<T> getAll() throws Exception;

    /**
     * Ruta para obtener la cantidad de registros total de una tabla
     * @return el total de los registros
     * @throws Exception
     */
    @GetMapping(RoutesConstants.GET_ALL_COUNT_ROUTE)
    public long getAllCount() throws Exception;

    /**
     * Ruta para obtener los registroscon paginacion
     * @param pageNumber numero de la pagina
     * @param pageSize cantidad de datos
     * @return Lista con los datos de la pagina ingresada y el numero de datos escogido
     * @throws Exception
     */
    @GetMapping(RoutesConstants.GET_ALL_BY_PAGE_ROUTE)
    public List<T> getAll(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) throws Exception;

    /**
     * Ruta que permite obtener todos los registros paginados a travésde un filtro
     * @param t filtro aplicado
     * @param pageNumber numero de la pagina
     * @param pageSize tamaño de la pagina
     * @return lista con los datos
     * @throws Exception
     */
    @PostMapping(RoutesConstants.GET_ALL_BY_FILTERS)
    public List<T> getAllByFilters(@RequestBody T t, @RequestParam("PageNumber") int pageNumber, @RequestParam("pageSize")int pageSize)throws Exception;

    /**
     * Ruta que permite obtener la cantidad de registros paginados a través de un filtro
     * @param t filtro
     * @return cantidad de registros
     * @throws Exception
     */
    @PostMapping(RoutesConstants.GET_ALL_BY_FILTERS)
    public long countAllByFilters(@RequestBody T t) throws Exception;

    /**
     * Obtiene un elemento por id
     * @param id identificador del elemento a buscar
     * @return el elemento encontrado
     * @throws Exception
     */
    @GetMapping(RoutesConstants.GET_BY_ID_ROUTE)
    public T getById(@RequestParam("id") String id) throws Exception;

    /**
     * Ruta para ingresar un nuevo registro
     * @param t objeto que se va a registrar perteneciente a la clase
     * @return el objeto ya creado
     * @throws Exception
     */
    @PostMapping(RoutesConstants.CREATE_ROUTE)
    public T create(@RequestBody T t) throws Exception;

    /**
     * Ruta para actualizar un registro
     * @param t objeto de la clase que se recibe en el cuerpo de la petición
     * @return el objeto actualizado
     * @throws Exception
     */
    @PutMapping(RoutesConstants.UPDATE_ROUTE)
    public T update(@RequestBody T t) throws Exception;

    /**
     * Ruta para la eliminación de un registro
     * @param id el identificador del registro
     * @return un map(json) con el exito de la eliminación
     * @throws Exception
     */
    @DeleteMapping(RoutesConstants.DELETE_ROUTE)
    public Map<String,String> delete(@RequestParam("id") String id) throws Exception;

}
