package org.keycloak.models.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = { "name", "application" }),
        @UniqueConstraint(columnNames = { "name", "realm" })
})
public abstract class RoleEntity {
    @Id
    private String id;

    private String description;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {})
    @JoinTable(name = "CompositeRole", joinColumns = @JoinColumn(name = "composite"), inverseJoinColumns = @JoinColumn(name = "role"))
    private Collection<RoleEntity> compositeRoles = new ArrayList<RoleEntity>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public abstract String getName();

    public abstract void setName(String name);

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<RoleEntity> getCompositeRoles() {
        return compositeRoles;
    }

    public void setCompositeRoles(Collection<RoleEntity> compositeRoles) {
        this.compositeRoles = compositeRoles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleEntity that = (RoleEntity) o;

        if (!id.equals(that.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
