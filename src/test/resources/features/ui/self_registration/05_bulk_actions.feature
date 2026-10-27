# language: id
@ui @self-registration @module-bulk-actions
Fitur: Pengujian Toolbar Aksi Massal Data User Registrasi Mobile
  Sebagai administrator manajemen
  Saya ingin memanfaatkan toolbar aksi massal saat memilih data user
  Agar dapat melakukan pembaruan massal secara cepat

  Latar Belakang:
    Dengan admin membuka halaman self-registration

  @BULK-001
  Skenario: Centang satu baris data
    Ketika admin mencentang baris user pertama
    Maka toolbar aksi massal tampil pada layar
    Dan counter checklist menampilkan "1 Di checklist"

  @BULK-002
  Skenario: Centang tiga baris data
    Ketika admin mencentang 3 baris data
    Maka toolbar aksi massal tampil pada layar
    Dan counter checklist menampilkan "3 Di checklist"

  @BULK-003
  Skenario: Centang seluruh baris via Select All
    Ketika admin mencentang checkbox Select All
    Maka counter checklist menampilkan seluruh baris tercentang

  @BULK-004
  Skenario: Hilangkan seluruh centang
    Ketika admin mencentang baris user pertama
    Dan admin mengklik kembali checkbox baris user pertama
    Maka toolbar aksi massal otomatis disembunyikan

  @BULK-005
  Skenario: Periksa tombol Update dichecklist
    Ketika admin mencentang baris user pertama
    Maka tombol Update dichecklist dapat ditekan

  @BULK-006
  Skenario: Klik tombol Update dichecklist saat ada baris dipilih
    Ketika admin mencentang baris user pertama
    Dan admin menekan tombol Update dichecklist
    Maka modal ubah data user dichecklist terbuka

  @BULK-007
  Skenario: Klik tombol Update dichecklist berulang kali
    Ketika admin mencentang baris user pertama
    Dan admin menekan tombol Update dichecklist berulang kali
    Maka modal ubah data user dichecklist terbuka
