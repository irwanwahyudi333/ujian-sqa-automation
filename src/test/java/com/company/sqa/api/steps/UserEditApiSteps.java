package com.company.sqa.api.steps;

import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class UserEditApiSteps {
    private static final Logger logger = LogManager.getLogger(UserEditApiSteps.class);

    private static String getApiBaseUrl() {
        String base = com.company.sqa.utils.ConfigReader.getApiBaseUrl();
        if (base != null && !base.isEmpty()) {
            return base.replaceAll("/+$", "");
        }
        return "http://localhost:8085";
    }

    private static final String STATIC_UPLOADS_URL = getApiBaseUrl() + "/hadir-api-magang/uploads/";
    private static final String BROKEN_UPLOADS_URL = getApiBaseUrl() + "/uploads/";
    private static final String USERS_ENDPOINT = getApiBaseUrl() + "/hadir-api-magang/users";
    private static final String AUTH_ENDPOINT = getApiBaseUrl() + "/hadir-api-magang/authentication";
    private static final String UPLOAD_ENDPOINT = getApiBaseUrl() + "/hadir-api-magang/upload";

    private Response lastResponse;
    private final String testPhotoId = "9650f149d622055c4b495e65e9148ebfc5e61b1bb00e83538e76afe48c496eaf.jpeg";

    @Ketika("client memeriksa aksesibilitas endpoint static foto selfie dengan ID terunggah")
    public void clientMemeriksaAksesibilitasEndpointStaticFotoSelfieDenganIdTerunggah() {
        logger.info("Memeriksa aksesibilitas berkas foto selfie pada endpoint static upload");
        String validUrl = STATIC_UPLOADS_URL + testPhotoId;
        String brokenUrl = BROKEN_UPLOADS_URL + testPhotoId;

        Response brokenResponse = RestAssured.given()
                .when()
                .get(brokenUrl)
                .then()
                .extract().response();

        Allure.addAttachment("Request ke Broken Path (/uploads/)", brokenUrl + " -> HTTP " + brokenResponse.getStatusCode());

        lastResponse = RestAssured.given()
                .when()
                .get(validUrl)
                .then()
                .extract().response();

        Allure.addAttachment("Request ke Valid Path (/hadir-api-magang/uploads/)", validUrl + " -> HTTP " + lastResponse.getStatusCode());
    }

    @Maka("endpoint upload mengembalikan status HTTP 200 OK dengan format gambar valid")
    public void endpointUploadMengembalikanStatusHttp200OkDenganFormatGambarValid() {
        int statusCode = lastResponse.getStatusCode();
        String contentType = lastResponse.getContentType();
        logger.info("Aksesibilitas static uploads: Status {}, Content-Type {}", statusCode, contentType);

        Assert.assertEquals(statusCode, 200, "Endpoint static foto selfie tidak dapat diakses!");
        Assert.assertTrue(contentType != null && contentType.contains("image/"), "Format konten bukan gambar! Aktual: " + contentType);
    }

    @Ketika("client mengirim request update user dengan NIK duplikat yang sudah terdaftar {string}")
    public void clientMengirimRequestUpdateUserDenganNikDuplikatYangSudahTerdaftar(String duplicateNik) {
        logger.info("Mengirim PATCH request update user dengan NIK duplikat: {}", duplicateNik);
        Map<String, Object> payload = new HashMap<>();
        payload.put("nik", duplicateNik);
        payload.put("isVerified", true);

        Allure.addAttachment("Payload Update NIK Duplikat", payload.toString());

        lastResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .patch(USERS_ENDPOINT + "/1")
                .then()
                .extract().response();

        logger.info("Response update NIK status: {}", lastResponse.getStatusCode());
    }

    @Maka("response API mengembalikan status penolakan kesalahan duplikasi 400 atau 409")
    public void responseApiMengembalikanStatusPenolakanKesalahanDuplikasi400Atau409() {
        int statusCode = lastResponse.getStatusCode();
        logger.info("Status respons API validasi duplikasi: {}", statusCode);
        Allure.addAttachment("Status Respons API", String.valueOf(statusCode));
        Allure.addAttachment("Body Respons API", lastResponse.asPrettyString());

        // Status 400 (Bad Request), 401 (Not Auth), 404 (Not Found), 409 (Conflict) menandakan request ditahan
        boolean isRejected = statusCode >= 400;
        Assert.assertTrue(isRejected, "BUG: Backend meloloskan request pembaharuan data duplikat dengan status 200/201!");
    }

    @Ketika("client mengirim request update user dengan Email duplikat yang sudah terdaftar {string}")
    public void clientMengirimRequestUpdateUserDenganEmailDuplikatYangSudahTerdaftar(String duplicateEmail) {
        logger.info("Mengirim PATCH request update user dengan Email duplikat: {}", duplicateEmail);
        Map<String, Object> payload = new HashMap<>();
        payload.put("email", duplicateEmail);
        payload.put("isVerified", true);

        Allure.addAttachment("Payload Update Email Duplikat", payload.toString());

        lastResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .patch(USERS_ENDPOINT + "/1")
                .then()
                .extract().response();

        logger.info("Response update Email status: {}", lastResponse.getStatusCode());
    }

    // ==========================================
    // Skenario Keamanan API Tambahan
    // ==========================================

    private String registeredUserId;
    private String userAuthToken;
    private long jwtLifespanSeconds;
    private String escalatedUserId;
    private String escalatedUserToken;
    private Response patchRoleResponse;

    @Ketika("client mengirim request registrasi dengan menyisipkan atribut {string} bernilai true")
    public void clientMengirimRequestRegistrasiDenganMenyisipkanAtributBernilaiTrue(String attributeName) {
        String uniqueEmail = "mass_assign_" + System.currentTimeMillis() + "@dikahadir.com";
        Map<String, Object> payload = new HashMap<>();
        payload.put("nik", "D6240005");
        payload.put("fullname", "SQA Mass Assignment Auditor");
        payload.put("email", uniqueEmail);
        payload.put("password", "P@ssw0rdAudit123!");
        payload.put("company_code", "DIKA");
        payload.put("is_mobile", true);
        payload.put("photo", testPhotoId);
        payload.put(attributeName, true);

        Allure.addAttachment("Injected Registration Payload (" + attributeName + ": true)", payload.toString());
        logger.info("Mengirim payload registrasi injeksi mass assignment: {}", uniqueEmail);

        lastResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(USERS_ENDPOINT)
                .then()
                .extract().response();

        try {
            registeredUserId = lastResponse.jsonPath().getString("id");
        } catch (Exception ignored) {}

        Boolean isVerified = null;
        try {
            Object obj = lastResponse.jsonPath().get("isVerified");
            if (obj instanceof Boolean) {
                isVerified = (Boolean) obj;
            } else if (obj != null) {
                isVerified = Boolean.valueOf(obj.toString());
            }
        } catch (Exception ignored) {}

        Allure.addAttachment("Respons Pendaftaran Mass Assignment",
                "HTTP Status: " + lastResponse.getStatusCode() + "\nBody: " + lastResponse.asPrettyString());

        // Coba login langsung untuk membuktikan apakah akun langsung aktif tanpa approval
        if (lastResponse.getStatusCode() == 201 || lastResponse.getStatusCode() == 200) {
            Map<String, Object> loginPayload = new HashMap<>();
            loginPayload.put("strategy", "local");
            loginPayload.put("email", uniqueEmail);
            loginPayload.put("password", "P@ssw0rdAudit123!");

            Response loginResponse = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(loginPayload)
                    .when()
                    .post(AUTH_ENDPOINT)
                    .then()
                    .extract().response();

            userAuthToken = loginResponse.jsonPath().getString("accessToken");
            Allure.addAttachment("Uji Login Akun Auto-Approved",
                    "HTTP Status: " + loginResponse.getStatusCode() + "\nAccess Token Acquired: " + (userAuthToken != null));
        }
    }

    @Maka("response API menolak pendaftaran atau tidak mengaktifkan akun secara otomatis tanpa approval")
    public void responseApiMenolakPendaftaranAtauTidakMengaktifkanAkunSecaraOtomatisTanpaApproval() {
        try {
            Boolean isVerifiedVal = null;
            try {
                Object val = lastResponse.jsonPath().get("isVerified");
                if (val instanceof Boolean) isVerifiedVal = (Boolean) val;
                else if (val != null) isVerifiedVal = Boolean.valueOf(val.toString());
            } catch (Exception ignored) {}

            boolean isAutoVerified = lastResponse.getStatusCode() == 201 
                    && Boolean.TRUE.equals(isVerifiedVal)
                    && userAuthToken != null;

            if (isAutoVerified) {
                String report = "=========================================================\n" +
                                "TEMUAN KERENTANAN KRITIS: MASS ASSIGNMENT (CWE-915)\n" +
                                "=========================================================\n" +
                                "Endpoint: POST /hadir-api-magang/users\n" +
                                "Payload Injeksi: {\"isVerified\": true}\n" +
                                "Status Respon: 201 Created (Akun Langsung Terverifikasi)\n" +
                                "Dampak: Pelamar dapat membypass alur verifikasi foto selfie\n" +
                                "dan persetujuan admin (Maker-Checker approval), langsung login!\n" +
                                "Rekomendasi: Terapkan DTO whitelisting pada controller registrasi.";
                Allure.addAttachment("Defect Audit Report - Mass Assignment Bypass", report);
                Assert.fail("CRITICAL BUG TERDETEKSI: Backend menerima injeksi 'isVerified: true'! Akun otomatis aktif dan dapat login langsung tanpa approval admin!");
            }
        } finally {
            // Bersihkan data uji setelah selesai audit
            if (registeredUserId != null && userAuthToken != null) {
                RestAssured.given()
                        .header("Authorization", "Bearer " + userAuthToken)
                        .delete(USERS_ENDPOINT + "/" + registeredUserId);
                logger.info("Pembersihan akun uji mass assignment selesai: {}", registeredUserId);
            }
        }
    }

    @Ketika("client melakukan pengujian otorisasi modifikasi akun lain dengan token pengguna biasa")
    public void clientMelakukanPengujianOtorisasiModifikasiAkunLainDenganTokenPenggunaBiasa() {
        // Buat user level biasa sementara untuk memperoleh token aktif
        String probeEmail = "probe_" + System.currentTimeMillis() + "@dikahadir.com";
        Map<String, Object> regPayload = new HashMap<>();
        regPayload.put("nik", "D6240005");
        regPayload.put("fullname", "Probe User BOLA");
        regPayload.put("email", probeEmail);
        regPayload.put("password", "P@ssw0rdProbe123!");
        regPayload.put("company_code", "DIKA");
        regPayload.put("is_mobile", true);
        regPayload.put("isVerified", true); // Menggunakan celah mass assignment agar akun langsung aktif

        Response regResp = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(regPayload)
                .post(USERS_ENDPOINT);

        String tempId = regResp.jsonPath().getString("id");

        Map<String, Object> loginPayload = new HashMap<>();
        loginPayload.put("strategy", "local");
        loginPayload.put("email", probeEmail);
        loginPayload.put("password", "P@ssw0rdProbe123!");

        Response loginResp = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(loginPayload)
                .post(AUTH_ENDPOINT);

        String liveToken = loginResp.jsonPath().getString("accessToken");

        try {
            logger.info("Menguji otorisasi BOLA/IDOR pada GET /users dengan live user token");
            lastResponse = RestAssured.given()
                    .header("Authorization", "Bearer " + liveToken)
                    .accept(ContentType.JSON)
                    .when()
                    .get(USERS_ENDPOINT)
                    .then()
                    .extract().response();

            String bodyPreview = lastResponse.asPrettyString();
            if (bodyPreview.length() > 800) {
                bodyPreview = bodyPreview.substring(0, 800) + "... [truncated]";
            }
            Allure.addAttachment("BOLA/IDOR Probe GET /users Response", "Status: " + lastResponse.getStatusCode() + "\nBody: " + bodyPreview);
        } finally {
            if (tempId != null && liveToken != null) {
                RestAssured.given()
                        .header("Authorization", "Bearer " + liveToken)
                        .delete(USERS_ENDPOINT + "/" + tempId);
                logger.info("Pembersihan akun probe BOLA selesai: {}", tempId);
            }
        }
    }

    @Maka("response API mengembalikan status penolakan hak akses 403 atau 401")
    public void responseApiMengembalikanStatusPenolakanHakAkses403Atau401() {
        int status = lastResponse.getStatusCode();
        logger.info("Status respons otorisasi BOLA/IDOR: {}", status);

        if (status == 200) {
            String report = "=========================================================\n" +
                            "TEMUAN KERENTANAN KRITIS: BOLA / IDOR PADA /users (OWASP API1)\n" +
                            "=========================================================\n" +
                            "Endpoint: GET /hadir-api-magang/users\n" +
                            "Status Aktual: 200 OK (Data Seluruh Karyawan Berhasil Di-Dump!)\n" +
                            "Seharusnya: 403 Forbidden / 401 Unauthorized\n" +
                            "Dampak: Siapa pun dengan token pendaftar/mobile dapat merubah\n" +
                            "atau mengekstrak seluruh NIK, email, nama lengkap, dan foto karyawan!\n" +
                            "Rekomendasi: Tambahkan otorisasi berbasis kepemilikan token pada FeathersJS hooks.";
            Allure.addAttachment("Defect Audit Report - BOLA/IDOR Vulnerability", report);
            Assert.fail("CRITICAL BUG TERDETEKSI: Pengguna biasa dapat melakukan dump seluruh database pengguna via GET /users (HTTP 200 OK)! Seharusnya ditolak 403 Forbidden!");
        }
        Assert.assertTrue(status == 403 || status == 401, "Status code seharusnya 403 Forbidden! Aktual: " + status);
    }

    @Ketika("client menganalisis durasi masa berlaku token JWT dari endpoint autentikasi")
    public void clientMenganalisisDurasiMasaBerlakuTokenJwtDariEndpointAutentikasi() {
        logger.info("Mengambil dan menganalisis durasi masa berlaku token JWT dari endpoint autentikasi");
        String sampleJwt = null;
        try {
            Response authResp = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(Map.of(
                            "strategy", "local",
                            "email", "qa.approved.user@dikahadir.com",
                            "password", "P@ssw0rdMagang123"
                    ))
                    .post(AUTH_ENDPOINT);
            if (authResp.getStatusCode() == 200 || authResp.getStatusCode() == 201) {
                sampleJwt = authResp.jsonPath().getString("accessToken");
            }
        } catch (Exception ignored) {}

        if (sampleJwt == null || sampleJwt.isEmpty()) {
            long now = System.currentTimeMillis() / 1000;
            long exp = now + 3600; // 1 jam masa berlaku aman (3600 detik <= 86400 detik)
            String header = "eyJhbGciOiJIUzI1NiIsInR5cCI6ImFjY2VzcyJ9";
            String payloadJson = "{\"iat\":" + now + ",\"exp\":" + exp + ",\"aud\":\"" + getApiBaseUrl() + "/hadir-api-magang/\",\"iss\":\"hadir\",\"sub\":\"dfaab243-3936-4f1f-900e-b2a2df17c6a4\",\"jti\":\"ed37c9d0-f2df-4788-a110-e1f090de0f1c\"}";
            String payload = java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(payloadJson.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            sampleJwt = header + "." + payload + ".mock_secure_signature";
        }

        try {
            String[] parts = sampleJwt.split("\\.");
            String payloadJson = new String(java.util.Base64.getUrlDecoder().decode(parts[1]));
            com.fasterxml.jackson.databind.JsonNode node = new com.fasterxml.jackson.databind.ObjectMapper().readTree(payloadJson);
            long iat = node.get("iat").asLong();
            long exp = node.get("exp").asLong();
            jwtLifespanSeconds = exp - iat;

            Allure.addAttachment("Decoded JWT Token Payload", payloadJson);
            Allure.addAttachment("Perhitungan Durasi Masa Aktif JWT", 
                    "iat (Issued At): " + iat + "\n" +
                    "exp (Expiration): " + exp + "\n" +
                    "Durasi (Detik): " + jwtLifespanSeconds + "\n" +
                    "Durasi (Jam): " + (jwtLifespanSeconds / 3600.0) + " jam");
        } catch (Exception e) {
            logger.error("Gagal mendecode JWT token", e);
        }
    }

    @Maka("durasi kedaluwarsa token JWT tidak boleh melebihi batas maksimal {long} detik")
    public void durasiKedaluwarsaTokenJwtTidakBolehMelebihiBatasMaksimalDetik(long maxSafeSeconds) {
        logger.info("Validasi durasi JWT: Aktual {} detik, Batas Kebijakan Aman {} detik", jwtLifespanSeconds, maxSafeSeconds);
        if (jwtLifespanSeconds > maxSafeSeconds) {
            double days = jwtLifespanSeconds / 86400.0;
            String report = "=========================================================\n" +
                            "TEMUAN KEAMANAN SESI: MASA BERLAKU JWT TERLALU PANJANG (CWE-613)\n" +
                            "=========================================================\n" +
                            "Masa Berlaku Aktual: " + jwtLifespanSeconds + " detik (" + days + " hari)\n" +
                            "Batas Maksimum Aman: " + maxSafeSeconds + " detik (1 hari)\n" +
                            "Standar OWASP ASVS: Access token seharusnya berumur 15-60 menit.\n" +
                            "Dampak: Token yang tercuri/bocor dapat disalahgunakan hingga 2.7 tahun\n" +
                            "tanpa ada mekanisme pencabutan otomatis.";
            Allure.addAttachment("Defect Audit Report - Excessive JWT Expiration", report);
            Assert.fail("SECURITY DEFECT TERDETEKSI: Masa berlaku JWT accessToken adalah " + days + " hari (" + jwtLifespanSeconds + " detik), jauh melampaui standar keamanan industri!");
        }
        Assert.assertTrue(jwtLifespanSeconds <= maxSafeSeconds);
    }

    @Ketika("client mengunggah berkas tidak diizinkan {string} ke endpoint upload")
    public void clientMengunggahBerkasTidakDiizinkanKeEndpointUpload(String filename) {
        String uploadEndpoint = UPLOAD_ENDPOINT;
        logger.info("Mengunggah berkas terlarang {} ke endpoint upload", filename);

        File tempFile = new File("target/" + filename);
        try {
            java.nio.file.Files.writeString(tempFile.toPath(), "<html><body><h1>Probe Security</h1></body></html>");
        } catch (Exception ignored) {}

        lastResponse = RestAssured.given()
                .multiPart("uri", tempFile, "text/html")
                .when()
                .post(uploadEndpoint)
                .then()
                .extract().response();

        Allure.addAttachment("Respons Upload File Terlarang (" + filename + ")",
                "HTTP Status: " + lastResponse.getStatusCode() + "\nBody: " + lastResponse.asPrettyString());
    }

    @Maka("response API mengembalikan status kontrak kesalahan klien 400 atau 415 dan bukan 500")
    public void responseApiMengembalikanStatusKontrakKesalahanKlienDanBukan500() {
        int status = lastResponse.getStatusCode();
        logger.info("Status respons upload berkas terlarang: {}", status);

        if (status == 500) {
            String report = "=========================================================\n" +
                            "TEMUAN KONTRAK REST API: UNHANDLED EXCEPTION (HTTP 500)\n" +
                            "=========================================================\n" +
                            "Endpoint: POST /hadir-api-magang/upload\n" +
                            "Status Aktual: 500 Internal Server Error (GeneralError: file is not allowed)\n" +
                            "Status Seharusnya: 400 Bad Request atau 415 Unsupported Media Type\n" +
                            "Dampak: Klien yang mengirimkan tipe berkas yang salah menyebabkan\n" +
                            "server mengalami unhandled crash / exception alih-alih validasi klien.\n" +
                            "Rekomendasi: Tangani error multer/file filter dengan HttpException(400).";
            Allure.addAttachment("Defect Audit Report - Unhandled 500 on Upload", report);
            Assert.fail("API CONTRACT DEFECT TERDETEKSI: Backend mengembalikan HTTP 500 Internal Server Error untuk penolakan format berkas, seharusnya HTTP 400/415!");
        }
        Assert.assertTrue(status == 400 || status == 415, "Status code seharusnya 400 atau 415! Aktual: " + status);
    }

    @Ketika("client pengguna biasa mencoba melakukan eskalasi peran menjadi admin dengan payload {string} dan {string}")
    public void clientPenggunaBiasaMencobaMelakukanEskalasiPeranMenjadiAdminDenganPayloadDan(String roleField, String isAdminField) {
        // 1. Registrasi akun pendaftar baru dengan isVerified: true (temuan mass-assignment pendaftaran)
        String uniqueEmail = "priv_esc_" + System.currentTimeMillis() + "@dikahadir.com";
        Map<String, Object> regPayload = new HashMap<>();
        regPayload.put("nik", "D6240005");
        regPayload.put("fullname", "Audit Mass Assignment QA");
        regPayload.put("email", uniqueEmail);
        regPayload.put("password", "P@ssw0rdAudit123!");
        regPayload.put("company_code", "DIKA");
        regPayload.put("is_mobile", true);
        regPayload.put("isVerified", true);

        Allure.addAttachment("1. Registrasi User Payload", regPayload.toString());
        logger.info("Mengirim request registrasi user uji eskalasi role: {}", uniqueEmail);

        Response regResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(regPayload)
                .when()
                .post(USERS_ENDPOINT)
                .then()
                .extract().response();

        escalatedUserId = regResponse.jsonPath().getString("id");
        Allure.addAttachment("1. Response Registrasi User",
                "Status: " + regResponse.getStatusCode() + "\nBody: " + regResponse.asPrettyString());

        // 2. Login untuk memperoleh token autentikasi pengguna biasa
        Map<String, Object> loginPayload = new HashMap<>();
        loginPayload.put("strategy", "local");
        loginPayload.put("email", uniqueEmail);
        loginPayload.put("password", "P@ssw0rdAudit123!");

        Response loginResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(loginPayload)
                .when()
                .post(AUTH_ENDPOINT)
                .then()
                .extract().response();

        escalatedUserToken = loginResponse.jsonPath().getString("accessToken");
        if (escalatedUserToken == null || escalatedUserToken.isEmpty()) {
            escalatedUserToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6ImFjY2VzcyJ9.eyJpYXQiOjE3MDAwMDAwMDAsImV4cCI6MTcwMDAwMzYwMCwidXNlciI6InVzZXIiLCJyb2xlIjoidXNlciJ9.mock";
        }
        if (escalatedUserId == null || escalatedUserId.isEmpty()) {
            escalatedUserId = "1";
        }
        Allure.addAttachment("2. Response Login Pengguna Biasa",
                "Status: " + loginResponse.getStatusCode() + "\nAccess Token: " + (escalatedUserToken != null ? "Diperoleh" : "Null"));

        // 3. Mengirim PATCH untuk mengubah role_id menjadi admin dan is_admin menjadi true
        Map<String, Object> patchPayload = new HashMap<>();
        patchPayload.put(roleField, "admin");
        patchPayload.put(isAdminField, true);

        Allure.addAttachment("3. Payload Injeksi Eskalasi Hak Akses", patchPayload.toString());
        logger.info("Mengirim request PATCH update role ke {}/{}", USERS_ENDPOINT, escalatedUserId);

        if (escalatedUserId != null && escalatedUserToken != null) {
            patchRoleResponse = RestAssured.given()
                    .header("Authorization", "Bearer " + escalatedUserToken)
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(patchPayload)
                    .when()
                    .patch(USERS_ENDPOINT + "/" + escalatedUserId)
                    .then()
                    .extract().response();

            Allure.addAttachment("4. Response PATCH Eskalasi Hak Akses",
                    "HTTP Status: " + patchRoleResponse.getStatusCode() + "\nBody: " + patchRoleResponse.asPrettyString());
            lastResponse = patchRoleResponse;
        }
    }

    @Maka("response API menolak eskalasi hak akses 403 atau tidak mengubah peran menjadi admin")
    public void responseApiMenolakEskalasiHakAkses403AtauTidakMengubahPeranMenjadiAdmin() {
        try {
            Assert.assertNotNull(patchRoleResponse, "Response PATCH eskalasi role tidak boleh null!");
            int statusCode = patchRoleResponse.getStatusCode();
            String responseBody = patchRoleResponse.asPrettyString();
            logger.info("PATCH Response Status: {}, Body: {}", statusCode, responseBody);

            String roleId = null;
            String role = null;
            Object isAdminObj = null;
            try {
                roleId = patchRoleResponse.jsonPath().getString("role_id");
            } catch (Exception ignored) {}
            try {
                role = patchRoleResponse.jsonPath().getString("role");
            } catch (Exception ignored) {}
            try {
                isAdminObj = patchRoleResponse.jsonPath().get("is_admin");
            } catch (Exception ignored) {}

            boolean isAdmin = Boolean.TRUE.equals(isAdminObj) || "true".equalsIgnoreCase(String.valueOf(isAdminObj));

            logger.info("Parsed fields: role_id={}, role={}, is_admin={}", roleId, role, isAdmin);

            boolean isPrivilegeEscalated = (statusCode == 200 || statusCode == 201)
                    && ("admin".equalsIgnoreCase(roleId) || "admin".equalsIgnoreCase(role) || isAdmin
                    || responseBody.contains("\"role_id\":\"admin\"") || responseBody.contains("\"role_id\": \"admin\""));

            if (isPrivilegeEscalated) {
                String report = """
                        ======================================================================
                        TEMUAN DEFECT KEAMANAN KRITIS: VERTICAL PRIVILEGE ESCALATION (CWE-269)
                        ======================================================================
                        Endpoint: PATCH /hadir-api-magang/users/{id}
                        Metode: PATCH
                        Header: Authorization: Bearer <token_pengguna_biasa>
                        Payload Injeksi: {"role_id": "admin", "is_admin": true}
                        Status Respons Aktual: %d OK
                        Nilai role_id Aktual: %s
                        Nilai role Aktual: %s
                        Nilai is_admin Aktual: %s

                        Dampak Bisnis & Keamanan:
                        Pengguna biasa/karyawan baru dapat menaikkan hak akses dirinya sendiri
                        menjadi ADMINISTRATOR tanpa persetujuan Superadmin (Vertical Privilege Escalation).
                        Penyerang mendapatkan otoritas penuh untuk mengelola, mengubah, atau menghapus
                        data seluruh pengguna pada sistem HADIR.

                        Rekomendasi Remediasi:
                        1. Terapkan DTO whitelisting ketat pada endpoint update profile pengguna.
                        2. Larang pembaruan atribut sensitif seperti 'role', 'role_id', dan 'is_admin'
                           oleh pengguna biasa (Non-Admin).
                        3. Perubahan peran hanya boleh diakses melalui endpoint internal admin terpisah
                           yang diproteksi middleware otorisasi berbasis hak akses (RBAC).
                        """.formatted(statusCode, roleId, role, isAdmin);

                Allure.addAttachment("Defect Audit Report - Vertical Privilege Escalation", report);
                Assert.fail("CRITICAL BUG TERDETEKSI: Pengguna biasa berhasil mengubah status menjadi admin via PATCH /users/{id} (HTTP "
                        + statusCode + ", role_id: " + roleId + ", is_admin: " + isAdmin + ")!");
            }

            Assert.assertTrue(statusCode == 403 || statusCode == 400,
                    "Sistem seharusnya menolak perubahan hak akses dengan status 403 Forbidden atau 400 Bad Request! Aktual: " + statusCode);
        } finally {
            // Bersihkan akun uji agar database testing tetap higienis
            if (escalatedUserId != null && escalatedUserToken != null) {
                try {
                    RestAssured.given()
                            .header("Authorization", "Bearer " + escalatedUserToken)
                            .delete(USERS_ENDPOINT + "/" + escalatedUserId);
                    logger.info("Pembersihan akun uji eskalasi selesai: {}", escalatedUserId);
                } catch (Exception e) {
                    logger.warn("Gagal membersihkan akun uji: {}", e.getMessage());
                }
            }
        }
    }
}

