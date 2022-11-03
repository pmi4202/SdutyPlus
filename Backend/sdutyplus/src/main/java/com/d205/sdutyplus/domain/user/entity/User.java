package com.d205.sdutyplus.domain.user.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@DynamicInsert
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private Long seq;

    @Column(nullable = false, name="email", length = 50, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name="social_type", length = 10)
    private SocialType socialType;

    @Column(name="nickname", length = 20, unique = true)
    private String nickname;

    @Column(name="job", columnDefinition = "INT UNSIGNED")
    private Long job;

    @Column(name="fcm_token", length = 250)
    private String fcmToken;

    @Column(name="img_url", length = 200)
    private String imgUrl;

    @Column(name="reset_time")
    private Time resetTime;

    @Column(name="reset_cron", length = 50)
    private String resetCron;

    @Column(name = "del_YN", nullable = false)
    private boolean delYN;

    @Column(name = "ban_YN", nullable = false)
    private boolean banYN;

    @Column(name = "reg_time", nullable = false)
    private LocalDateTime regTime;
}
