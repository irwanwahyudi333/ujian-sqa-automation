# language: id
@ui @monthly-schedule @ux
Fitur: Pengujian Aksesibilitas, Responsivitas, dan Interaksi Keyboard
  Sebagai pengguna aplikasi HADIR
  Saya ingin antarmuka jadwal bulanan responsif dan mendukung navigasi keyboard
  Agar aplikasi nyaman digunakan pada berbagai ukuran layar dan kondisi aksesibilitas

  Latar Belakang:
    Dengan admin membuka halaman manajemen monthly schedule

  @UX-001
  Skenario: Navigasi fokus keyboard via tombol Tab
    Ketika admin menekan tombol Tab secara berurutan
    Maka indikator fokus berpindah dari tombol download template, input nama, tombol cari, hingga tombol tabel

  @UX-002
  Skenario: Tekan tombol Enter pada form import
    Ketika admin memilih file import "src/test/resources/testdata/schedule/valid_monthly_schedule.xlsx"
    Dan admin menekan tombol Enter pada form import
    Maka proses import dijalankan

  @UX-003
  Skenario: Tekan tombol Enter pada field pencarian
    Ketika admin memasukkan kata kunci pencarian jadwal "Ahmad"
    Dan admin menekan tombol Enter pada keyboard
    Maka pencarian langsung dieksekusi tanpa harus mengklik tombol Cari

  @UX-004
  Skenario: Tutup modal edit dengan tombol Escape
    Ketika admin menekan tombol Edit pada baris ke-1
    Dan admin menekan tombol keyboard Escape
    Maka modal dialog Edit Schedule langsung tertutup

  @UX-005
  Skenario: Tutup modal hapus dengan tombol Escape
    Ketika admin menekan tombol Delete pada baris ke-1
    Dan admin menekan tombol keyboard Escape
    Maka modal dialog Hapus Data langsung tertutup

  @UX-006
  Skenario: Responsivitas tampilan pada resolusi tablet (1024x768)
    Ketika ukuran viewport browser diatur menjadi 1024x768 piksel
    Maka tata letak tabel dan card form tetap tersusun rapi tanpa overlap

  @UX-007
  Skenario: Responsivitas tampilan pada zoom 200%
    Ketika halaman di-zoom sebesar 200%
    Maka elemen teks dan tombol aksi tetap terbaca dan dapat diklik

  @UX-008
  Skenario: Hover effect pada tombol aksi dan baris tabel
    Maka tombol Edit dan Hapus memiliki indikator visual saat kursor diarahkan

  @UX-009
  Skenario: Hover effect pada tombol Download Template
    Maka tombol Download Template menunjukkan feedback visual saat di-hover

  @UX-010
  Skenario: Tooltip pada tombol aksi edit dan hapus
    Maka tombol Edit memiliki atribut title "Edit" dan tombol Hapus memiliki title "Hapus"
