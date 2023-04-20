package com.freshworks.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.freshworks.repository.FdMultitenantUserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "user_profiles")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile
        implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "account_id")
    private Long accountId;
    @Column(name = "job_title")
    private String jobTitle;
    @Column(name = "work_number")
    private String workNumber;
    @Column(name = "mobile_number")
    private String mobileNumber;
    @Column(name = "language")
    private String language;
    @Column(name = "time_zone")
    private String timeZone;
    @Column(name = "last_login_at")
    private Instant lastLoginAt;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "access_scope")
    private Integer accessScope;
    @Column(name = "logout_at")
    private Instant logoutAt;
    @Column(name = "deal_pipeline_id")
    private Long dealPipelineId;
    @Column(name = "configs")
    private String configs;
    @Column(name = "report_access")
    private Boolean reportAccess;
    @Column(name = "mobile_logout_at")
    private Instant mobileLogoutAt;
    @Column(name = "freshchat_restore_id")
    private String freshchatRestoreId;
    @Column(name = "forget_entity_access")
    private Boolean forgetEntityAccess;
    @Column(name = "is_forgotten")
    private Boolean isForgotten;
    @Column(name = "report_export")
    private Boolean reportExport;
    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;

    @OneToOne
    @MapsId("id")
    @JsonBackReference
    @JoinColumn(name="id", referencedColumnName = "id")
    private FdMultitenantUser user;
}
