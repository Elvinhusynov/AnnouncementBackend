package com.huseynov.announcementbackend.constant;

public class QuaryConstants {
    public static final String Get_City_List_Query = "SELECT * FROM cities";
    public static final String Get_Category_List_Query = "SELECT * FROM categories";
    public static final String Get_Announcement_List_Quary = """
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
                    ct.catagory_id,
                    ct.name as category_name
            FROM announcements a
                     LEFT JOIN cities c  on A.city_id = C.city_id
                     LEFT JOIN catagories ct on A.catagory_id = ct.catagory_id
            """;

}
