package co.com.auth_back.auth_back.controller;

import co.com.auth_back.auth_back.constants.MessageConstants;
import co.com.auth_back.auth_back.interfaces.CRUDController;
import co.com.auth_back.auth_back.models.GeneralModel;
import co.com.auth_back.auth_back.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controlador general provee al controlador la funcionalidad basica del crud y las rutas establecidas en el crudController
 * @param <T> la entidad u objeto
 */
public abstract class GeneralController <T extends GeneralModel> implements CRUDController<T> {

    protected  final GeneralService<T> generalService;

    @Autowired
    public GeneralController(GeneralService<T> generalService) {
        this.generalService=generalService;
    }

    /**
     * Obtiene todos los registros para una Entidad T
     * @return Lista con todos los registros
     * @throws Exception error en caso de fallo
     */
    @Override
    public List<T> getAll() throws Exception{
        return generalService.getAll();
    }

    /**
     * Obteine el numero total de elementos en la entidad
     * @return el numero total de elementos
     * @throws Exception
     */
    @Override
    public long getAllCount() throws Exception {
        return generalService.count();
    }

    /**
     * Obtiene un elemento de la entidad según el ID
     * @param id identificador del elemento a buscar
     * @return el elemento encontrado según su ID
     * @throws Exception
     */
    @Override
    public T getById(String  id) throws Exception{
        return generalService.findById(id);
    }

    /**
     * Obtiene una lista  de elementos de la entidad paginados según el número de página y el tamaño de página especificados
     * @param pageNumber numero de la pagina
     * @param pageSize cantidad de datos
     * @return una lista de elementos paginados
     * @throws Exception
     */
    @Override
    public List<T> getAll( int pageNumber, int pageSize) throws Exception{
        Pageable page = PageRequest.of(pageNumber,pageSize);
        return generalService.getAllPageable(page);
    }

    /**
     * Permite obtener todos los registros según el filtro aplicado
     * @param t filtro aplicado
     * @param pageNumber numero de la pagina
     * @param pageSize tamaño de la pagina
     * @return los registros según el filtro
     * @throws Exception
     */
    @Override
    public List<T> getAllByFilters(T t, int pageNumber, int pageSize) throws Exception{
        Pageable page = PageRequest.of(pageNumber,pageSize);
        return generalService.getAllByFilter(t,page);
    }

    /**
     * Cuanta los registros segun el filtro
     * @param t filtro
     * @return el numero de registros con ese filtro
     * @throws Exception
     */
    @Override
    public long countAllByFilters(T t) throws  Exception{
        return generalService.countByFilter(t);
    }

    /**
     * Crear un nuevo elementos en la entidad, añadir un registro
     * @param t objeto que se va a registrar perteneciente a la clase
     * @return el elementos creado
     * @throws Exception
     */
    public T create(T t) throws Exception{
        return generalService.save(t);
    }

    /**
     * Actualiza un elemento existente en los regsitros
     * @param t objeto de la clase que se recibe en el cuerpo de la petición
     * @return el elementos actualizado
     * @throws Exception
     */
    public T update(T t) throws Exception{
        T tFound = generalService.findById(t.getId());
        return generalService.save(t);
    }


    @Override
    public Map<String,String> delete(String id) throws Exception{
        Map<String,String> response = new HashMap<>();
        response.put("message", MessageConstants.FAILED_MESSAGE);
        try{
            generalService.delete(id);
            response.put("message",MessageConstants.SUCCESS_MESSAGE);
        }catch(Exception e){
            throw new Exception(MessageConstants.FAILED_MESSAGE);
        }
    return response;
    }





}
