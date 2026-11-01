# language: id
@ui @user-monitoring @add
Fitur: Penambahan Data User Monitoring Baru (Add User)
  Sebagai admin manajemen HADIR
  Saya ingin menambahkan user monitoring baru dengan form input lengkap
  Agar karyawan dapat dipantau aktivitas absensi dan operasionalnya

  Latar Belakang:
    Dengan admin membuka halaman manajemen user monitoring

  @ADD-001
  Skenario: Membuka modal dialog Tambah User Monitoring
    Ketika admin menekan tombol Tambahkan user monitoring
    Maka modal dialog Tambah User Monitoring terbuka
    Dan judul modal tambah user monitoring menampilkan "Add User Monitoring"

  @ADD-002
  Skenario: Verifikasi field wajib pada form tambah user monitoring
    Ketika admin menekan tombol Tambahkan user monitoring
    Maka input nama user, email, password, dan unit memiliki atribut required

  @ADD-003
  Skenario: Menambahkan user monitoring baru dengan seluruh data valid
    Ketika admin menekan tombol Tambahkan user monitoring
    Dan admin mengisi nama user baru "Gilang Pratama"
    Dan admin mengisi email user baru "gilang.pratama@hadir.com"
    Dan admin mengisi password user baru "Password123!"
    Dan admin memilih unit user baru "Technology"
    Dan admin menekan tombol Simpan pada modal tambah user monitoring
    Maka modal dialog Tambah User Monitoring tertutup
    Dan notifikasi toast user monitoring menampilkan pesan "Berhasil menambahkan user monitoring"
    Dan user bernama "Gilang Pratama" muncul pada tabel user monitoring

  @ADD-004
  Skenario: Menambahkan user monitoring pada unit Human Resources
    Ketika admin menekan tombol Tambahkan user monitoring
    Dan admin mengisi form tambah user dengan nama "Hana Permata", email "hana.permata@hadir.com", password "Hana123!", unit "Human Resources"
    Dan admin menekan tombol Simpan pada modal tambah user monitoring
    Maka notifikasi toast user monitoring menampilkan pesan "Berhasil menambahkan user monitoring"
    Dan user bernama "Hana Permata" muncul dengan badge unit "Human Resources"

  @ADD-005
  Skenario: Validasi field Nama User kosong saat tambah
    Ketika admin menekan tombol Tambahkan user monitoring
    Dan admin mengisi email user baru "user.tanpanama@hadir.com"
    Dan admin mengisi password user baru "Password123!"
    Dan admin memilih unit user baru "Technology"
    Dan admin menekan tombol Simpan pada modal tambah user monitoring
    Maka form tambah menolak submit karena nama user wajib diisi
    Dan modal dialog Tambah User Monitoring tetap terbuka

  @ADD-006
  Skenario: Validasi field Email kosong saat tambah
    Ketika admin menekan tombol Tambahkan user monitoring
    Dan admin mengisi nama user baru "Indra Lesmana"
    Dan admin mengisi password user baru "Password123!"
    Dan admin memilih unit user baru "Operations"
    Dan admin menekan tombol Simpan pada modal tambah user monitoring
    Maka form tambah menolak submit karena email wajib diisi
    Dan modal dialog Tambah User Monitoring tetap terbuka

  @ADD-007
  Skenario: Validasi field Password kosong saat tambah
    Ketika admin menekan tombol Tambahkan user monitoring
    Dan admin mengisi nama user baru "Joko Susilo"
    Dan admin mengisi email user baru "joko.susilo@hadir.com"
    Dan admin memilih unit user baru "Marketing"
    Dan admin menekan tombol Simpan pada modal tambah user monitoring
    Maka form tambah menolak submit karena password wajib diisi
    Dan modal dialog Tambah User Monitoring tetap terbuka

  @ADD-008
  Skenario: Validasi field Unit kosong saat tambah
    Ketika admin menekan tombol Tambahkan user monitoring
    Dan admin mengisi nama user baru "Kiki Amalia"
    Dan admin mengisi email user baru "kiki.amalia@hadir.com"
    Dan admin mengisi password user baru "Password123!"
    Dan admin menekan tombol Simpan pada modal tambah user monitoring
    Maka form tambah menolak submit karena unit wajib dipilih
    Dan modal dialog Tambah User Monitoring tetap terbuka

  @ADD-009
  Skenario: Menambahkan user dengan karakter simbol pada nama
    Ketika admin menekan tombol Tambahkan user monitoring
    Dan admin mengisi form tambah user dengan nama "O'Connor & Smith", email "oconnor.smith@hadir.com", password "Pass123!", unit "Finance & Accounting"
    Dan admin menekan tombol Simpan pada modal tambah user monitoring
    Maka user bernama "O'Connor & Smith" berhasil ditambahkan ke tabel

  @ADD-010
  Skenario: Uji validasi format email tidak standar
    Ketika admin menekan tombol Tambahkan user monitoring
    Dan admin mengisi form tambah user dengan nama "Format Rusak", email "bukan-sebuah-email", password "Pass123!", unit "Technology"
    Dan admin menekan tombol Simpan pada modal tambah user monitoring
    Maka form tambah mendeteksi format email tidak valid

  @ADD-011
  Skenario: Membatalkan penambahan user via tombol Batal
    Ketika admin menekan tombol Tambahkan user monitoring
    Dan admin mengisi nama user baru "Batal Tambah User"
    Dan admin menekan tombol Batal pada modal tambah user monitoring
    Maka modal dialog Tambah User Monitoring tertutup
    Dan user bernama "Batal Tambah User" tidak ditambahkan ke tabel

  @ADD-012
  Skenario: Menutup modal tambah user via tombol silang (X)
    Ketika admin menekan tombol Tambahkan user monitoring
    Dan admin menekan tombol silang tutup pada modal tambah user monitoring
    Maka modal dialog Tambah User Monitoring tertutup tanpa menyimpan data

  @ADD-013
  Skenario: Menutup modal tambah user via tombol Escape pada keyboard
    Ketika admin menekan tombol Tambahkan user monitoring
    Dan admin menekan tombol keyboard Escape pada user monitoring
    Maka modal dialog Tambah User Monitoring tertutup seketika

  @ADD-014
  Skenario: Verifikasi total count paginasi bertambah setelah tambah user
    Ketika admin menekan tombol Tambahkan user monitoring
    Dan admin mengisi form tambah user dengan nama "Lina Marlina", email "lina.marlina@hadir.com", password "Lina123!", unit "Operations"
    Dan admin menekan tombol Simpan pada modal tambah user monitoring
    Maka jumlah baris user monitoring di tabel bertambah

  @ADD-015
  Skenario: Reset isian form saat modal tambah dibuka kembali
    Ketika admin menekan tombol Tambahkan user monitoring
    Dan admin mengisi nama user baru "Teks Tertinggal"
    Dan admin menekan tombol Batal pada modal tambah user monitoring
    Dan admin menekan tombol Tambahkan user monitoring
    Maka field nama user baru dalam kondisi kosong bersih
