package co.com.auth_back.auth_back.controller;

import co.com.auth_back.auth_back.interfaces.CRUDController;
import co.com.auth_back.auth_back.models.GeneralModel;

public abstract class GeneralController <T extends GeneralModel> implements CRUDController<T> {

}
