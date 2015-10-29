package com.dateme.api.dao.impl;

import com.dateme.api.dao.DateMeDAO;
import com.dateme.core.model.Profile;
import com.dateme.core.model.RGB;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class SqliteDAO implements DateMeDAO {
    private Connection connection = null;

    //This should be in an external configuration file
    private String db;

    //This should be in an external configuration file
    private String initialize = "CREATE TABLE IF NOT EXISTS profiles (email string, color string, number int)";

    private PreparedStatement prepareStatement(Connection connection, String sql, Object[] args) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            if (arg instanceof String) {
                statement.setString(i + 1, (String) arg);
            } else if (arg instanceof Integer) {
                statement.setInt(i + 1, (Integer) arg);
            } else {
                statement.setObject(i + 1, arg);
            }
        }
        return statement;
    }

    private Optional<Integer> update(String sql, Object... args) {
        try {
            PreparedStatement statement = prepareStatement(connection, sql, args);
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            return Optional.of(statement.executeUpdate());
        } catch(SQLException e) {
            System.err.println(e.getMessage());
            return Optional.empty();
        }
    }

    private Optional<ResultSet> query(String sql, Object... args) {
        try {
            PreparedStatement statement = prepareStatement(connection, sql, args);
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            return Optional.of(statement.executeQuery());
        } catch(SQLException e) {
            System.err.println(e.getMessage());
            return Optional.empty();
        }
    }

    public SqliteDAO(String dburl) {
        db = dburl;
        try {
            connection = DriverManager.getConnection(db);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        if (!update(initialize).isPresent()) {
            System.exit(-1);
        }
    }

    public Profile createUser(Profile profile) {
        String email = profile.email;
        String color = profile.color.toHexString();
        int number = profile.number;
        String query = "INSERT INTO profiles VALUES(?, ?, ?)";
        update(query, email, color, number);
        return profile;
    }

    public Optional<Profile> getUser(String email) {
        String q = "SELECT * FROM profiles WHERE email = ? LIMIT 1";
        return query(q, email).flatMap(r -> { try {
            if (r.next()) {
                String e = r.getString("email");
                RGB color = new RGB(r.getString("color"));
                int number = r.getInt("number");
                return Optional.of(new Profile(e, color, number));
            }
            else return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }});
    }

    public Profile updateUser(Profile profile) {
        String email = profile.email;
        String color = profile.color.toHexString();
        int number = profile.number;
        String query = "UPDATE profiles SET color=?, number=? WHERE email=?";
        //TODO: error notifcation (what if update failed?)
        update(query, color, number, email);
        return profile;
    }

    public Optional<Profile> deleteUser(String email) {
        //TODO: error notifcation (what if deletion failed?)
        return getUser(email).map(p -> {
            update("DELETE FROM profiles WHERE email=?", p.email);
            return p;
        });
    }

    public List<Profile> findMostCompatible(Profile profile, int count) {
        return null;
    }
//    public String entityToTableName(Class<?> entityType) {
//        if (entityType.equals(Profile.class)) {
//            return "profiles";
//        }
//    }


}
