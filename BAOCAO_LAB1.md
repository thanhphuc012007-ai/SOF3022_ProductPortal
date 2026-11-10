# BÁO CÁO LAB 1 - SOF3022: SPRING BOOT MVC
**Học phần:** SOF3022 - Spring Boot MVC  
**Dự án:** SOF3022_ProductPortal (Hệ thống Quản lý Sản phẩm)  
**Học kỳ:** Summer 2026  

---

## BÀI 1: CÀI ĐẶT MÔI TRƯỜNG VÀ KIỂM TRA CÔNG CỤ PHÁT TRIỂN

### 1. Kiểm tra môi trường
- **Java Development Kit (JDK):** JDK 25 LTS (`25.0.5`)
- **Apache Maven:** 3.10.0 (qua Maven Wrapper `mvnw`)
- **IDE:** Spring Tool Suite 4 / Visual Studio Code / IntelliJ IDEA

### 2. Vai trò của JDK, Maven và IDE trong phát triển Spring Boot
| Công cụ | Vai trò trong quá trình phát triển |
| :--- | :--- |
| **JDK (Java Development Kit)** | Cung cấp môi trường biên dịch mã nguồn Java (`javac`), các thư viện cốt lõi của Java, và máy ảo Java (`JVM`) để chạy ứng dụng Spring Boot. |
| **Maven** | Công cụ quản lý dự án và build tự động: quản lý các thư viện phụ thuộc (dependencies qua file `pom.xml`), giải quyết xung đột phiên bản, tải tự động từ Maven Central, đóng gói ứng dụng thành file `.jar`/`.war`. |
| **IDE (STS4 / VS Code / IntelliJ)** | Môi trường phát triển tích hợp giúp viết code nhanh chóng: hỗ trợ gợi ý cú pháp (IntelliSense), kiểm tra lỗi thời gian thực, quản lý cấu trúc dự án, debug và chạy ứng dụng trực tiếp chỉ bằng một click. |

---

## BÀI 2: TẠO PROJECT SPRING BOOT MVC CHO HỆ THỐNG QUẢN LÝ SẢN PHẨM

### Bảng giải thích vai trò của các Dependency đã chọn

| Dependency | Tên trong Maven | Vai trò |
| :--- | :--- | :--- |
| **Spring Web** | `spring-boot-starter-webmvc` / `spring-boot-starter-web` | Cung cấp kiến trúc MVC (Model - View - Controller), nhúng máy chủ Tomcat Server, cấu hình sẵn bộ chuyển đổi dữ liệu JSON/XML và các annotation `@Controller`, `@GetMapping`, `@PostMapping` để xử lý HTTP Request. |
| **Thymeleaf** | `spring-boot-starter-thymeleaf` | Template Engine phía máy chủ (Server-side rendering), giúp trộn mã HTML tĩnh với dữ liệu động từ Controller qua cú pháp `th:*` để tạo ra trang HTML hoàn chỉnh trả về cho trình duyệt. |
| **Spring Boot DevTools** | `spring-boot-devtools` | Hỗ trợ lập trình nhanh: tự động khởi động lại ứng dụng (Live Reload) khi lưu file mã nguồn, tự động vô hiệu hóa bộ nhớ đệm (cache) của template để cập nhật giao diện ngay lập tức mà không cần build lại. |
| **Lombok** | `lombok` | Giảm thiểu mã lặp lại (boilerplate code) bằng cách tự động sinh Getter, Setter, Constructor, `toString()`, `equals()`, `hashCode()` trong lúc biên dịch thông qua các annotation như `@Getter`, `@Setter`, `@Data`. |

---

## BÀI 3: DỰNG GIAO DIỆN VÀ TRUYỀN GIÁ TRỊ TỪ CONTROLLER SANG VIEW

### 1. Cấu trúc Controller
Class `HomeController` nằm trong package `poly.edu.controller`:
- `@Controller`: Khai báo Bean tầng điều khiển (Controller) của Spring MVC.
- `@RequestMapping("/home")`: Định tuyến tiền tố URL cho toàn bộ controller.
- Ánh xạ các đường dẫn:
  - `/home/index` (và `/home`): Trả về `home/index` (`templates/home/index.html`)
  - `/home/about`: Trả về `home/about` (`templates/home/about.html`)
  - `/home/help`: Trả về `home/help` (`templates/home/help.html`)
  - `/`: Tự động chuyển hướng (Redirect) về `/home/index`.

### 2. Các thuộc tính truyền vào Model
- `appName` = `Product Portal`
- `subject` = `SOF3022 - Spring Boot MVC`
- `semester` = `Summer 2026`
- `message` = `Chào mừng bạn đến với hệ thống quản lý sản phẩm`

### 3. Cơ chế phối hợp Controller - Model - View trong bài `/home/index`
1. **Controller (`HomeController`):** Nhận request từ người dùng gửi đến URL `/home/index`. Controller khởi tạo dữ liệu nghiệp vụ và đưa vào đối tượng `Model` thông qua phương thức `model.addAttribute(tên, giá trị)`. Cuối cùng, Controller trả về tên logic của View là chuỗi `"home/index"`.
2. **Model:** Đóng vai trò là "chiếc cầu nối mang dữ liệu", chứa một Map các cặp key-value (`appName`, `subject`, `semester`, `message`) được vận chuyển từ Controller sang cho View.
3. **View (`templates/home/index.html`):** Template Thymeleaf nhận dữ liệu từ Model, đọc các biểu thức `${...}` (ví dụ `${appName}`) và thay thế vào nội dung HTML tương ứng để tạo ra trang kết quả cuối cùng gửi về trình duyệt.

---

## BÀI 4: QUAN SÁT HTML RESPONSE VÀ PHÂN TÍCH LUỒNG REQUEST/RESPONSE

### Trả lời câu hỏi:

#### 1. Trình duyệt nhận được file Thymeleaf gốc hay HTML đã render?
- **Trả lời:** Trình duyệt **nhận được mã HTML thuần túy đã được render hoàn chỉnh** bởi Thymeleaf engine tại phía máy chủ (Server-side rendering). Trình duyệt không nhận được file Thymeleaf gốc.

#### 2. Các biến như `${appName}`, `${subject}` còn xuất hiện trong HTML response không?
- **Trả lời:** **Không còn xuất hiện.** Trong quá trình xử lý trên máy chủ, Thymeleaf đã tìm tất cả các biểu thức `${...}` cùng các thuộc tính `th:*` và thay thế chúng bằng giá trị thực tế tương ứng (ví dụ: `Product Portal`, `SOF3022 - Spring Boot MVC`). Kết quả trả về cho client chỉ là các thẻ HTML tiêu chuẩn như `<h1>Product Portal</h1>`.

#### 3. Dữ liệu từ Controller được đưa vào View ở bước nào?
- **Trả lời:** Dữ liệu được đưa vào View ở bước **Thymeleaf View Resolution & Rendering**. Sau khi Controller trả về `View Name` và nạp dữ liệu vào `Model`, Spring MVC chuyển giao cả hai đối tượng này cho Thymeleaf View Resolver để tiến hành nạp file template từ thư mục `templates/` và điền dữ liệu từ Model vào template trước khi trả về HTTP Response.

#### 4. Sơ đồ mô tả luồng xử lý Request/Response của URL `/home/index`:
```
Browser (Trình duyệt)
  │
  │  1. Gửi HTTP GET /home/index
  ▼
Spring DispatcherServlet
  │
  │  2. Điều phối đến HomeController.index()
  ▼
HomeController.index()
  │
  │  3. Nạp dữ liệu vào Model:
  │     - appName = "Product Portal"
  │     - subject = "SOF3022 - Spring Boot MVC"
  │     - semester = "Summer 2026"
  │     - message = "Chào mừng bạn..."
  │     - Trả về view name: "home/index"
  ▼
Thymeleaf View Resolver & Engine
  │
  │  4. Tìm file templates/home/index.html
  │  5. Render HTML: thay thế các thẻ th:text="${...}" bằng giá trị thật từ Model
  ▼
Server Response
  │
  │  6. Trả mã HTML hoàn chỉnh qua luồng HTTP Response (Status 200 OK)
  ▼
Browser (Trình duyệt)
     7. Nhận mã HTML và CSS để hiển thị giao diện cho người dùng
```

#### 5. Phân biệt Template HTML và HTML Response trong Thymeleaf:
- **Template HTML (`src/main/resources/templates/home/index.html`):** Là file mẫu lưu trữ trên server, chứa các thẻ HTML kết hợp cú pháp riêng của Thymeleaf (ví dụ: `xmlns:th="http://www.thymeleaf.org"`, `th:text="${appName}"`, `th:href="@{/style.css}"`).
  - *Ví dụ trong file template:*
    ```html
    <h1 th:text="${appName}">Product Portal</h1>
    <li><b>Môn học:</b> <span th:text="${subject}">SOF3022 - Spring Boot MVC</span></li>
    ```
- **HTML Response:** Là đoạn mã HTML tĩnh mà máy chủ gửi qua giao thức HTTP đến trình duyệt của người dùng. Mọi cú pháp `th:*` và `${...}` đều đã biến mất, chỉ còn lại mã HTML chuẩn mà trình duyệt web có thể trực tiếp diễn giải và hiển thị.
  - *Ví dụ khi mở View Page Source trên trình duyệt:*
    ```html
    <h1>Product Portal</h1>
    <li><b>Môn học:</b> <span>SOF3022 - Spring Boot MVC</span></li>
    ```
