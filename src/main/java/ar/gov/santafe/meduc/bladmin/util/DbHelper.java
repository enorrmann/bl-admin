package ar.gov.santafe.meduc.bladmin.util;

import ar.gov.santafe.meduc.dto.SimpleDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author enorrmann
 */
public class DbHelper {

    private DataSource dataSource;

    public DbHelper(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<SimpleDto> select(String query) {
        PreparedStatement stmt = null;
        List<SimpleDto> resultList = new ArrayList<>();
        try {
            Connection con = dataSource.getConnection();
            stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                SimpleDto unResult = new SimpleDto();
                for (int i = 1; i <= columnsNumber; i++) {
                    String value = rs.getString(i);
                    String columnName = rsmd.getColumnName(i);
                    unResult.add(format(columnName), value);
                }
                resultList.add(unResult);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultList;
    }

    public SimpleDto selectById(String query, Long idLong) {
        PreparedStatement stmt = null;
        SimpleDto result = new SimpleDto();
        try {
            Connection con = dataSource.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setLong(1, idLong);
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            if (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    String value = rs.getString(i);
                    String columnName = rsmd.getColumnName(i);
                    result.add(format(columnName), value);
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    private String format(String columnName) {
        return columnName == null ? null : columnName.toLowerCase();
    }
}
