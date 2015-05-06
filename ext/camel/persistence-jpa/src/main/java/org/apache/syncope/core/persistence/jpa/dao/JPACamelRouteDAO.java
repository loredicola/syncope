/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.syncope.core.persistence.jpa.dao;

import java.util.List;
import javax.persistence.TypedQuery;
import org.apache.syncope.common.lib.types.SubjectType;
import org.apache.syncope.core.persistence.api.dao.CamelRouteDAO;
import org.apache.syncope.core.persistence.api.entity.CamelRoute;
import org.apache.syncope.core.persistence.jpa.entity.JPACamelRoute;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class JPACamelRouteDAO extends AbstractDAO<CamelRoute, String> implements CamelRouteDAO {

    @Override
    public CamelRoute find(final String key) {
        return entityManager.find(JPACamelRoute.class, key);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CamelRoute> find(final SubjectType subjectType) {
        TypedQuery<CamelRoute> query = entityManager.createQuery(
                "SELECT e FROM " + JPACamelRoute.class.getSimpleName()
                + " e WHERE e.subjectType = :subjectType", CamelRoute.class);
        query.setParameter("subjectType", subjectType);

        return query.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<CamelRoute> findAll() {
        TypedQuery<CamelRoute> query = entityManager.createQuery(
                "SELECT e FROM " + JPACamelRoute.class.getSimpleName() + " e ", CamelRoute.class);
        return query.getResultList();
    }

    @Override
    public CamelRoute save(final CamelRoute route) {
        return entityManager.merge(route);
    }

    @Override
    public void delete(final String key) {
        CamelRoute route = find(key);
        if (route != null) {
            entityManager.remove(route);
        }
    }

}
