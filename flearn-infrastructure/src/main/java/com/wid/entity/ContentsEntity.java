package com.wid.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "contents")
public class ContentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contentsId;

    private String title;

    private String url;

}
