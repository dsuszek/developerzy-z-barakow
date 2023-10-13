package org.kainos.ea.dao;

import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.exception.FailedToCreateBandException;
import org.kainos.ea.model.Band;
import org.kainos.ea.model.BandRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BandDao {

    public List<Band> getBands() throws SQLException {
        Connection c = DatabaseConnector.getConnection();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(
                "SELECT * FROM Bands;");
        List<Band> bands = new ArrayList<>();
        while (rs.next()) {
            Band band = new Band(
                    rs.getShort("id"),
                    rs.getString("name"),
                    rs.getShort("level")
            );
            bands.add(band);
        }
        return bands;
    }

    public Band createBand(BandRequest band) throws SQLException, FailedToCreateBandException {
        DatabaseConnector conn = new DatabaseConnector();
        Connection c = conn.getConnection();

        String insertStatement = "INSERT INTO `Bands` (name, level) VALUES (?, ?);";
        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setString(1, band.getName());
        st.setShort(2, band.getLevel());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (!rs.next()) {
            throw new FailedToCreateBandException();
        }

        return new Band
                (rs.getShort(1),
                        band.getName(),
                        band.getLevel());
    }
}