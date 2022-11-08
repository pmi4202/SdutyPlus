package com.d205.sdutyplus.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ResponseCode Convention
 * - 도메인 별로 나누어 관리
 * - [동사_목적어_SUCCESS] 형태로 생성
 * - 코드는 도메인명 앞에서부터 1~2글자로 사용
 * - 메시지는 "~~다."로 마무리
 */

@Getter
@AllArgsConstructor
public enum ResponseCode {
    // User
    LOGIN_SUCCESS(200, "U001", "로그인에 성공하였습니다."),
    GET_USERPROFILE_SUCCESS(200, "U002", "회원 프로필을 조회하였습니다."),
    UPLOAD_USER_IMAGE_SUCCESS(200, "U003", "회원 이미지를 등록하였습니다."),
    EDIT_PROFILE_SUCCESS(200, "U004", "회원 프로필을 수정하였습니다."),
    CHECK_USERNAME_GOOD(200, "U005", "사용가능한 username 입니다."),
    CHECK_USERNAME_BAD(200, "U006", "사용불가능한 username 입니다."),

    // Task
    CREATE_TASK_SUCCESS(200, "T001", "테스크가 생성되었습니다."),
    UPDATE_TASK_SUCCESS(200, "T002", "테스크가 수정되었습니다."),
    DELETE_TASK_SUCCESS(200, "T003", "테스크가 삭제되었습니다."),
    CREATE_SUBTASK_SUCCESS(200, "T004", "서브테스크가 생성되었습니다."),
    UPDATE_SUBTASK_SUCCESS(200, "T005", "서브테스크가 수정되었습니다."),
    DELETE_SUBTASK_SUCCESS(200, "T006", "서브테스크가 삭제되었습니다."),
    GET_DAILYTASK_SUCCESS(200, "T007", "데일리 테스크 조회에 성공하였습니다."),

    //Timer
    GET_LOCALDATETIME_SUCCESS(200, "TI001", "서버 날짜, 시간 조회가 성공하였습니다."),

    //Feed
    CREATE_FEED_SUCCESS(200, "F001", "피드가 등록되었습니다."),
    GET_ALL_FEED_SUCCESS(200, "F002", "전체 게시글 조회가 성공하였습니다."),

    // Warn
    WARN_SUCCESS(200, "W001", "신고 완료."),
    WARN_FAIL(200, "W002", "신고 실패"),

    ;

    private final int status;
    private final String code;
    private final String message;
}
