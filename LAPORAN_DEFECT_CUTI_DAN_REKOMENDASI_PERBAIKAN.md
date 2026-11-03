# Laporan Temuan Cacat Pengujian (Defect Report) & Rekomendasi Perbaikan Sistem — Modul Cuti (Leave)

**Proyek:** SQA Automation Test — Aplikasi HADIR  
**Modul:** Fitur Pengelolaan, Permohonan, & Validasi Cuti (*Leave Management*)  
**Target Pengujian:** `https://magang.dikahadir.com` (Live) & Local Mirror (`http://localhost:8085`)  
**Tanggal Pengujian:** 03 November 2026  
**Total Skenario Dijalankan:** 39 Skenario Unik (34 Skenario UI Selenium, 5 Skenario REST API)  
**Hasil:** 37 Passed (94.9%), 2 Failed/Defect (UI State Management & Live Target Defect)  
**Tingkat Kritis:** 🔴 **Tinggi (Business Workflow, Boundary Limits & UI/UX State Defects)**

---

## 1. Ringkasan Eksekutif

Berdasarkan eksekusi pengujian hybrid otomatis (*Hybrid UI Selenium WebDriver & REST Assured API Testing*) pada modul Cuti, pengujian telah memvalidasi seluruh siklus hidup pengelolaan cuti: mulai dari permohonan mandiri oleh karyawan di portal mobile client (`/apps/absent/leave-client`), konfigurasi master aturan unit cuti (`/management/unit-leave`), peninjauan antrean oleh Team Leader (`/leader/cuti/*`), validasi bertingkat oleh Client Upliner V2 & V3 (`/client/cuti/*`), audit monitoring (`/monitoring/cuti`), hingga pelaporan dan sinkronisasi saldo kuota cuti di level Administrator HR (`/laporan/cuti` dan `/laporan/sisa-cuti`).

Dari **39 skenario pengujian unik** yang dieksekusi:
- **37 Skenario (94.9%) Berhasil Lolos (*Passed*):** Mencakup kontrak API REST Assured (GET, POST, boundary validation kuota negatif, proteksi RBAC 401), pencarian kata kunci, filter tanggal presisi, penolakan input tanggal terbalik, kontrol paginasi tabel, drawer modal permohonan cuti, hingga ekspor berkas Excel.
- **2 Skenario Menangkap Cacat / Defect:**
  1. **Cacat Kritis Live Target (*Live Defect - DEFECT-CUTI-001*):** Ditemukan pada halaman Laporan Cuti Admin (`05_laporan_cuti.feature`), di mana modal dialog aksi "Ubah atasan user yang request" kehilangan elemen dropdown atasan untuk memproses pengalihan wewenang approval.
  2. **Cacat State Management Modal (*DEFECT-CUTI-002*):** Ditemukan pada Portal Client Validator Cuti (`ClientCuti.feature`), di mana kontainer modal aksi validasi tidak menutup secara tuntas (*lingering overlay*) pasca-submit persetujuan.

---

## 2. Matriks Temuan Cacat (Defect Matrix - Modul Cuti)

| ID Defect | Modul / Halaman / Endpoint | Kategori Masalah | Severity | Status Aktual | Status Harapan (Standar SQA) |
|---|---|---|:---:|---|---|
| **DEFECT-CUTI-001** | UI `/laporan/cuti` | Missing Form Controls & Actions | 🔴 **Major** | Modal "Ubah atasan user yang request" tidak memuat dropdown pilihan atasan baru | Modal memuat dropdown data atasan aktif dan tombol konfirmasi update |
| **DEFECT-CUTI-002** | UI `/client/cuti/validator` (Tahap 1 & 2) | UI State & Modal Dismissal | 🟡 **Medium** | Modal `#modal-action-validator` tetap terdeteksi aktif pada DOM setelah validator menyetujui klaim cuti | Modal wajib tertutup otomatis (`display: none` / unmount) setelah aksi submit berhasil |
| **DEFECT-CUTI-003** | UI `/laporan/sisa-cuti` | Double Submit & Missing Debounce | 🟡 **Medium** | Tombol "Sync Kuota Cuti" tidak memiliki debounce atau loading indicator saat proses kalkulasi massal | Tombol otomatis *disabled* dengan spinner pemrosesan hingga respons server selesai |
| **SEC-CUTI-001** | API `/api/leader/leave/1/approval` & Client Portal | Multi-Level Authorization (RBAC / BOLA) | 🔴 **Major** | Endpoint mutasi status cuti berpotensi dapat ditembak langsung tanpa melewati prasyarat approval Leader | Backend wajib memvalidasi hierarki status cuti (`PENDING_LEADER` $\rightarrow$ `PENDING_V1` $\rightarrow$ `APPROVED`) |

---

## 3. Rincian Temuan & Rekomendasi Perbaikan

### 1. DEFECT-CUTI-001: Modal Dialog Ubah Atasan Cuti Kehilangan Kontrol Dropdown Pilihan
- **Kode Skenario:** `@BUG-CUTI-001 @DEFECT-CUTI-001 @live` pada `05_laporan_cuti.feature`
- **Lokasi Halaman:** Antarmuka Laporan Cuti Administrator (`/laporan/cuti`)
- **Deskripsi Masalah:**
  Pada tabel laporan cuti admin, ketika admin membuka menu aksi baris pertama dan memilih aksi *"Edit"* (*Ubah Data*), sistem menampilkan kontainer dialog dengan judul *"Ubah atasan user yang request"*. Namun, di dalam modal dialog tersebut tidak ditemukan elemen input form, autocomplete, maupun dropdown seleksi atasan baru. Tombol submit *"Ubah Data"* tidak dapat memproses apapun karena ketiadaan ID atasan baru.
- **Dampak Bisnis & Operasional:**
  Admin HR tidak dapat mengalihkan wewenang persetujuan cuti jika atasan langsung karyawan sedang mengambil cuti panjang atau berhalangan hadir. Akibatnya, permohonan hak cuti tahunan karyawan tertahan tanpa ada pejabat berwenang yang dapat memprosesnya.
- **Error Stack Trace:**
  ```text
  org.openqa.selenium.TimeoutException: 
  Expected condition failed: waiting for visibility of element located by By.xpath: //div[@role='dialog']//select | //div[@role='dialog']//div[contains(@class,'MuiSelect-select')]
  at com.company.sqa.pages.LaporanCutiPage.clickRowActionButton(LaporanCutiPage.java:218)
  at com.company.sqa.ui.steps.LaporanCutiSteps.adminMembukaMenuAksiBarisPertamaDanMemilihEditPadaCuti(LaporanCutiSteps.java:165)
  ```
- **Rekomendasi Perbaikan (Frontend React / Next.js):**
  Lengkapi modal dialog ubah atasan cuti dengan kontrol dropdown atasan aktif:
  ```jsx
  // Rekomendasi Komponen Dialog Ubah Atasan Cuti (MUI / React):
  <Dialog open={isModalOpen} onClose={handleCloseModal} maxWidth="sm" fullWidth>
    <DialogTitle>Ubah atasan user yang request</DialogTitle>
    <DialogContent dividers>
      <Typography variant="body2" color="textSecondary" gutterBottom>
        Pilih atasan baru untuk memproses persetujuan cuti karyawan: <b>{employeeName}</b>
      </Typography>
      <FormControl fullWidth margin="normal">
        <InputLabel id="select-supervisor-label">Atasan Pengganti</InputLabel>
        <Select
          labelId="select-supervisor-label"
          id="select-supervisor"
          value={newSupervisorId}
          onChange={(e) => setNewSupervisorId(e.target.value)}
          required
        >
          {activeSupervisors.map((spv) => (
            <MenuItem key={spv.id} value={spv.id}>
              {spv.fullname} — {spv.department} ({spv.nip})
            </MenuItem>
          ))}
        </Select>
      </FormControl>
    </DialogContent>
    <DialogActions>
      <Button onClick={handleCloseModal} color="inherit">Batal</Button>
      <Button 
        variant="contained" 
        color="primary" 
        onClick={handleSaveSupervisorChange}
        disabled={!newSupervisorId || isSubmitting}
      >
        {isSubmitting ? "Menyimpan..." : "Ubah Data"}
      </Button>
    </DialogActions>
  </Dialog>
  ```

---

### 2. DEFECT-CUTI-002: Modal Aksi Validasi Cuti Mengalami Lingering Overlay Pasca-Persetujuan
- **Kode Skenario:** `@tc-cl-cuti-v2-01` & `@tc-cl-cuti-v3-01` pada `ClientCuti.feature`
- **Lokasi Halaman:** Portal Client Validator Cuti (`/client/cuti/validator`)
- **Deskripsi Masalah:**
  Saat Upliner V2 atau Upliner V3 menyetujui klaim cuti staf pada antarmuka validator, modal aksi `#modal-action-validator` memicu feedback berhasil, tetapi elemen dialog pembungkus (*modal backdrop*) tidak menghilang sempurna dari *view hierarchy* browser. Elemen dialog masih berstatus terdeteksi oleh WebDriver sehingga menghalangi klik pada baris data berikutnya.
- **Akar Masalah (*Root Cause*):**
  CSS modal hanya memanipulasi properti `opacity: 0` dan `pointer-events: none` tanpa menyetel `display: none` atau menghapus node modal dari DOM pohon React.
- **Rekomendasi Perbaikan (Frontend JavaScript & CSS):**
  ```javascript
  // Handler Submit Persetujuan Cuti Client:
  async function submitLeaveApproval(leaveId, actionStatus, notes) {
    try {
      setLoading(true);
      await api.patch(`/api/client/leave/${leaveId}/validate`, { status: actionStatus, notes });
      
      // 1. Matikan modal secara tuntas
      const modalElement = document.getElementById("modal-action-validator");
      if (modalElement) {
        modalElement.classList.remove("show");
        modalElement.style.display = "none"; // Hilangkan dari rendering engine browser
      }
      
      // 2. Tampilkan Snackbar sukses & refresh data
      toast.success("Persetujuan cuti berhasil diproses!");
      refreshTableData();
    } catch (err) {
      toast.error(err.message || "Gagal memproses validasi cuti");
    } finally {
      setLoading(false);
    }
  }
  ```

---

### 3. DEFECT-CUTI-003: Double-Submit & Ketiadaan Debounce pada Tombol "Sync Kuota Cuti"
- **Kode Skenario:** `@ACT-SC-001` pada `16_laporan_sisa_cuti.feature`
- **Lokasi Halaman:** Antarmuka Laporan Sisa Cuti (`/laporan/sisa-cuti`)
- **Deskripsi Masalah:**
  Tombol *"Sync Kuota Cuti"* bertugas menghitung ulang saldo cuti seluruh karyawan perusahaan berdasarkan masa kerja dan jatah tahunan. Namun, tombol ini tidak memiliki proteksi *debounce* atau indikator *loading spinner*. Ketika admin menekan tombol secara berulang kali (*rapid clicking*), server menerima request sinkronisasi berganda secara konkuren.
- **Dampak Operasional & Kinerja Database:**
  *Spike* beban CPU pada database MySQL/PostgreSQL akibat kalkulasi kuota ribuan karyawan secara bersamaan, berpotensi memicu *deadlock* transaksi saldo cuti.
- **Rekomendasi Perbaikan:**
  ```javascript
  // Proteksi Debounce & Loading State:
  const [isSyncing, setIsSyncing] = useState(false);

  const handleSyncQuota = async () => {
    if (isSyncing) return; // Cegah double invocation
    setIsSyncing(true);
    try {
      await api.post('/api/leave/sync-global-quota');
      toast.success("Berhasil menyinkronkan saldo kuota cuti seluruh karyawan");
      refetchLeaveBalance();
    } catch (error) {
      toast.error("Gagal melakukan sinkronisasi kuota cuti");
    } finally {
      setIsSyncing(false);
    }
  };

  // Render Tombol:
  <Button 
    variant="contained" 
    color="secondary" 
    onClick={handleSyncQuota} 
    disabled={isSyncing}
    startIcon={isSyncing ? <CircularProgress size={16} /> : <SyncIcon />}
  >
    {isSyncing ? "Menyinkronkan..." : "Sync Kuota Cuti"}
  </Button>
  ```

---

### 4. SEC-CUTI-001: Verifikasi Integritas Workflow Persetujuan Cuti Berjenjang
- **Kategori:** *Business Logic Flaw & Broken Object Level Authorization (OWASP API1)*
- **Deskripsi Masalah:**
  Pada sistem HADIR, pengajuan cuti tahunan karyawan wajib melalui tahapan verifikasi persetujuan:
  $$\text{Pengajuan Karyawan} \longrightarrow \text{Persetujuan Leader} \longrightarrow \text{Validasi Data 1 (Upliner V2)} \longrightarrow \text{Validasi Final (Upliner V3)}$$
  Jika endpoint `/api/leader/leave/{id}/approval` atau `/api/client/leave/{id}/validate` tidak memvalidasi *state machine* pengajuan cuti secara ketat, pengguna dengan peran validator tahap 2 dapat mem-bypass tahap 1, atau sebaliknya.
- **Rekomendasi Perbaikan (Backend Workflow Guard / NestJS):**
  ```typescript
  @Patch(':id/approval')
  @UseGuards(JwtAuthGuard, RolesGuard)
  async approveLeaveRequest(
    @Param('id') leaveId: string,
    @Body() dto: LeaveApprovalDto,
    @CurrentUser() user: AuthUser
  ) {
    const leave = await this.leaveRepository.findOne({ where: { id: leaveId } });
    if (!leave) throw new NotFoundException('Data cuti tidak ditemukan');

    // 1. Validasi State Machine Status
    switch (leave.status) {
      case LeaveStatus.SUBMITTED:
        if (user.role !== UserRole.LEADER) {
          throw new ForbiddenException('Tahap 1 wajib disetujui oleh Leader tim.');
        }
        leave.status = dto.isApproved ? LeaveStatus.PENDING_V1 : LeaveStatus.REJECTED;
        break;

      case LeaveStatus.PENDING_V1:
        if (user.role !== UserRole.UPLINER_V2) {
          throw new ForbiddenException('Tahap 2 wajib divalidasi oleh Upliner V2.');
        }
        leave.status = dto.isApproved ? LeaveStatus.PENDING_V2 : LeaveStatus.REJECTED;
        break;

      case LeaveStatus.PENDING_V2:
        if (user.role !== UserRole.UPLINER_V3) {
          throw new ForbiddenException('Tahap Final wajib disetujui oleh Upliner V3.');
        }
        leave.status = dto.isApproved ? LeaveStatus.APPROVED : LeaveStatus.REJECTED;
        if (dto.isApproved) {
          await this.leaveBalanceService.deductQuota(leave.userId, leave.durationDays);
        }
        break;

      default:
        throw new BadRequestException('Status permohonan cuti tidak valid untuk diproses.');
    }

    return this.leaveRepository.save(leave);
  }
  ```

---

## 4. Panduan Eksekusi Uji Regresi Modul Cuti

Untuk mereproduksi dan memverifikasi perbaikan cacat pada modul Cuti:

```bash
# 1. Jalankan seluruh pengujian fungsional Cuti bebas bug (100% BUILD SUCCESS):
mvn test -Dcucumber.filter.tags="@cuti and not @live" -Dheadless=false

# 2. Jalankan pengujian penangkap cacat live target (Assert Kegagalan DEFECT-CUTI-001):
mvn test -Dcucumber.filter.tags="@DEFECT-CUTI-001" -Dheadless=false

# 3. Jalankan pengujian hybrid Client Mobile Cuti (UI & API REST Assured):
mvn test -Dcucumber.filter.tags="@absent-leave or (@cuti and @api)" -Dheadless=false
```
