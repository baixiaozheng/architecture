package org.my.architecture.common.dao; 

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class BaseDao extends HibernateDaoSupport {
	@Autowired
    protected void init(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    /**
     * Model
     *
     * @return
     */
    protected abstract Class<?> entityClass();

    /**
     * 根据id获取唯一的对象 HQL
     *
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    protected <T> T get(final Serializable id) {
        return (T) getHibernateTemplate().get(entityClass(), id);
    }

    /**
     * 加载所有的数据，无分页 HQL
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    protected <T> List<T> list() {
        return (List<T>) getHibernateTemplate().loadAll(entityClass());
    }

    /**
     * 查询数据条数 HQL
     *
     * @return
     */
    protected long count() {
        String hql = "select count(id) from " + entityClass().getName();
        Object object = getWithHqlQuery(hql, null);
        return object == null ? 0 : ((Long) object).longValue();
    }

    /**
     * 根据sql查询结果数量
     *
     * @param sql
     * @return
     */
    protected long countWithSql(String sql) {
        Object object = getWithSQLQuery(sql, null, null, null, null);
        return object == null ? 0 : ((BigInteger) object).longValue();
    }

    /**
     * 查询数据条数 HQL
     *
     * @param hql
     * @param condition
     * @return
     */
    protected long countWithHql(final String hql,
                                final Map<String, Object> condition) {
        Object object = getWithHqlQuery(hql, condition);
        return object == null ? 0l : ((BigInteger) object).longValue();
    }

    /**
     * 查询数据条数 SQL
     *
     * @return
     */
    protected long countWithSql(final String sql,
                                final Map<String, Object> condition) {
        Object object = getWithSQLQuery(sql, condition, null, null, null);
        return object == null ? 0l : ((BigInteger) object).longValue();
    }

    /**
     * 添加对象 HQL
     *
     * @param entity
     * @return
     */
    protected <T> T add(final T entity) {
        getHibernateTemplate().persist(entity);
        return entity;
    }

    /**
     * 移除对象 HQL
     *
     * @param entity
     */
    protected <T> void remove(final T entity) {
        getHibernateTemplate().delete(entity);
    }

    /**
     * 更新对象 HQL
     *
     * @param entity
     * @return
     */
    protected <T> T update(final T entity) {
        getHibernateTemplate().merge(entity);
        return entity;
    }

    /**
     * 执行sql
     *
     * @param sql
     * @param condition
     * @return
     */
    protected int excuteWithSql(final String sql,
                                final Map<String, Object> condition) {
        return excuteSQLQuery(sql, condition);
    }

    /**
     * 刷新对象，并更新 HQL
     *
     * @param entity
     * @return
     */
    protected <T> T refresh(final T entity) {
        getHibernateTemplate().refresh(entity);
        return entity;
    }

    /**
     * 固定语句的查询 HQL
     *
     * @param hql
     * @return
     */
    protected List<?> list(final String hql) {
        return getHibernateTemplate().find(hql);
    }

    /**
     * 固定语句的查询 SQL
     *
     * @param sql
     * @return
     */
    protected List<?> listEntityWithSql(final String sql) {
        return listWithSQLQuery(sql, null, null, null, entityClass());
    }

    /**
     * 固定语句的查询 SQL
     *
     * @param sql
     * @return
     */
    protected List<?> listFiledsWithSql(final String sql) {
        return listWithSQLQuery(sql, null, null, null, null);
    }

    /**
     * 固定语句的查询 SQL
     *
     * @param sql
     * @return
     */
    protected List<?> listFiledsWithSql(final String sql, final Map<String, Object> condition) {
        return listWithSQLQuery(sql, condition, null, null, null);
    }

    /**
     * 固定含参数的查询 HQL
     *
     * @param hql
     * @param objects
     * @return
     */
    protected List<?> list(final String hql, Object... objects) {
        return getHibernateTemplate().find(hql, objects);
    }

    /**
     * 根据条件获取列表 HQL
     *
     * @param hql
     * @param condition
     * @return
     */
    protected List<?> list(final String hql,
                           final Map<String, Object> condition) {
        return listWithHqlQuery(hql, condition);
    }

    /**
     * 根据条件获取列表 SQL
     *
     * @param sql
     * @param condition
     * @return
     */
    protected List<?> listEntityWithSql(final String sql,final Map<String, Object> condition) {
        return listWithSQLQuery(sql, condition, null, null, entityClass());
    }

    /**
     * 根据条件获取唯一记录 HQL
     *
     * @param hql
     * @param condition
     * @return
     */
    @SuppressWarnings("unchecked")
    protected <T> T get(final String hql, final Map<String, Object> condition) {
        return (T) getWithHqlQuery(hql, condition);
    }

    /**
     * 根据条件获取唯一记录 SQL
     *
     * @param sql
     * @return
     */
    @SuppressWarnings("unchecked")
    protected <T> T getWithSql(final String sql) {
        return (T) getWithSQLQuery(sql, null, null, null, entityClass());
    }

    /**
     * 根据条件获取唯一记录 SQL
     *
     * @param sql
     * @param condition
     * @return
     */
    @SuppressWarnings("unchecked")
    protected <T> T getWithSql(final String sql, final Map<String, Object> condition) {
        return (T) getWithSQLQuery(sql, condition, null, null, entityClass());
    }

    /**
     * 根据条件获取唯一数据 HQL
     *
     * @param hql
     * @param condition
     * @return
     */
    protected Object select(final String hql,
                            final Map<String, Object> condition) {
        return getWithHqlQuery(hql, condition);
    }

    /**
     * 根据条件获取唯一数据 SQL
     *
     * @param sql
     * @param condition
     * @return
     */
    protected Object selectWithSql(final String sql,
                                   final Map<String, Object> condition) {
        return getWithSQLQuery(sql, condition, null, null, null);
    }

    /**
     * 根据条件获取列表数据，并转换为指定类型对象
     *
     * @param sql
     * @param condition
     * @param resultTransformer
     * @param scalarMapping
     * @return
     */
    protected List listWithSqlAndResultTransformer(final String sql,
                                                   final Map<String, Object> condition, final ResultTransformer resultTransformer, final Map<String, Type> scalarMapping) {
        return listWithSQLQuery(sql, condition, scalarMapping, resultTransformer, null);
    }

    /**
     * 根据条件获取列表数据，并转换为指定类型对象
     *
     * @param sql
     * @param condition
     * @param scalarMapping
     * @return
     */
    protected List listWithSqlAndResult(final String sql,
                                        final Map<String, Object> condition, final Map<String, Type> scalarMapping) {
        return listWithSQLQuery(sql, condition, scalarMapping, null, null);
    }


    /**
     * 根据条件获取唯一数据，并转换为指定类型对象
     */
    protected Object getWithSqlAndResultTransformer(final String sql,
                                                    final Map<String, Object> condition, final ResultTransformer resultTransformer, final Map<String, Type> scalarMapping) {
        return getWithSQLQuery(sql, condition, scalarMapping, resultTransformer, null);
    }

    /**
     * 根据条件获取唯一数据，并转换为指定类型对象
     */
    protected Object getWithSqlAndResult(final String sql,
                                         final Map<String, Object> condition, final Map<String, Type> scalarMapping) {
        return getWithSQLQuery(sql, condition, scalarMapping, null, null);
    }

    /**
     * config hibernate query
     *
     * @param hql
     * @param condition
     * @return
     */
    private final Object getWithHqlQuery(final String hql, final Map<String, Object> condition) {
        return getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(hql);
                configCondition(query, condition);
                return query.uniqueResult();
            }
        });
    }

    /**
     * config hibernate query
     *
     * @param hql
     * @param condition
     * @return
     */
    private final List listWithHqlQuery(final String hql, final Map<String, Object> condition) {
        return getHibernateTemplate().execute(new HibernateCallback<List>() {
            @Override
            public List doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(hql);
                configCondition(query, condition);
                return query.list();
            }
        });
    }

    /**
     * config sqlquery
     *
     * @param sql
     * @param condition
     * @return
     */
    private final Object getWithSQLQuery(final String sql, final Map<String, Object> condition, final Map<String, Type> scalarMapping, final ResultTransformer resultTransformer, final Class clazz) {
        return getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                SQLQuery query = session.createSQLQuery(sql);
                configCondition(query, condition);
                if (scalarMapping != null && !scalarMapping.isEmpty()) {
                    for (String key : scalarMapping.keySet()) {
                        Type type = scalarMapping.get(key);
                        query.addScalar(key, type);
                    }
                }
                if (resultTransformer != null) {
                    query.setResultTransformer(resultTransformer);
                }
                return clazz == null ? query.uniqueResult() : query.addEntity(clazz).uniqueResult();
            }
        });
    }

    /**
     * config sqlquery
     *
     * @param sql
     * @param condition
     * @return
     */
    private final List listWithSQLQuery(final String sql, final Map<String, Object> condition, final Map<String, Type> scalarMapping, final ResultTransformer resultTransformer, final Class clazz) {
        return getHibernateTemplate().execute(new HibernateCallback<List>() {
            @Override
            public List doInHibernate(Session session) throws HibernateException, SQLException {
                SQLQuery query = session.createSQLQuery(sql);
                configCondition(query, condition);
                if (scalarMapping != null && !scalarMapping.isEmpty()) {
                    for (String key : scalarMapping.keySet()) {
                        Type type = scalarMapping.get(key);
                        query.addScalar(key, type);
                    }
                }
                if (resultTransformer != null) {
                    query.setResultTransformer(resultTransformer);
                }
                return clazz == null ? query.list() : query.addEntity(clazz).list();
            }
        });
    }

    /**
     * config sqlquery
     *
     * @param sql
     * @param condition
     * @return
     */
    private final int excuteSQLQuery(final String sql, final Map<String, Object> condition) {
        return getHibernateTemplate().execute(new HibernateCallback<Integer>() {
            @Override
            public Integer doInHibernate(Session session) throws HibernateException, SQLException {
                SQLQuery query = session.createSQLQuery(sql);
                configCondition(query, condition);
                return query.executeUpdate();
            }
        });
    }

    /**
     * 根据条件匹配query
     *
     * @param query
     * @param condition
     */
    @SuppressWarnings("rawtypes")
    private void configCondition(Query query,
                                 final Map<String, Object> condition) {
        if (condition != null && !condition.isEmpty()) {
            for (String key : condition.keySet()) {
                Object object = condition.get(key);
                if (object instanceof Collection) {
                    query.setParameterList(key, (Collection) object);
                } else {
                    query.setParameter(key, object);
                }
            }
        }
    }

    /**
     * 创建map条件
     */
    protected final Map<String, Object> configMap(String[] keys, Object[] values) {
        if (keys.length != values.length) {
            throw new RuntimeException(
                    "config map failed, keys length not eq values length");
        }
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < keys.length; i++) {
            map.put(keys[i], values[i]);
        }
        return map;
    }

    /**
     * 创建map条件
     */
    protected final Map<String, Type> configScalarMap(String[] keys, Type[] values) {
        if (keys.length != values.length) {
            throw new RuntimeException(
                    "config map failed, keys length not eq values length");
        }
        Map<String, Type> map = new HashMap<String, Type>();
        for (int i = 0; i < keys.length; i++) {
            map.put(keys[i], values[i]);
        }
        return map;
    }
}
 