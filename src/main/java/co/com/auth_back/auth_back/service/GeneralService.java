package co.com.auth_back.auth_back.service;

import co.com.auth_back.auth_back.constants.MessageConstants;
import co.com.auth_back.auth_back.interfaces.CRUDService;
import co.com.auth_back.auth_back.models.GeneralModel;
import co.com.auth_back.auth_back.repositories.GeneralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

/**
 * Servicio que cuenta con todos los métodos básicos del CRUD
 * @param <T> Una clase que extienda de el GeneralModel debe ser un entity para poder usar los métodos (Establecer el repositorio que se va a usar)
 */

@Service
public abstract class GeneralService<T extends GeneralModel> implements CRUDService<T> {

    /**
     * Repositorio general,tener los datos básicos del CRUD
     */
    protected final GeneralRepository<T> generalRepository;

    @Autowired
    public GeneralService(GeneralRepository<T> generalRepository) {
        this.generalRepository = generalRepository;
    }
    /**
     * Obtiene todos los datosde la entidad T
     * @return una lista lista con todos los datos
     * @throws Exception
     */
    @Override
    public List<T> getAll() throws Exception{
        return (List<T>) generalRepository.findAll();
    }

    /**
     * Obtiene todos los datos teniendo en cuenta el paginado
     * @param page paginador con la página y los registros
     * @returnUna lista con todos los registros (de tipo T) dentro del paginado
     * @throws Exception
     */
    @Override
    public List<T> getAllPageable(Pageable page) throws Exception{
        return (List<T>) generalRepository.findAll(page);
    }

    /**
     * Obtiene todos los registros según lo aplicado en el filtro
      * @param t el filtro
     * @param page la cantidad de registros
     * @return la lista de los registros
     */
    @Override
    public List<T> getAllByFilter(T t ,Pageable page){
        return generalRepository.findByFilter(t,page);
    }

    @Override
    public long countByFilter(T t){
        return generalRepository.countByFilter(t);
    }
    /**
     * S e obtiene un elemento a partir de u id de registro
     * @param id valor por el que se quiere buscar el registro
     * @return Un optional que contiene el registro T en casodeque exista
     * @throws Exception En caso de no encontrarlo lanza un error
     */
    @Override
    public T findById(String id) throws Exception{
        Optional<T> t = generalRepository.findById(id);
        if(t.isEmpty()){
            throw new Exception(MessageConstants.EMPTY_MESSAGE);
        }
    return t.get();
    }

    /**
     * Guarda un registro T
     * @param t el objetoque se desa almacenar en la BD
     * @return el objeto que se guardo
     * @throws Exception En caso de obtener un datoduplicado o que no se cumpla con las condiciones del registro
     */
    @Override
    public T save(T t) throws Exception {
        try{
            return generalRepository.save(t);
        }catch(Exception e){
            throw new Exception(MessageConstants.DUPLICATED_MESSAGE);
        }
    }

    /**
     * Elimina un registrode la BD a partir de su ID
     * @param id Valor identificador del registro que sedesea eliminar
     * @throws Exception error en caso de que no se pueda eliminar
     */
    @Override
    public void delete(String id) throws Exception{
        try {
            generalRepository.deleteById(id);
        }catch(Exception e){
            throw new Exception(MessageConstants.FAILED_MESSAGE);
        }
    }

    /**
     * Cuenta la cantidad de registrosen una entidad o tabla
     * @return el total de los registros
     */
    @Override
    public long count(){
        return generalRepository.count();
    }
}
