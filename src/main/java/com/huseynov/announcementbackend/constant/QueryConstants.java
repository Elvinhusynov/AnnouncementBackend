package com.huseynov.announcementbackend.constant;

public class QueryConstants {
    public static final String Get_City_List_Query = "SELECT * FROM cities";
    public static final String Get_Category_List_Query = "SELECT * FROM categories";
    public static final String Get_Announcement_List_Query = """
            SELECT  A.announcement_id,
                    A.name,
                    A.description,
                    A.announcement_number,
                    A.price,
                    A.phone_number,
                    A.seller_full_name,
                    A.delivery,
                    A.created_date,
                    A.modified_date,
                    C.city_id,
                    C.name as city_name,
                    ct.category_id,
                    ct.name as category_name
            FROM announcements a
                     LEFT JOIN cities c  on A.city_id = C.city_id
                     LEFT JOIN categories ct on A.category_id = ct.category_id
            LIMIT ?  OFFSET ?
            """;
    public static final String CREAT_ANNOUNCEMENT_QUERY = """
            INSERT INTO announcements,
            (NAME, DESCRIPTION, ANNOUNCEMENT_NUMBER, PRICE, PHONE_NUMBER,
            SELLER_FULL_NAME, DELIVERY, CITY_ID, CATEGORY_ID)
            VALUE (?,?,?,?,?,?,?,?,?);
            """;
    public static final String UPDATE_ANNOUNCEMENT_QUERY = """
            UPDATE announcements
            SET NAME = ?,
                description = ?,
                price = ?,
                seller_full_name = ?,
                delivery = ?
             WHERE announcement_id =?
            """;
    public static final String DELETE_ANNOUNCEMENT_QUERY = """
            DELETE 
            FROM announcements
            WHERE announcement_id = ?
            """;
    public static final String GET_ANNOUNCEMENT_BY_ID = """
            SELECT  A.announcement_id,
                    A.name,
                    A.description,
                    A.announcement_number,
                    A.price,
                    A.phone_number,
                    A.seller_full_name,
                    A.delivery,
                    A.created_date,
                    A.modified_date,
                    C.city_id,
                    C.name as city_name,
                    ct.category_id,
                    ct.name as category_name
            FROM announcements a
                      LEFT JOIN cities c  on A.city_id = C.city_id
                      LEFT JOIN categories ct on A.category_id = ct.category_id
            WHERE announcement_id = ?
            """;
    public static final String GET_ANNOUNCEMENT_COUNT_QUERY = """
            SELECT COUNT(*) as totalCount FROM announcements
            """;
}
