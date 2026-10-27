/**
 * HADIR MANAGEMENT - REGISTRASI USER V2
 * Interactive logic mirroring the official Next.js/React component behavior
 */

document.addEventListener("DOMContentLoaded", () => {
  // Application State
  const formState = {
    photoFile: null,
    photoUrl: null,
    nik: "",
    fullname: "",
    email: "",
    password: "",
    division_id: null,
    job_departement_id: null,
    job_position_id: null,
    job_level_id: null,
    upliner_id: null,
    upliner2_id: null,
    upliner3_id: null,
    employee_type_id: null,
    location_point_id: null,
    shift_type: "NON_SHIFT",
    schedule_id: null,
    required_selfie: null,
    leave_amount: 0
  };

  // DOM Elements
  const formElement = document.getElementById("register-form");
  const photoInput = document.getElementById("logo");
  const previewContainer = document.getElementById("preview-container");
  const previewImage = document.getElementById("preview-image");
  const removePhotoBtn = document.getElementById("remove-photo-btn");
  
  const nikInput = document.getElementById("nik");
  const fullnameInput = document.getElementById("fullname");
  const emailInput = document.getElementById("email");
  const passwordInput = document.getElementById("password");
  const togglePasswordBtn = document.getElementById("toggle-password");
  
  const shiftTypeSelect = document.getElementById("shift_type");
  const selfieSelect = document.getElementById("required_selfie");
  const cutiInput = document.getElementById("jumlah-cuti");
  const submitBtn = document.getElementById("submit");
  
  const snackbarAlert = document.getElementById("snackbar-alert");
  const snackbarMessage = document.getElementById("snackbar-message");
  const snackbarCloseBtn = document.getElementById("snackbar-close");

  let snackbarTimeout = null;

  /* ==========================================================================
     1. SNACKBAR NOTIFICATION HELPER
     ========================================================================== */
  function showSnackbar(message, type = "error") {
    if (!snackbarAlert || !snackbarMessage) return;

    if (snackbarTimeout) {
      clearTimeout(snackbarTimeout);
    }

    snackbarMessage.textContent = message;
    snackbarAlert.className = `snackbar-alert alert-${type} show`;

    snackbarTimeout = setTimeout(() => {
      hideSnackbar();
    }, 5000);
  }

  function hideSnackbar() {
    if (snackbarAlert) {
      snackbarAlert.classList.remove("show");
    }
  }

  if (snackbarCloseBtn) {
    snackbarCloseBtn.addEventListener("click", hideSnackbar);
  }

  /* ==========================================================================
     2. PASSWORD VISIBILITY TOGGLE
     ========================================================================== */
  if (togglePasswordBtn && passwordInput) {
    togglePasswordBtn.addEventListener("click", (e) => {
      e.preventDefault();
      const isPassword = passwordInput.getAttribute("type") === "password";
      passwordInput.setAttribute("type", isPassword ? "text" : "password");

      // Toggle Feather Icon
      togglePasswordBtn.innerHTML = isPassword
        ? `<svg viewBox="0 0 24 24" width="20" height="20" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"></path><circle cx="12" cy="12" r="3"></circle></svg>`
        : `<svg viewBox="0 0 24 24" width="20" height="20" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M17.94 17.94A10.07 10.07 0 0 1 12 20c-7 0-11-8-11-8a18.45 18.45 0 0 1 5.06-5.94M9.9 4.24A9.12 9.12 0 0 1 12 4c7 0 11 8 11 8a18.5 18.5 0 0 1-2.16 3.19m-6.72-1.07a3 3 0 1 1-4.24-4.24"></path><line x1="1" y1="1" x2="23" y2="23"></line></svg>`;
    });
  }

  /* ==========================================================================
     3. FILE UPLOAD & PREVIEW (Limit 2 MB)
     ========================================================================== */
  if (photoInput) {
    photoInput.addEventListener("change", (e) => {
      const file = e.target.files[0];
      if (!file) return;

      // Check file size (2 MB = 2 * 1024 * 1024 bytes)
      const maxSize = 2 * 1024 * 1024;
      if (file.size > maxSize) {
        showSnackbar("Ukuran berkas melebihi batas maksimal 2 MB", "error");
        photoInput.value = "";
        return;
      }

      // Check file type and size
      if (!file.type.startsWith("image/") || file.size === 0 || file.name.includes(".php") || file.name.includes("corrupt")) {
        showSnackbar("* File harus berupa gambar", "error");
        photoInput.value = "";
        return;
      }

      formState.photoFile = file;

      // Render Preview
      const reader = new FileReader();
      reader.onload = (uploadEvent) => {
        formState.photoUrl = uploadEvent.target.result;
        previewImage.src = formState.photoUrl;
        previewContainer.classList.add("has-image");
      };
      reader.readAsDataURL(file);
    });
  }

  if (removePhotoBtn) {
    removePhotoBtn.addEventListener("click", () => {
      formState.photoFile = null;
      formState.photoUrl = null;
      photoInput.value = "";
      previewImage.src = "";
      previewContainer.classList.remove("has-image");
    });
  }

  /* ==========================================================================
     4. CUSTOM AUTOCOMPLETE IMPLEMENTATION
     ========================================================================== */
  function initAutocomplete(config) {
    const { inputId, dropdownId, data, labelKey, secondaryKey, onSelect, placeholder, disabled } = config;
    const input = document.getElementById(inputId);
    const dropdown = document.getElementById(dropdownId);
    const wrapper = input ? input.closest(".autocomplete-wrapper") : null;

    if (!input || !dropdown || !wrapper) return null;

    let items = [...data];
    let selectedItem = null;

    function renderList(list) {
      dropdown.innerHTML = "";
      if (list.length === 0) {
        const empty = document.createElement("div");
        empty.className = "autocomplete-empty";
        empty.textContent = "Tidak ada hasil ditemukan";
        dropdown.appendChild(empty);
        return;
      }

      list.forEach((item) => {
        const itemEl = document.createElement("div");
        itemEl.className = "autocomplete-item";
        if (selectedItem && selectedItem.id === item.id) {
          itemEl.classList.add("is-selected");
        }

        const labelSpan = document.createElement("span");
        labelSpan.textContent = typeof labelKey === "function" ? labelKey(item) : item[labelKey];
        itemEl.appendChild(labelSpan);

        if (secondaryKey) {
          const secSpan = document.createElement("span");
          secSpan.className = "autocomplete-item-secondary";
          secSpan.textContent = typeof secondaryKey === "function" ? secondaryKey(item) : item[secondaryKey];
          itemEl.appendChild(secSpan);
        }

        itemEl.addEventListener("mousedown", (e) => {
          e.preventDefault();
          select(item);
          close();
        });

        itemEl.addEventListener("click", (e) => {
          e.preventDefault();
          select(item);
          close();
        });

        dropdown.appendChild(itemEl);
      });
    }

    function open() {
      if (input.disabled) return;
      document.querySelectorAll(".autocomplete-wrapper.is-open").forEach((el) => {
        if (el !== wrapper) el.classList.remove("is-open");
      });
      wrapper.classList.add("is-open");
      renderList(items);
    }

    function close() {
      wrapper.classList.remove("is-open");
    }

    function select(item) {
      selectedItem = item;
      input.value = typeof labelKey === "function" ? labelKey(item) : item[labelKey];
      if (onSelect) onSelect(item);
    }

    input.addEventListener("focus", open);
    input.addEventListener("click", open);

    input.addEventListener("input", (e) => {
      const q = e.target.value.toLowerCase().trim();
      const filtered = items.filter((item) => {
        const text = (typeof labelKey === "function" ? labelKey(item) : item[labelKey]).toLowerCase();
        return text.includes(q);
      });
      renderList(filtered);
      if (!wrapper.classList.contains("is-open")) {
        wrapper.classList.add("is-open");
      }
    });

    document.addEventListener("click", (e) => {
      if (!wrapper.contains(e.target)) {
        close();
      }
    });

    return {
      setItems(newItems) {
        items = [...newItems];
        renderList(items);
      },
      setDisabled(state, newPlaceholder) {
        input.disabled = state;
        if (newPlaceholder) input.placeholder = newPlaceholder;
        if (state) {
          input.value = "";
          selectedItem = null;
        }
      },
      clear() {
        input.value = "";
        selectedItem = null;
      }
    };
  }

  // Initialize Autocompletes with MOCK_DATA
  const mock = window.MOCK_DATA || {};

  // 1. Divisi
  initAutocomplete({
    inputId: "divisi",
    dropdownId: "divisi-dropdown",
    data: mock.divisions || [],
    labelKey: "name",
    onSelect: (item) => {
      formState.division_id = item.id;
    }
  });

  // 2. Unit
  initAutocomplete({
    inputId: "unit",
    dropdownId: "unit-dropdown",
    data: mock.units || [],
    labelKey: "name",
    onSelect: (item) => {
      formState.job_departement_id = item.id;
    }
  });

  // 3. Posisi Kerja
  initAutocomplete({
    inputId: "posisi-kerja",
    dropdownId: "posisi-dropdown",
    data: mock.positions || [],
    labelKey: "name",
    onSelect: (item) => {
      formState.job_position_id = item.id;
    }
  });

  // 4. Atasan instance (declared before Jabatan to allow dependency linking)
  const atasanAutocomplete = initAutocomplete({
    inputId: "atasan",
    dropdownId: "atasan-dropdown",
    data: mock.upliners || [],
    labelKey: (item) => `${item.nik} - ${item.fullname}`,
    secondaryKey: "jobTitle",
    onSelect: (item) => {
      formState.upliner_id = item.id;
    }
  });

  // Set initial state of Atasan: Disabled until Jabatan is chosen!
  if (atasanAutocomplete) {
    atasanAutocomplete.setDisabled(true, "Pilih Jabatan Dulu");
  }

  // 5. Jabatan (Enables Atasan when selected!)
  initAutocomplete({
    inputId: "jabatan",
    dropdownId: "jabatan-dropdown",
    data: mock.jobLevels || [],
    labelKey: "name",
    onSelect: (item) => {
      formState.job_level_id = item.id;
      // Enable Atasan dropdown when Jabatan is selected
      if (atasanAutocomplete) {
        atasanAutocomplete.setDisabled(false, "Cari Atasan (optional)");
        // Filter upliners with level >= selected level
        const filteredUpliners = (mock.upliners || []).filter(u => u.level >= item.level);
        atasanAutocomplete.setItems(filteredUpliners.length ? filteredUpliners : mock.upliners);
      }
    }
  });

  // 6. Atasan Client V2
  initAutocomplete({
    inputId: "atasan-v2",
    dropdownId: "atasan-v2-dropdown",
    data: mock.uplinersClientV2 || [],
    labelKey: (item) => `${item.fullname} - ${item.job_departement.name}`,
    onSelect: (item) => {
      formState.upliner2_id = item.id;
    }
  });

  // 7. Atasan Client V3
  initAutocomplete({
    inputId: "atasan-v3",
    dropdownId: "atasan-v3-dropdown",
    data: mock.uplinersClientV3 || [],
    labelKey: (item) => `${item.fullname} - ${item.job_departement.name}`,
    onSelect: (item) => {
      formState.upliner3_id = item.id;
    }
  });

  // 8. Tipe Kontrak
  initAutocomplete({
    inputId: "tipe-kontrak",
    dropdownId: "tipe-kontrak-dropdown",
    data: mock.contractTypes || [],
    labelKey: "name",
    onSelect: (item) => {
      formState.employee_type_id = item.id;
    }
  });

  // 9. Lokasi Kerja
  initAutocomplete({
    inputId: "lokasi-kerja",
    dropdownId: "lokasi-kerja-dropdown",
    data: mock.locations || [],
    labelKey: "name",
    onSelect: (item) => {
      formState.location_point_id = item.id;
    }
  });

  // 10. Jadwal Kerja
  initAutocomplete({
    inputId: "jadwal-kerja",
    dropdownId: "jadwal-kerja-dropdown",
    data: mock.schedules || [],
    labelKey: "name",
    onSelect: (item) => {
      formState.schedule_id = item.id;
    }
  });

  /* ==========================================================================
     5. FORM INPUT LISTENERS
     ========================================================================== */
  if (nikInput) {
    nikInput.addEventListener("input", (e) => {
      formState.nik = e.target.value.trim();
    });
  }

  if (fullnameInput) {
    fullnameInput.addEventListener("input", (e) => {
      formState.fullname = e.target.value;
    });
  }

  if (emailInput) {
    emailInput.addEventListener("input", (e) => {
      formState.email = e.target.value.trim();
    });
  }

  if (passwordInput) {
    passwordInput.addEventListener("input", (e) => {
      formState.password = e.target.value;
    });
  }

  if (shiftTypeSelect) {
    shiftTypeSelect.addEventListener("change", (e) => {
      formState.shift_type = e.target.value;
    });
  }

  if (selfieSelect) {
    selfieSelect.addEventListener("change", (e) => {
      formState.required_selfie = e.target.value === "true";
    });
  }

  if (cutiInput) {
    cutiInput.addEventListener("input", (e) => {
      formState.leave_amount = parseInt(e.target.value, 10) || 0;
    });
  }

  /* ==========================================================================
     6. FORM SUBMISSION (Mirrors cn() async submit logic)
     ========================================================================== */
  if (formElement) {
    formElement.addEventListener("submit", (e) => {
      e.preventDefault();

      // 1. Mandatory Photo Check (Exact check from Hadir original: if(!Ce) return en("Foto tidak boleh kosong"))
      if (!formState.photoFile) {
        showSnackbar("Foto tidak boleh kosong", "error");
        return;
      }

      // 2. Account Information Validations
      if (!formState.nik || formState.nik.trim().length === 0) {
        showSnackbar("NIK tidak boleh kosong", "error");
        if (nikInput) nikInput.focus();
        return;
      }

      if (formState.nik.length !== 8) {
        showSnackbar("NIK harus 8 karakter alfanumerik", "error");
        if (nikInput) nikInput.focus();
        return;
      }

      if (/[^a-zA-Z0-9]/.test(formState.nik)) {
        showSnackbar("NIK hanya boleh karakter alfanumerik", "error");
        if (nikInput) nikInput.focus();
        return;
      }

      const registeredNiks = ["D6240001", "D6240002", "D6240003", "D6240004", "D6240099"];
      if (registeredNiks.includes(formState.nik.toUpperCase())) {
        showSnackbar("NIK sudah terdaftar", "error");
        if (nikInput) nikInput.focus();
        return;
      }

      if (!formState.fullname || formState.fullname.trim().length === 0) {
        showSnackbar("Nama Karyawan tidak boleh kosong", "error");
        if (fullnameInput) fullnameInput.focus();
        return;
      }

      if (/[0-9]/.test(formState.fullname)) {
        showSnackbar("Nama Karyawan tidak boleh mengandung angka", "error");
        if (fullnameInput) fullnameInput.focus();
        return;
      }

      if (/[!@#$%^&*()_+=\[\]{};:"\\|<>?~`]/.test(formState.fullname)) {
        showSnackbar("Nama Karyawan tidak boleh mengandung simbol", "error");
        if (fullnameInput) fullnameInput.focus();
        return;
      }

      if (!formState.email) {
        showSnackbar("Email tidak boleh kosong", "error");
        if (emailInput) emailInput.focus();
        return;
      }

      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      if (!emailRegex.test(formState.email) || /[<>]/.test(formState.email)) {
        showSnackbar("Format email tidak valid", "error");
        if (emailInput) emailInput.focus();
        return;
      }

      const registeredEmails = ["irwan@dikahadir.com", "admin@dikahadir.com", "duplikat@dikahadir.com", "sqa.registered@dikahadir.com"];
      if (registeredEmails.includes(formState.email.toLowerCase())) {
        showSnackbar("Email sudah terdaftar", "error");
        if (emailInput) emailInput.focus();
        return;
      }

      if (!formState.password) {
        showSnackbar("Password tidak boleh kosong", "error");
        if (passwordInput) passwordInput.focus();
        return;
      }

      if (formState.password.length < 6) {
        showSnackbar("Password minimal 6 karakter", "error");
        if (passwordInput) passwordInput.focus();
        return;
      }

      if (formState.leave_amount < 0) {
        showSnackbar("Jumlah Cuti tidak boleh negatif", "error");
        if (cutiInput) cutiInput.focus();
        return;
      }

      if (formState.leave_amount > 99) {
        showSnackbar("Jumlah Cuti maksimal 99 hari", "error");
        if (cutiInput) cutiInput.focus();
        return;
      }

      // 3. Construct Payload mirroring Hadir POST /api/v2/users
      const payload = {
        nik: formState.nik,
        fullname: formState.fullname,
        email: formState.email,
        password: formState.password,
        photo: "mock-uploaded-photo-uuid-9999",
        division_id: formState.division_id,
        job_departement_id: formState.job_departement_id,
        job_position_id: formState.job_position_id,
        job_level_id: formState.job_level_id,
        upliner_id: formState.upliner_id,
        upliner2_id: formState.upliner2_id,
        upliner3_id: formState.upliner3_id,
        employee_type_id: formState.employee_type_id,
        location_point_id: formState.location_point_id,
        shift_type: formState.shift_type || "NON_SHIFT",
        schedule_id: formState.schedule_id,
        required_selfie: formState.required_selfie,
        leave_amount: formState.leave_amount || 0,
        isVerified: true
      };

      console.log("[Hadir Submit] Form Payload:", payload);

      // Loading State
      if (submitBtn) {
        submitBtn.classList.add("is-loading");
      }

      // Simulate API call delay
      setTimeout(() => {
        if (submitBtn) {
          submitBtn.classList.remove("is-loading");
        }

        // Show Success Toast
        showSnackbar("Berhasil mendaftarkan user", "success");

        // Dispatch custom DOM event for automated testing assertions
        const successEvent = new CustomEvent("hadir:user-registered", {
          detail: { payload, timestamp: Date.now() }
        });
        document.dispatchEvent(successEvent);
      }, 1000);
    });
  }
});
