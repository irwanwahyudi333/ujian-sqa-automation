# language: id
@api @leader
Fitur: API Backend Modul Leader - Endpoint Presensi, Approval, dan Otorisasi Keamanan
  Sebagai sistem backend HADIR
  Saya ingin melayani permintaan API data tim, approval berjenjang, dan validasi RBAC
  Agar hak akses terlindungi dan data regu terkelola dengan aman

  @tc-api-ldr-01
  Skenario: Leader mengambil data ringkasan kehadiran tim
    Ketika leader mengirim request GET ke endpoint "/api/leader/reports/all" dengan parameter tanggal
    Maka response API leader mengembalikan status 200
    Dan response API leader memuat payload data yang valid

  @tc-api-ldr-02
  Skenario: Leader mengambil log kehadiran dan foto presensi harian
    Ketika leader mengirim request GET ke endpoint "/api/leader/reports/attendance"
    Maka response API leader mengembalikan status 200
    Dan response API leader memuat payload data yang valid

  @tc-api-ldr-03
  Skenario: Leader menyetujui koreksi jam absensi anggota tim
    Ketika leader mengirim request PATCH persetujuan ke endpoint "/api/leader/corrections/1" dengan status disetujui
    Maka response API leader mengembalikan status 200

  @tc-api-ldr-04
  Skenario: Leader menolak koreksi jam absensi anggota tim dengan alasan
    Ketika leader mengirim request PATCH penolakan ke endpoint "/api/leader/corrections/1" dengan alasan "Data jam tidak sesuai"
    Maka response API leader mengembalikan status 200

  @tc-api-ldr-05
  Skenario: Leader menyetujui pengajuan cuti anggota tim
    Ketika leader mengirim request PATCH persetujuan ke endpoint "/api/leader/leave/1/approval" dengan status disetujui
    Maka response API leader mengembalikan status 200

  @tc-api-ldr-06 @lembur
  Skenario: Leader menyetujui pengajuan lembur anggota tim
    Ketika leader mengirim request PATCH persetujuan ke endpoint "/api/leader/overtime/1/approval" dengan status disetujui
    Maka response API leader mengembalikan status 200

  @tc-api-ldr-07
  Skenario: Leader menyetujui permohonan penukaran shift kerja
    Ketika leader mengirim request PATCH persetujuan ke endpoint "/api/leader/shifting/1/approval" dengan status disetujui
    Maka response API leader mengembalikan status 200

  @tc-api-ldr-08
  Skenario: Leader men-generate rekapitulasi laporan tim
    Ketika leader mengirim request POST generate report ke endpoint "/api/leader/reports/generate"
    Maka response API leader mengembalikan status 200
    Dan response API leader memuat payload data yang valid

  @tc-api-ldr-sec-01 @security @rbac
  Skenario: Proteksi RBAC menolak akun staff biasa yang mengakses modul leader
    Ketika pengguna dengan peran staff mengirim request GET ke endpoint "/api/leader/reports/all"
    Maka sistem menolak akses dengan status 403 Forbidden

  @tc-api-ldr-sec-02 @security @anti-idor
  Skenario: Proteksi Anti-IDOR menolak tindakan leader terhadap staf tim lain
    Ketika leader mengirim request PATCH ke endpoint tim lain "/api/leader/corrections/999"
    Maka sistem menolak akses data IDOR dengan status 403 Forbidden

