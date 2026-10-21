# SQA Hybrid Automation Framework (Java 21)

Repositori ini adalah kerangka kerja automasi pengujian hibrida (**UI & API**) berbasis **Java 21** untuk pengujian aplikasi web **HADIR** ([https://magang.dikahadir.com](https://magang.dikahadir.com)). Framework ini dibangun dengan prinsip modularitas, ketahanan terhadap pengujian *flaky*, pemisahan *concern* yang bersih antara API dan UI, serta pelaporan visual komprehensif menggunakan **Allure Report**.

---

## 🛠️ Tools & Teknologi

| Komponen | Teknologi | Versi | Peran dalam Proyek |
| :--- | :--- | :--- | :--- |
| **Bahasa Pemrograman** | Java | **21 (LTS)** | Bahasa utama framework |
| **Build & Dependency Tool** | Apache Maven | **3.9+** | Manajemen dependensi & runner build |
| **UI Automation** | Selenium WebDriver | **4.25.0** | Otomasi interaksi browser web |
| **API Automation** | REST Assured | **5.5.0** | Pengujian HTTP REST API |
| **BDD Framework** | Cucumber-JVM | **7.20.1** | Penulisan skenario berbasis Gherkin (`.feature`) |
| **Test Runner** | TestNG | **7.10.2** | Eksekusi test suite, assertions, & listeners |
| **Reporting Tool** | Allure Report | **2.29.0** | Visualisasi laporan pengujian interaktif & screenshot |
| **Data-Driven Testing** | Apache POI | **5.3.0** | Pembacaan data uji eksternal spreadsheet Excel (`.xlsx`) |
| **Data Serialization** | Jackson Databind | **2.17.2** | Parsing & manipulasi payload JSON |
| **Logging Framework** | Apache Log4j 2 | **2.23.1** | Pencatatan log terpusat (Console & Rolling File) |

---

## 📁 Struktur Direktori Proyek

```text
ujian-sqa-automation/
├── src/
│   ├── main/
│   │   ├── java/com/company/sqa/
│   │   │   ├── pages/                       # Pola Page Object Model (POM)
│   │   │   │   └── BasePage.java            # Wrapper eksplisit wait & safe click JavaScript
│   │   │   └── utils/                       # Utilitas reusable
│   │   │       ├── ConfigReader.java        # Pembaca properti lingkungan
│   │   │       ├── DriverManager.java       # ThreadLocal WebDriver lifecycle (Chrome/Firefox)
│   │   │       ├── ExcelUtil.java           # Reader Apache POI dengan auto-trim sanitization
│   │   │       └── RsaUtil.java             # Helper enkripsi password RSA-OAEP SHA-256
│   │   └── resources/
│   │       ├── config.properties            # Konfigurasi URL target, timeout, dan browser
│   │       └── log4j2.xml                   # Konfigurasi logging console & rolling file
│   └── test/
│       ├── java/com/company/sqa/
│       │   ├── api/
│       │   │   ├── BaseApiTest.java         # Base Request/Response specifications Rest-Assured
│       │   │   └── steps/                   # Step Definitions Cucumber untuk API
│       │   ├── ui/
│       │   │   ├── BaseWebTest.java         # Lifecycle TestNG untuk Web UI
│       │   │   ├── hooks/
│       │   │   │   └── UiHooks.java         # Cucumber hooks (@ui), screenshot capture, driver quit
│       │   │   └── steps/                   # Step Definitions Cucumber untuk UI
│       │   ├── listeners/                   # Listeners ketahanan & reporting
│       │   │   ├── RetryAnalyzer.java       # IRetryAnalyzer (auto re-run flaky test, maxTry=1)
│       │   │   ├── AnnotationTransformer.java # Menghubungkan RetryAnalyzer global ke semua method
│       │   │   └── ScreenshotListener.java  # ITestListener pelampir screenshot kegagalan ke Allure
│       │   └── runner/                      # Cucumber Test Runners
│       │       ├── TestRunnerApi.java       # Runner API khusus (tag: @api, glue: api.steps)
│       │       └── TestRunnerUi.java        # Runner UI khusus (tag: @ui, glue: ui.steps & hooks)
│       └── resources/
│           ├── features/
│           │   ├── api/                     # Skenario BDD Gherkin API (@api)
│           │   └── ui/                      # Skenario BDD Gherkin UI (@ui)
│           └── testdata/                    # Berkas data uji eksternal (.xlsx, .json)
├── testng.xml                               # Suite utama eksekusi TestNG (API & UI)
├── pom.xml                                  # Konfigurasi build Maven & dependensi library
├── .gitignore                               # Aturan pengabaian artefak target, logs, dan IDE
└── README.md                                # Dokumentasi utama repositori
```

---

## 🚀 Fitur Unggulan Framework

1. **Pemisahan Pengujian Bersih (Hybrid UI & API):**
   - Runner API ([TestRunnerApi.java](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/test/java/com/company/sqa/runner/TestRunnerApi.java)) hanya mengeksekusi skenario `@api` tanpa pernah membuka browser.
   - Runner UI ([TestRunnerUi.java](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/test/java/com/company/sqa/runner/TestRunnerUi.java)) mengeksekusi skenario `@ui` dengan kontrol lifecycle browser yang ketat.
2. **Pencegahan Kebocoran Browser (*No Zombie Processes*):**
   - File [UiHooks.java](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/test/java/com/company/sqa/ui/hooks/UiHooks.java) memastikan `driver.quit()` selalu dipanggil di blok `finally` setelah skenario selesai, mencegah proses browser menggantung di memory Linux/OS.
3. **Ketahanan Flaky Test (*Auto Retry*):**
   - [RetryAnalyzer.java](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/test/java/com/company/sqa/listeners/RetryAnalyzer.java) otomatis mengulang pengujian yang gagal sebanyak 1 kali untuk menyaring kegagalan semu akibat jeda jaringan.
4. **Bukti Pengujian Otomatis (*Allure Attachments*):**
   - Tangkapan layar (*screenshot*) otomatis diambil sesaat setelah ada langkah UI yang gagal dan langsung disematkan ke dalam laporan Allure.
5. **Thread-Safe Architecture:**
   - Penggunaan `ThreadLocal<WebDriver>` pada [DriverManager.java](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/main/java/com/company/sqa/utils/DriverManager.java) memastikan framework siap untuk eksekusi paralel multi-thread.
6. **Data Sanitization:**
   - [ExcelUtil.java](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/main/java/com/company/sqa/utils/ExcelUtil.java) otomatis menerapkan `.trim()` pada string sel data untuk menghindari *false negative* akibat spasi tak sengaja.

---

## ⚙️ Persyaratan Sistem (Prerequisites)

Sebelum menjalankan pengujian, pastikan lingkungan lokal telah memiliki:
- **Java Development Kit (JDK) 21**
  ```bash
  java -version
  ```
- **Apache Maven 3.9+**
  ```bash
  mvn -version
  ```
- **Google Chrome** (versi modern, compatible dengan Chrome for Testing)

---

## 💻 Panduan Menjalankan Pengujian

### 1. Kompilasi Proyek
Untuk memastikan semua dependensi terunduh dan kode bebas error kompilasi:
```bash
mvn test-compile
```

### 2. Menjalankan Seluruh Pengujian (API & UI)
Eksekusi test suite standar menggunakan file [testng.xml](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/testng.xml):
```bash
mvn clean test
```

### 3. Menjalankan Berdasarkan Tag Tertentu (Cucumber Tags)
- **Hanya API:**
  ```bash
  mvn clean test -Dcucumber.filter.tags="@api"
  ```
- **Hanya UI:**
  ```bash
  mvn clean test -Dcucumber.filter.tags="@ui"
  ```
- **Hanya Fitur Tertentu (misal: Login):**
  ```bash
  mvn clean test -Dcucumber.filter.tags="@login"
  ```

### 4. Menjalankan Mode Headless / Tampil Layar Browser
Ubah parameter melalui argumen baris perintah:
- **Mode Tampil Layar (Headless False):**
  ```bash
  mvn clean test -Dheadless=false
  ```
- **Ganti Browser (misal: Firefox):**
  ```bash
  mvn clean test -Dbrowser=firefox
  ```

---

## 📊 Melihat Laporan Pengujian (Allure Report)

Setelah eksekusi tes selesai, data hasil uji disimpan di folder `target/allure-results`. Untuk membuka dashboard interaktif di browser:

```bash
mvn allure:serve
```

Perintah ini akan membuka server lokal otomatis dengan grafik metrik eksekusi, timeline, langkah-langkah Gherkin, dan lampiran screenshot kegagalan.

---

## 🔧 Pengaturan Konfigurasi (`config.properties`)

Berkas [src/main/resources/config.properties](file:///home/irwan/Dokumen/Pemrograman/sqa-automation-01/ujian-sqa-automation/src/main/resources/config.properties) menjadi sumber kebenaran tunggal (*Single Source of Truth*) pengaturan runtime:

```properties
# Target Application URL
ui.url=https://magang.dikahadir.com
api.base.url=https://magang.dikahadir.com

# Browser & UI Settings
browser=chrome
headless=true
timeout.implicit=10
timeout.explicit=15
page.load.timeout=30

# API Settings
api.timeout=10000
```

