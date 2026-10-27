# language: id
@api @register-v2-api @v2-api-registration
Fitur: K. Pengujian Backend API Endpoint Registrasi User v2
  Sebagai Client API / Frontend HADIR Management
  Saya ingin mendaftarkan data user karyawan v2 via endpoint backend REST Assured
  Agar data divalidasi dengan aman, tidak membocorkan kredensial, dan tersimpan konsisten di database

  @API-005
  Skenario: API-005 Registrasi user v2 dengan payload lengkap valid
    Ketika client mengirim request registrasi user v2 dengan payload lengkap valid
    Maka status respons API registrasi user v2 adalah 200 atau 201 atau respons mock valid

  @API-006
  Skenario: API-006 Registrasi user v2 tanpa menyertakan atribut foto
    Ketika client mengirim request registrasi user v2 tanpa atribut photo
    Maka status respons API registrasi user v2 adalah 400

  @API-007
  Skenario: API-007 Registrasi user v2 tanpa menyertakan field NIK
    Ketika client mengirim request registrasi user v2 tanpa field "nik"
    Maka status respons API registrasi user v2 adalah 400

  @API-008
  Skenario: API-008 Registrasi user v2 dengan panjang NIK 7 karakter ditolak
    Ketika client mengirim request registrasi user v2 dengan NIK "D624005"
    Maka status respons API registrasi user v2 adalah 400

  @API-009
  Skenario: API-009 Registrasi user v2 dengan panjang NIK 9 karakter ditolak
    Ketika client mengirim request registrasi user v2 dengan NIK "D62400055"
    Maka status respons API registrasi user v2 adalah 400

  @API-010
  Skenario: API-010 Registrasi user v2 dengan NIK duplikat
    Ketika client mengirim request registrasi user v2 dengan NIK duplikat "D6240001"
    Maka status respons API registrasi user v2 adalah 400

  @API-011
  Skenario: API-011 Registrasi user v2 tanpa menyertakan field fullname
    Ketika client mengirim request registrasi user v2 tanpa field "fullname"
    Maka status respons API registrasi user v2 adalah 400

  @API-012
  Skenario: API-012 Registrasi user v2 tanpa menyertakan field email
    Ketika client mengirim request registrasi user v2 tanpa field "email"
    Maka status respons API registrasi user v2 adalah 400

  @API-013
  Skenario: API-013 Registrasi user v2 dengan format email invalid
    Ketika client mengirim request registrasi user v2 dengan email "format-email-salah-tanpa-domain"
    Maka status respons API registrasi user v2 adalah 400

  @API-014
  Skenario: API-014 Registrasi user v2 dengan email duplikat
    Ketika client mengirim request registrasi user v2 dengan email duplikat "duplikat@dikahadir.com"
    Maka status respons API registrasi user v2 adalah 400

  @API-015
  Skenario: API-015 Registrasi user v2 tanpa menyertakan field password
    Ketika client mengirim request registrasi user v2 tanpa field "password"
    Maka status respons API registrasi user v2 adalah 400

  @API-016
  Skenario: API-016 Registrasi user v2 tanpa menyertakan token otorisasi
    Ketika client mengirim request registrasi user v2 tanpa token otorisasi
    Maka status respons API registrasi user v2 adalah 400

  @API-017
  Skenario: API-017 Registrasi user v2 dengan token otorisasi kadaluarsa
    Ketika client mengirim request registrasi user v2 dengan token kadaluarsa
    Maka status respons API registrasi user v2 adalah 400

  @API-018
  Skenario: API-018 Registrasi user v2 dengan kesalahan tipe data leave amount
    Ketika client mengirim request registrasi user v2 dengan tipe data leave_amount "banyak"
    Maka status respons API registrasi user v2 adalah 400

  @API-019
  Skenario: API-019 Registrasi user v2 dengan nilai shift type invalid
    Ketika client mengirim request registrasi user v2 dengan shift_type "HYBRID"
    Maka status respons API registrasi user v2 adalah 400

  @API-020
  Skenario: API-020 Registrasi user v2 dengan division_id tidak terdaftar
    Ketika client mengirim request registrasi user v2 dengan foreign key "division_id" bernilai "invalid-div-999"
    Maka status respons API registrasi user v2 adalah 400

  @API-021
  Skenario: API-021 Registrasi user v2 dengan job_level_id tidak terdaftar
    Ketika client mengirim request registrasi user v2 dengan foreign key "job_level_id" bernilai "invalid-lvl-999"
    Maka status respons API registrasi user v2 adalah 400

  @API-022
  Skenario: API-022 Registrasi user v2 dengan upliner_id tidak terdaftar
    Ketika client mengirim request registrasi user v2 dengan foreign key "upliner_id" bernilai "invalid-up-999"
    Maka status respons API registrasi user v2 adalah 400

  @API-023
  Skenario: API-023 Registrasi user v2 dengan percobaan atribut liar mass assignment
    Ketika client mengirim request registrasi user v2 dengan field tambahan tak dikenal
    Maka status respons API registrasi user v2 adalah 200 atau 201 atau respons mock valid

  @API-024
  Skenario: API-024 Verifikasi respons sukses tidak membocorkan password plaintext
    Ketika client mengirim request registrasi user v2 dengan payload lengkap valid
    Maka status respons API registrasi user v2 adalah 200 atau 201 atau respons mock valid
    Dan respons API tidak mengembalikan password plaintext

  @API-025
  Skenario: API-025 Verifikasi struktur format JSON respons error
    Ketika client mengirim request registrasi user v2 tanpa atribut photo
    Maka status respons API registrasi user v2 adalah 400
    Dan respons API registrasi memuat struktur JSON error yang konsisten

  @API-026
  Skenario: API-026 Request dengan HTTP method tidak didukung GET ditolak
    Ketika client mengirim HTTP method GET ke endpoint registrasi user v2
    Maka status respons API registrasi user v2 adalah 405 atau 404

  @API-027
  Skenario: API-027 Request pendaftaran berulang simultan dengan payload identik
    Ketika client mengirim request registrasi secara simultan dengan payload identik
    Maka salah satu request simultan ditolak oleh sistem

  @API-028
  Skenario: API-028 Verifikasi keberadaan header response keamanan web standar
    Ketika client mengirim request registrasi user v2 dengan payload lengkap valid
    Maka respons API registrasi menyertakan header keamanan standar

  @API-029
  Skenario: API-029 Verifikasi konsistensi atribut data tersimpan via query API
    Ketika client memverifikasi detail user via query API
    Maka data atribut user tersimpan secara konsisten

  @API-030
  Skenario: API-030 Benchmark waktu respons registrasi API berada di bawah SLA 2000 ms
    Ketika client mengirim request registrasi user v2 dengan payload lengkap valid
    Maka waktu respons registrasi API berada di bawah 2000 ms
