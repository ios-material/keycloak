package org.keycloak.models.jpa;

import java.util.Properties;

import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.models.ModelProvider;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class JpaModelProvider implements ModelProvider {

    @Override
    public String getId() {
        return "jpa";
    }

    @Override
    public KeycloakSessionFactory createFactory() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-keycloak-identity-store", getHibernateProperties());
        return new JpaKeycloakSessionFactory(emf);

    }

    // Allows to override some properties in persistence.xml by system properties
    protected Properties getHibernateProperties() {
        Properties result = new Properties();

        for (Object property : System.getProperties().keySet()) {
            if (property.toString().startsWith("hibernate.")) {
                String propValue = System.getProperty(property.toString());
                result.put(property, propValue);
            }
        }
        return result;
    }
}
