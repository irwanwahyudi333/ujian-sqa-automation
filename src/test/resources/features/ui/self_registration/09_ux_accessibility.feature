# language: id
@ui @self-registration @module-ux-accessibility
Fitur: Pengujian Responsivitas Zoom dan Aksesibilitas Halaman
  Sebagai administrator manajemen dengan ragam perangkat dan kebutuhan aksesibilitas
  Saya ingin antarmuka beradaptasi dengan zoom dan navigasi keyboard
  Agar seluruh kontrol tetap mudah diakses dan memenuhi standar WCAG

  Latar Belakang:
    Dengan admin membuka halaman self-registration

  @UX-001
  Skenario: Buka halaman dengan zoom browser 200 persen
    Ketika admin mengatur zoom browser 200 persen
    Maka tata letak halaman tetap proporsional dan dapat di-scroll

  @UX-002
  Skenario: Uji resolusi layar Laptop 1366 x 768
    Ketika admin menguji tampilan pada resolusi 1366x768
    Maka seluruh toolbar dan tabel dapat diakses dengan baik

  @UX-003
  Skenario: Navigasi fokus menggunakan tombol Tab keyboard
    Ketika admin berpindah fokus menggunakan tombol Tab
    Maka fokus berpindah secara urut antar kontrol interaktif

  @UX-004
  Skenario: Indikator fokus visual focus outline
    Maka elemen interaktif memiliki indikator fokus yang jelas

  @UX-005
  Skenario: Aksesibilitas pembaca layar screen reader
    Maka elemen form dan tabel memiliki atribut ARIA yang valid
