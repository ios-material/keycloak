package org.keycloak.models.mongo.keycloak.entities;

import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import org.keycloak.models.entities.ApplicationEntity;
import org.keycloak.models.mongo.api.MongoCollection;
import org.keycloak.models.mongo.api.MongoIdentifiableEntity;
import org.keycloak.models.mongo.api.MongoIndex;
import org.keycloak.models.mongo.api.context.MongoStoreInvocationContext;

/**
 * @author <a href="mailto:mposolda@redhat.com">Marek Posolda</a>
 */
@MongoCollection(collectionName = "applications")
@MongoIndex(fields = { "realmId", "name" }, unique = true)
public class MongoApplicationEntity extends ApplicationEntity implements MongoIdentifiableEntity {

    @Override
    public void afterRemove(MongoStoreInvocationContext context) {
        // Remove all roles, which belongs to this application
        DBObject query = new QueryBuilder()
                .and("applicationId").is(getId())
                .get();
        context.getMongoStore().removeEntities(MongoRoleEntity.class, query, context);
    }
}
