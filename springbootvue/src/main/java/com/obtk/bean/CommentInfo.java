package com.obtk.bean;

import lombok.Data;

@Data
public class CommentInfo {
    private Integer id;
    private String username;
    private String reminderTime;
    private String commentInfo;
    private String fileName;
}
