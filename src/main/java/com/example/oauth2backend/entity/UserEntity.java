package com.example.oauth2backend.entity;

import com.example.oauth2backend.entity.util.Auditable;
import com.example.oauth2backend.utill.enumeration.RegistrationSource;
import com.example.oauth2backend.utill.enumeration.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "user")
public class UserEntity extends Auditable {
    @Id
    private Integer id;
    private String firstName;
    @Column(unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private RegistrationSource registrationSource;

    private String picture;
}
