# language: id
@ui @leader-izin-off @izin-off @security-boundary
Fitur: Pengujian Keamanan, Batasan Input, dan Ketahanan Sistem Leader Izin Off
  Sebagai QA Engineer
  Saya ingin menguji ketahanan form terhadap serangan XSS, SQL injection, batasan panjang string, dan karakter khusus
  Agar sistem terhindar dari kerentanan keamanan dan error tidak tertangani

  Latar Belakang:
    Dengan leader membuka halaman manajemen leader izin off

  @SEC-001
  Skenario: Uji sanitasi script XSS pada kolom pencarian
    Ketika leader memasukkan kata kunci pencarian leader izin off "<script>alert('xss')</script>"
    Dan leader menekan tombol Search leader izin off
    Maka tidak ada dialog popup script terpicu pada peramban web leader izin off

  @SEC-002
  Skenario: Uji sanitasi tag img onerror XSS pada pencarian
    Ketika leader memasukkan kata kunci pencarian leader izin off "<img src=x onerror=alert(1)>"
    Dan leader menekan tombol Search leader izin off
    Maka tidak ada dialog popup script terpicu pada peramban web leader izin off

  @SEC-003
  Skenario: Uji ketahanan terhadap karakter kutip SQL injection pada pencarian
    Ketika leader memasukkan karakter tanda kutip SQL pada pencarian leader izin off
    Dan leader menekan tombol Search leader izin off
    Maka halaman leader izin off tidak melempar error dan merespons normal

  @SEC-004
  Skenario: Uji sanitasi script XSS pada Nama Karyawan di modal pengajuan izin off
    Ketika leader menekan tombol Ajukan Izin Off
    Dan leader mengisi form pengajuan izin off dengan nama "<b onmouseover=alert(1)>XSS Leader</b>", upliner "Irwan Wahyudi", tanggal "2024-12-29", alasan "Uji sanitasi XSS"
    Dan leader menekan tombol Simpan pada modal pengajuan leader izin off
    Maka tidak ada dialog popup script terpicu pada peramban web leader izin off
    Dan permohonan karyawan "<b onmouseover=alert(1)>XSS Leader</b>" tersimpan sebagai teks literal aman

  @SEC-005
  Skenario: Uji sanitasi script XSS pada Alasan Penolakan
    Ketika leader menekan tombol Tolak pada baris permohonan pertama
    Dan leader mengisi alasan penolakan "<script>alert('rejected')</script>"
    Dan leader menekan tombol konfirmasi pada modal approval leader izin off
    Maka tidak ada dialog popup script terpicu pada peramban web leader izin off

  @SEC-006
  Skenario: Uji input nama dengan string panjang batas 255 karakter
    Ketika leader menekan tombol Ajukan Izin Off
    Dan leader mengisi nama pengajuan dengan teks panjang 255 karakter
    Dan leader mengisi tanggal pengajuan "2024-12-25" dan alasan "Uji boundary 255"
    Dan leader menekan tombol Simpan pada modal pengajuan leader izin off
    Maka modal dialog pengajuan izin off baru tertutup dan data tersimpan tanpa buffer overflow

  @SEC-007
  Skenario: Uji input alasan dengan string panjang batas 500 karakter
    Ketika leader menekan tombol Ajukan Izin Off
    Dan leader mengisi nama pengajuan "Boundary Reason User" dan tanggal "2024-12-26"
    Dan leader mengisi alasan pengajuan dengan teks panjang 500 karakter
    Dan leader menekan tombol Simpan pada modal pengajuan leader izin off
    Maka modal dialog pengajuan izin off baru tertutup dan data tersimpan tanpa buffer overflow

  @SEC-008
  Skenario: Pencarian dengan karakter emoji dan simbol unik
    Ketika leader memasukkan kata kunci pencarian leader izin off "🏖️ Liburan & Off!"
    Dan leader menekan tombol Search leader izin off
    Maka sistem memproses pencarian simbol tanpa kegagalan encoding pada leader izin off

  @SEC-009
  Skenario: Uji ketahanan terhadap karakter SQL injection pada alasan penolakan
    Ketika leader menekan tombol Tolak pada baris permohonan pertama
    Dan leader mengisi alasan penolakan "' OR '1'='1 --"
    Dan leader menekan tombol konfirmasi pada modal approval leader izin off
    Maka sistem memproses penolakan secara aman tanpa error database pada leader izin off

  @SEC-010
  Skenario: Trimming whitespace berlebih pada input nama pengajuan
    Ketika leader menekan tombol Ajukan Izin Off
    Dan leader mengisi nama pengajuan dengan spasi berlebih "   Spasi User   "
    Dan leader mengisi tanggal pengajuan "2024-12-30" dan alasan "Uji spasi"
    Dan leader menekan tombol Simpan pada modal pengajuan leader izin off
    Maka permohonan atas nama "Spasi User" tampil di tabel leader izin off
