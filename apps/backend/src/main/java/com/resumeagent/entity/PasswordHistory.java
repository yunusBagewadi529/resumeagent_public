package com.resumeagent.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(
        name = "password_history",
        indexes = {
                @Index(name = "idx_password_history_user_id", columnList = "user_id"),
                @Index(name = "idx_password_history_created_at", columnList = "created_at DESC")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@ToString(exclude = "passwordHash")
public class PasswordHistory implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // -------------------------------------------------------------------------
    // Primary Key
    // -------------------------------------------------------------------------

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    // -------------------------------------------------------------------------
    // Relationships
    // -------------------------------------------------------------------------

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // -------------------------------------------------------------------------
    // Password Data
    // -------------------------------------------------------------------------

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    // -------------------------------------------------------------------------
    // Auditing
    // -------------------------------------------------------------------------

    @Column(
            name = "created_at",
            nullable = false,
            updatable = false,
            insertable = false
    )
    private Instant createdAt;
}
