# PROGRESS — corebank-lite (repo sản phẩm)

Chỉ chứa các bài mà code sẽ NẰM THẲNG trong repo này, ghép thành sản phẩm
hoàn chỉnh. Đặt file này ở root repo, tick [x] khi xong, commit kèm mỗi lần
cập nhật để lịch sử Git phản ánh đúng tiến độ thật.

## Giai đoạn nền tảng (đã xong)
- [x] Thiết kế DB (V1__init_schema.sql, V2__seed_data.sql)
- [x] Khởi tạo project Spring Boot 4.1.1 + pom.xml
- [ ] Tạo user Oracle `corebank_app`, chạy thử migration thành công
- [ ] Cấu trúc package rỗng (common/config/core/modules/security)

## Chặng 1: Cursor Pagination & Truy vấn động (bài 11-20)
- [ ] 11. API lấy lịch sử giao dịch theo cursor
- [ ] 12. Mã hóa cursor Base64
- [ ] 13. hasNextPage bằng limit+1
- [ ] 14. @Query lọc status xử lý NULL
- [ ] 15. Cursor phức hợp (created_at + id)
- [ ] 16. Projection tối ưu bộ nhớ
- [ ] 17. Native query giao dịch 24h
- [ ] 18. @Modifying update hàng loạt PENDING quá hạn
- [ ] 19. nextCursor/prevCursor 2 chiều
- [ ] 20. Xử lý race condition cursor + REVERSED

## Chặng 2: Quan hệ & Specification (bài 21-30)
- [ ] 21. Account kèm 5 giao dịch gần nhất
- [ ] 22. JOIN FETCH khử N+1
- [ ] 23. Specification lọc theo tên chủ TK
- [ ] 24. Chặn đóng account còn PENDING
- [ ] 25. MapStruct kèm accountHolderName
- [ ] 26. Thống kê GROUP BY theo account
- [ ] 27. Cursor pagination sort theo tên chủ TK
- [ ] 28. @Modifying mở khóa hàng loạt
- [ ] 29. @EntityGraph tối ưu
- [ ] 30. Specification toán tử IN

## Chặng 3: Validation & Exception (bài 31-40)
- [ ] 31. @Valid TransferRequest
- [ ] 32. @ValidAccountStatus
- [ ] 33. Validation Group
- [ ] 34. GlobalExceptionHandler
- [ ] 35. InsufficientBalanceException, AccountLockedException
- [ ] 36. Conflict 409 idempotency-key trùng
- [ ] 37. Generic ApiResponse<T>
- [ ] 38. BaseService<T,ID>
- [ ] 39. MapStruct Generic
- [ ] 40. Specification LIKE động

## Chặng 4: JWT Security (bài 41-50)
- [ ] 41. Security PermitAll/Authenticate
- [ ] 42. JwtTokenProvider
- [ ] 43. JwtAuthenticationFilter
- [ ] 44. API Login
- [ ] 45. Phân quyền TELLER/ADMIN
- [ ] 46. Lỗi 403 custom
- [ ] 47. SecurityContext -> performed_by
- [ ] 48. Refresh token
- [ ] 49. Auditing tự động created_by
- [ ] 50. Logout blacklist

## Chặng 5: phần LÀ sản phẩm thật (chỉ 3 bài, còn lại xem repo exercises)
- [ ] 52. BankAccount race condition demo -> chuyển thành integration test thật
- [ ] 57. Deadlock 2 account -> áp dụng đúng thứ tự lock trong TransactionService
- [ ] 60. Optimistic lock retry -> code thật trong AccountService, có test

## Chặng 6: Tổng hợp & triển khai (bài 61-70)
- [ ] 61. Redis cache trang đầu lịch sử
- [ ] 62. Unit test Mockito
- [ ] 63. Integration test Testcontainers
- [ ] 64. Docker Compose Spring Boot + Oracle + RabbitMQ
- [ ] 65. EXPLAIN PLAN tối ưu
- [ ] 66. Rate limiting
- [ ] 67. Refactor Clean Architecture
- [ ] 68. API lịch sử thay đổi account
- [ ] 69. Specification Metamodel
- [ ] 70. CI/CD GitHub Actions

## Mức "đủ để nộp CV" (tối thiểu)
- [ ] Toàn bộ Chặng 1-4
- [ ] Bài 52, 57, 60 (Chặng 5)
- [ ] Bài 62, 63, 70 (Chặng 6: test + CI)
- [ ] README hoàn chỉnh với kiến trúc + hướng dẫn chạy
