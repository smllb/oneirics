package com.lucid.oneiric.model;

public class UsersTableModel {
    Integer accountTypeId;
//    UserModel userData;
    String salt;


}


//        tablesCreationQueries.put("users", "CREATE TABLE users (\n" +
//                                          "    id CHAR(36) NOT NULL DEFAULT (UUID()),\n" +
//                                          "    login VARCHAR(255),\n" +
//                                          "    salt CHAR(32),\n" +
//                                          "    password VARCHAR(255),\n" +
//                                          "    email VARCHAR(255),\n" +
//                                          "    recovery_email VARCHAR(255),\n" +
//                                          "    account_type_id INT,\n" +
//                                          "    PRIMARY KEY (id),\n" +
//                                          "    FOREIGN KEY (account_type_id) REFERENCES account_types(id)\n" +
//                                          ");");