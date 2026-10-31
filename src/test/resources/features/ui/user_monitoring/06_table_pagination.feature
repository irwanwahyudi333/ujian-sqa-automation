# language: id
@ui @user-monitoring @table-pagination
Fitur: Integritas Tabel Data dan Kontrol Paginasi Halaman User Monitoring
  Sebagai admin manajemen HADIR
  Saya ingin mengubah jumlah baris per halaman dan menavigasi halaman data user monitoring
  Agar eksplorasi data pemantauan karyawan berlangsung cepat dan teratur

  Latar Belakang:
    Dengan admin membuka halaman manajemen user monitoring

  @PAG-001
  Skenario: Verifikasi opsi baris per halaman pada dropdown
    Maka dropdown baris per halaman user monitoring memuat opsi "5", "10", dan "25"

  @PAG-002
  Skenario: Mengubah batas baris per halaman menjadi 5
    Ketika admin memilih limit baris "5" pada dropdown paginasi user monitoring
    Maka tabel user monitoring menampilkan maksimal 5 baris data

  @PAG-003
  Skenario: Mengubah batas baris per halaman menjadi 25
    Ketika admin memilih limit baris "25" pada dropdown paginasi user monitoring
    Maka tabel user monitoring menampilkan seluruh data dalam 1 halaman

  @PAG-004
  Skenario: Navigasi ke halaman selanjutnya (Next Page)
    Ketika admin menekan tombol Next pada paginasi user monitoring
    Maka tabel user monitoring menampilkan baris data halaman kedua
    Dan tombol Previous pada paginasi user monitoring menjadi aktif

  @PAG-005
  Skenario: Navigasi kembali ke halaman sebelumnya (Previous Page)
    Ketika admin menekan tombol Next pada paginasi user monitoring
    Dan admin menekan tombol Previous pada paginasi user monitoring
    Maka tabel user monitoring kembali menampilkan baris data halaman pertama

  @PAG-006
  Skenario: Navigasi langsung ke halaman terakhir (Last Page)
    Ketika admin menekan tombol Last pada paginasi user monitoring
    Maka tabel user monitoring menampilkan baris data halaman terakhir
    Dan tombol Next pada paginasi user monitoring menjadi non-aktif

  @PAG-007
  Skenario: Navigasi langsung ke halaman pertama (First Page)
    Ketika admin menekan tombol Last pada paginasi user monitoring
    Dan admin menekan tombol First pada paginasi user monitoring
    Maka tabel user monitoring kembali ke halaman pertama
    Dan tombol First pada paginasi user monitoring menjadi non-aktif

  @PAG-008
  Skenario: Tombol Previous dan First non-aktif di halaman pertama
    Maka tombol Previous pada paginasi user monitoring berstatus non-aktif
    Dan tombol First pada paginasi user monitoring berstatus non-aktif

  @PAG-009
  Skenario: Tombol Next dan Last non-aktif di halaman terakhir
    Ketika admin menekan tombol Last pada paginasi user monitoring
    Maka tombol Next pada paginasi user monitoring berstatus non-aktif
    Dan tombol Last pada paginasi user monitoring berstatus non-aktif

  @PAG-010
  Skenario: Akurasi informasi teks displayed rows di halaman awal
    Maka teks informasi rentang baris user monitoring menampilkan "1-10 of 12"

  @PAG-011
  Skenario: Akurasi informasi teks displayed rows di halaman kedua
    Ketika admin menekan tombol Next pada paginasi user monitoring
    Maka teks informasi rentang baris user monitoring menampilkan "11-12 of 12"

  @PAG-012
  Skenario: Mengaktifkan tampilan rapat dengan checkbox Dense padding
    Ketika admin mengaktifkan checkbox Dense padding user monitoring
    Maka checkbox Dense padding user monitoring berstatus tercentang

  @PAG-013
  Skenario: Menonaktifkan tampilan rapat checkbox Dense padding
    Ketika admin mengaktifkan checkbox Dense padding user monitoring
    Dan admin menonaktifkan checkbox Dense padding user monitoring
    Maka checkbox Dense padding user monitoring berstatus tidak tercentang

  @PAG-014
  Skenario: Reset paginasi ke halaman pertama saat limit baris diubah
    Ketika admin menekan tombol Next pada paginasi user monitoring
    Dan admin memilih limit baris "25" pada dropdown paginasi user monitoring
    Maka tabel user monitoring otomatis kembali ke halaman pertama

  @PAG-015
  Skenario: Konsistensi data saat navigasi bolak-balik halaman
    Ketika admin menekan tombol Next pada paginasi user monitoring
    Dan admin menekan tombol Previous pada paginasi user monitoring
    Maka data baris pertama tetap menampilkan user yang sama secara konsisten
