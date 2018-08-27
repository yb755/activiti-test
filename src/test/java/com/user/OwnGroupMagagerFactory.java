package com.user;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.GroupIdentityManager;

public class OwnGroupMagagerFactory implements SessionFactory {

	private KeystoneConnection keystoneConnection;

    public OwnGroupMagagerFactory(KeystoneConnection keystoneConnection) {
        this.keystoneConnection = keystoneConnection;
    }

    public Class<?> getSessionType() {
        return GroupIdentityManager.class;
    }

    public Session openSession() {
        return new OwnGroupManager(this.getKeystoneConnection());
    }

    public KeystoneConnection getKeystoneConnection() {
        return keystoneConnection;
    }

    public void setKeystoneConnection(KeystoneConnection keystoneConnection) {
        this.keystoneConnection = keystoneConnection;
    }

}
