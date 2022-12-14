package App.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Data
@EqualsAndHashCode
@ToString
@MappedSuperclass //Auditable class
/***
 * AuditingEntityListener is an entity listener provided by Spring Data JPA that can be used to trigger the collection of auditing data
 * The @EntityListeners annotation can be used to activate the AuditingEntityListener for each entity
 */
@EntityListeners(AuditingEntityListener.class)
public class Auditable<T>  {

    @Column(name = "created_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    protected Date creationDate;
    @Column(name = "lastMod_date")
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastModifiedDate;
    //below might not be available, used for illustration purposes
    @CreatedBy
    @Column(name="created_by")
    protected T createdBy;
    @LastModifiedBy
    @Column(name="modified_by")
    protected T modifiedBy;
}
