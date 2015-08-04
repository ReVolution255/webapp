package main.java;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

/**
 * Created by 1 on 04.08.2015.
 */
public class BDModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(main.java.IBDReader.class)
                .to(main.java.UsersManager.class)
                .in(Scopes.SINGLETON);
    }
}
