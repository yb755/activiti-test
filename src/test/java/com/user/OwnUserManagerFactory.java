package com.user;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.UserIdentityManager;

public class OwnUserManagerFactory implements SessionFactory {
	
	private KeystoneConnection keystoneConnection;

    public OwnUserManagerFactory (KeystoneConnection keystoneConnection) {
        this.keystoneConnection = keystoneConnection;
    }

    public Class<?> getSessionType() {
        return UserIdentityManager.class;
    }

    public Session openSession() {
        return new OwnUserManager(this.getKeystoneConnection());
    }

    public KeystoneConnection getKeystoneConnection() {
        return keystoneConnection;
    }

    public void setKeystoneConnection(KeystoneConnection keystoneConnection) {
        this.keystoneConnection = keystoneConnection;
    }

}
