package org.kainos.ea.dao;

import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.exception.FailedToCreateBandException;
import org.kainos.ea.model.Band;
import org.kainos.ea.model.BandRequest;

import java.sql.*;

public class BandDao {

    public Band createBand(BandRequest band) throws SQLException, FailedToCreateBandException {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        Connection c = databaseConnector.getConnection();

        String insertStatement = "INSERT INTO `Bands` (name, level) VALUES (?, ?);";
        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setString(1, band.getName());
        st.setShort(2, band.getLevel());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (!rs.next()) {
            throw new FailedToCreateBandException("No band id has been returned");
        }

        return new Band
                (rs.getShort(1),
                        band.getName(),
                        band.getLevel());
    }
}