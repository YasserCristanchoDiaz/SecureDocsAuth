package co.com.auth_back.auth_back.controller;

import co.com.auth_back.auth_back.models.Credential;

import java.util.List;
import java.util.Map;

public class CredentialController extends GeneralController<Credential> {


    @Override
    public List<Credential> getAll() throws Exception {
        return null;
    }

    @Override
    public long getAllCount() throws Exception {
        return 0;
    }

    @Override
    public List<Credential> getAll(int pageNumber, int pageSize) throws Exception {
        return null;
    }

    @Override
    public List<Credential> getAllByFilters(Credential credential, int pageNumber, int pageSize) throws Exception {
        return null;
    }

    @Override
    public long countAllByFilters(Credential credential) throws Exception {
        return 0;
    }

    @Override
    public Credential getById(String id) throws Exception {
        return null;
    }

    @Override
    public Credential create(Credential credential) throws Exception {
        return null;
    }

    @Override
    public Credential update(Credential credential) throws Exception {
        return null;
    }

    @Override
    public Map<String, String> delete(String id) throws Exception {
        return null;
    }
}
