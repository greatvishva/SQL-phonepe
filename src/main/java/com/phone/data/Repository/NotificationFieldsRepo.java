package com.phone.data.Repository;

import com.phone.data.Entity.NotificationFields;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NotificationFieldsRepo extends JpaRepository<NotificationFields,Long> {

    @Query(value = "SELECT top 1 (Id) FROM notification_fields order by Id desc", nativeQuery = true)
    Long findbyfieldId();
}
