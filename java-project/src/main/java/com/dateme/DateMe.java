package com.dateme;

import com.dateme.api.DateMeApi;
import com.dateme.api.dao.DateMeDAO;
import com.dateme.api.dao.impl.CachedDAO;
import com.dateme.api.dao.impl.MongoDAO;
import com.dateme.core.RGB;
import com.dateme.core.User;

public class DateMe {
    public static void main(String[] args) {

        DateMeDAO mongo = new MongoDAO();

        DateMeDAO cachedDao = new CachedDAO(mongo);

        DateMeApi api = new DateMeApi(cachedDao);

        if (args[0].equals("create")) {
            api.createUserAccount(args[1], new RGB(0,0,0), Integer.parseInt(args[2]));
        }

    }
}
