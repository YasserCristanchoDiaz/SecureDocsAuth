package co.com.auth_back.auth_back.repositories;

import co.com.auth_back.auth_back.models.GeneralModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface GeneralRepository <T extends GeneralModel> extends CrudRepository<T,String> {
    public List<T> findAll(Pageable page);
}
