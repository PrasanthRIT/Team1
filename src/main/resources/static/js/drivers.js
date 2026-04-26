const API_BASE = "/api/drivers";

const tableBody = document.getElementById("driversTableBody");
const loadingState = document.getElementById("loadingState");
const driverForm = document.getElementById("driverForm");
const driverModalElement = document.getElementById("driverModal");
const driverModal = new bootstrap.Modal(driverModalElement);
const toastElement = document.getElementById("appToast");
const toastBody = document.getElementById("toastBody");
const appToast = new bootstrap.Toast(toastElement);

document.addEventListener("DOMContentLoaded", () => {
    loadDrivers();
    driverForm.addEventListener("submit", saveDriver);
});

function showToast(message) {
    toastBody.textContent = message;
    appToast.show();
}

function showLoading(show) {
    loadingState.classList.toggle("d-none", !show);
}

function renderDrivers(drivers) {
    if (!drivers || drivers.length === 0) {
        tableBody.innerHTML = `
            <tr>
                <td colspan="8" class="text-center text-muted py-4">No drivers found.</td>
            </tr>
        `;
        return;
    }

    tableBody.innerHTML = drivers.map(driver => `
        <tr>
            <td>${driver.id ?? ""}</td>
            <td>${driver.firstName ?? ""}</td>
            <td>${driver.lastName ?? ""}</td>
            <td>${driver.emailID ?? ""}</td>
            <td>${driver.licenseNumber ?? ""}</td>
            <td>${driver.phoneNumber ?? ""}</td>
            <td>${(driver.assignedBusNumbers || []).join(", ")}</td>
            <td>
                <button class="btn btn-sm btn-outline-primary me-1" onclick="openEditModal(${driver.id})">Edit</button>
                <button class="btn btn-sm btn-outline-danger" onclick="deleteDriver(${driver.id})">Delete</button>
            </td>
        </tr>
    `).join("");
}

async function loadDrivers() {
    showLoading(true);
    try {
        const response = await fetch(API_BASE);
        const data = await response.json();
        renderDrivers(data);
    } catch (error) {
        tableBody.innerHTML = `
            <tr>
                <td colspan="8" class="text-center text-danger py-4">Failed to load drivers.</td>
            </tr>
        `;
    } finally {
        showLoading(false);
    }
}

async function searchDrivers() {
    const query = document.getElementById("searchInput").value.trim();

    if (!query) {
        loadDrivers();
        return;
    }

    showLoading(true);
    try {
        const response = await fetch(`${API_BASE}/search?licenseNumber=${encodeURIComponent(query)}`);
        const data = await response.json();
        renderDrivers(data);
    } catch (error) {
        showToast("Search failed.");
    } finally {
        showLoading(false);
    }
}

function openCreateModal() {
    document.getElementById("driverModalTitle").textContent = "Add Driver";
    driverForm.reset();
    document.getElementById("driverId").value = "";
}

async function openEditModal(id) {
    try {
        const response = await fetch(`${API_BASE}/${id}`);
        const driver = await response.json();

        document.getElementById("driverModalTitle").textContent = "Edit Driver";
        document.getElementById("driverId").value = driver.id ?? "";
        document.getElementById("firstName").value = driver.firstName ?? "";
        document.getElementById("lastName").value = driver.lastName ?? "";
        document.getElementById("emailID").value = driver.emailID ?? "";
        document.getElementById("licenseNumber").value = driver.licenseNumber ?? "";
        document.getElementById("phoneNumber").value = driver.phoneNumber ?? "";
        document.getElementById("assignedBusNumbers").value = (driver.assignedBusNumbers || []).join(", ");

        driverModal.show();
    } catch (error) {
        showToast("Could not load driver details.");
    }
}

async function saveDriver(event) {
    event.preventDefault();

    const id = document.getElementById("driverId").value.trim();
    const payload = {
        firstName: document.getElementById("firstName").value.trim(),
        lastName: document.getElementById("lastName").value.trim(),
        emailID: document.getElementById("emailID").value.trim(),
        licenseNumber: document.getElementById("licenseNumber").value.trim(),
        phoneNumber: document.getElementById("phoneNumber").value.trim(),
        assignedBusNumbers: document.getElementById("assignedBusNumbers").value
            .split(",")
            .map(bus => bus.trim())
            .filter(bus => bus.length > 0)
    };

    const url = id ? `${API_BASE}/${id}` : API_BASE;
    const method = id ? "PUT" : "POST";

    try {
        const response = await fetch(url, {
            method,
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(payload)
        });

        if (!response.ok) {
            throw new Error("Save failed");
        }

        driverModal.hide();
        showToast(id ? "Driver updated successfully." : "Driver added successfully.");
        await loadDrivers();
    } catch (error) {
        showToast("Could not save driver.");
    }
}

async function deleteDriver(id) {
    const confirmed = confirm("Are you sure you want to delete this driver?");
    if (!confirmed) return;

    try {
        const response = await fetch(`${API_BASE}/${id}`, {
            method: "DELETE"
        });

        if (!response.ok) {
            throw new Error("Delete failed");
        }

        showToast("Driver deleted successfully.");
        await loadDrivers();
    } catch (error) {
        showToast("Could not delete driver.");
    }
}