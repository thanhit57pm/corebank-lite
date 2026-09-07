package com.example.corebank.common.exception;
/**
 * Exception duy nhất cho MỌI lỗi nghiệp vụ trong hệ thống.
 * Không dùng hierarchy nhiều class con — kế thừa không tạo ra hành vi
 * khác biệt thì không có giá trị OOP thật, chỉ gây phình số lượng file.
 * Phân biệt loại lỗi qua ErrorCode (enum), không qua Java type.
 */
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;
    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
