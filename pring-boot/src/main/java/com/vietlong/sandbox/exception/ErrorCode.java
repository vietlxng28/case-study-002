package com.vietlong.sandbox.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    UNCATEGORIZED_EXCEPTION("SYS_500", "Đã xảy ra lỗi hệ thống, vui lòng thử lại sau.", HttpStatus.INTERNAL_SERVER_ERROR),
    RECORD_NOT_FOUND("RECORD_404", "Không tìm thấy bản ghi với ID: %s", HttpStatus.NOT_FOUND),
    INVALID_KEY("REQ_400", "Dữ liệu yêu cầu không hợp lệ.", HttpStatus.BAD_REQUEST);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    public String formatMessage(Object... args) {
        if (args != null && args.length > 0) {
            try {
                return String.format(this.message, args);
            } catch (Exception e) {
                return this.message;
            }
        }
        return this.message;
    }
}
