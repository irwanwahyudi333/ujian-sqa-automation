# language: id
@ui @self-registration @module-user-edit-validation
Fitur: Validasi Halaman Edit User dan Integritas Approval Pendaftar Mandiri
  Sebagai administrator sistem dan tim SQA
  Saya ingin memverifikasi integritas halaman edit user (/management/user-v2/edit/{id})
  Agar foto selfie pendaftar tampil valid serta sistem menolak pelolosan NIK dan Email duplikat

  Latar Belakang:
    Dengan admin membuka halaman self-registration
    Dan admin menavigasi ke halaman edit user pertama

  @BUG-U2-001 @visual @image-validation
  Skenario: Verifikasi ketersediaan dan validitas URL gambar foto selfie pendaftar
    Maka halaman edit user menampilkan kontainer foto profil pendaftar
    Dan URL foto selfie pendaftar merujuk ke endpoint static upload yang valid
    Dan sistem tidak menampilkan gambar rusak atau status 404 pada foto profil

  @BUG-U2-002 @security @nik-validation @data-integrity
  Skenario: Verifikasi sistem menolak pelolosan pendaftar dengan NIK yang sudah terdaftar
    Ketika admin mengisi NIK pada halaman edit dengan NIK yang sudah terdaftar "3201234567890002"
    Dan admin menekan tombol submit edit user
    Maka sistem menolak pelolosan dan menampilkan notifikasi kesalahan duplikasi NIK

  @BUG-U2-003 @security @email-validation @data-integrity
  Skenario: Verifikasi sistem menolak pengubahan email menjadi sama persis dengan email orang lain
    Ketika admin mengisi email pada halaman edit dengan email akun lain "ahmad.subagyo@example.com"
    Dan admin menekan tombol submit edit user
    Maka sistem menolak pelolosan dan menampilkan notifikasi kesalahan duplikasi email

  @BUG-U2-004 @validation @nik-length-validation
  Skenario: Verifikasi field NIK pada form edit menolak input terlalu pendek kurang dari panjang standar
    Ketika admin mengisi NIK pada halaman edit dengan format tidak valid "AB"
    Dan admin menekan tombol submit edit user
    Maka sistem menolak pelolosan dan memvalidasi batas panjang minimum NIK

  @BUG-UI-BATCH-001 @bulk-action @algorithm-defect
  Skenario: Verifikasi integritas pemilihan baris non-kontigu pada aksi batch edit tabel
    Ketika admin mencentang baris tabel non-kontigu baris ke 2 dan ke 4
    Dan admin menekan tombol Update dichecklist
    Maka modal ubah data user dichecklist terbuka
    Dan sistem memproses seluruh baris terpilih tanpa menghasilkan payload undefined

