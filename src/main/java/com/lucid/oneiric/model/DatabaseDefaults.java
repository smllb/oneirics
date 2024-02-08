package com.lucid.oneiric.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class DatabaseDefaults {
    String databaseName;
    Map<String, String> tablesCreationQueries = new LinkedHashMap<>();
    public DatabaseDefaults() {
        databaseName = "oneiric";

        tablesCreationQueries.put("accountTypes", "CREATE TABLE account_types (\n" +
                "    id INT NOT NULL AUTO_INCREMENT,\n" +
                "    name VARCHAR(32),\n" +
                "    PRIMARY KEY (id)\n" +
                ");");
        tablesCreationQueries.put("users", "CREATE TABLE users (\n" +
                "    id CHAR(36) NOT NULL DEFAULT (UUID()),\n" +
                "    login VARCHAR(255),\n" +
                "    salt CHAR(32),\n" +
                "    password VARCHAR(255),\n" +
                "    email VARCHAR(255),\n" +
                "    recovery_email VARCHAR(255),\n" +
                "    account_type_id INT,\n" +
                "    PRIMARY KEY (id),\n" +
                "    FOREIGN KEY (account_type_id) REFERENCES account_types(id)\n" +
                ");");
        tablesCreationQueries.put("posts", "CREATE TABLE posts (\n" +
                "    id INT NOT NULL AUTO_INCREMENT,\n" +
                "    author_id CHAR(36),\n" +
                "    text TEXT,\n" +
                "    PRIMARY KEY(id),\n" +
                "    FOREIGN KEY (author_id) REFERENCES users(id)\n" +
                ");");
        tablesCreationQueries.put("visibility", "CREATE TABLE visibility (\n" +
                "        id INT NOT NULL AUTO_INCREMENT,\n" +
                "        name VARCHAR(32),\n" +
                "        PRIMARY KEY(id)\n" +
                ");");

        tablesCreationQueries.put("friendship_requests", "CREATE table friendship_requests(\n" +
                "        id INT NOT NULL AUTO_INCREMENT,\n" +
                "        sender_id CHAR(36),\n" +
                "        receiver_id CHAR(36),\n" +
                "        status ENUM('pending', 'accepted', 'declined'),\n" +
                "        PRIMARY KEY(id),\n" +
                "        FOREIGN KEY(sender_id) REFERENCES users(id),\n" +
                "        FOREIGN KEY(receiver_id) REFERENCES users(id)\n" +
                ");");

        tablesCreationQueries.put("friendships", "CREATE table friendships(\n" +
                "        id INT NOT NULL AUTO_INCREMENT,\n" +
                "        user1_id CHAR(36),\n" +
                "        user2_id CHAR(36),\n" +
                "        PRIMARY KEY (id),\n" +
                "        FOREIGN KEY(user1_id) REFERENCES users(id),\n" +
                "        FOREIGN KEY(user2_id) REFERENCES users(id)\n" +
                "        )");

        tablesCreationQueries.put("users_blocked_by_users", "CREATE TABLE users_blocked_by_users(\n" +
                "        id INT NOT NULL AUTO_INCREMENT,\n" +
                "        user1_id CHAR(36),\n" +
                "        user2_id CHAR(36),\n" +
                "        reason VARCHAR(255),\n" +
                "        PRIMARY KEY (id),\n" +
                "        FOREIGN KEY(user1_id) REFERENCES users(id),\n" +
                "        FOREIGN KEY(user2_id) REFERENCES users(id)\n" +
                ")");

        tablesCreationQueries.put("user_groups", "CREATE TABLE user_groups(\n" +
                "        id CHAR(36) NOT NULL DEFAULT (UUID()),\n" +
                "        owner CHAR(36),\n" +
                "        name VARCHAR(64),\n" +
                "        description VARCHAR(255),\n" +
                "        PRIMARY KEY(id),\n" +
                "        FOREIGN KEY(owner) REFERENCES users(id)\n" +
                ")");

        tablesCreationQueries.put("groupship", "CREATE TABLE groupship(\n" +
                "        id INT NOT NULL AUTO_INCREMENT,\n" +
                "        group_id CHAR(36),\n" +
                "        member_id CHAR(36),\n" +
                "        PRIMARY KEY(id),\n" +
                "        FOREIGN KEY(group_id) REFERENCES user_groups(id),\n" +
                "        FOREIGN KEY(member_id) REFERENCES users(id)\n" +
                ")");

    }

    public Map<String, String> getTableQueries() {
        return tablesCreationQueries;
    }
    public String getDatabaseName() {
        return databaseName;
    }
}

