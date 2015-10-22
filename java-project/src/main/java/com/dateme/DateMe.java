package com.dateme;

import com.dateme.api.DateMeApi;
import com.dateme.api.dao.DateMeDAO;
import com.dateme.api.dao.impl.CachedDAO;
import com.dateme.api.dao.impl.MongoDAO;
import com.dateme.api.dao.impl.SqliteDAO;
import com.dateme.core.Profile;
import com.dateme.core.RGB;

import java.sql.SQLException;

public class DateMe {
    public static void main(String[] args) throws SQLException {

        DateMeDAO dao = new SqliteDAO();

        DateMeApi api = new DateMeApi(dao);

        System.out.println("Find: " + api.findUserAccountByEmail("timothyk@gwu.edu"));
        System.out.println("Created: " + api.createUserAccount("timothyk@gwu.edu", new RGB(0,0,0), 10));
        System.out.println("Find: " + api.findUserAccountByEmail("timothyk@gwu.edu"));
        System.out.println("Update: " + api.updateUserAccount("timothyk@gwu.edu", new RGB(255, 255, 255), 1));
        System.out.println("Find: " + api.findUserAccountByEmail("timothyk@gwu.edu"));
        System.out.println("Delete: " + api.removeAccount("timothyk@gwu.edu"));
        System.out.println("Find: " + api.findUserAccountByEmail("timothyk@gwu.edu"));

    }
}
