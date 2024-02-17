package com.phone.data.Repository;

import com.phone.data.Entity.NotificationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NotificationDataRepo extends JpaRepository<NotificationData,Long> {

    @Query(value = "SELECT top 1 (DateTime) FROM notification_data order by DateTime desc", nativeQuery = true)
    String findbyDateTime();

    @Query(value = "select count(*) from notification_data where  DateTime like ?1% ",nativeQuery = true)

    String yesterdayCount(String valueOf);
}
