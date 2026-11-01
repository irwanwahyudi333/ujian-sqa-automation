# language: id
@ui @leader-izin-off @izin-off @table-pagination
Fitur: Paginasi dan Pengaturan Tampilan Tabel Leader Izin Off
  Sebagai Leader
  Saya ingin mengatur jumlah baris per halaman dan berpindah antar halaman tabel
  Agar saya dapat menavigasi daftar permohonan izin off yang banyak dengan mudah

  Latar Belakang:
    Dengan leader membuka halaman manajemen leader izin off

  @PAG-001
  Skenario: Verifikasi label informasi rentang data paginasi default
    Maka teks informasi rentang data leader izin off menampilkan "Menampilkan 1 - 5 dari 12 data"

  @PAG-002
  Skenario: Navigasi ke halaman kedua menggunakan tombol Next
    Ketika leader menekan tombol Next pada paginasi leader izin off
    Maka teks informasi rentang data leader izin off menampilkan "Menampilkan 6 - 10 dari 12 data"

  @PAG-003
  Skenario: Navigasi kembali ke halaman pertama menggunakan tombol Previous
    Ketika leader menekan tombol Next pada paginasi leader izin off
    Dan leader menekan tombol Previous pada paginasi leader izin off
    Maka teks informasi rentang data leader izin off menampilkan "Menampilkan 1 - 5 dari 12 data"

  @PAG-004
  Skenario: Memastikan tombol Previous nonaktif pada halaman pertama
    Maka tombol Previous pada paginasi leader izin off berstatus nonaktif

  @PAG-005
  Skenario: Memastikan tombol Next nonaktif pada halaman terakhir
    Ketika leader menekan tombol Next pada paginasi leader izin off
    Dan leader menekan tombol Next pada paginasi leader izin off
    Maka tombol Next pada paginasi leader izin off berstatus nonaktif

  @PAG-006
  Skenario: Mengubah limit baris per halaman menjadi 10 baris
    Ketika leader memilih limit baris "10" pada dropdown paginasi leader izin off
    Maka jumlah baris di tabel leader izin off berjumlah tepat 10 baris

  @PAG-007
  Skenario: Mengubah limit baris per halaman menjadi 25 baris
    Ketika leader memilih limit baris "25" pada dropdown paginasi leader izin off
    Maka seluruh data sebanyak 12 baris tampil dalam satu halaman leader izin off

  @PAG-008
  Skenario: Reset paginasi ke halaman pertama saat limit baris diubah
    Ketika leader menekan tombol Next pada paginasi leader izin off
    Dan leader memilih limit baris "10" pada dropdown paginasi leader izin off
    Maka tabel leader izin off otomatis kembali ke halaman pertama

  @PAG-009
  Skenario: Mengaktifkan tampilan rapat dengan checkbox Dense padding
    Ketika leader mengaktifkan switch Dense padding leader izin off
    Maka switch Dense padding leader izin off berstatus tercentang

  @PAG-010
  Skenario: Menonaktifkan tampilan rapat checkbox Dense padding
    Ketika leader mengaktifkan switch Dense padding leader izin off
    Dan leader menonaktifkan switch Dense padding leader izin off
    Maka switch Dense padding leader izin off berstatus tidak tercentang

  @PAG-011
  Skenario: Tampilan paginasi saat data hasil filter kurang dari limit
    Ketika leader memilih status filter "REJECTED" pada dropdown leader izin off
    Maka tombol Previous dan Next pada paginasi leader izin off berstatus nonaktif

  @PAG-012
  Skenario: Tampilan paginasi saat data hasil filter kosong
    Ketika leader memasukkan kata kunci pencarian leader izin off "DataKosong99"
    Dan leader menekan tombol Search leader izin off
    Maka teks informasi rentang data leader izin off menampilkan "Menampilkan 0 - 0 dari 0 data"

  @PAG-013
  Skenario: Konsistensi data saat navigasi bolak-balik halaman
    Ketika leader menekan tombol Next pada paginasi leader izin off
    Dan leader menekan tombol Previous pada paginasi leader izin off
    Maka data baris pertama leader izin off tetap menampilkan karyawan yang sama secara konsisten

  @PAG-014
  Skenario: Verifikasi jumlah baris sesuai dengan batas rows per page default
    Maka jumlah baris di tabel leader izin off berjumlah tepat 5 baris

  @PAG-015
  Skenario: Eksekusi approval permohonan pada halaman kedua tabel
    Ketika leader menekan tombol Next pada paginasi leader izin off
    Dan leader menekan tombol Setujui pada baris permohonan pertama
    Dan leader menekan tombol konfirmasi pada modal approval leader izin off
    Maka notifikasi toast leader izin off menampilkan pesan "Berhasil menyetujui izin off"
