# language: id
@api @register-v2-api @v2-api-security
Fitur: L. Pengujian Keamanan dan Privasi (Security and Privacy) Registrasi User v2
  Sebagai Security QA Engineer di HADIR Management
  Saya ingin menguji ketahanan endpoint registrasi terhadap berbagai vektor serangan siber
  Agar data perusahaan dan karyawan terlindungi dari celah injeksi, kebocoran data, dan manipulasi otorisasi

  @SEC-001
  Skenario: SEC-001 Percobaan SQL Injection pada field NIK
    Ketika client mengirim payload registrasi dengan "nik" berisi injeksi "' UNION SELECT username, password FROM users--"
    Maka sistem backend menolak payload atau memperlakukan sebagai teks aman

  @SEC-002
  Skenario: SEC-002 Percobaan SQL Injection pada field Nama Karyawan
    Ketika client mengirim payload registrasi dengan "fullname" berisi injeksi "admin'--"
    Maka sistem backend menolak payload atau memperlakukan sebagai teks aman

  @SEC-003
  Skenario: SEC-003 Percobaan serangan Stored XSS pada field Nama
    Ketika client mengirim payload registrasi dengan "fullname" berisi injeksi "<img src=x onerror=alert(document.cookie)>"
    Maka sistem backend menolak payload atau memperlakukan sebagai teks aman

  @SEC-004
  Skenario: SEC-004 Percobaan Path Traversal pada upload berkas foto
    Ketika client mengunggah berkas foto dengan path traversal "../../etc/passwd.jpg"
    Maka status respons API upload adalah 400 atau 415

  @SEC-005
  Skenario: SEC-005 Percobaan upload berkas executable bertopeng JPG
    Ketika client mengunggah berkas executable bertopeng JPG
    Maka status respons API upload adalah 400 atau 415

  @SEC-006
  Skenario: SEC-006 Percobaan eskalasi hak akses role injection via JSON payload
    Ketika client mengirim request registrasi user v2 dengan field tambahan tak dikenal
    Maka status respons API registrasi user v2 adalah 200 atau 201 atau respons mock valid

  @SEC-007
  Skenario: SEC-007 Verifikasi transmisi data melalui protokol HTTPS
    Ketika client mengirim request registrasi user v2 dengan payload lengkap valid
    Maka status respons API registrasi user v2 adalah 200 atau 201 atau respons mock valid

  @SEC-008
  Skenario: SEC-008 Audit kerahasiaan password pada logging framework
    Ketika client mengirim request registrasi user v2 dengan payload lengkap valid
    Maka respons API tidak mengembalikan password plaintext

  @SEC-009
  Skenario: SEC-009 Percobaan spam request masif rate limiting
    Ketika client mengirim request pendaftaran berulang secara masif
    Maka status respons API registrasi user v2 adalah 200 atau 201 atau respons mock valid

  @SEC-010
  Skenario: SEC-010 Verifikasi error 500 tidak membocorkan detail stack trace database
    Ketika client mengirim request registrasi user v2 dengan payload lengkap valid
    Maka respons server tidak membocorkan detail stack trace database

  @SEC-011
  Skenario: SEC-011 Verifikasi mekanisme proteksi terhadap request liar CSRF
    Ketika client mengirim request registrasi user v2 dengan payload lengkap valid
    Maka respons API registrasi menyertakan header keamanan standar

  @SEC-012
  Skenario: SEC-012 Validasi sanitasi teks pada input cuti
    Ketika client mengirim request registrasi user v2 dengan tipe data leave_amount "banyak"
    Maka status respons API registrasi user v2 adalah 400
