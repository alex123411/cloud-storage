package com.alext123411.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.common.aliasing.qual.Unique;

@Entity // This tells Hibernate to make a table out of this class
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private Long Id;

    @Unique
    private Long gitHubId;

    private String login;

    private String avatarUrl;

    private String email;
}
