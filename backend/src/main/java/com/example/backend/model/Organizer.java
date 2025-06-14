package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_organizer", uniqueConstraints = {
        @UniqueConstraint(name = "unique_email", columnNames = {"email"}),
        @UniqueConstraint(name = "user_id", columnNames = {"user_id"})
})
public class Organizer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organizer_id", nullable = false)
    private Integer id;

    @Size(max = 200)
    @Column(name = "org_name", length = 200)
    private String orgName;

    @Size(max = 50)
    @Column(name = "org_type", length = 50)
    private String orgType;

    @Size(max = 50)
    @Column(name = "tax_code", length = 50)
    private String taxCode;

    @Size(max = 255)
    @Column(name = "org_address")
    private String orgAddress;

    @Size(max = 255)
    @Column(name = "website")
    private String website;

    @Size(max = 100)
    @Column(name = "business_field", length = 100)
    private String businessField;

    @Lob
    @Column(name = "org_info")
    private String orgInfo;

    @Size(max = 255)
    @Column(name = "org_logo_url")
    private String orgLogoUrl;

    @Size(max = 255)
    @Column(name = "id_card_front_url")
    private String idCardFrontUrl;

    @Size(max = 255)
    @Column(name = "id_card_back_url")
    private String idCardBackUrl;

    @Size(max = 255)
    @Column(name = "business_license_url")
    private String businessLicenseUrl;

    @Lob
    @Column(name = "experience")
    private String experience;

    @ColumnDefault("'pending'")
    @Lob
    @Column(name = "status")
    private String status;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at")
    private Instant updatedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "organizer")
    @JsonManagedReference
    private Set<Event> tblEvents = new LinkedHashSet<>();

}