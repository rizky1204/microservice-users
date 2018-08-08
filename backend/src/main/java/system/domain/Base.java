package system.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
@Data
public abstract class Base implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, updatable = false)
    protected Integer id;

    @Column(name = "SECURE_ID", unique = true, length = 36, updatable = false)
    private String secureId;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE", updatable = false)
    private Date creationDate;

    @CreatedBy
    @Column(name = "CREATED_BY", updatable = false, length = 30)
    private String createdBy;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_MODIFIED_DATE")
    private Date lastModifiedDate;

    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY", length = 30)
    private String lastModifiedBy;

    @Version
    @Column(name = "VERSION")
    private Integer version;

    @Column(name = "DISABLED")
    private Boolean disabled;

    @PrePersist
    public void onCreate() {
        this.secureId = UUID.randomUUID().toString();
        this.disabled = Boolean.FALSE;
    }
}
