package com.app.college.models.idGenerator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.jdbc.Work;

@SuppressWarnings("serial")
public class CustomStringSequenceGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object)
            throws HibernateException {

        String prefix = "RUNT"; // Prefix for the identifier
        String sequenceName = "staffs_id_seq"; // Name of the sequence

        try {
            Long nextValue = getNextValue(session, sequenceName);

            // Generate the string identifier with the prefix and sequence value
            return prefix + String.format("%06d", nextValue); // Adjust the format as needed
        } catch (SQLException e) {
            throw new HibernateException("Unable to generate sequence value for identifier", e);
        }
    }

    private Long getNextValue(SharedSessionContractImplementor session, String sequenceName) throws SQLException {
        final Long[] nextValueHolder = new Long[1];
        session.doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                try (Statement statement = connection.createStatement()) {
                    try (ResultSet rs = statement.executeQuery("SELECT nextval('" + sequenceName + "')")) {
                        if (rs.next()) {
                            nextValueHolder[0] = rs.getLong(1);
                        }
                    }
                }
            }
        });
        return nextValueHolder[0];
    }
}


