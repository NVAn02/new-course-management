package org.example.coursemanager.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class PageResponse {
    private List<?> content;
    private int pageNo;
    private int pageSize;
    private Long totalElements;
    private int totalPages;
    private boolean last;
}
