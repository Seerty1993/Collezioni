package org.progetto.config;

import io.ebean.config.CurrentUserProvider;
import jakarta.enterprise.context.ApplicationScoped;


//Solo per DEV, per segnare l'utente che fa le CRUD nel db .
//Modificare in fase di implementazione Auth.
@ApplicationScoped
public class CurrentUserProviderImpl implements CurrentUserProvider {

    @Override
    public Object currentUser() {
        return getCurrentUser();
    }

    public String getCurrentUser() {
        return "system01";
    }
}
