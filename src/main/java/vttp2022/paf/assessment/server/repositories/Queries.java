package vttp2022.paf.assessment.server.repositories;

public interface Queries {
    public static final String SQL_SELECT_USER =
    "select user_id,username,name from user where username=?";

    public static final String SQL_INSERT_USER =
    "insert into user(user_id,username) values (?,?)";

    public static final String SQL_INSERT_TASK =
    "insert into task(description,priority,due_date,username) values (?,?,?,?)";

}
