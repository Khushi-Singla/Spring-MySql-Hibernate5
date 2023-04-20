package com.freshworks.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "fd_multitenant_users")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FdMultitenantUser
        implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "account_id")
    private Long accountId;
    @Column(name = "name")
    private String name;
    @Column(name = "password_digest")
    private String passwordDigest;
    @Column(name = "authentication_token")
    private String authenticationToken;
    @Column(name = "confirmation_token")
    private String confirmationToken;
    @Column(name = "confirmed")
    private Boolean confirmed;
    @Column(name = "account_admin")
    private Boolean accountAdmin;
    @Column(name = "twitter_id")
    private String twitterId;
    @Column(name = "facebook_id")
    private String facebookId;
    @Column(name = "freshid_mapping_status")
    private Integer freshidMappingStatus;
    @Column(name = "inviter_id")
    private Long inviterId;
    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private UserProfile userProfile;
}
