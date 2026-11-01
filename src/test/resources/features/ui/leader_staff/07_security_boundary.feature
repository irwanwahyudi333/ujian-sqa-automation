# language: id
@ui @leader-staff @staff @security-boundary
Fitur: Pengujian Keamanan Input dan Boundary Testing Halaman Leader Staff Lists
  Sebagai QA Engineer
  Saya ingin menguji ketahanan antarmuka terhadap muatan berbahaya XSS, SQLi, dan string batas
  Agar sistem terbebas dari kerentanan keamanan dan berjalan stabil

  Latar Belakang:
    Dengan leader membuka halaman daftar staff

  @SEC-001
  Skenario: Sanitasi muatan script XSS pada kolom pencarian
    Ketika leader memasukkan kata kunci pencarian leader staff "<script>alert('xss')</script>"
    Dan leader menekan tombol Search leader staff
    Maka sistem merender input pencarian secara aman tanpa eksekusi alert dialog

  @SEC-002
  Skenario: Ketahanan terhadap muatan SQL Injection pada kolom pencarian
    Ketika leader memasukkan kata kunci pencarian leader staff "' OR '1'='1"
    Dan leader menekan tombol Search leader staff
    Maka sistem memperlakukan input SQL Injection sebagai string pencarian normal

  @SEC-003
  Skenario: Ketahanan pencarian terhadap karakter tanda kutip tunggal dan ganda
    Ketika leader memasukkan kata kunci pencarian leader staff "'\""
    Dan leader menekan tombol Search leader staff
    Maka sistem tetap stabil tanpa melempar unhandled exception

  @SEC-004
  Skenario: Sanitasi muatan XSS pada input Nama Staff saat penambahan
    Ketika leader menekan tombol Tambah Staff
    Dan leader mengisi form tambah staff dengan NIK "STF-XSS", nama "<b onmouseover=alert(1)>XSS</b>", email "xss@hadir.com", unit "Technology", posisi "Dev", phone "081299998888", dan status "Aktif"
    Dan leader menekan tombol Simpan pada modal tambah staff
    Maka staf dengan NIK "STF-XSS" tersimpan secara aman tanpa mengeksekusi payload

  @SEC-005
  Skenario: Ketahanan terhadap input string panjang 255 karakter
    Ketika leader menekan tombol Tambah Staff
    Dan leader mengisi nama staf dengan string panjang 255 karakter
    Dan leader menekan tombol Batal pada modal tambah staff
    Maka modal tambah data staff tertutup dengan stabil

  @SEC-006
  Skenario: Ketahanan pencarian terhadap karakter emoji dan simbol unik
    Ketika leader memasukkan kata kunci pencarian leader staff "👤👨‍💻🔥#*&"
    Dan leader menekan tombol Search leader staff
    Maka sistem merespons normal tanpa error encoding UTF-8
