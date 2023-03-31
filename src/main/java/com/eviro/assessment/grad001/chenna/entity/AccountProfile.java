package com.eviro.assessment.grad001.chenna.entity;

import lombok.*;

import javax.persistence.*;
import java.net.URI;

@Entity
@Table(name = "account_profile")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "account_holder_name", nullable = false)
    private String accountHolderName;

    @Column(name = "account_holder_surname", nullable = false)
    private String accountHolderSurname;

    @Column(name = "http_image_link")
    private URI httpImageLink;
}
