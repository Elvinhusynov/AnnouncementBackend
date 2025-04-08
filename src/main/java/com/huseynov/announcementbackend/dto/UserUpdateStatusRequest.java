package com.huseynov.announcementbackend.dto;


public record UserUpdateStatusRequest(Boolean status) {
}
// həmişə aşağıdakı kimi yazırdıq record yuxarıdaki kimi yazılır eyni nəticəyə gəlir.
//@Data
//public class UserUpdateStatusRequest {
//    private Boolean status;
