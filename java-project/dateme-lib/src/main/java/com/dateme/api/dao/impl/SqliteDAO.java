package com.dateme.api.dao.impl;

import com.dateme.api.dao.DateMeDAO;
import com.dateme.core.model.Profile;
import com.dateme.core.model.RGB;
import com.dateme.core.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteDAO implements DateMeDAO {
    private Connection connection = null;

    //This should be in an external configuration file
    private String db;

    //This should be in an external configuration file
    private String[] initialize = new String[]{
            "CREATE TABLE IF NOT EXISTS profiles (email string, color string, number int, name string);",
            "CREATE TABLE IF NOT EXISTS users (email string, created int, enc string);"};

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

        for (String q : initialize) {
            if (!update(q).isPresent()) {
                System.exit(-1);
            }
        }
    }


    public Profile createProfile(Profile profile) {
        String email = profile.email;
        String color = profile.color.toHexString();
        int number = profile.number;
        String name = profile.name;
        String query = "INSERT INTO profiles (email, color, number, name) VALUES(?, ?, ?, ?)";
        update(query, email, color, number, name);
        return profile;
    }

    public Optional<Profile> getProfile(String email) {
        String q = "SELECT * FROM profiles WHERE email = ? LIMIT 1";
        return query(q, email).flatMap(r -> { try {
            if (r.next()) {
                String e = r.getString("email");
                RGB color = new RGB(r.getString("color"));
                int number = r.getInt("number");
                String name = r.getString("name");
                return Optional.of(new Profile(e, name, color, number));
            }
            else return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }});
    }

    public Profile updateProfile(Profile profile) {
        String query = "UPDATE profiles SET color=?, number=?, name=? WHERE email=?";
        String email = profile.email;
        String color = profile.color.toHexString();
        String name = profile.name;
        int number = profile.number;
        update(query, color, number, name, email);
        return profile;
        //TODO: error notification (what if update failed?)
    }

    public Optional<Profile> deleteProfile(String email) {
        //TODO: error notification (what if deletion failed?)
        return getProfile(email).map(p -> {
            update("DELETE FROM profiles WHERE email=?", p.email);
            return p;
        });
    }

    @Override
    public List<Profile> allProfiles() {
        String query = "SELECT * FROM profiles";
        try {
            ResultSet r = query(query).get();
            ArrayList<Profile> profiles = new ArrayList<>();
            while (r.next()) {
                String e = r.getString("email");
                RGB color = new RGB(r.getString("color"));
                int number = r.getInt("number");
                String name = r.getString("name");
                profiles.add(new Profile(e, name, color, number));
            }
            return profiles;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }



    @Override
    public User createUser(User user) {
        String query = "INSERT INTO users VALUES(?, ?, ?)";
        String email = user.email;
        long time = user.createdTime;
        String enc = user.enc;
        update(query, email, time, enc);
        return user;
    }

    @Override
    public Optional<User> getUser(String email) {
        String q = "SELECT * FROM users WHERE email = ? LIMIT 1";
        return query(q, email).flatMap(r -> { try {
            if (r.next()) {
                String e = r.getString("email");
                long time = r.getLong("created");
                String enc = r.getString("enc");
                return Optional.of(new User(e, time, enc));
            }
            else return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }});
    }

    @Override
    public User updateUser(User User) {
        return null;
    }

    @Override
    public Optional<User> deleteUser(String email) {
        return null;
    }

    @Override
    public List<User> allUsers() {
        String query = "SELECT * FROM users";
        try {
            ResultSet r = query(query).get();
            ArrayList<User> users = new ArrayList<>();
            while (r.next()) {
                String e = r.getString("email");
                long time = r.getLong("created");
                String enc = r.getString("enc");
                users.add(new User(e, time, enc));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Profile> findMostCompatible(Profile profile, int count) {
        return null;
    }


}
