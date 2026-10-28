# language: id
@ui @divisi @ux-accessibility
Fitur: Pengujian Aksesibilitas dan Pengalaman Pengguna Halaman Divisi
  Sebagai admin manajemen HADIR dengan berbagai kondisi perangkat dan kebutuhan aksesibilitas
  Saya ingin antarmuka halaman divisi mematuhi kaidah aksesibilitas web dan responsif
  Agar sistem mudah dinavigasi menggunakan keyboard dan nyaman digunakan di berbagai ukuran layar

  Latar Belakang:
    Dengan admin membuka halaman manajemen divisi

  @UX-001
  Skenario: Navigasi elemen halaman menggunakan tombol Tab keyboard
    Ketika admin menavigasi elemen interaktif menggunakan tombol Tab
    Maka fokus berpindah secara berurutan dari input search hingga kontrol tabel

  @UX-002
  Skenario: Memeriksa focus trap keyboard saat modal dialog terbuka
    Ketika admin menekan tombol Tambahkan divisi
    Dan admin menekan tombol Tab berulang kali di dalam modal tambah
    Maka fokus keyboard tetap terperangkap di dalam modal dan tidak bocor ke latar belakang

  @UX-003
  Skenario: Memeriksa kontras warna teks tombol Tambahkan dan header tabel
    Maka tombol Tambahkan dan header tabel memiliki kontras warna yang memenuhi standar keterbacaan

  @UX-004
  Skenario: Memeriksa responsivitas tampilan tabel pada resolusi tablet 768px
    Ketika admin mengatur ukuran jendela browser ke resolusi tablet "768x1024"
    Maka kontainer tabel divisi dapat digulirkan secara horizontal tanpa merusak tata letak

  @UX-005
  Skenario: Memeriksa tampilan antarmuka pada zoom browser 200 persen
    Ketika admin mengatur tingkat zoom browser ke 200 persen
    Maka teks, tombol aksi, dan tabel divisi tetap dapat dibaca dan dioperasikan

  @UX-006
  Skenario: Memeriksa atribut aria-label pada tombol aksi interaktif
    Maka tombol aksi tabel dan tombol tutup modal memiliki atribut aria-label yang sesuai

  @UX-007
  Skenario: Memeriksa transisi animasi pembukaan modal dialog divisi
    Ketika admin menekan tombol Tambahkan divisi
    Maka modal dialog tampil dengan kelas animasi transisi halus

  @UX-008
  Skenario: Memeriksa status disabled pada tombol saat proses submit berlangsung
    Ketika admin menekan tombol Tambahkan divisi
    Dan admin memasukkan nama divisi "Debounce Check Division"
    Dan admin menekan tombol Simpan pada modal tambah
    Maka sistem memproses penambahan tanpa terjadinya duplikasi data submit

  @UX-009
  Skenario: Memeriksa atribut title atau tooltip pada tombol kontrol paginasi
    Maka tombol navigasi First Page, Prev, Next, dan Last memiliki atribut title atau tooltip navigasi

  @UX-010
  Skenario: Memeriksa konsistensi fungsionalitas halaman pada peramban web
    Maka seluruh fungsi pencarian, modal dialog, dan paginasi beroperasi secara konsisten
