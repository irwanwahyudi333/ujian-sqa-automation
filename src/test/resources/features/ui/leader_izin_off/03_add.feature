# language: id
@ui @leader-izin-off @izin-off @add
Fitur: Penambahan dan Pengajuan Izin Off Baru
  Sebagai Leader
  Saya ingin dapat mengajukan permohonan izin off baru untuk anggota tim
  Agar pencatatan jadwal libur karyawan tetap tertib dan terdokumentasi

  Latar Belakang:
    Dengan leader membuka halaman manajemen leader izin off

  @ADD-001
  Skenario: Membuka modal pengajuan izin off baru
    Ketika leader menekan tombol Ajukan Izin Off
    Maka modal dialog pengajuan izin off baru terbuka

  @ADD-002
  Skenario: Pengajuan izin off baru dengan seluruh data valid
    Ketika leader menekan tombol Ajukan Izin Off
    Dan leader mengisi form pengajuan izin off dengan nama "Rian Pratama", upliner "Irwan Wahyudi", tanggal "2024-12-25", alasan "Acara keluarga akhir tahun"
    Dan leader menekan tombol Simpan pada modal pengajuan leader izin off
    Maka notifikasi toast leader izin off menampilkan pesan "Berhasil menambahkan permohonan izin off"
    Dan permohonan atas nama "Rian Pratama" tampil di tabel leader izin off

  @ADD-003
  Skenario: Permohonan izin off yang baru ditambahkan berstatus PENDING
    Ketika leader menekan tombol Ajukan Izin Off
    Dan leader mengisi form pengajuan izin off dengan nama "Sari Wulandari", upliner "Irwan Wahyudi", tanggal "2024-12-28", alasan "Keperluan penting keluarga"
    Dan leader menekan tombol Simpan pada modal pengajuan leader izin off
    Maka status permohonan "Sari Wulandari" di tabel bernilai "PENDING"

  @ADD-004
  Skenario: Validasi field nama karyawan tidak boleh kosong
    Ketika leader menekan tombol Ajukan Izin Off
    Dan leader mengisi tanggal pengajuan "2024-12-20" dan alasan "Izin off"
    Dan leader menekan tombol Simpan pada modal pengajuan leader izin off
    Maka modal dialog pengajuan izin off baru tetap terbuka

  @ADD-005
  Skenario: Validasi field tanggal off tidak boleh kosong
    Ketika leader menekan tombol Ajukan Izin Off
    Dan leader mengisi nama pengajuan "Karyawan Uji" dan alasan "Keperluan pribadi"
    Dan leader menekan tombol Simpan pada modal pengajuan leader izin off
    Maka modal dialog pengajuan izin off baru tetap terbuka

  @ADD-006
  Skenario: Validasi field alasan permohonan tidak boleh kosong
    Ketika leader menekan tombol Ajukan Izin Off
    Dan leader mengisi nama pengajuan "Karyawan Uji" dan tanggal "2024-12-20"
    Dan leader menekan tombol Simpan pada modal pengajuan leader izin off
    Maka modal dialog pengajuan izin off baru tetap terbuka

  @ADD-007
  Skenario: Batalkan pengajuan izin off via tombol Batal
    Ketika leader menekan tombol Ajukan Izin Off
    Dan leader menekan tombol Batal pada modal pengajuan leader izin off
    Maka modal dialog pengajuan izin off baru tertutup

  @ADD-008
  Skenario: Tutup modal pengajuan izin off via tombol silang (X)
    Ketika leader menekan tombol Ajukan Izin Off
    Dan leader menekan tombol silang tutup pada modal pengajuan leader izin off
    Maka modal dialog pengajuan izin off baru tertutup

  @ADD-009
  Skenario: Tutup modal pengajuan izin off via tombol keyboard Escape
    Ketika leader menekan tombol Ajukan Izin Off
    Dan leader menekan tombol keyboard Escape pada leader izin off
    Maka modal dialog pengajuan izin off baru tertutup

  @ADD-010
  Skenario: Pengajuan izin off dengan tanggal di masa depan
    Ketika leader menekan tombol Ajukan Izin Off
    Dan leader mengisi form pengajuan izin off dengan nama "Taufik Hidayat", upliner "Irwan Wahyudi", tanggal "2025-06-15", alasan "Rencana liburan tahun depan"
    Dan leader menekan tombol Simpan pada modal pengajuan leader izin off
    Maka notifikasi toast leader izin off menampilkan pesan "Berhasil menambahkan permohonan izin off"

  @ADD-011
  Skenario: Pengajuan izin off dengan karakter kutip pada alasan
    Ketika leader menekan tombol Ajukan Izin Off
    Dan leader mengisi form pengajuan izin off dengan nama "Wahyu Setiawan", upliner "Irwan Wahyudi", tanggal "2024-12-30", alasan "Acara 'Family Gathering' di Bandung"
    Dan leader menekan tombol Simpan pada modal pengajuan leader izin off
    Maka permohonan atas nama "Wahyu Setiawan" tampil di tabel leader izin off

  @ADD-012
  Skenario: Reset isian form saat modal pengajuan dibuka kembali
    Ketika leader menekan tombol Ajukan Izin Off
    Dan leader mengisi nama pengajuan "Nama Sementara" dan alasan "Alasan sementara"
    Dan leader menekan tombol Batal pada modal pengajuan leader izin off
    Dan leader menekan tombol Ajukan Izin Off
    Maka field nama pengajuan izin off dalam kondisi kosong bersih

  @ADD-013
  Skenario: Verifikasi jumlah total baris bertambah setelah penambahan berhasil
    Ketika leader menekan tombol Ajukan Izin Off
    Dan leader mengisi form pengajuan izin off dengan nama "Yusuf Mansur", upliner "Irwan Wahyudi", tanggal "2024-12-16", alasan "Urusan keluarga"
    Dan leader menekan tombol Simpan pada modal pengajuan leader izin off
    Maka jumlah baris leader izin off di tabel bertambah

  @ADD-014
  Skenario: Verifikasi upliner default terisi otomatis
    Ketika leader menekan tombol Ajukan Izin Off
    Maka field upliner pada modal pengajuan izin off terisi otomatis

  @ADD-015
  Skenario: Pengajuan permohonan dengan nama panjang 100 karakter
    Ketika leader menekan tombol Ajukan Izin Off
    Dan leader mengisi nama pengajuan dengan teks panjang 100 karakter
    Dan leader mengisi tanggal pengajuan "2024-12-19" dan alasan "Keperluan mendesak"
    Dan leader menekan tombol Simpan pada modal pengajuan leader izin off
    Maka modal dialog pengajuan izin off baru tertutup
