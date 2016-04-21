package ar.gov.santafe.meduc.bladmin.dao.service;

import ar.gov.santafe.meduc.bladmin.dao.config.GenericDao;
import ar.gov.santafe.meduc.dto.SimpleDto;
import java.util.List;
import javax.sql.DataSource;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

/**
 *
 * @author enorrmann
 */
public class DaoFactory {

    private static DaoFactory instance;
    public static DaoFactory instance(DataSource dataSource) {
        if (instance==null){
            instance = new DaoFactory(dataSource);
        }
        return instance;
    }

    private DataSource dataSource;

    private DaoFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public GenericDao forDao(Class<?> aClass) {
        return new DaoImpl(aClass);
    }

    private class DaoImpl<T extends GenericDao> implements GenericDao {

        private final Class<T> daoClass;

        public DaoImpl(Class<T> daoClass) {
            this.daoClass = daoClass;
        }

        @Override
        public List<SimpleDto> all() {
            DBI dbi = new DBI(dataSource);
            Handle handle = dbi.open();
            List<SimpleDto> result = getDao(handle).all();
            handle.close();
            return result;
        }

        @Override
        public SimpleDto findById(Long id) {
            DBI dbi = new DBI(dataSource);
            Handle handle = dbi.open();
            SimpleDto result = getDao(handle).findById(id);
            handle.close();
            return result;
        }

        @Override
        public int insert(SimpleDto simpleDto) {
            DBI dbi = new DBI(dataSource);
            Handle handle = dbi.open();
            getDao(handle).insert(simpleDto);
            handle.close();
            return 0;
        }

        @Override
        public int update(SimpleDto simpleDto) {
            DBI dbi = new DBI(dataSource);
            Handle handle = dbi.open();
            getDao(handle).update(simpleDto);
            handle.close();

            return 0;
        }

        @Override
        public int delete(SimpleDto simpleDto) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        protected GenericDao getDao(Handle handle) {
            return handle.attach(daoClass);
        }
    }

}
